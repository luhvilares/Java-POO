package projetofloricultura;


public class Vaso extends Flor{
         @Override
 	public String opcao() {
		return "Vaso de cerâmica";
	}
	public Vaso(String tipo, int valor, String cor) {
		super(tipo, valor, cor);
		this.tamanho = "Médio";
	}
    
}
