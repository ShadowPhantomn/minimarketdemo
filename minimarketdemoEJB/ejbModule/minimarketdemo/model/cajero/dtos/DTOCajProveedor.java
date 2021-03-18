package minimarketdemo.model.cajero.dtos;

import java.util.List;

public class DTOCajProveedor {

	private int idProveedor;
	private String cedula;
	private String email;
	private String nombre;
	private String telefono;
	private List<DTOCajFactura> factura;

	
	
	public DTOCajProveedor(int idProveedor, String cedula, String email, String nombre, String telefono,
			List<DTOCajFactura> factura) {
		super();
		this.idProveedor = idProveedor;
		this.cedula = cedula;
		this.email = email;
		this.nombre = nombre;
		this.telefono = telefono;
		this.factura = factura;
	}

	public DTOCajProveedor(int idProveedor, String cedula, String email, String nombre, String telefono) {
		this.idProveedor = idProveedor;
		this.cedula = cedula;
		this.email = email;
		this.nombre = nombre;
		this.telefono = telefono;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<DTOCajFactura> getFactura() {
		return factura;
	}

	public void setFactura(List<DTOCajFactura> factura) {
		this.factura = factura;
	}

	
}
