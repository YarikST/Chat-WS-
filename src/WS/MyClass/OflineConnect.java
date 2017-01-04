package WS.MyClass;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admin-iorigins on 10.07.16.
 */
public class OflineConnect extends Connect {

    private ArrayList<FileChannel> list;

    private ArrayList<ByteBuffer>listBuffer;

    public OflineConnect(SelectionKey key, FileChannel fileChannel) {

        list = new ArrayList<>(2);
        list.add(fileChannel);

        map = new HashMap<>(2);
        add(key);

    }

    public OflineConnect(Selector selector) {
        super(selector);
    }

    @Override
    public void read(SelectionKey key) {
        super.read(key);
        listBuffer = new ArrayList<>(map.get(key));
        write();
    }

    public void write() {
        for (FileChannel fileChannel : list) {
            int i = 0;
            for (ByteBuffer  buffer = listBuffer.get(i);buffer != null;) {
                try {
                    fileChannel.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    i++;
                    buffer.flip();
                }
            }
        }

    }

    public void add(SelectionKey key, FileChannel fileChannel) {
        super.add(key);
        add(fileChannel);
    }

    public void add(FileChannel fileChannel) {
        list.add(fileChannel);
    }
}
