package org.hls.check;

public class CustomException extends RuntimeException {

    @Override
    public String getMessage(){
        return "CustomException";
    }
}
