package com.igor.ns.rest.endpoint;

import com.igor.ns.ejb.CellDAO;
import com.igor.ns.entity.Cell;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CREATED;

@Path("/cells")
@Produces(APPLICATION_JSON)
public class CellConfigurationService {

    @Inject
    private CellDAO cellDAO;

    @GET
    @Path("/list")
    public Response getAllCells() {
        final List<Cell> cells = cellDAO.getCells();
        return Response.ok(cells).build();
    }

    @POST
    @Path("/add")
    public Response createCell(final Cell cell) {
        cellDAO.persistCell(cell);
        return Response.status(CREATED).entity(cell).build();
    }

    @GET
    @Path("/{id}")
    public Response findCell(@PathParam("id") final long id) {
        final Cell cell = cellDAO.getCellById(id);
        return Response.ok(cell).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteCell(@PathParam("id") final long id) {
        cellDAO.delete(id);
        return Response.ok().build();
    }
}
