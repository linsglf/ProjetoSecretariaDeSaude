package DTO;

public class MedicoDTO extends DTO {

	protected String nomeMedico;
	protected String CRM;
	protected String municipio;
	protected String statusCRM;
	protected String especialidade;
	protected String areaAtuacao;

	public String getNomeMedico() {
		return nomeMedico;
	}

	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}

	public String getCRM() {
		return CRM;
	}

	public void setCRM(String CRM) {
		this.CRM = CRM;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getStatusCRM() {
		return statusCRM;
	}

	public void setStatusCRM(String statusCRM) {
		this.statusCRM = statusCRM;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}
}
