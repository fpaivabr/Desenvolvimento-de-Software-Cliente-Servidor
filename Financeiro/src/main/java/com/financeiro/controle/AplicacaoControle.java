package com.financeiro.controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import com.financeiro.negocio.NegocioUsuario;
import com.financeiro.modelo.Usuario;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;

public class AplicacaoControle {

    Usuario usuarioLogado = null;

    @FXML
    private Button btnCadastrarUsuario;

    @FXML
    private Button btnIncluirPesquisa;

    @FXML
    private Label lblCancelarCadastroUsuario;

    @FXML
    private Label lblConfirmarSenha;

    @FXML
    private Label lblErroLogin;

    @FXML
    private Label lblErroSenha;

    @FXML
    private Label lblLogin;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblSenha;

    @FXML
    private Tab tabCadastro;

    @FXML
    private Tab tabComparacao;

    @FXML
    private Tab tabGastos;

    @FXML
    private Tab tabItens;

    @FXML
    private Tab tabLancamentos;

    @FXML
    private Tab tabLogin;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabTelaInicial;

    @FXML
    private TextField txtConfirmarSenha;

    @FXML
    private TextField txtLoginCadastro;

    @FXML
    private TextField txtLoginLogin;

    @FXML
    private TextField txtNome;

    @FXML
    private Label txtNovoCadastroUsuario;

    @FXML
    private TextField txtSenhaCadastro;

    @FXML
    private TextField txtSenhaLogin;
    @FXML
    void btnIncluirPesquisaOnAction(ActionEvent event) {

    }
    @FXML
    void onClickTxtNovoCadastroUsuario(MouseEvent event) {
        this.tabCadastro.setDisable(false);
        this.tabPane.getSelectionModel().select(this.tabCadastro);// SELECIONA QUAL A TELA ATUAL
        this.tabLogin.setDisable(true);

    }

    @FXML
    void onClickCancelarCadastroUsuario(MouseEvent event) {
        this.voltarTelaLogin();

    }
    @FXML
    void onClickLogin(MouseEvent event) throws SQLException {
        this.lblErroLogin.setText("");
        this.lblErroSenha.setText("");

        NegocioUsuario negocioUsuario = new NegocioUsuario();
        usuarioLogado = negocioUsuario.consultar(txtLoginLogin.getText());
        if (usuarioLogado == null){
            this.lblErroLogin.setText("Usuário não cadastrado");
            return;
        }
        if (!usuarioLogado.getSenha().equals(txtSenhaLogin.getText())){
            this.lblErroSenha.setText("Senha Inválida");
            return;
        }

        this.tabLogin.setDisable(true);
        this.tabCadastro.setDisable(true);
        this.tabTelaInicial.setDisable(false);
        this.tabItens.setDisable(false);
        this.tabLancamentos.setDisable(false);
        this.tabGastos.setDisable(false);
        this.tabComparacao.setDisable(false);
        this.tabPane.getSelectionModel().select(this.tabTelaInicial);// SELECIONA QUAL A TELA ATUAL

    }

    @FXML
    void onClickCadastrarUsuario(MouseEvent event) throws Exception {
        limpeErrosCadastroUsuario();

        NegocioUsuario negocioUsuario = new NegocioUsuario();
        Usuario usuario = new Usuario();
        usuario.setLogin(txtLoginCadastro.getText());
        usuario.setNome(txtNome.getText());
        usuario.setSenha(txtSenhaCadastro.getText());
        boolean cadastroInconsistente = false;
        if(txtSenhaCadastro!=null && !txtSenhaCadastro.getText().equals(txtConfirmarSenha.getText())){
            lblSenha.setText("Senhas não correspondem!");
            cadastroInconsistente = true;
        }
        if(txtNome.getText().equals("")){
            lblNome.setText("Nome obrigatório");
            cadastroInconsistente=true;
        }
        if(txtLoginCadastro.getText().equals("")){
            lblLogin.setText("Login obrigatório");
            cadastroInconsistente=true;
        }
        if(txtSenhaCadastro.getText().equals("")){
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
                limpeErrosCadastroUsuario();
                voltarTelaLogin();
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

    private void voltarTelaLogin(){
        this.tabCadastro.setDisable(true);
        this.tabPane.getSelectionModel().select(this.tabLogin);
        this.tabLogin.setDisable(false);
    }

}
