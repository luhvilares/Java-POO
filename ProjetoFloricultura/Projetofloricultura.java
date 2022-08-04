package projetofloricultura;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Projetofloricultura {

  	private final ArrayList<Flor> flores;


	public Projetofloricultura( ) {
		this.flores = new ArrayList<>();
	}

	public void adicionaFlor(Flor mani) {
		this.flores.add(mani);
	}

	public void listarFlores() {
            flores.forEach((mani) ->
              {
                  System.out.println(mani.toString());
              });
		System.out.println("Total = " + this.flores.size() + " pedidos listados com sucesso!\n");
	}
	
	public void excluirFlor(Flor mani) {
		if (this.flores.contains(mani)) {
			this.flores.remove(mani);
			System.out.println("[Flor " + mani.toString() + "excluída com sucesso!]\n");
		}
		else
			System.out.println("Flor inexistente!\n");
	}

	public void excluirFlores() {
		flores.clear();
		System.out.println("Flores excluídas com sucesso!\n");
	}
	public void gravarFlores()  {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream (new FileOutputStream("c:\\temp\\flores.dat"));
			for(Flor mani:flores) {
				outputStream.writeObject(mani);
			}
		}catch (FileNotFoundException ex) {
		}catch (IOException ex) {
		}finally{
			try {
				if (outputStream != null ) {
					outputStream.flush();
					outputStream.close();
				}
			}catch (IOException ex) {
			}
		}
	}
	public void recuperarFlores() {
		ObjectInputStream inputStream = null;
		try {
			inputStream	= new ObjectInputStream (new FileInputStream ("c:\\temp\\flores.dat"));
			Object obj = null;
			while((obj = inputStream.readObject ()) != null) {
				if (obj instanceof Arranjo)  
					this.flores.add((Arranjo)obj);
				else if (obj instanceof Vaso)  
					this.flores.add((Vaso)obj);
			}
		}catch (EOFException ex) {     // when EOF is reached
			System.out.println ("End of file reached");
		}catch (ClassNotFoundException | IOException ex) {
		}finally{
			try {
				if (inputStream != null ) {
					inputStream.close();
					System.out.println("Flores recuperadas com sucesso!\n");
				}
			}catch (IOException ex) {
			}
		}
	}


	public static void main(String[] args) {
		Projetofloricultura flores  = new Projetofloricultura();

		Arranjo astromelia    = new Arranjo("Astromélia",    90, "Branca");
		Arranjo rosa = new Arranjo("Rosa", 100, "Vermelha");
		Vaso  orquidea      = new Vaso ("Orquídea",  150, "Azul");
		Vaso  margarida     = new Vaso("Margarida", 80, "Branca e amarela");
		flores.adicionaFlor(astromelia);
		flores.adicionaFlor(rosa);
		flores.adicionaFlor(orquidea);
		flores.adicionaFlor(margarida);
		flores.listarFlores();
		flores.gravarFlores();
		flores.excluirFlor(rosa);
		flores.listarFlores();
		flores.excluirFlores();
		flores.listarFlores();
		flores.recuperarFlores();
		flores.listarFlores();
	}

}