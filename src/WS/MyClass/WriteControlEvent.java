package WS.MyClass;

import java.nio.ByteBuffer;

/**
 * Created by admin-iorigins on 12.07.16.
 */
public class WriteControlEvent {
    private WriteControl writeControl;

    public WriteControlEvent(WriteControl writeControl) {
        this.writeControl = writeControl;
    }

    public WriteControl getWriteControl() {
        return writeControl;
    }
}
