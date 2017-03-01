package br.edu.ifpb.mt.model;

/**
 * Modelo que representa um mensageiro do Ajude mais
 * @author rafaelfeitosa
 *
 */
public class Mensageiro {
	
	
	private Endereco endereco;

	
	public Mensageiro(Endereco endereco) {
		super();
		this.endereco = endereco;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	
	
}
