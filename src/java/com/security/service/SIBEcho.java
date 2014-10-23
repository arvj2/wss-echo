
package com.security.service;

import javax.jws.HandlerChain;
import javax.jws.WebService;

/**
 * @author Jansel Rodriguez
 */
@WebService(serviceName = "EchoService", portName = "EchoBindingPort", endpointInterface = "security.com.service.EchoPort", targetNamespace = "http://com.security/service", wsdlLocation = "WEB-INF/wsdl/SIBEcho/service.wsdl")
@HandlerChain(file = "handlers.xml")
public class SIBEcho {
    public java.lang.String echo(java.lang.String echoed) {
       return "Echoed "+echoed;
       
    }
}
