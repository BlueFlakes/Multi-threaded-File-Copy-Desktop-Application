package mth.filecopier.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("singleton")
@Component
public class DestinationPathParser {

    public String retrieveFileDestination(String destination){

        Character last = destination.charAt(destination.length() - 1);

        if(last != '/'){
            destination += "/";
        }
        return destination;
        }
    }

