package jext.metrics;

public class NoSuchProviderException extends MetricsException {

    public NoSuchProviderException(String name, Throwable e) {
        super(name, e);
    }
}
