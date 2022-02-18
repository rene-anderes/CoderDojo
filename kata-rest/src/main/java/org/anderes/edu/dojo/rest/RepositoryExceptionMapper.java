package org.anderes.edu.dojo.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.anderes.edu.dojo.rest.persistence.RepositoryException;

@Provider
public class RepositoryExceptionMapper implements ExceptionMapper<RepositoryException> {

    @Override
    public Response toResponse(RepositoryException exception) {
        return Response.status(Status.fromStatusCode(999)).build();
    }

}
