package com.financeiro.negocio;

import com.financeiro.dao.UsuarioDao;
import com.financeiro.modelo.Usuario;

import java.sql.SQLException;

public class NegocioUsuario { //validação do usuário
    private UsuarioDao usuarioDao;

    public NegocioUsuario() {
        this.usuarioDao = new UsuarioDao();
    }

    public Usuario cadastrar(Usuario usuario) throws Exception {
        if(usuarioDao.consultarPorLogin(usuario.getLogin())!=null){
            throw new Exception("Usuário já cadastrado");
        }
        return usuarioDao.inserir(usuario);
    }

}
