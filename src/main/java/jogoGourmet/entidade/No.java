package jogoGourmet.entidade;

public class No {

	private String nome;
	private String caracteristica;
	private No sim;
	private No nao;

	public No(String nome, String caracteristica, No sim, No nao) {
		super();
		this.nome = nome;
		this.caracteristica = caracteristica;
		this.sim = sim;
		this.nao = nao;
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
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

}
