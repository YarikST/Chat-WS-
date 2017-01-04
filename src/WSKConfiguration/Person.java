
package WSKConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for person complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="person">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nik" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="pass" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="d" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="m" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="y" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "person", propOrder = {
    "nik",
    "status",
    "password",
    "d",
    "m",
    "y",
    "ip"
})
public class Person {

    protected String nik;
    protected String status;
    protected Person.Password password;
    protected int d;
    protected int m;
    protected int y;
    protected String ip;

    public Person(String nik, String status, char[] mas, int d, int m, int y, String ip) {
        this.nik = nik;
        this.status = status;
        Password password = new Password();
        password.pass = passChar(mas);
        this.password = password;
        this.d = d;
        this.m = m;
        this.y = y;
        this.ip = ip;
    }

    public Person() {
    }

    private ArrayList<Integer> passChar(char mas[]) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < mas.length; i++) {
            list.add((int)mas[i]);
        }
        return list;
    }
    /**
     * Gets the value of the nik property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNik() {
        return nik;
    }

    /**
     * Sets the value of the nik property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNik(String value) {
        this.nik = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link Person.Password }
     *     
     */
    public Person.Password getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link Person.Password }
     *     
     */
    public void setPassword(Person.Password value) {
        this.password = value;
    }

    /**
     * Gets the value of the d property.
     * 
     */
    public int getD() {
        return d;
    }

    /**
     * Sets the value of the d property.
     * 
     */
    public void setD(int value) {
        this.d = value;
    }

    /**
     * Gets the value of the m property.
     * 
     */
    public int getM() {
        return m;
    }

    /**
     * Sets the value of the m property.
     * 
     */
    public void setM(int value) {
        this.m = value;
    }

    /**
     * Gets the value of the y property.
     * 
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the value of the y property.
     * 
     */
    public void setY(int value) {
        this.y = value;
    }

    /**
     * Gets the value of the ip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIp() {
        return ip;
    }

    /**
     * Sets the value of the ip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIp(String value) {
        this.ip = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="pass" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "pass"
    })
    public static class Password {

        @XmlElement(nillable = true)
        @XmlSchemaType(name = "unsignedShort")
        protected List<Integer> pass;

        /**
         * Gets the value of the pass property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the pass property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPass().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Integer }
         * 
         * 
         */
        public List<Integer> getPass() {
            if (pass == null) {
                pass = new ArrayList<Integer>();
            }
            return this.pass;
        }

    }

}
