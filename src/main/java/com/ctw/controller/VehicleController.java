package com.ctw.controller;

import com.ctw.dto.VehicleDTO;
import com.ctw.service.VehicleService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/vehicles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VehicleController {
    @Inject
    VehicleService vehicleService;

    @GET
    public List<VehicleDTO> getAll() {
        return vehicleService.getAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") String id) {
        VehicleDTO dto = vehicleService.getById(id);
        if (dto == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(dto).build();
    }

    @POST
    public Response create(VehicleDTO dto) {
        VehicleDTO created = vehicleService.create(dto);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, VehicleDTO dto) {
        VehicleDTO updated = vehicleService.update(id, dto);
        if (updated == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        boolean deleted = vehicleService.delete(id);
        if (!deleted) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.noContent().build();
    }
}

