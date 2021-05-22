package jogoGourmet.motor;

import javax.swing.JOptionPane;
import jogoGourmet.entidade.No;

public class Gerenciar {

	private No pai;
	private boolean respostaGeral; 

	public void inicio() {
		pai = new No(null, "massa", null, null);
		pai.setSim(new No("Lasanha", null, null, null));
		pai.setNao(new No("Bolo de Chocolate", null, null, null));
		iniciarPartida(pai);
	}

	public void iniciarPartida(No noAtual) {
		int ok = JOptionPane.showConfirmDialog(null, "Pense em um prato que você gosta", "Jogo Gourmet",
				JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null);
		if (ok == 0) {
			boolean opcao = JOptionPane.showConfirmDialog(null,
					"O prato que você pensou é " + noAtual.getCaracteristica() + "?", "Jogo Gourmet",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION;
			if (opcao) {
				if (noAtual.getSim().getCaracteristica() == null) {
					perguntarNome(noAtual.getSim());
				} else {
					perguntarCaracteristica(noAtual.getSim());
				}
			} else {
				if (noAtual.getNao() == null) {
					perguntarNome(noAtual);
				} else {
					if (noAtual.getNao().getCaracteristica() == null) {
						perguntarNome(noAtual.getNao());
					} else {
						perguntarCaracteristica(noAtual.getNao());
					}
				}
			}
		}
	}

	public boolean naoExistePrato(No noAtual) {
		return noAtual.getSim() == null && noAtual.getNao() == null;
	}

	public void perguntarCaracteristica(No noAtual) {
		respostaGeral= JOptionPane.showConfirmDialog(null,
				"O prato que você pensou é " + noAtual.getCaracteristica() + "?", "Confirm",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
		if (respostaGeral && noAtual.getSim() !=null && noAtual.getSim().getNome()!=null) {
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
		boolean resposta = JOptionPane.showConfirmDialog(null, "O prato que você pensou é " + noAtual.getNome() + "?",
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
		String prato = JOptionPane.showInputDialog(null, "Qual prato você pensou?", "Desisto",
				JOptionPane.QUESTION_MESSAGE);
		if (prato == null) {
			System.exit(0);
		} else if (prato.trim().equals("")) {
			outroPrato(noAtual);
		} else {
			caracteristica = JOptionPane.showInputDialog(null, prato + " é ______ mas " + noAtual.getNome() + " não.",
					"Complete", JOptionPane.QUESTION_MESSAGE);
			if (caracteristica == null) {
				System.exit(0);
			} else if (caracteristica.trim().equals("")) {
				outroPrato(noAtual);
			} else {
				inserirNo(noAtual, prato, caracteristica,respostaGeral);
				iniciarPartida(pai);
			}
		}

	}

	public void inserirNo(No noAtual, String prato, String caracteristica,boolean ladoSim) {
		if(noAtual.getCaracteristica()==null) {
		noAtual.setCaracteristica(caracteristica);
		}
		if(ladoSim) {
		noAtual.setSim(new No(prato, null, null, null));
		}else {
			noAtual.setNao(new No(prato, null, null, null));
		}
	}

	public void acerto() {
		JOptionPane.showMessageDialog(null, "Acertei de novo", "Jogo Gourmet", JOptionPane.INFORMATION_MESSAGE);
		iniciarPartida(pai);
	}

}
