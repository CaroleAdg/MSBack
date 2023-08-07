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

import fr.istic.taa.dto.FormulaDto;
import fr.istic.taa.dto.FormulaOneDto;
import fr.istic.taa.dto.ProductOneDto;
import fr.istic.taa.jaxrs.dao.generic.FormulaDao;
import fr.istic.taa.jaxrs.dao.generic.OrdersDao;
import fr.istic.taa.jaxrs.dao.generic.ProductDao;
import fr.istic.taa.jaxrs.domain.Formula;
import fr.istic.taa.jaxrs.domain.Orders;
import fr.istic.taa.jaxrs.domain.Product;
import io.swagger.v3.oas.annotations.Parameter;


@Path("/formulas")
@Produces({"application/json"})
public class FormulaRessource {
	FormulaDao fdao = new FormulaDao();
	
	@GET
	@Path("/{formulaId}")
	public Response getFormulaById(@PathParam("formulaId") Long id_formula) {
		Formula formula= fdao.findOne(id_formula);
		if(formula==null) {
			return Response.status(Response.Status.NOT_FOUND).entity("La formule avec l'identifiant " +id_formula+" n'existe pas" ).build();
		}
		   return Response.status(Response.Status.OK).entity(new FormulaOneDto(formula)).build();
	}
	
	@GET
	@Path("/{FormulaAll}")

	public Response getFormulas() {
		List<Formula> formulas =fdao.findAll();
		List<FormulaOneDto> fODto= new ArrayList<FormulaOneDto>();
		for(Formula formula:formulas) {
			fODto.add(new FormulaOneDto(formula));
			}
		if(fODto.size()>0) {
			return Response.status(Response.Status.OK).entity(fODto).build();
		}
		return Response.status(Response.Status.NOT_FOUND).entity("Aucune formule n'existe" ).build();

		}
	
	
	@POST
	@Consumes("application/json")
	public Response addFormula(
		@Parameter(description = "Formula object that needs to be added to the store", required = true) FormulaDto fdto) {
		
		List<Product> product =new ArrayList<Product>();
		ProductDao pdao = new ProductDao();
		for (int i = 0; i < fdto.getIdProduct().size();i++) {
			Product p = pdao.findOne(fdto.getIdProduct().get(i).longValue());
			if(p==null) {
				return Response.status(Response.Status.NOT_FOUND).entity("Un ou plusieurs produits n'existe(nt) pas" ).build();
			}
			product.add(p);
		}
		Formula formula = new Formula();
		if(product!=null) {
			formula.setP(product);
		}
		formula.setFormulaName(fdto.getFormulaName());
		formula.setFormulaPrice(fdto.getFormulaPrice());
		fdao.save(formula);
		return Response.ok().entity(formula).build();
	}
	
	@PUT
	@Path("/{formulaId}")
	@Consumes("application/json")
	public Response updateFormula(
		@PathParam("formulaId") Long id_formula,
		@Parameter(description = "Formula object that needs to be added to the store", required = true)FormulaDto fdto) {
		
			Formula formula=fdao.findOne(id_formula);
			if(formula==null) {
				return Response.status(Response.Status.NOT_FOUND).entity("La formule avec l'identifiant " +id_formula+" n'existe pas" ).build();
			}
			else {
			List<Product> products =new ArrayList<Product>();
			ProductDao pdao = new ProductDao();
			for (Integer id : fdto.getIdProduct()) {
				Product p = pdao.findOne(id.longValue());
				if(p==null) {
					return Response.status(Response.Status.NOT_FOUND).entity("Ce produit n'existe pas" ).build();
				}
				products.add(p);
			}
			if(products!=null) {
				formula.setP(products);
			}	
				formula.setFormulaName(fdto.getFormulaName());
				formula.setFormulaPrice(fdto.getFormulaPrice());
				fdao.update(formula);
				return Response.ok().entity("Success").build();
			}
		}
	
	@DELETE
	  @Path("/{formulaId}")
	public Response deleteFormula(@PathParam("formulaId") Long id_formula) {
		Formula formula= fdao.findOne(id_formula);
		if(formula==null) {
			fdao.delete(formula);
			 return Response.status(Response.Status.NOT_FOUND).entity("La formule avec l'identifiant " +id_formula+" a bien été supprimé" ).build();
		}
		 return Response.status(Response.Status.OK).entity("La formule avec l'identifiant " +id_formula+" n'existe pas" ).build();
	}
}
