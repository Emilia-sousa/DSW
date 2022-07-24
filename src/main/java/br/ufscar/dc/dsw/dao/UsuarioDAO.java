package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.ResultSet;

import br.ufscar.dc.dsw.domain.Agencia;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Usuario;

public class UsuarioDAO extends GenericDAO{

    public Usuario getByEmail(String email){
        Usuario usuario = null;

        String sql = "SELECT * FROM Usuario LEFT JOIN Agencia ON Usuario.id = Agencia.id LEFT JOIN Cliente ON Usuario.id = Cliente.id WHERE Usuario.email = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                String tipo = resultSet.getString("tipo");

                if (tipo.equals("agencia")) {
                    String cnpj = resultSet.getString("cnpj");
                    String descricao = resultSet.getString("descricao");
                    usuario = new Agencia(id, nome, email, senha, tipo, cnpj, descricao);
                } else if (tipo.equals("cliente")) {
                    String cpf = resultSet.getString("cpf");
                    String telefone = resultSet.getString("telefone");
                    String sexo = resultSet.getString("sexo");
                    Timestamp dataNascimento = resultSet.getTimestamp("data_nascimento");
                    usuario = new Cliente(id, nome, email, senha, tipo, cpf, telefone, sexo, dataNascimento);
                } else {
                    usuario = new Usuario(id, nome, email, senha, tipo);
                }
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return usuario;
    }

}