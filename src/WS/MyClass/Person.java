package WS.MyClass;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by admin-iorigins on 21.08.16.
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class Person implements Serializable {
    @XmlElement
    private String nik, status;
    @XmlElementWrapper(name = "password")
    private char pass[];
    @XmlElement
    private int d,m, y;
    @XmlElement
    private String ip;


    public Person(String nik, String status, char[] pass, int d, int m, int y, String ip) {
        this.nik = nik;
        this.status = status;
        this.pass = pass;
        this.d = d;
        this.m = m;
        this.y = y;
        this.ip = ip;
    }

    public Person() {
    }


    public String getNik() {
        return nik;
    }

    public String getStatus() {
        return status;
    }

    public char[] getPass() {
        return pass;
    }

    public int getD() {
        return d;
    }

    public int getY() {
        return y;
    }

    public int getM() {
        return m;
    }

    public String getIp() {
        return ip;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPass(char[] pass) {
        this.pass = pass;
    }

    public void setD(int d) {
        this.d = d;
    }

    public void setM(int m) {
        this.m = m;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return nik+"("+super.toString()+")";
    }

    @Override
    public boolean equals(Object obj) {
        Person person = (Person) obj;
        return ip.equals(person.getIp());
    }

    @Override
    public int hashCode() {
        StringTokenizer stringTokenizer = new StringTokenizer(ip, ".");
        int[] masIp = new int[4];
        for (int i = 0; i < 3; i++) {
            masIp[i] = Integer.parseInt(stringTokenizer.nextElement().toString());
        }
        int i = new Integer(masIp[0] + "" + masIp[1] + "" + masIp[2] + "" + masIp[3] + "");
        return i;
    }
}
