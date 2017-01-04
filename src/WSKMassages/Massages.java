
package WSKMassages;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Massages", targetNamespace = "http://WS/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Massages {


    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "addGrup", targetNamespace = "http://WS/", className = "WSKMassages.AddGrup")
    @ResponseWrapper(localName = "addGrupResponse", targetNamespace = "http://WS/", className = "WSKMassages.AddGrupResponse")
    @Action(input = "http://WS/Massages/addGrupRequest", output = "http://WS/Massages/addGrupResponse")
    public void addGrup(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "conenctGrup", targetNamespace = "http://WS/", className = "WSKMassages.ConenctGrup")
    @ResponseWrapper(localName = "conenctGrupResponse", targetNamespace = "http://WS/", className = "WSKMassages.ConenctGrupResponse")
    @Action(input = "http://WS/Massages/conenctGrupRequest", output = "http://WS/Massages/conenctGrupResponse")
    public void conenctGrup(
        @WebParam(name = "arg0", targetNamespace = "")
        List<String> arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        List<String> arg2);

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "conenct", targetNamespace = "http://WS/", className = "WSKMassages.Conenct")
    @ResponseWrapper(localName = "conenctResponse", targetNamespace = "http://WS/", className = "WSKMassages.ConenctResponse")
    @Action(input = "http://WS/Massages/conenctRequest", output = "http://WS/Massages/conenctResponse")
    public void conenct(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "massagesOfline", targetNamespace = "http://WS/", className = "WSKMassages.MassagesOfline")
    @ResponseWrapper(localName = "massagesOflineResponse", targetNamespace = "http://WS/", className = "WSKMassages.MassagesOflineResponse")
    @Action(input = "http://WS/Massages/massagesOflineRequest", output = "http://WS/Massages/massagesOflineResponse")
    public void massagesOfline(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}