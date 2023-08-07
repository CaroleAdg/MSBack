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

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import fr.istic.taa.dto.FormulaOneDto;
import fr.istic.taa.dto.FormulaQuantityDto;
import fr.istic.taa.dto.OrdersDto;
import fr.istic.taa.dto.OrdersOneDto;
import fr.istic.taa.dto.ProductQuantityDto;
import fr.istic.taa.dto.UserDto;
import fr.istic.taa.jaxrs.dao.generic.FormulaDao;
import fr.istic.taa.jaxrs.dao.generic.OrdersDao;
import fr.istic.taa.jaxrs.dao.generic.ProductDao;
import fr.istic.taa.jaxrs.dao.generic.UserDao;
import fr.istic.taa.jaxrs.domain.Formula;
import fr.istic.taa.jaxrs.domain.Orders;
import fr.istic.taa.jaxrs.domain.OrdersFormulaId;
import fr.istic.taa.jaxrs.domain.OrdersProductId;
import fr.istic.taa.jaxrs.domain.Orders_Formula;
import fr.istic.taa.jaxrs.domain.Orders_Product;
import fr.istic.taa.jaxrs.domain.Product;
import fr.istic.taa.jaxrs.domain.User;
import io.swagger.v3.oas.annotations.Parameter;

@Path("/orders")
@Produces({"application/json"})
public class OrdersRessource {
	
	OrdersDao odao= new OrdersDao();
	@GET
	@Path("/{ordersId}")
	public Response getOrderById(@PathParam("ordersId") Long id_order) {
		Orders order= odao.findOne(id_order);
		if(order==null) {
			return Response.status(Response.Status.NOT_FOUND).entity("La commande avec l'identifiant " +id_order+" n'existe pas" ).build();
		}
		    return Response.status(Response.Status.OK).entity(new OrdersOneDto(order)).build();
		
	}
	
	
	@POST
	@Consumes("application/json")
	public Response addOrder(
		@Parameter(description = "Client object that needs to be added to the store", required = true) OrdersDto odto) {
		
		//valider le user
		UserDao udao= new UserDao();
		User user= udao.findOne(odto.getUserId());
		if(user==null)
			 return Response.status(Response.Status.NOT_FOUND).entity("L'utilisateur n'existe pas" ).build();
		
		List<Product> products = new ArrayList<>();
		List<ProductQuantityDto> pq = new ArrayList<ProductQuantityDto>();
		
		//valider les produits
		ProductDao pdao= new ProductDao();
		for(ProductQuantityDto idP : odto.getProductQuantityId()) {
			Product p = pdao.findOne(idP.getIdProduct());
			if(p!=null) {
				products.add(p);
				pq.add(idP);
			}
		}
		
		//valider les formules
		List<Formula> formulas = new ArrayList<Formula>();
		List<FormulaQuantityDto> fqs = new ArrayList<FormulaQuantityDto>();
		FormulaDao fdao= new FormulaDao();
		for(FormulaQuantityDto idf : odto.getFormulaQuantityId() ) {
			Formula formula = fdao.findOne(idf.getFormulaId());
			if(formula!=null) {
				formulas.add(formula);
				fqs.add(idf);
			}
			
		}
		
		if(products.size()== 0 && formulas.size() == 0)
			 return Response.status(Response.Status.BAD_REQUEST).entity("VOtre commande doit comporter au moins un produit ou une formule" ).build();
		
		//Nous avons au moins un produit ou une formule valide
		Orders order= new Orders();
		List<Orders_Product>ops = new ArrayList<Orders_Product>();
		List<Orders_Formula> ofs= new ArrayList<Orders_Formula>();
		
		if(products.size()> 0) {
			//Nous avons au moins un produit valide
			for(Product product : products) {
				OrdersProductId opId = new OrdersProductId();
				opId.setOrder(order);
				opId.setProduct(product);
				Orders_Product op= new Orders_Product();
				op.setOrdersProductId(opId);
				int productIndice=products.indexOf(product);
				op.setQuantityOfProduct(pq.get(productIndice).getQuantity());
				ops.add(op);
			}
		}
		
		if(formulas.size()> 0) {
			//Nous avons au moins une formule valide
			for(Formula formula : formulas) {
				OrdersFormulaId ofId = new OrdersFormulaId();
				ofId.setFormula(formula);
				ofId.setOrder(order);
				Orders_Formula of= new Orders_Formula();
				of.setOrdersFormulaId(ofId);
				int formulaIndice=formulas.indexOf(formula);
				of.setQuantityOfFormula(fqs.get(formulaIndice).getQuantity());
				ofs.add(of);
			}
		}
		
		order.setU(user);
		order.setOp(ops);
		order.setOf(ofs);
		order.setOrderPrice(odto.getOrderPrice());
		order.setOrderStatus(odto.getOrderStatus());
		odao.save(order);
		return Response.ok().entity("Votre commande a bien été créée").build();
	}
	
	@PUT
	@Consumes("application/json")
	@Path("/{ordersId}")
	public Response updateOrders(@PathParam("ordersId") Long id_order,
			@Parameter(description = "Client object that needs to be added to the store", required = true) OrdersDto odto) {
		Orders order=odao.findOne(id_order);
		if(order==null) {
			 return Response.status(Response.Status.NOT_FOUND).entity("La commande avec l'identifiant " +id_order+" n'existe pas" ).build();
		}
		else {
			
		// Validation de User
		UserDao udao = new UserDao();
		User user=udao.findOne(odto.getUserId());
		if(user==null) {
			 return Response.status(Response.Status.NOT_FOUND).entity("L'utilisateur avec l'identifiant " +odto.getUserId()+" n'existe pas" ).build();
		}
		
		List<Product> products = new ArrayList<Product>();
		List<ProductQuantityDto> productsQuantityDto = new ArrayList<ProductQuantityDto>();
		for(ProductQuantityDto productQuantityDto : odto.getProductQuantityId()) {
			ProductDao pdao = new ProductDao();
			Product product= pdao.findOne(productQuantityDto.getIdProduct()) ;
			if(product!=null) {
				products.add(product);
				productsQuantityDto.add(productQuantityDto);
			}
		}
		List<Formula> formulas = new ArrayList<Formula>();
		List<FormulaQuantityDto> formulasQuantityDto= new ArrayList<FormulaQuantityDto>();
		for(FormulaQuantityDto formulaQuantityDto : formulasQuantityDto) {
			FormulaDao fdao = new FormulaDao();
			Formula formula = fdao.findOne(formulaQuantityDto.getFormulaId());
			if(formula!=null) {
				formulas.add(formula);
				formulasQuantityDto.add(formulaQuantityDto);
			}
		}
		
		if(products.size() == 0 && formulas.size() == 0) {
			 return Response.status(Response.Status.BAD_REQUEST).entity("Votre commande doit comporter au moins un produit ou une formule" ).build();
		}
		//Vider les formules et les produits dejà assignés !
		order.setOp(new ArrayList<>());
		order.setOf(new ArrayList<>());
		odao.update(order);
		
		
		List<Orders_Product> orderProductList= new ArrayList<Orders_Product>();
		
		if(products.size()> 0) {
			for(Product p : products) {
				OrdersProductId orderProductId= new OrdersProductId();
				orderProductId.setOrder(order);
				orderProductId.setProduct(p);
				
				Orders_Product ordersProduct = new Orders_Product();
				ordersProduct.setOrdersProductId(orderProductId);
				int indice = productsQuantityDto.indexOf(p);
				ordersProduct.setQuantityOfProduct(indice);
				orderProductList.add(ordersProduct);
			}
		}
		
		
		List<Orders_Formula> orderFormulaList = new ArrayList<Orders_Formula>();
		if(formulas.size()>0) {
			for(Formula f: formulas) {
				OrdersFormulaId ordersFormulaId = new OrdersFormulaId();
				ordersFormulaId.setFormula(f);
				ordersFormulaId.setOrder(order);
				Orders_Formula orderFormula = new Orders_Formula();
				int indice = formulasQuantityDto.indexOf(f);
				orderFormula.setOrdersFormulaId(ordersFormulaId);
				orderFormula.setQuantityOfFormula(indice);
				orderFormulaList.add(orderFormula);
				
			}
		}
		order.setU(user);
		order.setOp(orderProductList);
		order.setOf(orderFormulaList);
		order.setOrderPrice(odto.getOrderPrice());
		order.setOrderStatus(odto.getOrderStatus());
		odao.update(order);
		return Response.ok().entity("Votre commande a été mis à jour").build();
		}
	}
		
	
	@DELETE
	@Path("/{ordersId}")
	public Response deleteOrders(@PathParam("ordersId") Long id_order) {
		Orders order= odao.findOne(id_order);
		if(order==null) {
			 return Response.status(Response.Status.NOT_FOUND).entity("La commande avec l'identifiant " +id_order+" n'existe pas" ).build();
		}
	    return Response.status(Response.Status.OK).entity("La commande "+id_order+" a bien été supprimée").build();
	}

}
