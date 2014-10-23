
package com.security.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.security.client package. 
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

    private final static QName _Echo_QNAME = new QName("http://com.security/types", "Echo");
    private final static QName _EchoResponse_QNAME = new QName("http://com.security/types", "EchoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.security.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EchoType }
     * 
     */
    public EchoType createEchoType() {
        return new EchoType();
    }

    /**
     * Create an instance of {@link EchoResponseType }
     * 
     */
    public EchoResponseType createEchoResponseType() {
        return new EchoResponseType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EchoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.security/types", name = "Echo")
    public JAXBElement<EchoType> createEcho(EchoType value) {
        return new JAXBElement<EchoType>(_Echo_QNAME, EchoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EchoResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.security/types", name = "EchoResponse")
    public JAXBElement<EchoResponseType> createEchoResponse(EchoResponseType value) {
        return new JAXBElement<EchoResponseType>(_EchoResponse_QNAME, EchoResponseType.class, null, value);
    }

}
