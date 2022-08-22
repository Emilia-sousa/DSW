package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Foto;

public class FotoDAO extends GenericDAO {

    public void insert(Foto foto) {
        String sql = "INSERT INTO Foto (id_pacote, url) VALUES (?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement = conn.prepareStatement(sql);
            statement.setLong(1, foto.getIdPacote());
            statement.setString(2, foto.getUrl());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Foto> getAllById(Long idPacote) {

        List<Foto> listaFoto = new ArrayList<>();

        String sql = "SELECT * FROM Foto WHERE id_pacote=" + idPacote;

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id_pacote");
                String url = resultSet.getString("url");
    
                Foto foto = new Foto(id, url);
                listaFoto.add(foto);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaFoto;
    }

}