package Myclass.Tranksmisions;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.file.Path;

/**
 * Created by admin-iorigins on 06.09.16.
 */
public class TrankcmisionsHandred implements InvocationHandler {
    private Object object;
    private Path path;

    private FileOutputStream  fileOutputStream;
    private OutputStreamWriter outputStreamWriter;
    private PrintWriter writer;

    public TrankcmisionsHandred(Object object, Path path) {
        this.object = object;
        this.path = path;
        try {
            fileOutputStream = new FileOutputStream(path.toFile());
            outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            writer = new PrintWriter(outputStreamWriter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public  synchronized Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        try {
            writer.write(method.getName()+"\n");

            System.out.println(method.invoke(object,objects));

            Object o1 = method.invoke(object, objects);
            writer.write("ok: " + o1+"\n");
            return o1;
        } catch (Exception e) {
            writer.write("err: "+"\n");
            e.printStackTrace();
        }

        return null;
    }

    public void close() {
       writer.close();
    }
}
