package com.security.client.launcher;

import com.sun.xml.wss.ProcessingContext;
import com.sun.xml.wss.XWSSProcessor;
import com.sun.xml.wss.XWSSProcessorFactory;
import com.sun.xml.wss.XWSSecurityException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 * @author Jansel Rodriguez
 */
public class DecoratorHandler implements SOAPHandler<SOAPMessageContext> {

    private XWSSProcessor proc;
    private boolean trace = true;

    public DecoratorHandler() {
        try {
            XWSSProcessorFactory fact = XWSSProcessorFactory.newInstance();
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("client.xml");

            proc = fact.createProcessorForSecurityConfiguration(in, new Prompter());
            in.close();
        } catch (XWSSecurityException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

        
    @Override
    public Set<QName> getHeaders() {
        String uri = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
        QName wssHeader = new QName(uri, "Security", "wsse");
        Set<QName> headers = new HashSet<QName>();
        headers.add(wssHeader);
        return headers;
    }
    
    
    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outbound) {
            try {
                SOAPMessage msg = context.getMessage();
                ProcessingContext pcxt = proc.createProcessingContext(msg);
                pcxt.setSOAPMessage(msg);
                
                SOAPMessage secure = proc.secureOutboundMessage(pcxt);
                context.setMessage(secure);
                if (trace) {
                    dump(secure);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return false;
    }

    @Override
    public void close(MessageContext context) {
    }

    private void dump(SOAPMessage msg) throws Exception {
        OutputStream out = new ByteArrayOutputStream();
        msg.writeTo(out);
        System.out.println(out.toString());
    }
}
