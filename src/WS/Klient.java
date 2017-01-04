package WS;

import WS.MyClass.ConnectEvent;
import WS.MyClass.ConnectListener;
import WSKConfiguration.*;
import WSKConfiguration.Configuration;
import WSKMassages.*;
import WSKMassages.Massages;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.*;


public class Klient implements Runnable {

    private boolean is = true;
    public static int port = 2524;
    public static int oflinePort = 2529;

    private Charset charset;
    private CharsetEncoder charsetEncoder;
    private CharsetDecoder charsetDecoder;


    private ServerSocketChannel socketChannel;
    private ServerSocket serverSocket;

    private String login;
    private HashSet<ConnectListener> listenerConnect;
    private ArrayList<KlientKey> klientKeys;

    private WSKConfiguration.Configuration configuration;
    private WSKMassages.Massages massages;


    public Klient()  {

        charset = Charset.forName("US-ASCII");
        charsetEncoder = charset.newEncoder();
        charsetDecoder = charset.newDecoder();

        try {
            socketChannel = ServerSocketChannel.open();
            serverSocket = socketChannel.socket();
            serverSocket.bind(new InetSocketAddress(port));
        } catch (IOException e) {
            e.printStackTrace();
        }



        listenerConnect = new HashSet<>(2);
        klientKeys = new ArrayList<>(2);

        configuration = new ConfigurationService().getConfigurationPort();
        massages = new MassagesService().getMassagesPort();

        new Thread(this).start();

    }

    public void setLogin(String login) {
        this.login = login;
        configuration.online(login);
    }
    public Configuration configuration() {
        return configuration;
    }

    public Massages getMassages() {
        return massages;
    }

    @Override
    public void run() {
        while (is) {
            try {

                SocketChannel socket = socketChannel.accept();
                ByteBuffer buffer = ByteBuffer.allocate(1024 * 5);
                socket.read(buffer);
                buffer.flip();
                KlientKey key = new KlientKey(socket);
                klientKeys.add(key);
                listener(new ConnectEvent(charsetDecoder.decode(buffer).toString(),key));
            } catch (IOException e) {
                e.printStackTrace();

            }

            close();
        }
    }


    public void close() {
        configuration.offline(login);
        try {
            serverSocket.close();
            socketChannel.close();
            listenerConnect = null;
            for (KlientKey key : klientKeys
                    ) {
                key.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        charsetDecoder = null;
        charsetEncoder = null;

    }

    private void listener(ConnectEvent connectEvent) {
        for (ConnectListener ob : listenerConnect) {
            ob.connect(connectEvent);
        }
    }


    public void addConnectListener(ConnectListener connectListener) {
        this.listenerConnect.add(connectListener);
    }

    public void removeConnectListener(ConnectListener connectListener) {
        this.listenerConnect.remove(connectListener);
    }


  public   class KlientKey  {

        private CharBuffer charBuffer;
        private ByteBuffer byteBuffer;

        private SocketChannel socketChannel;

        public KlientKey(SocketChannel socketChannel) {
            this.socketChannel = socketChannel;

            charBuffer = CharBuffer.allocate(1024 * 5);
            byteBuffer = ByteBuffer.allocate(1024 * 5);

        }

        public void write(String s) {

            try {
                charBuffer.put(s);
                charBuffer.flip();
                socketChannel.write(charsetEncoder.encode(charBuffer));
                charBuffer.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public String read() throws IOException {

                byteBuffer.clear();
                socketChannel.read(byteBuffer);
                return charsetDecoder.decode(byteBuffer).toString();

        }

        public void close() {
            byteBuffer = null;
            charBuffer = null;
        }

    }

}
