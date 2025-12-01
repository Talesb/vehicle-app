package com.ctw.controller;

import com.ctw.dto.EngineDTO;
import com.ctw.service.EngineService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/engines")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EngineController {
    @Inject
    EngineService engineService;

    @GET
    public List<EngineDTO> getAll() {
        return engineService.getAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") String id) {
        EngineDTO dto = engineService.getById(id);
        if (dto == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(dto).build();
    }

    @POST
    public Response create(EngineDTO dto) {
        EngineDTO created = engineService.create(dto);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, EngineDTO dto) {
        EngineDTO updated = engineService.update(id, dto);
        if (updated == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        boolean deleted = engineService.delete(id);
        if (!deleted) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.noContent().build();
    }
}

