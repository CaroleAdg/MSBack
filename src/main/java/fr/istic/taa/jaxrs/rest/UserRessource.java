package fr.istic.taa.jaxrs.rest;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import fr.istic.taa.dto.UserDto;
import fr.istic.taa.dto.UserOneDto;
import fr.istic.taa.jaxrs.dao.generic.UserDao;
import fr.istic.taa.jaxrs.domain.Customer;
import fr.istic.taa.jaxrs.domain.Manager;
import fr.istic.taa.jaxrs.domain.User;
import io.swagger.v3.oas.annotations.Parameter;


@Path("/users")
@Produces({"application/json"})
public class UserRessource {
	UserDao udao= new UserDao();
	
	  @GET
	  @Path("/{userId}")
	  public Response getUserById(@PathParam("userId") Long  id_user)  {
	   User user = udao.findOne(id_user);
	   if(user==null) {
			return Response.status(Response.Status.NOT_FOUND).entity("L'utilisateur avec l'identifiant"+" " +id_user+" "+"n'existe pas" ).build();
	   }
	   return Response.status(Response.Status.OK).entity(new UserOneDto(user)).build();
	  }
	  @GET
	  @Path("/{usersAll}")
	  public Response getUsers() {
		  List <User> users = udao.findAll();
		  List<UserDto> usersDto = new ArrayList<UserDto>();
		  
		  for(User user : users) {
			  usersDto.add(new UserDto(user));
			  }
		  return Response.status(Response.Status.OK).entity(usersDto).build();

	  }
	  
	  @POST
	  @Consumes("application/json")
	  @Path("/client")
	  public Response addCient(
	      @Parameter(description = "Client object that needs to be added to the store", required = true) UserDto udto) {
		  Customer customer= new Customer();
		  customer.setFirstName(udto.getFirstName());
		  customer.setLastName(udto.getLastName());
		  customer.setEmail(udto.getEmail());
		  customer.setPassword(udto.getPassword());
	    udao.save(customer);
	    return Response.ok().entity(customer).build();
	  }
	  
	  @Path("/manager")
	  @POST
	  @Consumes("application/json")
	  public Response addManager(
	      @Parameter(description = "Manager object that needs to be added to the store", required = true) UserDto udto) {
		  Manager manager= new Manager();
		  manager.setFirstName(udto.getFirstName());
		  manager.setLastName(udto.getLastName());
		  manager.setEmail(udto.getEmail());
		  manager.setPassword(udto.getPassword());
	    udao.save(manager);
	    return Response.ok().entity(manager).build();
	  }
	  
	  @PUT
	  @Consumes("application/json")
	  @Path("/{id}")
	  public Response updateClient(@PathParam("id") Long id,
		
		@Parameter(description = "Client object that needs to be updated to the store", required = true)  UserDto udto) {
		  System.out.println(id);
		User user= udao.findOne(id);
		if(user!=null) {
			user.setFirstName(udto.getFirstName());
			user.setLastName(udto.getLastName());
			user.setEmail(udto.getEmail());
			//user.setPassword(udto.getPassword());
		    udao.update(user);
		    return Response.ok().entity(user).build();
		}
		return Response.status(Response.Status.NOT_FOUND).entity("L'utilisateur avec l'identifiant" +id+"n'existe pas" ).build();
	  }
	  
	  @DELETE
	  @Path("/{userId}")
	  public Response deleteUser(
	  @PathParam("userId") Long  id_user) {
		  User user= udao.findOne(id_user);
		  if(user!=null) {
			  udao.delete(user);
			  return Response.status(Response.Status.NOT_FOUND).entity("L'utilisateur avec l'identifiant" +id_user+"a été bien supprimé" ).build();
		  }
		  return Response.status(Response.Status.NOT_FOUND).entity("L'utilisateur avec l'identifiant" +id_user+"n'existe pas" ).build();
		  
	  }
	  
}
