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

import fr.istic.taa.dto.DailyMenuDto;
import fr.istic.taa.dto.DailyMenuOneDto;
import fr.istic.taa.dto.FormulaDailyMenuStatusDto;
import fr.istic.taa.dto.FormulaOneDto;
import fr.istic.taa.dto.OrdersDto;
import fr.istic.taa.jaxrs.dao.generic.DailyMenuDao;
import fr.istic.taa.jaxrs.dao.generic.FormulaDao;
import fr.istic.taa.jaxrs.dao.generic.ProductDao;
import fr.istic.taa.jaxrs.domain.DailyMenu;
import fr.istic.taa.jaxrs.domain.Formula;
import fr.istic.taa.jaxrs.domain.FormulaDailyMenuId;
import fr.istic.taa.jaxrs.domain.Formula_DailyMenu;
import fr.istic.taa.jaxrs.domain.Product;
import io.swagger.v3.oas.annotations.Parameter;

@Path("/daily_menus")
@Produces({"application/json"})
public class DailymenuRessource {
	
	DailyMenuDao dmdao = new DailyMenuDao();
	
	@GET
	@Path("/{dailyMenuId}")
	public Response getDailyMenuById(@PathParam("dailyMenuId") Long id_dailyMenu) {
		DailyMenu dailyMenu = dmdao.findOne(id_dailyMenu);
		if(dailyMenu==null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Le menu du jour " +id_dailyMenu+" n'existe pas").build();
		}
		    return Response.status(Response.Status.OK).entity(new DailyMenuOneDto(dailyMenu)).build();
	}
	
	@GET
	  @Path("/{DailyMenuAll}")
	public Response getAllDailyMenu() {
		List<DailyMenu> dmList = dmdao.findAll();
		List<DailyMenuOneDto> dmListDto = new ArrayList<DailyMenuOneDto>();
		for(DailyMenu dm : dmList) {
			dmListDto.add(new DailyMenuOneDto(dm));
		}
		if(dmListDto.size()>0) {
			return Response.status(Response.Status.OK).entity(dmListDto).build();
		}
		return Response.status(Response.Status.NOT_FOUND).entity("Aucun menu du jour n'existe").build();
	}
	
	
	@POST
	@Consumes("application/json")
	public Response addDailyMenu(
		@Parameter(description = "DailyMenus object that needs to be added to the store", required = true) DailyMenuDto dmdto) {
		
		ProductDao  pdao= new ProductDao();
		List<Product> products = new ArrayList <Product>();
		for(Long idProduct : dmdto.getId_Product()) {
			Product product = pdao.findOne(idProduct);
			if(product == null) {
				return Response.status(Response.Status.BAD_REQUEST).entity("Le produit " +idProduct+" n'existe pas").build();
			}
			products.add(product);
		}
		
		FormulaDao fdao= new FormulaDao();
		List<Formula> formulas = new ArrayList<Formula>();
		List <FormulaDailyMenuStatusDto> fdmStatusList= new ArrayList <FormulaDailyMenuStatusDto>();
		
		for(FormulaDailyMenuStatusDto fdmStatus : dmdto.getFdstatus()) {
			Formula formula = fdao.findOne(fdmStatus.getFormulaId());
			if(formula ==null) {
				return Response.status(Response.Status.BAD_REQUEST).entity("La formule " +fdmStatus.getFormulaId()+" n'existe pas").build();
			}
			formulas.add(formula);
			fdmStatusList.add(fdmStatus);
		}
		
		if(products.size() == 0 && formulas.size() == 0) {
			 return Response.status(Response.Status.BAD_REQUEST).entity("Votre menu doit comporter au moins un produit ou une formule" ).build();
		}
		
		DailyMenu dm = new DailyMenu();
		List<Formula_DailyMenu> fdms = new ArrayList<Formula_DailyMenu>();
		
		for(Formula f : formulas) {
			FormulaDailyMenuId fdmId = new FormulaDailyMenuId();
			fdmId.setDailyMenu(dm);
			fdmId.setFormula(f);
			
			Formula_DailyMenu fdm = new Formula_DailyMenu();
			int indice =fdmStatusList.indexOf(f);
			fdm.setFormulaDailyMenuId(fdmId);
			fdm.setStatus(indice);
			fdms.add(fdm);
			
		}
		
		dm.setP(products);
		dm.setFdm(fdms);
		dmdao.save(dm);
		return Response.ok().entity("Votre menu du jour a bien été créée").build();
		
	}
	
	@PUT
	@Consumes("application/json")
	@Path("/{dailyMenuId}")
	public Response updateDailyMenu (
			@PathParam("dailyMenuId") Long id_dailyMenu,
			@Parameter(description = "DailyMenu object that needs to be added to the store", required = true) DailyMenuDto dmdto) {
		DailyMenu dm = dmdao.findOne(id_dailyMenu);
		if(dm==null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Le menu du jour " +id_dailyMenu+" n'existe pas").build();
		}
		
		dm.setFdm(new ArrayList<Formula_DailyMenu>());
		dmdao.update(dm);
		
		List<Product> products = new ArrayList<Product>();
		ProductDao pdao= new ProductDao();
		for(Long productId : dmdto.getId_Product()) {
			Product p = pdao.findOne(productId);
			if(p==null) {
				return Response.status(Response.Status.NOT_FOUND).entity("Le produit " +productId+" n'existe pas").build();
			}
			products.add(p);
		}
		
		List<FormulaDailyMenuStatusDto> fdmsDto = new ArrayList<FormulaDailyMenuStatusDto>();
		List<Formula> formulas = new ArrayList<Formula>();
		FormulaDao fdao = new FormulaDao();
		for(FormulaDailyMenuStatusDto fdm : dmdto.getFdstatus()) {
			Formula formula = fdao.findOne(fdm.getFormulaId());
			if(formula==null) {
				return Response.status(Response.Status.NOT_FOUND).entity("La formule " +fdm.getFormulaId()+" n'existe pas").build();
			}
			formulas.add(formula);
			fdmsDto.add(fdm);
		}
		
		if(products.size()==0 && formulas.size()==0) {
			 return Response.status(Response.Status.BAD_REQUEST).entity("Votre menu doit comporter au moins un produit ou une formule" ).build();
		}
		
		List<Formula_DailyMenu> formulaDailyMenuList = new ArrayList <Formula_DailyMenu>();
		Formula_DailyMenu formulaDailyMenu= new Formula_DailyMenu();
		for(Formula formula : formulas) {
			FormulaDailyMenuId fdId = new FormulaDailyMenuId();
			fdId.setDailyMenu(dm);
			fdId.setFormula(formula);
			int indice = fdmsDto.indexOf(formula);
			formulaDailyMenu.setFormulaDailyMenuId(fdId);
			formulaDailyMenu.setStatus(indice);
			formulaDailyMenuList.add(formulaDailyMenu);
		}
		dm.setP(products);
		dm.setFdm(formulaDailyMenuList);
		dmdao.update(dm);
		return Response.ok().entity("Votre menu du jour a été mis à jour").build();

		
	}

	
	
	@DELETE
	@Path("/{dailyMenuId}")
	public Response deleteDailyMenu(@PathParam("dailyMenuId") Long id_dailyMenu) {
		DailyMenu dm = dmdao.findOne(id_dailyMenu);
		if(dm==null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Le menu du jour " +id_dailyMenu+" n'existe pas").build();
		}
		dmdao.delete(dm);
	    return Response.status(Response.Status.OK).entity("Le menu du jour " +id_dailyMenu+" a bien été supprimé").build();

	}
	
}
