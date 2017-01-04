
package WSKMassages;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the WSKMassages package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ConenctGrup_QNAME = new QName("http://WS/", "conenctGrup");
    private final static QName _AddGrupResponse_QNAME = new QName("http://WS/", "addGrupResponse");
    private final static QName _MassagesOfline_QNAME = new QName("http://WS/", "massagesOfline");
    private final static QName _MassagesOflineResponse_QNAME = new QName("http://WS/", "massagesOflineResponse");
    private final static QName _Conenct_QNAME = new QName("http://WS/", "conenct");
    private final static QName _AddGrup_QNAME = new QName("http://WS/", "addGrup");
    private final static QName _ConenctGrupResponse_QNAME = new QName("http://WS/", "conenctGrupResponse");
    private final static QName _ConenctResponse_QNAME = new QName("http://WS/", "conenctResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: WSKMassages
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConenctResponse }
     * 
     */
    public ConenctResponse createConenctResponse() {
        return new ConenctResponse();
    }

    /**
     * Create an instance of {@link ConenctGrupResponse }
     * 
     */
    public ConenctGrupResponse createConenctGrupResponse() {
        return new ConenctGrupResponse();
    }

    /**
     * Create an instance of {@link AddGrup }
     * 
     */
    public AddGrup createAddGrup() {
        return new AddGrup();
    }

    /**
     * Create an instance of {@link Conenct }
     * 
     */
    public Conenct createConenct() {
        return new Conenct();
    }

    /**
     * Create an instance of {@link MassagesOflineResponse }
     * 
     */
    public MassagesOflineResponse createMassagesOflineResponse() {
        return new MassagesOflineResponse();
    }

    /**
     * Create an instance of {@link MassagesOfline }
     * 
     */
    public MassagesOfline createMassagesOfline() {
        return new MassagesOfline();
    }

    /**
     * Create an instance of {@link AddGrupResponse }
     * 
     */
    public AddGrupResponse createAddGrupResponse() {
        return new AddGrupResponse();
    }

    /**
     * Create an instance of {@link ConenctGrup }
     * 
     */
    public ConenctGrup createConenctGrup() {
        return new ConenctGrup();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConenctGrup }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "conenctGrup")
    public JAXBElement<ConenctGrup> createConenctGrup(ConenctGrup value) {
        return new JAXBElement<ConenctGrup>(_ConenctGrup_QNAME, ConenctGrup.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddGrupResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "addGrupResponse")
    public JAXBElement<AddGrupResponse> createAddGrupResponse(AddGrupResponse value) {
        return new JAXBElement<AddGrupResponse>(_AddGrupResponse_QNAME, AddGrupResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MassagesOfline }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "massagesOfline")
    public JAXBElement<MassagesOfline> createMassagesOfline(MassagesOfline value) {
        return new JAXBElement<MassagesOfline>(_MassagesOfline_QNAME, MassagesOfline.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MassagesOflineResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "massagesOflineResponse")
    public JAXBElement<MassagesOflineResponse> createMassagesOflineResponse(MassagesOflineResponse value) {
        return new JAXBElement<MassagesOflineResponse>(_MassagesOflineResponse_QNAME, MassagesOflineResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Conenct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "conenct")
    public JAXBElement<Conenct> createConenct(Conenct value) {
        return new JAXBElement<Conenct>(_Conenct_QNAME, Conenct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddGrup }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "addGrup")
    public JAXBElement<AddGrup> createAddGrup(AddGrup value) {
        return new JAXBElement<AddGrup>(_AddGrup_QNAME, AddGrup.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConenctGrupResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "conenctGrupResponse")
    public JAXBElement<ConenctGrupResponse> createConenctGrupResponse(ConenctGrupResponse value) {
        return new JAXBElement<ConenctGrupResponse>(_ConenctGrupResponse_QNAME, ConenctGrupResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConenctResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "conenctResponse")
    public JAXBElement<ConenctResponse> createConenctResponse(ConenctResponse value) {
        return new JAXBElement<ConenctResponse>(_ConenctResponse_QNAME, ConenctResponse.class, null, value);
    }

}
