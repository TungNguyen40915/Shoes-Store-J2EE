package com.store.controller.admin;

import com.store.annotation.Admin;
import com.store.converter.OrderConverter;
import com.store.dao.OrderDAO;
import com.store.dto.OrderDTO;
import com.store.model.CustomerOrder;
import com.store.model.Response;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/admin/order")
public class AdminOrderController {
    ObjectMapper mapper = new ObjectMapper();

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Admin
    public Response getList(
            @QueryParam("page") int page,
            @QueryParam("page-size") int pageSize){
        List<OrderDTO> orderDTOList = new ArrayList<OrderDTO>();
        Response res = new Response();
        try {
            res.setCode("OK");
            res.setMsg("Updated Successfully");
            List<CustomerOrder> customerOrderList = OrderDAO.getOrderList(page,pageSize);
            int totalRecords = OrderDAO.getTotalRecords();
            res.setTotalRecords(totalRecords);

            for (CustomerOrder order : customerOrderList) {
                orderDTOList.add(OrderConverter.ConvertOrderEntityToOrderDTO(order));
            }

            res.setData(mapper.writeValueAsString(orderDTOList));
        } catch (Exception e) {
            e.printStackTrace();
            res.setCode("FAIL");
            res.setMsg("Something Went Wrong");
            res.setData("[]");
            res.setTotalRecords(0);
        }
        return res;
    }

}
