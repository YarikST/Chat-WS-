package WS.MyClass;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.util.LinkedHashSet;

/**
 * Created by admin-iorigins on 12.07.16.
 */
public class WriteControl {
    private SelectionKey key;
    private ByteBuffer buffer;
    private LinkedHashSet<WriteControlListener> set;

    public WriteControl(SelectionKey key, ByteBuffer buffer) {
        this.key = key;
        this.buffer = buffer;

        set = new LinkedHashSet<>();
    }
    public SelectionKey getKey() {
        return key;
    }

    public ByteBuffer getBuffer() {
        return buffer;
    }

    public void addWriteControlListener(WriteControlListener listener) {
        set.add(listener);
    }

    public void removeWriteControlListener(WriteControlListener listener) {
        set.remove(listener);
    }

    public void control() {

            WriteControlEvent event = new WriteControlEvent(this);
            for (WriteControlListener listener : set) {
                listener.writeControl(event);
            }

    }

    public static boolean is(ByteBuffer buffer) {
        if (buffer.position() == 0) {
            return false;
        }
        return buffer.get(buffer.position()-1)==10?true:false;
    }
}
