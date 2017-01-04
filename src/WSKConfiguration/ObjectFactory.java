
package WSKConfiguration;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the WSKConfiguration package. 
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

    private final static QName _Aut_QNAME = new QName("http://WS/", "aut");
    private final static QName _Online_QNAME = new QName("http://WS/", "online");
    private final static QName _Offline_QNAME = new QName("http://WS/", "offline");
    private final static QName _GetPerson_QNAME = new QName("http://WS/", "getPerson");
    private final static QName _Add_QNAME = new QName("http://WS/", "add");
    private final static QName _IsKlientResponse_QNAME = new QName("http://WS/", "isKlientResponse");
    private final static QName _RemoveResponse_QNAME = new QName("http://WS/", "removeResponse");
    private final static QName _OnlineResponse_QNAME = new QName("http://WS/", "onlineResponse");
    private final static QName _Remove_QNAME = new QName("http://WS/", "remove");
    private final static QName _AutResponse_QNAME = new QName("http://WS/", "autResponse");
    private final static QName _OfflineResponse_QNAME = new QName("http://WS/", "offlineResponse");
    private final static QName _AddResponse_QNAME = new QName("http://WS/", "addResponse");
    private final static QName _Person_QNAME = new QName("http://WS/", "person");
    private final static QName _Update_QNAME = new QName("http://WS/", "update");
    private final static QName _GetPersonResponse_QNAME = new QName("http://WS/", "getPersonResponse");
    private final static QName _NewGrupResponse_QNAME = new QName("http://WS/", "newGrupResponse");
    private final static QName _IsKlient_QNAME = new QName("http://WS/", "isKlient");
    private final static QName _UpdateResponse_QNAME = new QName("http://WS/", "updateResponse");
    private final static QName _IsOnlineResponse_QNAME = new QName("http://WS/", "isOnlineResponse");
    private final static QName _NewGrup_QNAME = new QName("http://WS/", "newGrup");
    private final static QName _IsOnline_QNAME = new QName("http://WS/", "isOnline");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: WSKConfiguration
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link Update }
     * 
     */
    public Update createUpdate() {
        return new Update();
    }

    /**
     * Create an instance of {@link OfflineResponse }
     * 
     */
    public OfflineResponse createOfflineResponse() {
        return new OfflineResponse();
    }

    /**
     * Create an instance of {@link AddResponse }
     * 
     */
    public AddResponse createAddResponse() {
        return new AddResponse();
    }

    /**
     * Create an instance of {@link Remove }
     * 
     */
    public Remove createRemove() {
        return new Remove();
    }

    /**
     * Create an instance of {@link AutResponse }
     * 
     */
    public AutResponse createAutResponse() {
        return new AutResponse();
    }

    /**
     * Create an instance of {@link OnlineResponse }
     * 
     */
    public OnlineResponse createOnlineResponse() {
        return new OnlineResponse();
    }

    /**
     * Create an instance of {@link RemoveResponse }
     * 
     */
    public RemoveResponse createRemoveResponse() {
        return new RemoveResponse();
    }

    /**
     * Create an instance of {@link IsKlientResponse }
     * 
     */
    public IsKlientResponse createIsKlientResponse() {
        return new IsKlientResponse();
    }

    /**
     * Create an instance of {@link Add }
     * 
     */
    public Add createAdd() {
        return new Add();
    }

    /**
     * Create an instance of {@link Offline }
     * 
     */
    public Offline createOffline() {
        return new Offline();
    }

    /**
     * Create an instance of {@link GetPerson }
     * 
     */
    public GetPerson createGetPerson() {
        return new GetPerson();
    }

    /**
     * Create an instance of {@link Online }
     * 
     */
    public Online createOnline() {
        return new Online();
    }

    /**
     * Create an instance of {@link Aut }
     * 
     */
    public Aut createAut() {
        return new Aut();
    }

    /**
     * Create an instance of {@link IsOnline }
     * 
     */
    public IsOnline createIsOnline() {
        return new IsOnline();
    }

    /**
     * Create an instance of {@link NewGrup }
     * 
     */
    public NewGrup createNewGrup() {
        return new NewGrup();
    }

    /**
     * Create an instance of {@link IsOnlineResponse }
     * 
     */
    public IsOnlineResponse createIsOnlineResponse() {
        return new IsOnlineResponse();
    }

    /**
     * Create an instance of {@link UpdateResponse }
     * 
     */
    public UpdateResponse createUpdateResponse() {
        return new UpdateResponse();
    }

    /**
     * Create an instance of {@link IsKlient }
     * 
     */
    public IsKlient createIsKlient() {
        return new IsKlient();
    }

    /**
     * Create an instance of {@link NewGrupResponse }
     * 
     */
    public NewGrupResponse createNewGrupResponse() {
        return new NewGrupResponse();
    }

    /**
     * Create an instance of {@link GetPersonResponse }
     * 
     */
    public GetPersonResponse createGetPersonResponse() {
        return new GetPersonResponse();
    }

    /**
     * Create an instance of {@link Person.Password }
     * 
     */
    public Person.Password createPersonPassword() {
        return new Person.Password();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Aut }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "aut")
    public JAXBElement<Aut> createAut(Aut value) {
        return new JAXBElement<Aut>(_Aut_QNAME, Aut.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Online }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "online")
    public JAXBElement<Online> createOnline(Online value) {
        return new JAXBElement<Online>(_Online_QNAME, Online.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Offline }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "offline")
    public JAXBElement<Offline> createOffline(Offline value) {
        return new JAXBElement<Offline>(_Offline_QNAME, Offline.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "getPerson")
    public JAXBElement<GetPerson> createGetPerson(GetPerson value) {
        return new JAXBElement<GetPerson>(_GetPerson_QNAME, GetPerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Add }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "add")
    public JAXBElement<Add> createAdd(Add value) {
        return new JAXBElement<Add>(_Add_QNAME, Add.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsKlientResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "isKlientResponse")
    public JAXBElement<IsKlientResponse> createIsKlientResponse(IsKlientResponse value) {
        return new JAXBElement<IsKlientResponse>(_IsKlientResponse_QNAME, IsKlientResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "removeResponse")
    public JAXBElement<RemoveResponse> createRemoveResponse(RemoveResponse value) {
        return new JAXBElement<RemoveResponse>(_RemoveResponse_QNAME, RemoveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OnlineResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "onlineResponse")
    public JAXBElement<OnlineResponse> createOnlineResponse(OnlineResponse value) {
        return new JAXBElement<OnlineResponse>(_OnlineResponse_QNAME, OnlineResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Remove }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "remove")
    public JAXBElement<Remove> createRemove(Remove value) {
        return new JAXBElement<Remove>(_Remove_QNAME, Remove.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AutResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "autResponse")
    public JAXBElement<AutResponse> createAutResponse(AutResponse value) {
        return new JAXBElement<AutResponse>(_AutResponse_QNAME, AutResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OfflineResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "offlineResponse")
    public JAXBElement<OfflineResponse> createOfflineResponse(OfflineResponse value) {
        return new JAXBElement<OfflineResponse>(_OfflineResponse_QNAME, OfflineResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "addResponse")
    public JAXBElement<AddResponse> createAddResponse(AddResponse value) {
        return new JAXBElement<AddResponse>(_AddResponse_QNAME, AddResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Person }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "person")
    public JAXBElement<Person> createPerson(Person value) {
        return new JAXBElement<Person>(_Person_QNAME, Person.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Update }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "update")
    public JAXBElement<Update> createUpdate(Update value) {
        return new JAXBElement<Update>(_Update_QNAME, Update.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "getPersonResponse")
    public JAXBElement<GetPersonResponse> createGetPersonResponse(GetPersonResponse value) {
        return new JAXBElement<GetPersonResponse>(_GetPersonResponse_QNAME, GetPersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NewGrupResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "newGrupResponse")
    public JAXBElement<NewGrupResponse> createNewGrupResponse(NewGrupResponse value) {
        return new JAXBElement<NewGrupResponse>(_NewGrupResponse_QNAME, NewGrupResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsKlient }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "isKlient")
    public JAXBElement<IsKlient> createIsKlient(IsKlient value) {
        return new JAXBElement<IsKlient>(_IsKlient_QNAME, IsKlient.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "updateResponse")
    public JAXBElement<UpdateResponse> createUpdateResponse(UpdateResponse value) {
        return new JAXBElement<UpdateResponse>(_UpdateResponse_QNAME, UpdateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsOnlineResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "isOnlineResponse")
    public JAXBElement<IsOnlineResponse> createIsOnlineResponse(IsOnlineResponse value) {
        return new JAXBElement<IsOnlineResponse>(_IsOnlineResponse_QNAME, IsOnlineResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NewGrup }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "newGrup")
    public JAXBElement<NewGrup> createNewGrup(NewGrup value) {
        return new JAXBElement<NewGrup>(_NewGrup_QNAME, NewGrup.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsOnline }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WS/", name = "isOnline")
    public JAXBElement<IsOnline> createIsOnline(IsOnline value) {
        return new JAXBElement<IsOnline>(_IsOnline_QNAME, IsOnline.class, null, value);
    }

}
