package jogoGourmet.partida;

import javax.swing.JOptionPane;

import jogoGourmet.entidade.No;
import jogoGourmet.entidade.Prato;

public class Iniciar {
	
	String resposta = "";
	Prato pratoAtual = new Prato();
	Prato pratoRetorno = new Prato();
	
	
	public void iniciarPartida(Gerenciar gerenciar, Prato prato, Prato ultimoPrato){
		pratoRetorno = gerenciar.getnoRaiz().getPrato();
		JOptionPane.showMessageDialog(null, "Pense em um prato que você gosta", "Jogo Gourmet", JOptionPane.INFORMATION_MESSAGE);
		Prato palpitePrato= palpitarTipo(gerenciar, prato,ultimoPrato);
		palpitarPrato(gerenciar, palpitePrato, ultimoPrato);
		if(JOptionPane.showConfirmDialog(null, "Continuar jogando?", "Jogo Gourmet", JOptionPane.YES_NO_OPTION) == 1) {
			System.exit(1);
		}
		iniciarPartida(gerenciar, gerenciar.getnoRaiz().getPrato(), ultimoPrato);
		
	}	

	private Prato palpitarTipo(Gerenciar gerenciar, Prato prato, Prato ultimoPrato) {
		pratoAtual = prato; 
		if (prato.getTipo() == null)
			return ultimoPrato;
		boolean tipoPalpitado = JOptionPane.showConfirmDialog(null, "O prato que você pensou é "+ prato.getTipo() +"?",
				"Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
		resposta = tipoPalpitado ? "sim" : "nao";
		Prato proximoPrato = gerenciar.proximoPrato(prato, resposta);
		if (tipoPalpitado) {
			pratoRetorno = prato;
		}
		return proximoPrato == null ? pratoRetorno : palpitarTipo(gerenciar, proximoPrato, ultimoPrato);
	}

	private void palpitarPrato(Gerenciar gerenciar, Prato prato, Prato ultimoPrato) {
		boolean resposta = JOptionPane.showConfirmDialog(null, "O prato que você pensou é "+ prato.getNome() +"?",
				"Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
		if (resposta) {
			JOptionPane.showMessageDialog(null, "Acertei de novo!!", "Jogo Gourmet", JOptionPane.INFORMATION_MESSAGE);
		} else 
			addPrato(gerenciar, pratoAtual, ultimoPrato);
	}

	private void addPrato(Gerenciar gerenciar, Prato prato, Prato ultimoPrato) {
		Prato novoPrato = new Prato();	
		novoPrato.setNome(
				JOptionPane.showInputDialog(null, "Qual prato você pensou?", "Desisto", JOptionPane.QUESTION_MESSAGE));
		novoPrato.setTipo(
				JOptionPane.showInputDialog(null,novoPrato.getNome() + " não ______ mas " + prato.getNome() + " não.", 
						"Complete", JOptionPane.QUESTION_MESSAGE));
		No noEncontrado = gerenciar.proximoNoQueTemPrato(gerenciar.getnoRaiz(), pratoAtual);
		gerenciar.setNoAtual(noEncontrado != null ? noEncontrado : gerenciar.noAtual());
		if (resposta.equals("nao"))
			if (noEncontrado.getPrato().getNome().equals(ultimoPrato.getNome()))
				gerenciar.inserirPenultimoNo(novoPrato);
			else
				gerenciar.inserirNao(novoPrato);
		else {
			gerenciar.inserirSim(novoPrato);
		}
	}
}