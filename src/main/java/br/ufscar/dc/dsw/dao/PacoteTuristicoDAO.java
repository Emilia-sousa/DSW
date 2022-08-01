package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Agencia;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Destino;
import br.ufscar.dc.dsw.domain.Foto;
import br.ufscar.dc.dsw.domain.PacoteTuristico;

public class PacoteTuristicoDAO extends GenericDAO {
    public void insert(PacoteTuristico pacoteTuristico) {
        String sql = "INSERT INTO PacoteTuristico (cnpj_agencia, destino_cidade, destino_estado, destino_pais, data_partida, duracao_dias, valor, descricao, qtd_foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, pacoteTuristico.getAgencia().getCnpj());
            statement.setString(2, pacoteTuristico.getDestino().getCidade());
            statement.setString(3, pacoteTuristico.getDestino().getEstado());
            statement.setString(4, pacoteTuristico.getDestino().getPais());
            statement.setTimestamp(5, pacoteTuristico.getDataPartida());
            statement.setInt(6, pacoteTuristico.getDuracaoDias());
            statement.setFloat(7, pacoteTuristico.getValor());
            statement.setString(8, pacoteTuristico.getDescricao());
            statement.setInt(9, pacoteTuristico.getQtdFotos());
            statement.executeUpdate();

            if (pacoteTuristico.getQtdFotos() > 0) {
                Long idPacote = getLastInsertId();

                FotoDAO fotoDao = new FotoDAO();

                for(Foto foto : pacoteTuristico.getFotos()) {
                    foto.setIdPacote(idPacote);
                    fotoDao.insert(foto);
                }
            }

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long getLastInsertId() {
        String sql = "SELECT MAX(id) as id FROM PacoteTuristico";

        Long id = null;

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                id = resultSet.getLong("id");
            }

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return id;
    }

    public PacoteTuristico getById(String idPacote) {
        String sql = "SELECT * FROM PacoteTuristico p, Usuario u, Agencia a WHERE p.id = " + idPacote + " AND p.cnpj_agencia = a.cnpj AND u.id = a.id";

        PacoteTuristico pacote = null;

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long idUsuario = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String tipo = resultSet.getString("tipo");
                String cnpj = resultSet.getString("cnpj");
                String descricaoAgencia = resultSet.getString("descricao");
                Agencia agenciaDomain = new Agencia(idUsuario, nome, email, senha, tipo, cnpj, descricaoAgencia);

                Long id = resultSet.getLong("id");
                String destinoCidade = resultSet.getString("destino_cidade");
                String destinoEstado = resultSet.getString("destino_estado");
                String destinoPais = resultSet.getString("destino_pais");
                Timestamp dataPartidaPacote = resultSet.getTimestamp("data_partida");
                Integer duracaoDias = resultSet.getInt("duracao_dias");
                Float valor = resultSet.getFloat("valor");
                String descricao = resultSet.getString("descricao");
                Integer qtdFotos = resultSet.getInt("qtd_foto");

                Destino destinoModel = new Destino(destinoCidade, destinoEstado, destinoPais);

                List<Foto> fotos = new FotoDAO().getAllById(id);

                pacote = new PacoteTuristico(id, agenciaDomain, destinoModel, dataPartidaPacote, duracaoDias, valor, descricao, qtdFotos, fotos);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pacote;
    }

    public List<PacoteTuristico> getByAgencia(Agencia agencia, Boolean vigente) {
        String sql = "SELECT * FROM PacoteTuristico WHERE cnpj_agencia = " + agencia.getCnpj();

        if (vigente) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = new java.util.Date();
            String currentDate = dateFormat.format(date);
            sql = sql + " AND DATE(data_partida) > '" + currentDate + "'";
        }

        List<PacoteTuristico> listaPacotes =  new ArrayList<>();


        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String destinoCidade = resultSet.getString("destino_cidade");
                String destinoEstado = resultSet.getString("destino_estado");
                String destinoPais = resultSet.getString("destino_pais");
                Timestamp dataPartidaPacote = resultSet.getTimestamp("data_partida");
                Integer duracaoDias = resultSet.getInt("duracao_dias");
                Float valor = resultSet.getFloat("valor");
                String descricao = resultSet.getString("descricao");
                Integer qtdFotos = resultSet.getInt("qtd_foto");

                Destino destinoModel = new Destino(destinoCidade, destinoEstado, destinoPais);

                List<Foto> fotos = new FotoDAO().getAllById(id);

                PacoteTuristico pacote = new PacoteTuristico(id, agencia, destinoModel, dataPartidaPacote, duracaoDias, valor, descricao, qtdFotos, fotos);

                listaPacotes.add(pacote);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaPacotes;
    }

    public List<PacoteTuristico> getByCliente(Cliente cliente) {
        String sql = "SELECT * FROM PacoteTuristico p, Compra c WHERE p.id = c.id_pacote AND c.id_cliente =" + cliente.getId();

        List<PacoteTuristico> listaPacotes =  new ArrayList<>();

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String destinoCidade = resultSet.getString("destino_cidade");
                String destinoEstado = resultSet.getString("destino_estado");
                String destinoPais = resultSet.getString("destino_pais");
                Timestamp dataPartidaPacote = resultSet.getTimestamp("data_partida");
                Integer duracaoDias = resultSet.getInt("duracao_dias");
                Float valor = resultSet.getFloat("valor");
                String descricao = resultSet.getString("descricao");
                Integer qtdFotos = resultSet.getInt("qtd_foto");

                Destino destinoModel = new Destino(destinoCidade, destinoEstado, destinoPais);

                List<Foto> fotos = new FotoDAO().getAllById(id);

                PacoteTuristico pacote = new PacoteTuristico(id, null, destinoModel, dataPartidaPacote, duracaoDias, valor, descricao, qtdFotos, fotos);

                listaPacotes.add(pacote);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaPacotes;
    }

    public List<PacoteTuristico> getAll(String destino, String agencia, Timestamp dataPartida) {
        List<PacoteTuristico> listaPacotesTuristicos = new ArrayList<>();

        String sql = "SELECT * FROM PacoteTuristico p, Agencia a, Usuario u WHERE (p.cnpj_agencia = a.cnpj AND a.id = u.id)";

        if (!destino.isEmpty() && destino != null) {
            sql = sql + " AND (p.destino_cidade LIKE '%" + destino + "%' OR p.destino_estado LIKE '%" + destino + "%' OR p.destino_pais LIKE '%" + destino + "%')";
        }

        if (!agencia.isEmpty() && agencia != null) {
            sql = sql +" AND u.nome LIKE '%" + agencia + "%'";
        }

        if (dataPartida != null) {
            sql = sql + " AND p.data_partida = '" + dataPartida + "'";
        }

        sql = sql + " ORDER BY p.id";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long idUsuario = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String tipo = resultSet.getString("tipo");
                String cnpj = resultSet.getString("cnpj");
                String descricaoAgencia = resultSet.getString("descricao");
                Agencia agenciaDomain = new Agencia(idUsuario, nome, email, senha, tipo, cnpj, descricaoAgencia);

                

                Long id = resultSet.getLong("id");
                String destinoCidade = resultSet.getString("destino_cidade");
                String destinoEstado = resultSet.getString("destino_estado");
                String destinoPais = resultSet.getString("destino_pais");
                Timestamp dataPartidaPacote = resultSet.getTimestamp("data_partida");
                Integer duracaoDias = resultSet.getInt("duracao_dias");
                Float valor = resultSet.getFloat("valor");
                String descricao = resultSet.getString("descricao");
                Integer qtdFotos = resultSet.getInt("qtd_foto");

                Destino destinoModel = new Destino(destinoCidade, destinoEstado, destinoPais);

                List<Foto> fotos = new FotoDAO().getAllById(id);

                PacoteTuristico pacoteTuristico = new PacoteTuristico(id, agenciaDomain, destinoModel, dataPartidaPacote, duracaoDias, valor, descricao, qtdFotos, fotos);
                listaPacotesTuristicos.add(pacoteTuristico);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaPacotesTuristicos;
    }
}