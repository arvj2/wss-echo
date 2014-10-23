
package com.security.client.launcher;

import com.sun.xml.wss.impl.callback.PasswordCallback;
import com.sun.xml.wss.impl.callback.UsernameCallback;
import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;


/**
 * @author Jansel Rodriguez
 */
public class Prompter implements CallbackHandler{
    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
       for(  Callback callback : callbacks ){
           if( callback instanceof UsernameCallback )
               ((UsernameCallback)callback).setUsername("jansel");
           else if( callback instanceof PasswordCallback )
               ((PasswordCallback)callback).setPassword("123456");
       }
    }   
}
