package DAO;

import DTO.Medico;
import DTO.MedicoDTO;
import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicoDAO {
	
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	ArrayList<Medico> lista = new ArrayList<>();
	public void cadastrarMedico(Medico objMedicoDTO) {
		String sql = "INSERT INTO medico (nome_medico, crm_medico, municipio_medico, crm_status_medico, especialidade_medico, area_medico) VALUES (?,?,?,?,?,?);";

		conn = new ConexaoDAO().conectaBD();

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, objMedicoDTO.getNomeMedico());
			pstm.setString(2, objMedicoDTO.getCRM());
			pstm.setString(3, objMedicoDTO.getMunicipio());
			pstm.setString(4, objMedicoDTO.getStatusCRM());
			pstm.setString(5, objMedicoDTO.getEspecialidade());
			pstm.setString(6, objMedicoDTO.getAreaAtuacao());

			pstm.execute();
			pstm.close();
		} catch (Exception e) {

			Alerts.showAlert("Error", null,"MedicoDAO Cadastrar" + e.getMessage(), AlertType.ERROR);
		}
	}

	public ArrayList<Medico> listarMedico() {
		String sql = "SELECT * FROM medico;";

		conn = new ConexaoDAO().conectaBD();

		try{
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()){
				MedicoDTO objMedicoDTO = new MedicoDTO();
				objMedicoDTO.setIdMedico(rs.getInt("id_medico"));
				objMedicoDTO.setNomeMedico(rs.getString("nome_medico"));
				objMedicoDTO.setCRM(rs.getString("crm_medico"));
				objMedicoDTO.setMunicipio(rs.getString("municipio_medico"));
				objMedicoDTO.setStatusCRM(rs.getString("crm_status_medico"));
				objMedicoDTO.setEspecialidade(rs.getString("especialidade_medico"));
				objMedicoDTO.setAreaAtuacao(rs.getString("area_medico"));

				lista.add(objMedicoDTO);
			}
		} catch (Exception e) {
			Alerts.showAlert("Error", null,"MedicoDAO Pesquisar:" + e.getMessage(), AlertType.ERROR);
		}
		return lista;
	}

	public void updateFuncionario(Medico objMedicoDTO) {
		String sql = "UPDATE medico SET nome_medico = ?, crm_medico = ?, municipio_medico = ?, crm_status_medico = ?, especialidade_medico = ?, area_medico = ? WHERE id_medico = ?;";

		conn = new ConexaoDAO().conectaBD();

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, objMedicoDTO.getNomeMedico());
			pstm.setString(2, objMedicoDTO.getCRM());
			pstm.setString(3, objMedicoDTO.getMunicipio());
			pstm.setString(4, objMedicoDTO.getStatusCRM());
			pstm.setString(5, objMedicoDTO.getEspecialidade());
			pstm.setString(6, objMedicoDTO.getAreaAtuacao());
			pstm.setInt(7, objMedicoDTO.getIdMedico());

			pstm.execute();
			pstm.close();
		} catch (Exception e) {
			Alerts.showAlert("Error", null,"MedicoDAO Update" + e.getMessage(), AlertType.ERROR);
		}
	}

	public void deleteFuncionario(Medico objMedicoDTO) {
		String sql = "DELETE FROM medico WHERE id_medico = ?;";

		conn = new ConexaoDAO().conectaBD();

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, objMedicoDTO.getIdMedico());

			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			Alerts.showAlert("Error", null,"MedicoDAO Delete" + e.getMessage(), AlertType.ERROR);
		}
	}

	public ArrayList<Medico> pesquisarMedico(String pesquisa, String municipio, String statusCRM, String especialidade, String areaAtuacao) {
		if (municipio == null) {
			municipio = "";
		}
		if (statusCRM == null) {
			statusCRM = "";
		}
		if (especialidade == null) {
			especialidade = "";
		}
		if (areaAtuacao == null) {
			areaAtuacao = "";
		}

		String sql =
				"SELECT * FROM medico WHERE (nome_medico LIKE ? OR crm_medico LIKE ?)" +
				" AND (municipio_medico LIKE ?)" +
				" AND (especialidade_medico LIKE ?)" +
				" AND (area_medico LIKE ?)" +
				" AND (crm_status_medico = ? OR ? = '');";

		conn = new ConexaoDAO().conectaBD();

		try{
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + pesquisa + "%");
			pstm.setString(2, "%" + pesquisa + "%");
			pstm.setString(3, "%" + municipio + "%");
			pstm.setString(4, "%" + especialidade + "%");
			pstm.setString(5, "%" + areaAtuacao + "%");
			pstm.setString(6, statusCRM);
			pstm.setString(7, statusCRM);


			rs = pstm.executeQuery();

			while (rs.next()) {
				MedicoDTO objMedicoDTO = new MedicoDTO();
				objMedicoDTO.setIdMedico(rs.getInt("id_medico"));
				objMedicoDTO.setNomeMedico(rs.getString("nome_medico"));
				objMedicoDTO.setCRM(rs.getString("crm_medico"));
				objMedicoDTO.setMunicipio(rs.getString("municipio_medico"));
				objMedicoDTO.setStatusCRM(rs.getString("crm_status_medico"));
				objMedicoDTO.setEspecialidade(rs.getString("especialidade_medico"));
				objMedicoDTO.setAreaAtuacao(rs.getString("area_medico"));

				lista.add(objMedicoDTO);
			}
		} catch (SQLException e) {
			Alerts.showAlert("Error", null,"MedicoDAO Pesquisar:" + e.getMessage(), AlertType.ERROR);
		}

		return lista;
	}
}
