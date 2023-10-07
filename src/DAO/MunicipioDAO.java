package DAO;

import DAO.ConexaoDAO;
import DTO.Medico;
import DTO.MunicipioDTO;
import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MunicipioDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    List<MunicipioDTO> listaMunicipios = new ArrayList<>();

    public List<MunicipioDTO> listarMunicipios() {

        String sql = "SELECT * FROM municipios;";

        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                MunicipioDTO objMunicipioDTO = new MunicipioDTO();
                objMunicipioDTO.setNomeMunicipio(rs.getString("nome_municipio"));
                objMunicipioDTO.setIdMunicipio(rs.getInt("id_municipio"));

                listaMunicipios.add(objMunicipioDTO);
            }
        } catch (SQLException e) {
            Alerts.showAlert("Error", null, "MunicipioDAO Listar:" + e.getMessage(), AlertType.ERROR);
        }

        return listaMunicipios;
    }

    public void cadastrarMunicipio(MunicipioDTO objMunicipioDTO) {
        String sql = "INSERT INTO municipios (nome_municipio) VALUES (?);";

        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objMunicipioDTO.getNomeMunicipio());

            pstm.execute();
            pstm.close();
        } catch (Exception e) {

            Alerts.showAlert("Error", null,"MunicipioDAO Cadastrar" + e.getMessage(), AlertType.ERROR);
        }
    }

    public void updateMunicipio(MunicipioDTO objMunicipioDTO) {
        String sql = "UPDATE municipios SET nome_municipio = ? WHERE id_municipio = ?;";

        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objMunicipioDTO.getNomeMunicipio());
            pstm.setInt(2, objMunicipioDTO.getIdMunicipio());

            pstm.execute();
            pstm.close();
        } catch (Exception e) {
            Alerts.showAlert("Error", null,"MunicipioDAO Update" + e.getMessage(), AlertType.ERROR);
        }
    }

    public void deleteMunicipio(MunicipioDTO objMunicipioDTO) {
        String sql = "DELETE FROM municipios WHERE id_municipio = ?;";

        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, objMunicipioDTO.getIdMunicipio());

            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            Alerts.showAlert("Error", null,"MunicipioDAO Delete" + e.getMessage(), AlertType.ERROR);
        }
    }
}
