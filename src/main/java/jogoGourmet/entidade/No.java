package jogoGourmet.entidade;

public class No {

	private No pai;
	private No sim;
	private No nao;
	private Prato prato;

	public No() {
		super();
	}

	public No(No pai, No sim, No nao, Prato prato) {
		super();
		this.pai = pai;
		this.sim = sim;
		this.nao = nao;
		this.prato = prato;
	}



	public No getPai() {
		return pai;
	}

	public void setPai(No pai) {
		this.pai = pai;
	}

	public No getSim() {
		return sim;
	}

	public void setSim(No sim) {
		this.sim = sim;
	}

	public No getNao() {
		return nao;
	}

	public void setNao(No nao) {
		this.nao = nao;
	}

	public Prato getPrato() {
		return prato;
	}

	public void setPrato(Prato prato) {
		this.prato = prato;
	}

	

}
