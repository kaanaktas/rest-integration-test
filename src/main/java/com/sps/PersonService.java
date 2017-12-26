package com.sps;

import com.sps.utils.Person;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/personService")
public class PersonService {

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonList() {
        JSONArray responseJson = new JSONArray();
        JSONObject personInfo = new JSONObject();
        personInfo.put("name", "Kaan");
        personInfo.put("surname", "Aktas");
        personInfo.put("city", "London");
        JSONObject cityDetail = new JSONObject();
        cityDetail.put("county", "London Central");
        cityDetail.put("postCode", "0000");
        personInfo.put("cityDetail", cityDetail);
        responseJson.put(personInfo);

        personInfo = new JSONObject();
        personInfo.put("name", "Tom");
        personInfo.put("surname", "Brown");
        personInfo.put("city", "Oxford");
        cityDetail = new JSONObject();
        cityDetail.put("county", "Oxford Central");
        cityDetail.put("postCode", "1111");
        personInfo.put("cityDetail", cityDetail);
        responseJson.put(personInfo);

        return Response.status(200).entity(responseJson.toString()).build();
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("name") String name) {
        JSONObject personInfo = new JSONObject();

        if ("Kaan".equals(name)) {
            personInfo.put("name", "Kaan");
            personInfo.put("surname", "Aktas");
            personInfo.put("city", "London");
            JSONObject cityDetail = new JSONObject();
            cityDetail.put("county", "London Central");
            cityDetail.put("postCode", "0000");
            personInfo.put("cityDetail", cityDetail);
        } else if ("Tom".equals(name)) {
            personInfo.put("name", "Tom");
            personInfo.put("surname", "Brown");
            personInfo.put("city", "Oxford");
            JSONObject cityDetail = new JSONObject();
            cityDetail.put("county", "Oxford Central");
            cityDetail.put("postCode", "1111");
            personInfo.put("cityDetail", cityDetail);
        } else {
            return Response.status(404).entity("{}").build();
        }

        return Response.status(200).entity(personInfo.toString()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(Person person) {
        JSONObject personInfo = new JSONObject();
        personInfo.put("result", person.getName()+" has been added successfully!");
        return Response.status(200).entity(personInfo.toString()).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(Person person) {
        JSONObject personInfo = new JSONObject();
        personInfo.put("result", person.getName()+" has been updated successfully!");
        return Response.status(200).entity(personInfo.toString()).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePerson(Person person) {
        JSONObject personInfo = new JSONObject();
        personInfo.put("result", person.getName()+" has been deleted successfully!");
        return Response.status(200).entity(personInfo.toString()).build();
    }
}
