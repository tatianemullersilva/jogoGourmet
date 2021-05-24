package jogoGourmet.partida;


import jogoGourmet.entidade.No;
import jogoGourmet.entidade.Prato;

public class Gerenciar {

	private No noRaiz;  
    private No noAtual;
    
    
    public No noAtual(){ 
		return this.noAtual; 
	}
	
	public void setNoAtual(No noAtual) {
		this.noAtual = noAtual;
	}
	
	public No getnoRaiz() {
		return noRaiz;
	}
	
	
    public Gerenciar() {
    	noRaiz = new No(null, null, null, new Prato("Lasanha", "Massa"));
    	noRaiz.setNao(new No(noRaiz, null, null, new Prato("Bolo de Chocolate", null)));
    	noAtual = noRaiz;
    }
    
    public void inserirNao(Prato prato){
		No no = new No();
		no.setPrato(prato);
		no.setPai(noAtual);
		noAtual.setNao(no);
    } 
    
   
    public void inserirPenultimoNo(Prato prato){
		No node = new No();
		node.setPrato(prato);
    	node.setNao(noAtual);
		node.setPai(noAtual.getPai());
		
		noAtual.getPai().setNao(node);
		noAtual.setPai(node);
    } 
    
    
    public void inserirSim(Prato prato){
    	No no = new No();
		no.setPrato(prato);
		no.setPai(noAtual);
		noAtual.setSim(no);
    } 
    
   
    public Prato proximoPrato(Prato prato, String resposta) {
		No no = new No();
    	no = proximoNoQueTemPrato(noRaiz, prato);
		if (no == null)
			return null;
    	return resposta.equals("nao") ?
    				no.getNao() == null ? null : no.getNao().getPrato() :
    				no.getSim() == null ? null : no.getSim().getPrato();
		
	}
    
    public No proximoNoQueTemPrato(No no, Prato prato) {
    	if (no.getPrato().equals(prato))
    		return no;
    	No noSim = no.getSim() == null ? null : no.getSim();
    	No noNao = no.getNao() == null ? null : no.getNao();
    	if (noSim != null) {
    		if (noSim.getPrato().equals(prato)) {
				return noSim;
    		} else {
    			return proximoNoQueTemPrato(noSim, prato);
    		}
    	}
		if (noNao != null) {
			if (noNao.getPrato().equals(prato)) {
				return noNao;
			} else {
				return proximoNoQueTemPrato(noNao, prato);
			}
		}
		return buscarNoNaoNoPai(no, no.getPai(), prato);
	}

	private No buscarNoNaoNoPai(No no, No noPai, Prato prato) {
    	No noPaiSim = noPai.getNao() == null ? null : noPai.getNao();
		if (noPai.getSim() != null && no.equals(noPai.getSim())) {
			if (noPaiSim != null && noPaiSim.getPrato().equals(prato)) {
				return noPaiSim;
			} else if (noPaiSim != null) {
				return proximoNoQueTemPrato(noPaiSim, prato);
			}
		}
		return buscarNoNaoNoPai(noPai, noPai.getPai(), prato);
	}

	

	

}
