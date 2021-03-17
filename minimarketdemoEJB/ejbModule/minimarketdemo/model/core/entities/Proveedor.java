package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the proveedor database table.
 * 
 */
@Entity
@Table(name="proveedor")
@NamedQuery(name="Proveedor.findAll", query="SELECT p FROM Proveedor p")
public class Proveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_proveedor", unique=true, nullable=false)
	private Integer idProveedor;

	@Column(nullable=false, length=10)
	private String cedula;

	@Column(name="ciudad_nacimiento", nullable=false, length=50)
	private String ciudadNacimiento;

	@Column(nullable=false, length=50)
	private String direccion;

	@Column(length=30)
	private String email;

	@Column(nullable=false, length=20)
	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento", nullable=false)
	private Date fechaNacimiento;

	@Column(name="limite_credito", nullable=false)
	private double limiteCredito;

	@Column(nullable=false, length=50)
	private String nombre;

	@Column(nullable=false, length=15)
	private String telefono;

	@Column(name="tipo_proveedor", nullable=false, length=10)
	private String tipoProveedor;

	//bi-directional many-to-one association to Factura
	@OneToMany(mappedBy="proveedor")
	private List<Factura> facturas;

	public Proveedor() {
	}

	public Integer getIdProveedor() {
		return this.idProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCiudadNacimiento() {
		return this.ciudadNacimiento;
	}

	public void setCiudadNacimiento(String ciudadNacimiento) {
		this.ciudadNacimiento = ciudadNacimiento;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public double getLimiteCredito() {
		return this.limiteCredito;
	}

	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipoProveedor() {
		return this.tipoProveedor;
	}

	public void setTipoProveedor(String tipoProveedor) {
		this.tipoProveedor = tipoProveedor;
	}

	public List<Factura> getFacturas() {
		return this.facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public Factura addFactura(Factura factura) {
		getFacturas().add(factura);
		factura.setProveedor(this);

		return factura;
	}

	public Factura removeFactura(Factura factura) {
		getFacturas().remove(factura);
		factura.setProveedor(null);

		return factura;
	}

}