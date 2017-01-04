package WS;

import WS.MyClass.Connect;
import WS.MyClass.OflineConnect;
import org.xml.sax.InputSource;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@WebService
public class Massages {
    public static int port;
    private int sysPort;
    public static Massages massages;
    private static Configuration configuration;
    public static URL url;

    private ExecutorService executorService;
    private ExecutorService executorServiceOfline;
    private HashMap<String, Selector> mapGrupSelector;
    private HashMap<String, OflineConnect> mapGrupConnect;
    private Selector selectorOffline;
    private OflineConnect oflineConnect;

    private Charset charset = Charset.forName("US-ASCII");


    public Massages() {
        try {
            port = 2527;
            sysPort = port+759;

            url = new URL("http://192.168.1.247:20162/Massages");
            massages = this;
            configuration = Configuration.configuration;
            mapGrupSelector = new HashMap<>(5);
            mapGrupConnect = new HashMap<>(5);
            executorService = Executors.newCachedThreadPool();
            executorServiceOfline = Executors.newCachedThreadPool();
            Endpoint endpoint = Endpoint.publish(url.toString(), this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @WebMethod
    public void conenct(String nik1, String nik2) {
        if (configuration.isOnline(nik2)) {
            try {
                Selector selector = Selector.open();
                InetAddress inetAddress1 = getAddress(nik1), inetAddress2 = getAddress(nik2);

                SocketChannel socketChannel1 = SocketChannel.open();
                SocketChannel socketChannel2 = SocketChannel.open();

                socketChannel1.bind(new InetSocketAddress(InetAddress.getLocalHost(), getSystemPort()));
                socketChannel2.bind(new InetSocketAddress(InetAddress.getLocalHost(), getSystemPort()));

                socketChannel1.connect(new InetSocketAddress(inetAddress1, Klient.port));
                socketChannel2.connect(new InetSocketAddress(inetAddress2, Klient.port));

                CharBuffer charBuffer1 = CharBuffer.wrap(nik1);
                CharBuffer charBuffer2 = CharBuffer.wrap(nik2);


                ByteBuffer encode1 = charset.encode(charBuffer1);
                ByteBuffer encode2 = charset.encode(charBuffer2);

                socketChannel1.write(encode2);
                socketChannel2.write(encode1);

                socketChannel1.configureBlocking(false);
                socketChannel2.configureBlocking(false);

                int op = SelectionKey.OP_READ;

                SelectionKey register1 = socketChannel1.register(selector, op);
                SelectionKey register2 = socketChannel2.register(selector, op);

                Connect connect = new Connect(selector);

                register1.attach(connect);
                register2.attach(connect);




                Runnable runnable = getRunnable(selector);
                executorService.execute(runnable);


            } catch (IOException e) {
                e.printStackTrace();
                configuration.offline(nik2);
            }
        } else {
            if (selectorOffline == null) {
                try {
                    selectorOffline = Selector.open();
                    executorServiceOfline.execute(getRunnable(selectorOffline));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            try {
                SocketChannel socketChannel = SocketChannel.open();
                socketChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(), getSystemPort()));
                socketChannel.connect(new InetSocketAddress(getAddress(nik1),Klient.port));
                socketChannel.configureBlocking(false);

                socketChannel.write(charset.encode(nik2));

                int po = SelectionKey.OP_READ;
                SelectionKey register = socketChannel.register(selectorOffline, po);
                FileChannel fileChannel = fileChannel(nik1, nik2);
                if (oflineConnect == null) {
                    oflineConnect = new OflineConnect(register, fileChannel);
                } else {
                    oflineConnect.add(register, fileChannel);
                }

                register.attach(oflineConnect);

                selectorOffline.wakeup();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    @WebMethod
    public void conenctGrup(String nik[], String grup, LinkedList<String> list) {
        try {
            Selector selector = mapGrupSelector.get(grup);

            ByteBuffer encode = charset.encode(CharBuffer.wrap(grup));

            SelectableChannel selectableChannel[] = new SelectableChannel[nik.length];
            for (int i = 0; i < selectableChannel.length; i++) {
                SocketChannel open = SocketChannel.open();
                open.bind(new InetSocketAddress(InetAddress.getLocalHost(), getSystemPort()));
                open.connect(new InetSocketAddress(getAddress(nik[i]), Klient.port));

                selectableChannel[i] =  open.socket().getChannel();

                selectableChannel[i].configureBlocking(false);

                open.write(encode);
                encode.flip();
            }

            int op = SelectionKey.OP_READ;


            SelectionKey register[] = new SelectionKey[selectableChannel.length];
            for (int i = 0; i < register.length; i++) {
                register[i] = selectableChannel[i].register(selector, op);
            }

            OflineConnect connect = mapGrupConnect.get(grup);

            for (int i = 0; i < register.length; i++) {
                connect.add(register[i]);
                register[i].attach(connect);
            }

            if (list.size() != 0) {
                Path grupPath = Paths.get(Configuration.derPath.resolve(grup).toString() + File.separatorChar);
                char c = File.separatorChar;
                for (int i = 0; i < list.size(); i++) {
                    Path path = Paths.get(grupPath.toString() + c + list.poll());
                    Files.createDirectories(path);
                    Path pathMassages = Paths.get(path.toString() + c + "massages"+".txt");
                    connect.add(FileChannel.open(pathMassages, StandardOpenOption.APPEND, StandardOpenOption.WRITE, StandardOpenOption.CREATE));
                }

                executorServiceOfline.execute(getRunnable(selector));
            }

            selector.wakeup();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @WebMethod
    public void addGrup(String name) {
        try {
            mapGrupSelector.put(name, Selector.open());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mapGrupConnect.put(name, new OflineConnect(mapGrupSelector.get(name)));

        executorService.execute(getRunnable(mapGrupSelector.get(name)));
    }

    @WebMethod
    public void massagesOfline(String nik) {

    }

    //                                                 no web Servise

    private InetAddress getAddress(String nik) {
        String ip = "";
        XPath xPath = XPathFactory.newInstance().newXPath();
        try {
            ip = xPath.evaluate("/Person/ip", new InputSource(Configuration.derPath.resolve(nik).resolve(nik+".txt").toString()));
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        try {
            return InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Runnable getRunnable(final Selector selector) {
        Runnable runnable = new Runnable() {
            ExecutorService executorService = null;
            @Override
            public void run() {
                if (selector.keys().size() / 2 > 0) {

                    executorService = Executors.newFixedThreadPool(selector.keys().size() / 2);
                }else{
                    executorService = Executors.newFixedThreadPool(2);
                }

                boolean is = true;
                while (is) {
                    try {
                        for (int k = 0; selector.select(1000*5) == 0; k++) {
                            Thread.sleep(1000);
                            if (k == 10) {
                                System.out.println("k");
                                is = false;
                                selector.close();
                                executorService.shutdown();
                                executorService = null;
                                oflineConnect = null;
                            }
                        }
                        Set<SelectionKey> selectionKeys = selector.selectedKeys();
                        for (Iterator<SelectionKey> iterator = selectionKeys.iterator();iterator.hasNext();) {
                        final SelectionKey key= iterator.next();
                            iterator.remove();
                            key.interestOps(0);
                            executorService.execute(new Runnable() {
                                @Override
                                public void run() {
                                    Connect connect = (Connect) key.attachment();
                                    if (key.isReadable()) {
                                       connect.read(key);

                                    } else {
                                        connect.write(key);
                                    }
                                    selector.wakeup();
                                }
                            });

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }

            }
        };
        return runnable;
    }

    private FileChannel fileChannel(String nik0, String nik) {
        Path path =Configuration.derPath.resolve(nik0).resolve(nik);
        Path pathFile = Paths.get(path.toString() + File.separatorChar + nik+".txt");

        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            return FileChannel.open(pathFile, StandardOpenOption.APPEND, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getSystemPort() {
        return ++sysPort;
    }

}