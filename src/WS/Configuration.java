package WS;


import WS.MyClass.Person;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.*;
import javax.xml.ws.Endpoint;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebService
public class Configuration {
    public static URL url;
    public static Path derPath;
    public static Configuration configuration;
    public static Massages massages;

    private Charset charset;

    private HashSet<String> mapOnnline;

    public Configuration() {
        try {
            url = new URL("http://192.168.1.247:20161/Configuration");
       //     derPath = Paths.get("/media/admin-iorigins/00BAF197BAF18984/ServerChat/");
            derPath = Paths.get("/home/admin-iorigins/ServerChat/");
            Files.createDirectories(derPath);
            configuration = this;
            new Massages();
            massages = Massages.massages;
            mapOnnline = new HashSet<>();
            Endpoint endpoint = Endpoint.publish(url.toString(), this);
            charset = Charset.forName("US-ASCII");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @WebMethod
    public void update(Person person) {
        try {
            Files.delete(derPath.resolve(person.getNik()).resolve(person.getNik()+".txt"));
            setPerson(person);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @WebMethod
    public boolean aut(String nik, char[] pass) {
        Path path = derPath.resolve(nik).resolve(nik+".txt");
        boolean is = false;

        XPath xPath = XPathFactory.newInstance().newXPath();
        try {
            xPath.evaluate("password", new InputSource(new FileInputStream(path.toFile())));

            NodeList s = (NodeList) xPath.evaluate("//pass", new InputSource(new FileInputStream("/home/admin-iorigins/ServerChat/nik/nik.txt")), XPathConstants.NODESET);
            is = true;
            for (int i = 0; i < s.getLength(); i++) {
                Node node = s.item(i);
                if (pass[i] != Integer.parseInt(node.getTextContent())) {
                    is = false;
                    break;

                }

            }

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return is;

    }

    @WebMethod
    public boolean isKlient(String nik) {
        return Files.isDirectory(derPath.resolve(nik));
    }

    @WebMethod
    public boolean isOnline(String nik) {
        boolean b = false;

        String s = null;
        for (Iterator<String> iterator = mapOnnline.iterator(); iterator.hasNext();) {
            s = iterator.next();
            if (s.equals(nik)) {
                b = true;
                break;

            }
        }
            return b;
    }

    @WebMethod
    public void online(String nik) {
        mapOnnline.add(nik);
    }

    @WebMethod
    public void offline(String nik) {
        mapOnnline.remove(nik);
    }

    @WebMethod
    public boolean add(Person person) {
        if(isKlient(person.getNik())) return false;
        setPerson(person);
        online(person.getNik());
        return true;
    }

    @WebMethod
    public void remove(String nik) {
        Path dir = derPath.resolve(nik);
        try {
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir);
            for (Path path : directoryStream) {
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    try {
                        remove(path.toFile().getName());
                        Files.delete(path);
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }

            }

            Files.delete(dir);
            try {
                directoryStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @WebMethod
    public void newGrup(String masNik[],String grupName) {
        Path dir = derPath.resolve(grupName);
        try {
            Files.createDirectories(dir);
            Path pathFile = Paths.get(dir.toString() + File.separatorChar + "config");
            FileChannel channel = FileChannel.open(pathFile, StandardOpenOption.WRITE, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
            ByteBuffer mas[] = new ByteBuffer[masNik.length];
            for (int i = 0; i < masNik.length; i++) {
                mas[i] = charset.encode(masNik[i]);
            }
            for (int i = 0; i < mas.length; i++) {
                channel.write(mas[i]);
                channel.write(charset.encode(";\n"));

            }

            LinkedList<String> list = new LinkedList<>();

            for (int i = 0; i < masNik.length; i++) {
                if (!isOnline(masNik[i])) {
                    list.offer(masNik[i]);
                }
            }

            channel.close();
            massages.addGrup(grupName);
            massages.conenctGrup(masNik, grupName,list);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @WebMethod
    public Person getPerson(String nik) {
        Person person = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            person = (Person) unmarshaller.unmarshal(Configuration.derPath.resolve(nik).resolve("nik.txt").toFile());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return person;
    }

//                                                no web
    private void setPerson(Person person) {
        Path dir = derPath.resolve(person.getNik());
        try {
            Files.createDirectories(dir);
            Path path = Paths.get(dir.toString() + File.separatorChar + person.getNik()+".txt");
            JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(person, new FileOutputStream(path.toFile()));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (PropertyException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }finally {

        }
    }



}
