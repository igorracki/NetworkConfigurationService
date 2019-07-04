package com.igor.ns.exception;

public class NetworkConfigurationServiceException extends RuntimeException {
    public NetworkConfigurationServiceException(final String s) {
        super(s);
    }

    public NetworkConfigurationServiceException(final String s, final Throwable throwable) {
        super(s, throwable);
    }
}
