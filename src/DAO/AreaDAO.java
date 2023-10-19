package DAO;

import DTO.AreaDTO;
import gui.util.Alerts;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaDAO implements DAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    List<AreaDTO> listaAreas = new ArrayList<>();

    public List<AreaDTO> listarAreas() {

        String sql = "SELECT * FROM areas;";

        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                AreaDTO objAreaDTO = new AreaDTO();
                objAreaDTO.setId(rs.getInt("id_area"));
                objAreaDTO.setNomeArea(rs.getString("nome_area"));

                listaAreas.add(objAreaDTO);
            }
        } catch (SQLException e) {
            Alerts.showAlert("Error", null, "AreaDAO Listar:" + e.getMessage(), Alert.AlertType.ERROR);
        }

        return listaAreas;
    }

    @Override
    public void insert(Object obj) {
        if (obj instanceof AreaDTO) {
            AreaDTO objAreaDTO = (AreaDTO) obj;
            String sql = "INSERT INTO areas (nome_area) VALUES (?);";

            conn = new ConexaoDAO().conectaBD();

            try {
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, objAreaDTO.getNomeArea());

                pstm.execute();
                pstm.close();
            } catch (Exception e) {
                Alerts.showAlert("Error", null,"AreaDAO Cadastrar" + e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            Alerts.showAlert("Error", null,"Objeto Inválido", Alert.AlertType.ERROR);
        }
    }

    @Override
    public void update(Object obj) {
        if (obj instanceof AreaDTO) {
            AreaDTO objAreaDTO = (AreaDTO) obj;
            String sql = "UPDATE areas SET nome_area = ? WHERE id_area = ?;";

            conn = new ConexaoDAO().conectaBD();

            try {
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, objAreaDTO.getNomeArea());
                pstm.setInt(2, objAreaDTO.getId());

                pstm.execute();
                pstm.close();
            } catch (Exception e) {
                Alerts.showAlert("Error", null,"AreaDAO Update" + e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            Alerts.showAlert("Error", null,"Objeto Inválido", Alert.AlertType.ERROR);
        }
    }

    @Override
    public void delete(Object obj) {
        if (obj instanceof AreaDTO) {
            AreaDTO objAreaDTO = (AreaDTO) obj;
            String sql = "DELETE FROM areas WHERE id_area = ?;";

            conn = new ConexaoDAO().conectaBD();

            try {
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, objAreaDTO.getId());

                pstm.execute();
                pstm.close();
            } catch (Exception e) {
                Alerts.showAlert("Error", null,"AreaDAO Delete" + e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            Alerts.showAlert("Error", null,"Objeto Inválido", Alert.AlertType.ERROR);
        }
    }
}
