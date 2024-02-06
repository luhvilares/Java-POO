package projetofloricultura;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Floricultura {
    	private ArrayList<Flor>flores;

	public Floricultura() {
		this.flores = new ArrayList<>();
	}
	public String[] leValores (String [] dadosIn){
		String [] dadosOut = new String [dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog  ("Insira " + dadosIn[i]+ ": ");

		return dadosOut;
	}

	public Arranjo leArranjo (){

		String [] valores = new String [3];
		String [] nomeVal = {"Tipo da flor", "Preço", "Cor da flor"};
		valores = leValores (nomeVal);

		int valor = this.retornaInteiro(valores[1]);

		Arranjo arranjo = new Arranjo (valores[0],valor,valores[2]);
		return arranjo;
	}

	public Vaso leVaso (){

		String [] valores = new String [3];
		String [] nomeVal = {"Tipo da flor", "Preço", "Cor da flor"};
		valores = leValores (nomeVal);

		int valor = this.retornaInteiro(valores[1]);

		Vaso vaso = new Vaso (valores[0],valor,valores[2]);
		return vaso;
	}

	private boolean intValido(String s) {
		try {
			Integer.parseInt(s); // Método estático, que tenta tranformar uma string em inteiro
			return true;
		} catch (NumberFormatException e) { // Não conseguiu tranformar em inteiro e gera erro
			return false;
		}
	}
	public int retornaInteiro(String entrada) { // retorna um valor inteiro
		int numInt;

		//Enquanto não for possível converter o valor de entrada para inteiro, permanece no loop
		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
		}
		return Integer.parseInt(entrada);
	}

	public void salvaFlores (ArrayList<Flor> flor){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream 
					(new FileOutputStream("c:\\temp\\floricultura.dados"));
			for (int i=0; i < flores.size(); i++)
				outputStream.writeObject(flor.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Impossível criar arquivo!");
		} catch (IOException ex) {
		} finally {  //Close the ObjectOutputStream
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
			}
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Flor> recuperaFlor (){
		ArrayList<Flor> floresTemp = new ArrayList<>();

		ObjectInputStream inputStream = null;

		try {	
			inputStream = new ObjectInputStream
					(new FileInputStream("c:\\temp\\floricultura.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Flor) {
					floresTemp.add((Flor) obj);
				}   
			}          
		} catch (EOFException ex) { // when EOF is reached
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException | IOException ex) {
		} finally {  //Close the ObjectInputStream
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
			}
			return floresTemp;
		}
	}

	public void menuFloricultura (){

		String menu = "";
		String entrada;
		int    opc1, opc2;

		do {
			menu = "SISTEMA ADMINISTRATIVO DA FLORICULTURA LG\n" +
					"Opções:\n" + 
					"1. Entrar Flores\n" +
					"2. Exibir Flores\n" +
					"3. Limpar Flores\n" +
					"4. Gravar Flores\n" +
					"5. Recuperar Flores\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:// Entrar dados
				menu = "Entrada de opções\n" +
						"Opções:\n" + 
						"1. Arranjo\n" +
						"2. Vaso\n";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2){
				case 1: flores.add((Flor)leArranjo());
				break;
				case 2: flores.add((Flor)leVaso());
				break;
				default: 
					JOptionPane.showMessageDialog(null,"Opção para entrada NÃO escolhida!");
				}

				break;
			case 2: // Exibir dados
				if (flores.isEmpty()) {
					JOptionPane.showMessageDialog(null,"Entre com as flores primeiramente");
					break;
				}
				String dados = "";
				for (int i=0; i < flores.size(); i++)	{
					dados += flores.get(i).toString() + "---------------\n";
				}
				JOptionPane.showMessageDialog(null,dados);
				break;
			case 3: // Limpar Dados
				if (flores.isEmpty()) {
					JOptionPane.showMessageDialog(null,"Entre com as flores, primeiramente");
					break;
				}
				flores.clear();
				JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
				break;
			case 4: // Grava Dados
				if (flores.isEmpty()) {
					JOptionPane.showMessageDialog(null,"Entre com as flores, primeiramente");
					break;
				}
				salvaFlores(flores);
				JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
				break;
			case 5: // Recupera Dados
				flores = recuperaFlor();
				if (flores.isEmpty()) {
					JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
					break;
				}
				JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null,"O sistema da floricultura foi desligado.");
				break;
			}
		} while (opc1 != 9);
	}


	public static void main (String [] args){

		Floricultura flor = new Floricultura ();
		flor.menuFloricultura();

	}

}
    

