package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Compra;


public class CompraDAO extends GenericDAO {
    public void insert(Compra compra){
        String sql = "INSERT INTO Compra (id_cliente, id_pacote) VALUES (?, ?)";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, compra.getCliente().getId());
            statement.setLong(2, compra.getPacoteTuristico().getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Boolean verificaCompraClienteByIdPacote(Cliente cliente, String idPacote){
        String sql = "SELECT * FROM Compra WHERE id_pacote = " + idPacote + " AND id_cliente = ?";

        Boolean comprou = false;

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, cliente.getId());

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                comprou = true;
            }
            resultSet.close();
            statement.close();
            conn.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return comprou;
    }
}
