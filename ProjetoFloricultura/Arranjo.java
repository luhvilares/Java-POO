package projetofloricultura;

public class Arranjo extends Flor{
    	private static final long serialVersionUID = 1L;

            @Override
	public String opcao() {
		return "Arranjo";
	}
	public Arranjo(String tipo, int valor, String cor) {
		super(tipo, valor, cor);
		this.tamanho = "Grande";
	}
}
    
