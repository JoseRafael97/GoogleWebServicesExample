package br.edu.ifpb.mt.exceptions;

/**
 * Exeception utilizada quando não existe nenhum mensageiro na área requisitada
 * @author rafaelfeitosa
 *
 */
public class NaoExisteMensageiro extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NaoExisteMensageiro(String msg) {
		super(msg);
	}

}
