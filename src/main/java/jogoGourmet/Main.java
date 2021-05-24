package jogoGourmet;


import jogoGourmet.entidade.Prato;
import jogoGourmet.partida.Gerenciar;
import jogoGourmet.partida.Iniciar;

public class Main {

	public static void main(String[] args) { 
		Gerenciar gerenciar = new Gerenciar();
        Prato prato = gerenciar.getnoRaiz().getPrato();
        Prato ultimoPrato = gerenciar.getnoRaiz().getNao().getPrato(); 
        new Iniciar().iniciarPartida(gerenciar, prato, ultimoPrato);
   }

}
