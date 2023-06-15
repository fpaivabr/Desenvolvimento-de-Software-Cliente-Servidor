package com.financeiro.dao;

import com.financeiro.conexao.Conexao;
import com.financeiro.modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    //INSERIR OU CRIAR
    public Usuario inserir(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuario (nome_usuario, login_usuario, senha_usuario) VALUES (?,?,?)";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, usuario.getSenha());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    usuario.setId(generatedKeys.getInt(1));
                }
            }
        }
        return usuario;
    }
/*
    //ATUALIZAR
    public boolean atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE Usuario SET nome = ? WHERE id = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setInt(2, usuario.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    // EXCLUIR OU DELETAR
    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM Usuario WHERE id = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
*/

    //CONSULTAR
    public Usuario consultarPorLogin(String loginInformado) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE login_usuario = ?";
        Usuario usuario = null;
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, loginInformado);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    String nome = resultSet.getString("nome_usuario");
                    String login = resultSet.getString("login_usuario");
                    String senha = resultSet.getString("senha_usuario");
                    usuario = new Usuario();
                    usuario.setNome(nome);
                    usuario.setLogin(login);
                    usuario.setSenha(senha);
                }
            }
        }
        return usuario;
    }

/*
    public List<Usuario> listarTodos() throws SQLException {
        String sql = "SELECT * FROM Usuario";
        List<Usuario> clientes = new ArrayList<>();
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                Usuario usuario = new Usuario(id, nome);
                usuario.setTelefones(new TelefoneDao().listarPorClienteId(id));
                clientes.add(usuario);
            }
        }
        return clientes;
    }
*/

}


