package jogoGourmet.motor;

import jogoGourmet.entidade.No;

public class Iniciar {
	
	private No pai;
	
	
	public void inicio() {
		pai= new No(null, "massa", null, null);
		pai.setSim(new No("Lasanha",null,null,null));
		pai.setNao(new No("Bolo de Chocolate",null,null,null));
		new Gerenciar().iniciarPartida(pai);
	}
	
	
	

}
