package DTO;

public class MedicoDTO extends Medico {

	private int idMedico;

	@Override
	public int getIdMedico() {
		return idMedico;
	}

	@Override
	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}
}
