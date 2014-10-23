
package com.security.client.launcher;

import com.security.client.EchoPort;
import com.security.client.EchoService;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;

/**
 * @author Jansel Rodriguez
 */
public class Launcher {
    public static void main(String...args){
        EchoService service = new EchoService();
        EchoPort port = service.getEchoBindingPort();
        Binding binding = ((BindingProvider)port).getBinding();
        
        List<Handler> handlers = new ArrayList();
        handlers.add( new DecoratorHandler() );
        binding.setHandlerChain(handlers);
        
        System.out.println( port.echo("Jansel") );
    }
}
