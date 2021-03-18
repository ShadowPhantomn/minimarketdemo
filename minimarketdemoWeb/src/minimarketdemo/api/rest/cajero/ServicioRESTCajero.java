package minimarketdemo.api.rest.cajero;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.jws.WebMethod;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import minimarketdemo.model.cajero.dtos.DTOCajFactura;
import minimarketdemo.model.cajero.dtos.DTOCajProveedor;
import minimarketdemo.model.cajero.managers.ManagerCajero;
import minimarketdemo.model.core.entities.Factura;
import minimarketdemo.model.core.entities.Proveedor;


@RequestScoped
@Path("cajero")
@Produces("application/json")
@Consumes("application/json")
public class ServicioRESTCajero {

	
	
	@EJB
	private ManagerCajero mCajero;
	
	@GET
	@Path(value = "listaProveedores")
	public List<DTOCajProveedor> findAllProveedor(){
		return mCajero.findAllDTOProveedor();
	}
	
	@GET
	@Path(value = "listaProveedorFacturaCorriente")
	public List<DTOCajProveedor> findCreditoProvedorFacturas(){
		return mCajero.findDTOProveedorTipoFactura("credito");
	}
	
	@GET
	@Path(value = "listaFacturaCorriente")
	public List<DTOCajFactura> findCreditoFacturas(){
		return mCajero.findDTOTipoFactura("credito");
	}
	
	
}
