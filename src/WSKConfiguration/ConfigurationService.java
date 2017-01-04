
package WSKConfiguration;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ConfigurationService", targetNamespace = "http://WS/", wsdlLocation = "http://192.168.1.247:20161/Configuration?wsdl")
public class ConfigurationService
    extends Service
{

    private final static URL CONFIGURATIONSERVICE_WSDL_LOCATION;
    private final static WebServiceException CONFIGURATIONSERVICE_EXCEPTION;
    private final static QName CONFIGURATIONSERVICE_QNAME = new QName("http://WS/", "ConfigurationService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://192.168.1.247:20161/Configuration?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CONFIGURATIONSERVICE_WSDL_LOCATION = url;
        CONFIGURATIONSERVICE_EXCEPTION = e;
    }

    public ConfigurationService() {
        super(__getWsdlLocation(), CONFIGURATIONSERVICE_QNAME);
    }

    public ConfigurationService(WebServiceFeature... features) {
        super(__getWsdlLocation(), CONFIGURATIONSERVICE_QNAME, features);
    }

    public ConfigurationService(URL wsdlLocation) {
        super(wsdlLocation, CONFIGURATIONSERVICE_QNAME);
    }

    public ConfigurationService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CONFIGURATIONSERVICE_QNAME, features);
    }

    public ConfigurationService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ConfigurationService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Configuration
     */
    @WebEndpoint(name = "ConfigurationPort")
    public Configuration getConfigurationPort() {
        return super.getPort(new QName("http://WS/", "ConfigurationPort"), Configuration.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Configuration
     */
    @WebEndpoint(name = "ConfigurationPort")
    public Configuration getConfigurationPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://WS/", "ConfigurationPort"), Configuration.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CONFIGURATIONSERVICE_EXCEPTION!= null) {
            throw CONFIGURATIONSERVICE_EXCEPTION;
        }
        return CONFIGURATIONSERVICE_WSDL_LOCATION;
    }

}