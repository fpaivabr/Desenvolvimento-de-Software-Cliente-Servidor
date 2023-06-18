package com.financeiro.dao;

import com.financeiro.conexao.Conexao;
import com.financeiro.modelo.Item;
import com.financeiro.modelo.Lancamento;
import com.financeiro.modelo.Usuario;
import com.financeiro.modelo.enumeradores.Mes;
import com.financeiro.modelo.enumeradores.TipoLancamento;
import com.financeiro.modelo.enumeradores.TipoRecorrencia;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LancamentoDao {

    //INSERIR OU CRIAR
    public Lancamento inserir(Lancamento lancamento) throws SQLException {
        String sql = "INSERT INTO Lancamento (tipo_lancamento, valor_lancamento, data_lancamento, " +
                "tipo_recorrencia, codigo_item, id_usuario) VALUES (?,?,?,?,?,?)";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, lancamento.getTipoLancamento().toString());
            stmt.setDouble(2, lancamento.getValor());
            stmt.setDate(3, Date.valueOf(lancamento.getDataLancamento()));
            stmt.setString(4, lancamento.getTipoRecorrencia().toString());
            stmt.setInt(5, lancamento.getItem().getCodigo());
            stmt.setInt(6, lancamento.getUsuario().getId());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    lancamento.setIdLancamento(generatedKeys.getInt(1));
                }
            }
        }
        return lancamento;
    }

    //ATUALIZAR
    public void atualizar(Lancamento lancamento) throws SQLException {

        String sql = "UPDATE Lancamento SET valor_lancamento = ?, data_lancamento = ?, " +
                "tipo_recorrencia = ? WHERE id_lancamento = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, lancamento.getValor());
            stmt.setDate(2, Date.valueOf(lancamento.getDataLancamento()));
            stmt.setString(3, lancamento.getTipoRecorrencia().toString());
            stmt.setInt(4, lancamento.getIdLancamento());

            stmt.executeUpdate();
        }
    }

    // EXCLUIR OU DELETAR
    public void excluir(Lancamento lancamento) throws SQLException {
        String sql = "DELETE FROM Lancamento WHERE id_lancamento = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, lancamento.getIdLancamento());

            stmt.executeUpdate();
        }
    }

    //CONSULTAR
    public Lancamento consultar(int idLancamento) throws SQLException {
        String sql = "SELECT L.*, I.descricao_item "+
                     " FROM Lancamento L inner join Item I on I.codigo_item = L.codigo_item"+
                     " WHERE L.id_lancamento = ?";
        Lancamento lancamento = null;
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idLancamento);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    lancamento = getLancamento(resultSet);
                }

            }
        }
        return lancamento;
    }

    public List<Lancamento> listarLancamentos(int idUsuario, LocalDate dataInicio, LocalDate dataFim) throws SQLException {
        String sql = "SELECT L.*, I.descricao_item FROM Lancamento L inner join Item I on I.codigo_item = L.codigo_item"+
                " WHERE L.id_usuario = ? AND data_lancamento BETWEEN ? AND ?";
        List<Lancamento> lancamentos = new ArrayList<>();
        try (Connection connection = Conexao.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            stmt.setDate(2, Date.valueOf(dataInicio));
            stmt.setDate(3, Date.valueOf(dataFim));

            try (ResultSet resultSet = stmt.executeQuery()) {

                while (resultSet.next()) {
                    Lancamento lancamento = getLancamento(resultSet);
                    lancamentos.add(lancamento);
                }
            }
        }
        return lancamentos;
    }


    public List<Lancamento> consultarLancamentosMes (int idUsuario, Integer ano, Mes mes) throws SQLException {
        String sql = "SELECT L.*, I.descricao_item FROM Lancamento L "+
                "inner join Item I on I.codigo_item = L.codigo_item"+
                " WHERE L.id_usuario = ? AND year(data_lancamento) = ? and month(data_lancamento) = ?";
        List<Lancamento> lancamentos = new ArrayList<>();
        try (Connection connection = Conexao.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, ano);
            stmt.setInt(3, mes.ordinal()+ 1);

            try (ResultSet resultSet = stmt.executeQuery()) {

                while (resultSet.next()) {
                    Lancamento lancamento = getLancamento(resultSet);
                    lancamentos.add(lancamento);
                }
            }
        }
        return lancamentos;
    }

    private Lancamento getLancamento(ResultSet resultSet) throws SQLException {
        Lancamento lancamento = new Lancamento();
        lancamento.setIdLancamento(resultSet.getInt("id_lancamento"));
        lancamento.setTipoLancamento(Enum.valueOf(TipoLancamento.class, resultSet.getString("tipo_lancamento")));
        lancamento.setValor(resultSet.getDouble("valor_lancamento"));
        lancamento.setDataLancamento(resultSet.getDate("data_lancamento").toLocalDate());
        lancamento.setTipoRecorrencia(Enum.valueOf(TipoRecorrencia.class, resultSet.getString("tipo_recorrencia")));

        Item item = new Item();
        item.setCodigo(resultSet.getInt("codigo_item"));
        item.setDescricao(resultSet.getString("descricao_item"));

        lancamento.setItem(item);

        Usuario usuario = new Usuario();
        usuario.setId(resultSet.getInt("id_usuario"));
        lancamento.setUsuario(usuario);

        return lancamento;
    }
}