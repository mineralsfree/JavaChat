package men.brakh;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/message")
public class CustomerService {
    @GET
    @Path("ping")
    public String getdServerTime() {
        System.out.println("RESTful Service 'MessageService' is running ==> ping");
        return "received ping on "+new Date().toString();
    }
    @GET
    @Path("ping")
    public String getServerTime() {
        System.out.println("RESTful Service 'MessageService' is running ==> ping");
        return "received ping on "+new Date().toString();
    }


}