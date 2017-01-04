package WS.MyClass;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Connect {

    private Selector selector;
    protected HashMap<SelectionKey, LinkedList<ByteBuffer>> map;
    protected HashMap<SelectionKey, WriteControl> mapControl;

    public Connect() {
    }

    public Connect(Selector selector) {
        this.selector = selector;
        map = new HashMap<>(selector.keys().size() / 2);
        mapControl = new HashMap<>(map.size());

        for (Iterator<SelectionKey> keys = selector.keys().iterator(); keys.hasNext(); ) {
            add(keys.next());
        }
    }

    public void read(final SelectionKey key) {
        System.out.println("Read");

        WriteControl writeControl = mapControl.get(key);
        ByteBuffer buffer;
        if (writeControl == null) {
            buffer = addBuffer(key);
        } else {
            buffer = writeControl.getBuffer();
        }

        SocketChannel channel = (SocketChannel) key.channel();

        try {
            channel.read(buffer);
            boolean boo = WriteControl.is(buffer);
            if (!boo) {
                if(writeControl==null) {
                    writeControl = new WriteControl(key, buffer);
                    WriteControlListener listener = new WriteControlListener() {
                        @Override
                        public void writeControl(WriteControlEvent event) {
                            WriteControl control = event.getWriteControl();
                            ByteBuffer byteBuffer = control.getBuffer();
                            int l = byteBuffer.limit();

                            for (Iterator<SelectionKey> iterator = map.keySet().iterator(); iterator.hasNext(); ) {
                                SelectionKey selectionKey = iterator.next();
                                LinkedList<ByteBuffer> list = map.get(selectionKey);
                                if (list != map.get(key)) {
                                    ByteBuffer wrap = ByteBuffer.wrap(byteBuffer.array());
                                    wrap.limit(l);
                                    list.offer(wrap);
                                    selectionKey.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                                }
                            }

                        }
                    };
                    writeControl.addWriteControlListener(listener);
                    mapControl.put(key, writeControl);
                }

                key.interestOps(SelectionKey.OP_READ);

            } else {
                buffer.flip();
                if (writeControl != null) {
                    writeControl.control();
                    mapControl.remove(key);
                } else {
                    map.get(key).offer(buffer);
                }
                key.interestOps(SelectionKey.OP_WRITE | SelectionKey.OP_READ);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void write(SelectionKey key) {
            System.out.println("Write");

            LinkedList<ByteBuffer> bufferLinkedList = map.get(key);
            ByteBuffer buffer = bufferLinkedList.peek();

            try {
                    SocketChannel channel = (SocketChannel) key.channel();
                    channel.write(buffer);
                    if (buffer.position() != buffer.limit()) {
                        key.interestOps(SelectionKey.OP_WRITE | SelectionKey.OP_READ);
                    } else {
                        int size = bufferLinkedList.size();
                        if (size <= 1) {
                            key.interestOps(SelectionKey.OP_READ);
                        } else {
                            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                        }
                        bufferLinkedList.remove(buffer);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

    }

    public ByteBuffer addBuffer(SelectionKey key) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 8);
        LinkedList<ByteBuffer> list = map.get(key);
        list.offer(byteBuffer);

        return byteBuffer;
    }

    public void  add(SelectionKey key) {
        LinkedList<ByteBuffer> list = new LinkedList<>();
        map.put(key, list);
    }


}
