package Myclass.Tranksmisions;

import java.lang.reflect.Proxy;
import java.nio.file.Path;

/**
 * Created by admin-iorigins on 06.09.16.
 */
public class Tranksmisions {
    private TrankcmisionsHandred invocationHandler;

    public <T> Object handred(Class<T> aClass,Object o, Path path) {
        invocationHandler = new TrankcmisionsHandred(o, path);
       return Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{aClass}, invocationHandler);
    }

    public void close() {
        invocationHandler.close();
    }

    public interface  I{
        void m();

        void m(int i) throws Throwable;
    }

    static public class Test implements I {



        @Override
        public void m() {
            System.out.println("yes");

        }

        public   void m(int i) throws Throwable {
            throw new NoSuchFieldError("erorr 404");
        }
    }

}
