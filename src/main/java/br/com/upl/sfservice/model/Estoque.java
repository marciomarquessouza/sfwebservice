package br.com.upl.sfservice.model;

public class Estoque {

	private String material;
	private String materialDes;
	private String unidade;
	private String filial;
	private String tipoMaterial;
	private String deposito;
	private String lote;
	private String estoqueDisponivel;
	private String estoqueQualidade;
	private String estoqueTransito;
	private String estoqueBloqueado;
	private String base;
	
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getMaterialDes() {
		return materialDes;
	}
	public void setMaterialDes(String materialDes) {
		this.materialDes = materialDes;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public String getFilial() {
		return filial;
	}
	public void setFilial(String filial) {
		this.filial = filial;
	}
	public String getTipoMaterial() {
		return tipoMaterial;
	}
	public void setTipoMaterial(String tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}
	public String getDeposito() {
		return deposito;
	}
	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public String getEstoqueDisponivel() {
		return estoqueDisponivel;
	}
	public void setEstoqueDisponivel(String estoqueDisponivel) {
		this.estoqueDisponivel = estoqueDisponivel;
	}
	public String getEstoqueQualidade() {
		return estoqueQualidade;
	}
	public void setEstoqueQualidade(String estoqueQualidade) {
		this.estoqueQualidade = estoqueQualidade;
	}
	public String getEstoqueTransito() {
		return estoqueTransito;
	}
	public void setEstoqueTransito(String estoqueTransito) {
		this.estoqueTransito = estoqueTransito;
	}
	public String getEstoqueBloqueado() {
		return estoqueBloqueado;
	}
	public void setEstoqueBloqueado(String estoqueBloqueado) {
		this.estoqueBloqueado = estoqueBloqueado;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	
}
