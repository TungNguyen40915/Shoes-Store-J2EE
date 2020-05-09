package com.store.controller;

import com.store.DAO.OrderDAO;
import com.store.DAO.StatusDAO;
import com.store.model.Order;
import com.store.model.OrderDetail;
import com.store.model.Status;
import com.store.responsebody.UpdateStatusResponseBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/invoice")
public class InvoiceController {

    @GET
    @Path("/get-order")
    @Produces({ MediaType.APPLICATION_JSON })
    public Order getOrder(@QueryParam("id") int id) {
        Order order = OrderDAO.getOrderById(id);
        return order;
    }

    @POST
    @Path("/get-order-detail")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<OrderDetail> getOrderDetail(@QueryParam("id") int id) {
        List<OrderDetail> details = OrderDAO.getOrderDetailsByOrderId(id);
        return details;
    }

    @POST
    @Path("/get-status")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Status> getStatus() {
        List<Status> status = StatusDAO.getAllStatus();
        return status;
    }

    @POST
    @Path("/update-status")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getStatus(@QueryParam("id") int orderId, @QueryParam("stt") int statusId) {
        OrderDAO.updateStatus(orderId,statusId);
        return Response.status(Response.Status.OK).entity(new UpdateStatusResponseBody(true,"update success !")).build();
    }
}
