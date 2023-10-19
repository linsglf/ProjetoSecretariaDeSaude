package DAO;

import DTO.EspecialidadeDTO;
import gui.util.Alerts;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadeDAO implements DAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    List<EspecialidadeDTO> listaEspecialidade = new ArrayList<>();

    public List<EspecialidadeDTO> listarEspecialidade() {

        String sql = "SELECT * FROM especialidades;";

        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                EspecialidadeDTO objEspecialidadeDTO = new EspecialidadeDTO();
                objEspecialidadeDTO.setIdEspecialidade(rs.getInt("id_especialidade"));
                objEspecialidadeDTO.setNomeEspecialidade(rs.getString("nome_especialidade"));

                listaEspecialidade.add(objEspecialidadeDTO);
            }
        } catch (SQLException e) {
            Alerts.showAlert("Error", null, "EspecialidadeDAO Listar:" + e.getMessage(), Alert.AlertType.ERROR);
        }

        return listaEspecialidade;
    }

    @Override
    public void insert(Object obj) {
        if (obj instanceof EspecialidadeDTO) {
            EspecialidadeDTO objEspecialidadeDTO = (EspecialidadeDTO) obj;
            String sql = "INSERT INTO especialidades (nome_especialidade) VALUES (?);";

            conn = new ConexaoDAO().conectaBD();

            try {
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, objEspecialidadeDTO.getNomeEspecialidade());

                pstm.execute();
                pstm.close();
            } catch (Exception e) {

                Alerts.showAlert("Error", null,"EspecialidadeDAO Cadastrar" + e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            Alerts.showAlert("Error", null,"Objeto Inválido", Alert.AlertType.ERROR);
        }
    }

    @Override
    public void update(Object obj) {
        if (obj instanceof EspecialidadeDTO) {
            EspecialidadeDTO objEspecialidadeDTO = (EspecialidadeDTO) obj;
            String sql = "UPDATE especialidades SET nome_especialidade = ? WHERE id_especialidade = ?;";

            conn = new ConexaoDAO().conectaBD();

            try {
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, objEspecialidadeDTO.getNomeEspecialidade());
                pstm.setInt(2, objEspecialidadeDTO.getIdEspecialidade());

                pstm.execute();
                pstm.close();
            } catch (Exception e) {
                Alerts.showAlert("Error", null,"EspecialidadeDAO Update" + e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            Alerts.showAlert("Error", null,"Objeto Inválido", Alert.AlertType.ERROR);
        }
    }

    @Override
    public void delete(Object obj) {
        if (obj instanceof EspecialidadeDTO) {
            EspecialidadeDTO objEspecialidadeDTO = (EspecialidadeDTO) obj;
            String sql = "DELETE FROM especialidades WHERE id_especialidade = ?;";

            conn = new ConexaoDAO().conectaBD();

            try {
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, objEspecialidadeDTO.getIdEspecialidade());

                pstm.execute();
                pstm.close();
            } catch (SQLException e) {
                Alerts.showAlert("Error", null,"EspecialidadeDAO Delete" + e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            Alerts.showAlert("Error", null,"Objeto Inválido", Alert.AlertType.ERROR);
        }
    }
}
