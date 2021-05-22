package jogoGourmet.motor;

import javax.swing.JOptionPane;
import jogoGourmet.entidade.No;

public class Gerenciar {

	private No pai;

	public void inicio() {
		pai = new No(null, "massa", null, null);
		pai.setSim(new No("Lasanha", null, null, null));
		pai.setNao(new No("Bolo de Chocolate", null, null, null));
		iniciarPartida(pai);
	}

	public void iniciarPartida(No noAtual) {
		int ok = JOptionPane.showConfirmDialog(null, "Pense em um prato que voc� gosta", "Jogo Gourmet",
				JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null);
		if (ok == 0) {
			boolean opcao = JOptionPane.showConfirmDialog(null,
					"O prato que voc� pensou � " + noAtual.getCaracteristica() + "?", "Jogo Gourmet",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION;
			if (opcao) {
				if (noAtual.getSim().getNome() != null) {
					perguntarNome(noAtual.getSim());
				} else if (noAtual.getSim().getCaracteristica() != null) {
					perguntarCaracteristica(noAtual.getSim());
				}

			} else {
				if (noAtual.getNao().getCaracteristica() != null && noAtual.getNao().getNome() == null) {
					perguntarCaracteristica(noAtual.getNao());
				} else {
					if (noAtual.getNao().getNome() != null) {
						perguntarNome(noAtual.getNao());
					} else {
						perguntarNome(noAtual.getSim());
					}
				}
			}
		}
	}

	public boolean naoExistePrato(No noAtual) {
		return noAtual.getSim() == null && noAtual.getNao() == null;
	}

	public void perguntarCaracteristica(No noAtual) {
		boolean resposta = JOptionPane.showConfirmDialog(null,
				"O prato que voc� pensou � " + noAtual.getCaracteristica() + "?", "Confirm",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
		if (resposta) {
			perguntarNome(noAtual.getSim());
		} else {
			if (naoExistePrato(noAtual)) {
				outroPrato(noAtual);
			} else if (noAtual.getNao() != null && noAtual.getNao().getCaracteristica() != null) {
				perguntarCaracteristica(noAtual.getNao());
			} else if (noAtual.getNao() == null || noAtual.getNao().getNome() == null) {
				outroPrato(noAtual);
			} else if (noAtual.getNao() != null) {
				perguntarNome(noAtual.getNao());
			}
		}
	}

	public void perguntarNome(No noAtual) {
		boolean resposta = JOptionPane.showConfirmDialog(null, "O prato que voc� pensou � " + noAtual.getNome() + "?",
				"Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
		if (resposta) {
			acerto();
		} else {
			if (naoExistePrato(noAtual)) {
				outroPrato(noAtual);
			} else {
				perguntarNome(noAtual.getNao());
			}
		}
	}

	public void outroPrato(No noAtual) {
		String caracteristica = null;
		String prato = JOptionPane.showInputDialog(null, "Qual prato voc� pensou?", "Desisto",
				JOptionPane.QUESTION_MESSAGE);
		if (prato == null) {
			System.exit(0);
		} else if (prato.trim().equals("")) {
			outroPrato(noAtual);
		} else {
			caracteristica = JOptionPane.showInputDialog(null, prato + " � ______ mas " + noAtual.getNome() + " n�o.",
					"Complete", JOptionPane.QUESTION_MESSAGE);
			if (caracteristica == null) {
				System.exit(0);
			} else if (caracteristica.trim().equals("")) {
				outroPrato(noAtual);
			} else {
				noAtual=proximaRodada(noAtual, prato, caracteristica);
				iniciarPartida(noAtual);
			}
		}

	}

	public No proximaRodada(No noAtual, String prato, String caracteristica) {
		String nomePai = noAtual.getNome();
		String tipo= noAtual.getCaracteristica();
		No aux= new No(nomePai,null,null,null);
		noAtual.setCaracteristica(tipo);
		noAtual.setNome(null);
		noAtual.setSim(new No(null, caracteristica, null, null));
		noAtual.getSim().setSim(new No(prato, null, null, null));
		noAtual.getSim().setNao(aux);
		//pai.setNao(noAtual);
		//buscaPratos(aux,noAtual);
		//iniciarPartida(noAtual);
		return noAtual;
	}
	
	public No buscaPratos(No noAtual, No prox) {
		No aux=noAtual;
		if(pai==noAtual) {
			return pai;
		}else if(noAtual.getSim()==prox) {
			return noAtual.getSim();
		}else {
			return noAtual.getNao();
		}
	}

	public void acerto() {
		JOptionPane.showMessageDialog(null, "Acertei de novo", "Jogo Gourmet", JOptionPane.INFORMATION_MESSAGE);
		iniciarPartida(pai);
	}

}