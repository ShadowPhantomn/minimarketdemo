package minimarketdemo.model.cajero.dtos;

import java.util.Date;

import minimarketdemo.model.core.entities.Proveedor;

public class DTOCajFactura {

	private int idFactura;
	private Date fechaFactura;
	private Date fechaVencimiento;
	private String tipoFactura;
	private double valorFactura;
	private int idproveedor;
	
	public DTOCajFactura(int idFactura, Date fechaFactura, Date fechaVencimiento, String tipoFactura,
			double valorFactura, int idproveedor) {
		this.idFactura = idFactura;
		this.fechaFactura = fechaFactura;
		this.fechaVencimiento = fechaVencimiento;
		this.tipoFactura = tipoFactura;
		this.valorFactura = valorFactura;
		this.idproveedor = idproveedor;
	}

	public DTOCajFactura(int idFactura, Date fechaFactura, Date fechaVencimiento, String tipoFactura,
			double valorFactura) {
		this.idFactura = idFactura;
		this.fechaFactura = fechaFactura;
		this.fechaVencimiento = fechaVencimiento;
		this.tipoFactura = tipoFactura;
		this.valorFactura = valorFactura;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public Date getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(String tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public double getValorFactura() {
		return valorFactura;
	}

	public void setValorFactura(double valorFactura) {
		this.valorFactura = valorFactura;
	}

	public int getIdproveedor() {
		return idproveedor;
	}

	public void setIdproveedor(int idproveedor) {
		this.idproveedor = idproveedor;
	}

	
	
}
