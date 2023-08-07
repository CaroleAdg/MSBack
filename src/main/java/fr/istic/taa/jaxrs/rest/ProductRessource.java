package fr.istic.taa.jaxrs.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import fr.istic.taa.dto.ProductDto;
import fr.istic.taa.dto.ProductOneDto;
import fr.istic.taa.dto.UserOneDto;
import fr.istic.taa.jaxrs.dao.generic.ProductDao;
import fr.istic.taa.jaxrs.domain.Product;
import io.swagger.v3.oas.annotations.Parameter;

@Path("/products")
@Produces({"application/json"})
public class ProductRessource {
	ProductDao pdao = new ProductDao();
	@GET
	@Path("{productId}")
	public Response getProductById(@PathParam("productId") Long id_product) {
		Product product = pdao.findOne(id_product);
		if(product==null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Le produit avec l'identifiant" +id_product+"n'existe pas" ).build();

		}
		   return Response.status(Response.Status.OK).entity(new ProductOneDto(product)).build();
	}
	
	@POST
	@Consumes("application/json")
	public Response addProduct(
		@Parameter(description = "Product object that needs to be added to the store", required = true) ProductDto pdto) {
		Product product = new Product();
		product.setProductName(pdto.getProductName());
		product.setDescription(pdto.getDescription());
		product.setProductPrice(pdto.getProductPrice());
		product.setType(pdto.getType());
		
		pdao.save(product);
		return Response.ok().entity(product).build();
		
	}
	
	@PUT
	@Consumes("application/json")
	@Path("{idProduct}")
	public Response updateproduct(
		@PathParam("idProduct") Long id_product,
		@Parameter(description = "Product object that needs to be updated to the store", required = true) ProductDto pdto) {
			
			Product product= pdao.findOne(id_product);
			if(product!=null) {
				product.setProductName(pdto.getProductName());
				product.setDescription(pdto.getDescription());
				product.setProductPrice(pdto.getProductPrice());
				product.setType(pdto.getType());
				pdao.save(product);
				return Response.ok().entity(product).build();
			}
			return Response.status(Response.Status.NOT_FOUND).entity("Le produit avec l'identifiant" +id_product+"n'existe pas" ).build();
		}
	
	@DELETE
	@Consumes("application/json")
	@Path("{id_Product}")
	public Response deleteProduct(
		@PathParam("id_Product") Long id_product) {
		Product product=pdao.findOne(id_product);
		if(product!=null) {
			pdao.delete(product);
			return Response.status(Response.Status.NOT_FOUND).entity("Le produit avec l'identifiant"+" " +id_product+" "+"a bien été supprimé" ).build();
		}
		return Response.status(Response.Status.OK).entity("Le produit avec l'identifiant " +id_product+" n'existe pas" ).build();
	}
	
	
}
