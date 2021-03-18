package minimarketdemo.api.soap.cajero;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

import minimarketdemo.model.cajero.dtos.DTOCajProveedor;
import minimarketdemo.model.cajero.managers.ManagerCajero;
import minimarketdemo.model.core.entities.Factura;
import minimarketdemo.model.core.entities.Proveedor;


@WebService(serviceName = "ServiceCajero")
public class ServiceSOAPCajero {
	
	@EJB
	private ManagerCajero mCajero;
	
	@WebMethod(operationName="findProveedores")
	public List<DTOCajProveedor> findAllProveedor(){
		return mCajero.findAllDTOProveedor();
	}
	
	@WebMethod(operationName="findFacturas")
	public List<Factura> findCreditoFacturas(){
		return mCajero.findTipoFactura("tipo_factura='credito'");
	}
	
}
