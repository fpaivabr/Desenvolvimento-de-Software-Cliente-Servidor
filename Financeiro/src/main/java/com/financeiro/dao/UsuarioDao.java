package com.financeiro.dao;

import com.financeiro.conexao.Conexao;
import com.financeiro.modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
                    Integer id = resultSet.getInt("id_usuario");
                    usuario = new Usuario();
                    usuario.setNome(nome);
                    usuario.setLogin(login);
                    usuario.setSenha(senha);
                    usuario.setId(id);
                }
            }
        }
        return usuario;
    }

}


