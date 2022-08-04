package projetofloricultura;

import java.io.Serializable;

public abstract class Flor implements Serializable {
    	private static final long serialVersionUID = 1L;
	private final String tipo;
	private final int valor;
	private final String cor;
	protected String tamanho;
	
	public Flor(String tipo, int valor, String cor) {
		this.tipo = tipo;
		this.valor = valor;
		this.cor = cor;
	}
        @Override
	public String toString() {
		String retorno = "";
		retorno += "Tipo: "     + this.tipo     + "\n";
		retorno += "Valor: "    + this.valor    + " reais\n";
		retorno += "Cor: "     + this.cor     + "\n";
		retorno += "Tamanho: "  + this.tamanho  + "\n";
		retorno += "Opção selecionada: "  + opcao()        + "\n";
		return retorno;
	}
	public abstract String opcao();
}
