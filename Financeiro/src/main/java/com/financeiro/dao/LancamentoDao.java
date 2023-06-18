package com.financeiro.dao;

import com.financeiro.conexao.Conexao;
import com.financeiro.modelo.Item;
import com.financeiro.modelo.Lancamento;
import com.financeiro.modelo.Usuario;
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
            stmt.setString(4, lancamento.getTipoRecorrenciarecorrencia().toString());
            stmt.setInt(5, lancamento.getItem().getCodigo());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    lancamento.setIdLancamento(generatedKeys.getInt("id_lancamento"));
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
            stmt.setString(3, lancamento.getTipoRecorrenciarecorrencia().toString());
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
        String sql = "SELECT * FROM Lancamento WHERE id_lancamento = ?";
        Lancamento lancamento = null;
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, lancamento.getIdLancamento());

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    lancamento = getLancamento(resultSet);
                }

            }
        }
        return lancamento;
    }

    public List<Lancamento> listarLancamentos(int idUsuario, LocalDate dataInicio, LocalDate dataFim) throws SQLException {
        String sql = "SELECT * FROM Lancamento WHERE id_usuario = ? AND data_lancamento BETWEEN ? AND ?";
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

    private Lancamento getLancamento(ResultSet resultSet) throws SQLException {
        Lancamento lancamento = new Lancamento();
        lancamento.setIdLancamento(resultSet.getInt("id_lancamento"));
        lancamento.setTipoLancamento(Enum.valueOf(TipoLancamento.class, resultSet.getString("tipo_lancamento")));
        lancamento.setValor(resultSet.getDouble("valor_lancamento"));
        lancamento.setDataLancamento(resultSet.getDate("data_lancamento").toLocalDate());
        lancamento.setTipoRecorrencia(Enum.valueOf(TipoRecorrencia.class, resultSet.getString("tipo_recorrencia")));

        Item item = new Item();
        item.setCodigo(resultSet.getInt("codigo_item"));
        lancamento.setItem(item);

        Usuario usuario = new Usuario();
        usuario.setId(resultSet.getInt("id_usuario"));
        lancamento.setUsuario(usuario);

        return lancamento;
    }

}



