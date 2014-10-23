package com.security.service.handlers;

import com.sun.xml.wss.ProcessingContext;
import com.sun.xml.wss.XWSSProcessor;
import com.sun.xml.wss.XWSSProcessorFactory;
import java.io.ByteArrayOutputStream;
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
public class Verifier implements SOAPHandler<SOAPMessageContext> {

    private XWSSProcessor proc;
    private boolean trace = false;

    public Verifier() {
        try {
            XWSSProcessorFactory fact = XWSSProcessorFactory.newInstance();
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("server.xml");

            proc = fact.createProcessorForSecurityConfiguration(in, new VerifierCallback());
        } catch (Exception ex) {
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
        if (!outbound) {
            SOAPMessage msg = context.getMessage();
            try {
                ProcessingContext pcxt = proc.createProcessingContext(msg);
                pcxt.setSOAPMessage(msg);
                SOAPMessage verified = proc.verifyInboundMessage(pcxt);
                context.setMessage(verified);
                if (trace) {
                    dump(verified);
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
