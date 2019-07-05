package com.igor.ns.rest.endpoint;

import com.igor.ns.ejb.NodeDAO;
import com.igor.ns.entity.Node;
import com.igor.ns.rest.utility.CellsForNode;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CREATED;

@Path("/nodes")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@DeclareRoles("Manager")
public class NodeConfigurationService {

    @Inject
    private NodeDAO nodeDAO;

    @GET
    public Response getDefaultResponse() {
        return Response.ok("Welcome to the Node Configuration Service!").build();
    }

    @GET
    @Path("/list")
    public Response getAllNodes() {
        final List<Node> nodes = nodeDAO.getNodes();
        return Response.ok(nodes).build();
    }

    @POST
    @Path("/add")
    @RolesAllowed("Manager")
    public Response createNode(final Node node) {
        nodeDAO.persistNode(node);
        return Response.status(CREATED).entity(node).build();
    }

    @GET
    @Path("/{id}")
    public Response findNode(@PathParam("id") final long id) {
        final Node node = nodeDAO.getNodeById(id);
        return Response.ok(node).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @RolesAllowed("Manager")
    public Response deleteNode(@PathParam("id") final long id) {
        nodeDAO.delete(id);
        return Response.ok().build();
    }

    @POST
    @Path("/cells/add/")
    @RolesAllowed("Manager")
    public Response addCellsToNode(final CellsForNode cellsForNode) {
        nodeDAO.addCellsToNode(cellsForNode.getNodeId(), cellsForNode.getCellIds());
        return Response.ok().build();
    }
}
