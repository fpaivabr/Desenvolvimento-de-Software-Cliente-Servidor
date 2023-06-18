package com.financeiro.controle;

import com.financeiro.modelo.enumeradores.TipoLancamento;
import com.financeiro.modelo.enumeradores.TipoRecorrencia;
import com.financeiro.negocio.NegocioItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import com.financeiro.negocio.NegocioUsuario;
import com.financeiro.modelo.Usuario;
import com.financeiro.modelo.Item;
import com.financeiro.modelo.Lancamento;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.function.UnaryOperator;

public class AplicacaoControle {

//******************************
// Atributos: Campos de controle
//******************************

    Usuario usuarioLogado = null;
    Item itemSelecionado = null;
    Lancamento lancamentoSelecionado = null;


//**************************
// Atributos: Campos de tela
//**************************

    @FXML
    private Button btnCadastrarItem;

    @FXML
    private Button btnCadastrarLancamento;

    @FXML
    private Button btnCadastrarUsuario;

    @FXML
    private Button btnConsultarComparacao;

    @FXML
    private Button btnConsultarGastos;

    @FXML
    private Button btnExcluirItem;

    @FXML
    private Button btnIncluirPesquisa;

    @FXML
    private Button btnPesquisarItem;

    @FXML
    private ComboBox<Item> cmbItemLancamento;

    @FXML
    private ComboBox<?> cmbMes1Comparacao;

    @FXML
    private ComboBox<?> cmbMes2Comparacao;

    @FXML
    private ComboBox<TipoRecorrencia> cmbRecorrenciaLancamento;

    @FXML
    private ComboBox<TipoLancamento> cmbTipoLancamento;

    @FXML
    private Label lblCancelarCadastroUsuario;

    @FXML
    private Label lblConfirmarSenha;

    @FXML
    private Label lblErroDescricaoItem;

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
    private DatePicker pickerDataFimGastos;

    @FXML
    private DatePicker pickerDataInicioGastos;

    @FXML
    private DatePicker pickerDataLancamento;

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
    private TableView<?> tabelaComparacao;

    @FXML
    private TableView<?> tabelaGastos;

    @FXML
    private TableView<Item> tabelaItens;

    @FXML
    private TableColumn<?, ?> tableColumnComparacaoItem;

    @FXML
    private TableColumn<?, ?> tableColumnComparacaoMes1;

    @FXML
    private TableColumn<?, ?> tableColumnComparacaoMes2;

    @FXML
    private TableColumn<?, ?> tableColumnDiferenca;

    @FXML
    private TableColumn<?, ?> tableColumnGastosItem;

    @FXML
    private TableColumn<?, ?> tableColumnGastosRecorrencia;

    @FXML
    private TableColumn<?, ?> tableColumnGastosTipo;

    @FXML
    private TableColumn<?, ?> tableColumnGastosValor;

    @FXML
    private TableColumn<Item, String> tableColumnItem;

    @FXML
    private TableColumn<?, ?> tableColunmGastosData;

/* TABELAS DUPLICADAS
    @FXML
    private TableView<?> tabelaItens1;

    @FXML
    private TableView<?> tabelaItens11;

    @FXML
    private TableColumn<?, ?> tableColumnCodigo1;

    @FXML
    private TableColumn<?, ?> tableColumnCodigo11;

    @FXML
    private TableColumn<?, ?> tableColumnItem1;

    @FXML
    private TableColumn<?, ?> tableColumnItem11;
*/
    @FXML
    private TextField txtAnoComparacao;

    @FXML
    private TextField txtConfirmarSenha;

    @FXML
    private TextField txtDescricaoItem;

    @FXML
    private TextField txtLoginCadastro;

    @FXML
    private TextField txtLoginLogin;

    @FXML
    private TextField txtNome;

    @FXML
    private Label txtNovoCadastroUsuario;

    @FXML
    private TextField txtPesquisaItem;

    @FXML
    private TextField txtSenhaCadastro;

    @FXML
    private TextField txtSenhaLogin;

    @FXML
    private TextField txtValorLancamento;

    @FXML
    private TableColumn<Item, Integer> tableColumnCodigo;

    @FXML
    private TextField txtCodigo;

//********************
// Métodos: Tela Login
//********************

    @FXML
    void btnIncluirPesquisaOnAction(ActionEvent event) {

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
        this.prepararCarregamentoListas();

    }
    private void voltarTelaLogin(){
        this.tabCadastro.setDisable(true);
        this.tabPane.getSelectionModel().select(this.tabLogin);
        this.tabLogin.setDisable(false);
    }
    private void prepararCarregamentoListas() throws SQLException {
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, valorAntigo, valorNovo) -> {
            if(valorNovo.equals(tabLancamentos)){
                try {
                    prepararTelaLancamentos();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if(valorNovo.equals(tabItens)){
                try {
                    carregarTabelaItens();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    private void carregarTabelaItens() throws SQLException {
        //tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<Item, Integer>("codigo"));
        tableColumnItem.setCellValueFactory(new PropertyValueFactory<Item, String>("descricao"));
        NegocioItem negocioItem = new NegocioItem();
        List<Item> itens = negocioItem.consultarLista(usuarioLogado.getId());
        ObservableList<Item> listaTabelaItens = FXCollections.observableArrayList(itens);
        tabelaItens.setItems(listaTabelaItens);
        tabelaItens.getSelectionModel().selectedItemProperty()
                .addListener((itemObservavel, valorAntigo, valorNovo) ->{
            if (valorNovo != null) {
                itemSelecionado = valorNovo;
                btnExcluirItem.setDisable(false);
                btnCadastrarItem.setText("Atualizar");
                txtDescricaoItem.setText(valorNovo.getDescricao());
            }
        });
    }

    private void prepararTelaLancamentos() throws SQLException {
        cmbTipoLancamento.getItems().addAll(TipoLancamento.values()); // TIPO LANÇAMENTO
        cmbRecorrenciaLancamento.getItems().addAll(TipoRecorrencia.values()); // TIPO RECORRÊNCIA

        NegocioItem negocioItem = new NegocioItem(); // ITEM LANÇAMENTO
        List<Item> itens = negocioItem.consultarLista(usuarioLogado.getId());
        ObservableList<Item> listaTabelaItens = FXCollections.observableArrayList(itens);
        cmbItemLancamento.setItems(listaTabelaItens);

        //txt Field Valor
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("^\\d*(,\\d*)?$")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        txtValorLancamento.setTextFormatter(textFormatter);
    }



//*******************************
// Métodos: Tela Cadastro Usuário
//*******************************

    @FXML
    void onClickCadastrarUsuario(MouseEvent event) throws Exception {
        limparErrosCadastroUsuario();

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
                limparErrosCadastroUsuario();
                voltarTelaLogin();
            } catch (Exception ex){
                lblLogin.setText(ex.getMessage());
            }
        }
    }

    @FXML
    void onClickNovoCadastroUsuario(MouseEvent event) {
        this.tabCadastro.setDisable(false);
        this.tabPane.getSelectionModel().select(this.tabCadastro);// SELECIONA QUAL A TELA ATUAL
        this.tabLogin.setDisable(true);

    }

    @FXML
    void onClickCancelarCadastroUsuario(MouseEvent event) {
        this.voltarTelaLogin();

    }

    private void limparErrosCadastroUsuario(){
        lblSenha.setText("");
        lblLogin.setText("");
        lblNome.setText("");
        lblConfirmarSenha.setText("");
    }

//********************
// Métodos: Tela Itens
//********************

    @FXML
    void onClickCadastrarItem(MouseEvent event) {
        lblErroDescricaoItem.setText("");

        Item item = itemSelecionado== null ? new Item(): itemSelecionado;
        NegocioItem negocioItem = new NegocioItem();
        boolean cadastroInconsistente = false;

        item.setDescricao(txtDescricaoItem.getText());
        if(txtDescricaoItem.getText().equals("")){
            lblErroDescricaoItem.setText("Item obrigatório");
            cadastroInconsistente=true;
        }
        if(!cadastroInconsistente){
            try {
                item.setUsuario(usuarioLogado);
                if(item.getCodigo()==null){
                    item = negocioItem.cadastrar(item);
                    tabelaItens.getItems().add(item);
                }else{
                    negocioItem.atualizar(item);
                }

                tabelaItens.getItems().remove(item);
                tabelaItens.getItems().add(item);
                tabelaItens.getSelectionModel().select(0);
                tabelaItens.getSelectionModel().clearSelection();
                itemSelecionado = null;
                txtDescricaoItem.setText("");
                lblErroDescricaoItem.setText("");
                this.btnExcluirItem.setDisable(true);
                this.btnCadastrarItem.setText("Cadastrar");


            } catch (Exception ex){
                lblErroDescricaoItem.setText(ex.getMessage());
            }
        }

    }

    @FXML
    void OnclickPesquisarItem(MouseEvent event) {

    }
    @FXML
    void onClickExcluirItem(MouseEvent event) throws Exception {
        NegocioItem negocioItem = new NegocioItem();
        negocioItem.excluir(itemSelecionado);
        tabelaItens.getItems().remove(itemSelecionado);
        itemSelecionado = null;
        txtDescricaoItem.setText("");
        lblErroDescricaoItem.setText("");
        this.btnExcluirItem.setDisable(true);
        this.btnCadastrarItem.setText("Cadastrar");

        tabelaItens.getSelectionModel().select(0);
        tabelaItens.getSelectionModel().clearSelection();
    }

//**************************
// Métodos: Tela Lançamentos
//**************************

    @FXML
    void onClickCadastrarLancamento(MouseEvent event) {
        Lancamento lancamento = new Lancamento();
        lancamento.setItem(cmbItemLancamento.getSelectionModel().getSelectedItem());
        lancamento.setTipoLancamento(cmbTipoLancamento.getSelectionModel().getSelectedItem());
        if(txtValorLancamento.getText().equals("")){
            //lblErroValorLancamento.setText("Campo obrigatorio"); // CRIAR LBL DE VALIDAÇÃO!!!
        }else{
            try {
                lancamento.setValor(Double.parseDouble(txtValorLancamento.getText()
                        .replace(",", ".")));
            }catch (Exception ex){
                //lblErroValorLancamento.setText("Informe um número válido");
            }
        }
        lancamento.setDataLancamento(pickerDataLancamento.getValue());
        lancamento.setTipoRecorrencia(cmbRecorrenciaLancamento.getSelectionModel().getSelectedItem());

    }

//*********************
// Métodos: Tela Gastos
//*********************

    @FXML
    void OnclickConsultarGastos(MouseEvent event) {

    }

//*************************
// Métodos: Tela Comparação
//*************************

    @FXML
    void onClickComparar(MouseEvent event) {

    }

}


