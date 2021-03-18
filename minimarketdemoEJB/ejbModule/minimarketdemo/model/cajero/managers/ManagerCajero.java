package minimarketdemo.model.cajero.managers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.auditoria.managers.ManagerAuditoria;
import minimarketdemo.model.cajero.dtos.DTOCajFactura;
import minimarketdemo.model.cajero.dtos.DTOCajProveedor;
import minimarketdemo.model.core.entities.Factura;
import minimarketdemo.model.core.entities.Proveedor;
import minimarketdemo.model.core.entities.SegAsignacion;
import minimarketdemo.model.core.entities.SegModulo;
import minimarketdemo.model.core.entities.SegUsuario;
import minimarketdemo.model.core.managers.ManagerDAO;
import minimarketdemo.model.core.utils.ModelUtil;
import minimarketdemo.model.seguridades.dtos.LoginDTO;

/**
 * Implementa la logica de manejo de usuarios y autenticacion.
 * Funcionalidades principales:
 * <ul>
 * 	<li>Verificacion de credenciales de usuario.</li>
 *  <li>Asignacion de modulos a un usuario.</li>
 * </ul>
 * @author mrea
 */
@Stateless
@LocalBean
public class ManagerCajero {
	@EJB
	private ManagerDAO mDAO;
	@EJB
	private ManagerAuditoria mAuditoria;
    /**
     * Default constructor. 
     */
    public ManagerCajero() {
        
    }


    

    public List<Proveedor> findAllProveedor(){
    	return mDAO.findAll(Proveedor.class);
    }
    
    public List<DTOCajProveedor> findAllDTOProveedor(){
    	List<DTOCajProveedor> listaDTO = new ArrayList<DTOCajProveedor>();
    	for(Proveedor proveed:findAllProveedor()) {
    		DTOCajProveedor p= new DTOCajProveedor(proveed.getIdProveedor(), proveed.getCedula(), proveed.getEmail(),
    				proveed.getNombre(), proveed.getTelefono());
    		listaDTO.add(p);
    	}
    	return listaDTO;
    }
    
    public List<DTOCajProveedor> findDTOProveedorTipoFactura(String tipoFactura){
    	
    	List<DTOCajProveedor> listaDTOProv = new ArrayList<DTOCajProveedor>();
    	List<DTOCajFactura> listaDTOFact = new ArrayList<DTOCajFactura>();
    	
    	for(Proveedor proveed:findAllProveedor()) {
    		
    		for(Factura fact:findTipoFactura(tipoFactura, proveed.getIdProveedor())) {
    			
    			DTOCajFactura f = new DTOCajFactura(fact.getIdFactura(), fact.getFechaFactura(), fact.getFechaVencimiento(),
    					fact.getTipoFactura(), fact.getValorFactura().doubleValue());
    			listaDTOFact.add(f);
    		}
    		
    		DTOCajProveedor p= new DTOCajProveedor(proveed.getIdProveedor(), proveed.getCedula(), proveed.getEmail(),
    				proveed.getNombre(), proveed.getTelefono(),listaDTOFact);
    		
    		listaDTOProv.add(p);
    		//listaDTOFact.clear();
    	}
    	return listaDTOProv;
    }
    
    public List<DTOCajFactura> findDTOTipoFactura(String tipoFactura){
    	
    	List<DTOCajFactura> listaDTOFact = new ArrayList<DTOCajFactura>();
    	
    	for(Proveedor proveed:findAllProveedor()) {
    		
    		for(Factura fact:findTipoFactura(tipoFactura, proveed.getIdProveedor())) {
    			
    			DTOCajFactura f = new DTOCajFactura(fact.getIdFactura(), fact.getFechaFactura(), fact.getFechaVencimiento(),
    					fact.getTipoFactura(), fact.getValorFactura().doubleValue(),proveed.getIdProveedor());
    			listaDTOFact.add(f);
    		}
    		
    		//listaDTOFact.clear();
    	}
    	return listaDTOFact;
    }
    
    
    
    
    public List<Factura> findAllFactura(){
    	return mDAO.findAll(Factura.class);
    }
    
    public List<Factura> findTipoFactura(String tipofactura, int IDProveedor){
    	return mDAO.findWhere(Factura.class,"tipo_factura='"+tipofactura+"' and id_proveedor="+IDProveedor,null);
    }
    
    public Proveedor findByIdProveedor(int idProveedor) throws Exception {
    	return (Proveedor) mDAO.findById(Proveedor.class, idProveedor);
    }
    
    public void insertarUsuario(LoginDTO loginDTO, SegUsuario nuevoUsuario) throws Exception {
    	nuevoUsuario.setCodigo("n/a");
    	mDAO.insertar(nuevoUsuario);
    	mAuditoria.mostrarLog(loginDTO, getClass(), "insertarUsuario", 
    			"Usuario ("+nuevoUsuario.getIdSegUsuario()+") "+nuevoUsuario.getApellidos()+" "+nuevoUsuario.getNombres()+" creado.");
    }
    
    public void actualizarUsuario(LoginDTO loginDTO,SegUsuario edicionUsuario) throws Exception {
    	SegUsuario usuario=(SegUsuario) mDAO.findById(SegUsuario.class, edicionUsuario.getIdSegUsuario());
    	usuario.setApellidos(edicionUsuario.getApellidos());
    	usuario.setClave(edicionUsuario.getClave());
    	usuario.setCorreo(edicionUsuario.getCorreo());
    	usuario.setCodigo(edicionUsuario.getCodigo());
    	usuario.setNombres(edicionUsuario.getNombres());
    	mDAO.actualizar(usuario);
    	mAuditoria.mostrarLog(loginDTO, getClass(), "actualizarUsuario", "Usuario ("+usuario.getIdSegUsuario()+") actualizado.");
    }
    
    public void activarDesactivarUsuario(int idSegUsuario) throws Exception {
    	SegUsuario usuario=(SegUsuario) mDAO.findById(SegUsuario.class, idSegUsuario);
    	if(idSegUsuario==1)
    		throw new Exception("No se puede desactivar al usuario administrador.");
    	usuario.setActivo(!usuario.getActivo());
    	System.out.println("activar/desactivar "+usuario.getActivo());
    	mDAO.actualizar(usuario);
    }
    
    public void eliminarUsuario(int idSegUsuario) throws Exception {
    	SegUsuario usuario=(SegUsuario) mDAO.findById(SegUsuario.class, idSegUsuario);
    	if(usuario.getIdSegUsuario()==1)
    		throw new Exception("No se puede eliminar el usuario administrador.");
    	if(usuario.getSegAsignacions().size()>0)
    		throw new Exception("No se puede elimininar el usuario porque tiene asignaciones de módulos.");
    	mDAO.eliminar(SegUsuario.class, usuario.getIdSegUsuario());
    }
    
    public List<SegModulo> findAllModulos(){
    	return mDAO.findAll(SegModulo.class, "nombreModulo");
    }
    
    public SegModulo insertarModulo(SegModulo nuevoModulo) throws Exception {
    	SegModulo modulo=new SegModulo();
    	modulo.setNombreModulo(nuevoModulo.getNombreModulo());
    	modulo.setRutaAcceso(nuevoModulo.getRutaAcceso());
    	mDAO.insertar(modulo);
    	return modulo;
    }
    
    public void eliminarModulo(int idSegModulo) throws Exception {
    	mDAO.eliminar(SegModulo.class, idSegModulo);
    }
    
    public void actualizarModulo(SegModulo edicionModulo) throws Exception {
    	SegModulo modulo=(SegModulo) mDAO.findById(SegModulo.class, edicionModulo.getIdSegModulo());
    	modulo.setNombreModulo(edicionModulo.getNombreModulo());
    	modulo.setRutaAcceso(edicionModulo.getRutaAcceso());
    	mDAO.actualizar(modulo);
    }
    
    public List<SegAsignacion> findAsignacionByUsuario(int idSegUsuario){
    	String consulta="o.segUsuario.idSegUsuario="+idSegUsuario;
		List<SegAsignacion> listaAsignaciones=mDAO.findWhere(SegAsignacion.class, consulta, null);
		return listaAsignaciones;
    }
    
    /**
     * Permite asignar el acceso a un modulo del inventario de sistemas.
     * @param idSegUsuario El codigo del usuario.
     * @param idSegModulo El codigo del modulo que se va a asignar.
     * @throws Exception
     */
    public void asignarModulo(int idSegUsuario,int idSegModulo) throws Exception{
    	//Buscar los objetos primarios:
    	SegUsuario usuario=(SegUsuario)mDAO.findById(SegUsuario.class, idSegUsuario);
    	SegModulo modulo=(SegModulo)mDAO.findById(SegModulo.class, idSegModulo);
    	//crear la relacion:
    	SegAsignacion asignacion=new SegAsignacion();
    	asignacion.setSegModulo(modulo);
    	asignacion.setSegUsuario(usuario);
    	//guardar la asignacion:
    	mDAO.insertar(asignacion);
    	mAuditoria.mostrarLog(getClass(), "asignarModulo", "Modulo "+idSegModulo+" asigando a usuario "+idSegUsuario);
    }
    
    public void eliminarAsignacion(int idSegAsignacion) throws Exception {
    	mDAO.eliminar(SegAsignacion.class, idSegAsignacion);
    }

}
