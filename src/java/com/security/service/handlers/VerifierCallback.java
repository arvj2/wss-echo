
package com.security.service.handlers;

import com.sun.xml.wss.impl.callback.PasswordValidationCallback;
import com.sun.xml.wss.impl.callback.TimestampValidationCallback;
import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

/**
 * @author Jansel Rodriguez
 */
public class VerifierCallback implements CallbackHandler{
    private static final String user = "jansel";
    private static final String password = "123456";
        
    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
       for( Callback callback : callbacks ){
           if( callback instanceof PasswordValidationCallback )
               ((PasswordValidationCallback)callback).setValidator( new PlainTextValidator() );
           else if( callback instanceof TimestampValidationCallback )
               ((TimestampValidationCallback)callback).setValidator( new PlainTimestampValidator() );
       }
    }
    
    private static class PlainTextValidator implements PasswordValidationCallback.PasswordValidator{
        @Override
        public boolean validate(PasswordValidationCallback.Request rqst) throws PasswordValidationCallback.PasswordValidationException {
             PasswordValidationCallback.PlainTextPasswordRequest r = ( PasswordValidationCallback.PlainTextPasswordRequest)rqst;
             if( user.equals(r.getUsername()) && password.equals(r.getPassword()))
                 return true;
             return false;
        }        
    }
    
    private static class PlainTimestampValidator  implements TimestampValidationCallback.TimestampValidator{
        @Override
        public void validate(TimestampValidationCallback.Request rqst) throws TimestampValidationCallback.TimestampValidationException {
           TimestampValidationCallback.UTCTimestampRequest request = (TimestampValidationCallback.UTCTimestampRequest)rqst;
        }        
    }
}
