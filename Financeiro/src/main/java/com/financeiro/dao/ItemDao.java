package com.financeiro.dao;

import com.financeiro.conexao.Conexao;
import com.financeiro.modelo.Item;
import com.financeiro.modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDao {

    //INSERIR OU CRIAR
    public Item inserir(Item item) throws SQLException {
        String sql = "INSERT INTO Item (id_usuario, descricao_item) VALUES (?,?)";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, item.getUsuario().getId());
            stmt.setString(2, item.getDescricao());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setCodigo(generatedKeys.getInt(1));
                }
            }
        }
        return item;
    }

    //ATUALIZAR
    public void atualizar(Item item) throws SQLException {
        String sql = "UPDATE Item SET descricao_item = ? WHERE codigo_item = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, item.getDescricao());
            stmt.setInt(2, item.getCodigo());

            stmt.executeUpdate();
        }
    }

    // EXCLUIR OU DELETAR
    public void excluir(Item item) throws SQLException {
        String sql = "DELETE FROM Item WHERE codigo_item = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, item.getCodigo());

            stmt.executeUpdate();
        }
    }

    //CONSULTAR
    public Item consultar(int codigoItem) throws SQLException {
        String sql = "SELECT * FROM Item WHERE codigo_item = ?";
        Item item = null;
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, codigoItem);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    item = getItem(resultSet, codigoItem);
                }

            }
        }
        return item;
    }
    public Item consultar(String descricao, int idUsuario) throws SQLException {
        String sql = "SELECT * FROM Item WHERE descricao_item = ? AND id_usuario = ?";
        Item item = null;
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, descricao);
            stmt.setInt(2, idUsuario);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    item = getItem(resultSet, resultSet.getInt("codigo_item"));
                }

            }
        }
        return item;
    }

    public List<Item> listarItens(int idUsuario, String descricao) throws SQLException {
        String sql = "SELECT * FROM Item WHERE id_usuario = ? ";
        if(descricao!=null && !descricao.isEmpty()){
            sql += " AND UPPER(descricao_item) LIKE UPPER(?)";
        }

        List<Item> itens = new ArrayList<>();
        try (Connection connection = Conexao.getConnection()){
             PreparedStatement stmt = connection.prepareStatement(sql);
             stmt.setInt(1, idUsuario);

            if(descricao!=null && !descricao.isEmpty()){
                stmt.setString(2, descricao);
            }

             try (ResultSet resultSet = stmt.executeQuery()) {

                 while (resultSet.next()) {
                     Item item = getItem(resultSet, resultSet.getInt("codigo_item"));
                     itens.add(item);
                 }
             }
        }
        return itens;
    }


    public Item getItem(ResultSet resultSet, int codigoItem) throws SQLException {

        String descricaoItem = resultSet.getString("descricao_item");
        Item item = new Item();
        item.setCodigo(codigoItem);
        item.setDescricao(descricaoItem);
        Usuario usuario = new Usuario();
        usuario.setId(resultSet.getInt("id_usuario"));
        item.setUsuario(usuario);

        return item;

    }

}



