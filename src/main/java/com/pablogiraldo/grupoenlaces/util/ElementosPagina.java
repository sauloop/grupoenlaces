package com.pablogiraldo.grupoenlaces.util;

public class ElementosPagina {

	private int numero;
	private boolean actual;

	public ElementosPagina() {
	}

	public ElementosPagina(int numero, boolean actual) {

		this.numero = numero;
		this.actual = actual;
	}

	public int getNumero() {
		return numero;
	}

	public boolean isActual() {
		return actual;
	}

}
