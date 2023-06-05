package com.financeiro.controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import com.financeiro.negocio.NegocioUsuario;
import com.financeiro.modelo.Usuario;

public class AplicacaoControle {

    @FXML
    private Button btnCadastrarUsuario;

    @FXML
    private Button btnIncluirPesquisa;

    @FXML
    private Label lblConfirmarSenha;

    @FXML
    private Label lblLogin;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblSenha;

    @FXML
    private Tab tabCadastro;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabPesquisa;

    @FXML
    private TextField txtConfirmarSenha;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtUsernamePesquisa;

    @FXML
    private TextField txtUsernamePesquisa1;

    @FXML
    void btnIncluirPesquisaOnAction(ActionEvent event) {

    }
    @FXML
    void onClickCadastrarUsuario(MouseEvent event) throws Exception {
        limpeErros();
        NegocioUsuario negocioUsuario = new NegocioUsuario();
        Usuario usuario = new Usuario();
        usuario.setLogin(txtLogin.getText());
        usuario.setNome(txtNome.getText());
        usuario.setSenha(txtSenha.getText());
        boolean cadastroInconsistente = false;
        if(txtSenha!=null && !txtSenha.getText().equals(txtConfirmarSenha.getText())){
            lblSenha.setText("Senhas não correspondem!");
            cadastroInconsistente = true;
        }
        if(txtNome.getText().equals("")){
            lblNome.setText("Nome obrigatório");
            cadastroInconsistente=true;
        }
        if(txtLogin.getText().equals("")){
            lblLogin.setText("Login obrigatório");
            cadastroInconsistente=true;
        }
        if(txtSenha.getText().equals("")){
            lblSenha.setText("Senha obrigatória");
            cadastroInconsistente=true;
        }
        if(txtConfirmarSenha.getText().equals("")){
            lblConfirmarSenha.setText("Confirmar senha obrigatório");
            cadastroInconsistente=true;
        }
        if(!cadastroInconsistente){
            try {
                negocioUsuario.cadastrar(usuario);
                limpeErros();
            } catch (Exception ex){
                lblLogin.setText(ex.getMessage());
                cadastroInconsistente = true;
            }
        }
    }

    private void limpeErrosCadastroUsuario(){
        lblSenha.setText("");
        lblLogin.setText("");
        lblNome.setText("");
        lblConfirmarSenha.setText("");
    }
    private void limpeCamposCadastroUsuario(){

    }

}
