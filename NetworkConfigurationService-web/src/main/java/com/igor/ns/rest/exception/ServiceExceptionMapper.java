package com.igor.ns.rest.exception;

import com.igor.ns.exception.NetworkConfigurationServiceException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
public class ServiceExceptionMapper implements ExceptionMapper<NetworkConfigurationServiceException> {
    @Override
    public Response toResponse(NetworkConfigurationServiceException e) {
        return Response.status(BAD_REQUEST).entity(e.getMessage()).build();
    }
}
