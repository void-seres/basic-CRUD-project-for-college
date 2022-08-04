package org.grupo2.model.enums;

public enum TipoSangue {
	AP("A+") , AN("A-") ,
	BP("B+") , BN("B-") ,
	ABP("AB+"), ABN("AB-"),
	OP("O+") , ON("O-") ;
	
	private final String tipo;
	TipoSangue(String tipo) {
		this.tipo= tipo;
	}
	public String getTipo() {
		return tipo;
	}
}
