package com.financeiro.controle;

import com.financeiro.modelo.enumeradores.Idiomas;
import com.financeiro.modelo.enumeradores.Mes;
import com.financeiro.modelo.enumeradores.TipoLancamento;
import com.financeiro.modelo.enumeradores.TipoRecorrencia;
import com.financeiro.negocio.NegocioItem;
import com.financeiro.negocio.NegocioLancamento;
import com.financeiro.util.ComparacaoLancamentos;
import com.financeiro.util.LancamentoDataSource;
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

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

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
    private ComboBox<Mes> cmbMes1Comparacao;

    @FXML
    private ComboBox<Mes> cmbMes2Comparacao;

    @FXML
    private ComboBox<TipoRecorrencia> cmbRecorrenciaLancamento;

    @FXML
    private ComboBox<TipoLancamento> cmbTipoLancamento;

    @FXML
    private Label lblOla;

    @FXML
    private Label lblColocarNomeUsuario;

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
    private Label lblErroTipoLancamento;

    @FXML
    private Label lblErroValorLancamento;

    @FXML
    private Label lblErroRecorrenciaLancamento;

    @FXML
    private Label lblErroItemLancamento;

    @FXML
    private Label lblErroDataLancamento;

    @FXML
    private Label lblErroDataFim;

    @FXML
    private Label lblErroDataInicio;

    @FXML
    private Label lblErroMes1;

    @FXML
    private Label lblErroMes2;

    @FXML
    private Label lblErroAno;

    @FXML
    private Label lblErroItemCadastro;

    @FXML
    private Label lblErroItemPesquisa;

    @FXML
    private Label lblApresentarDespesa;

    @FXML
    private Label lblApresentarReceita;

    @FXML
    private Label lblApresentarSaldo;

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
    private TableView<ComparacaoLancamentos> tabelaComparacao;

    @FXML
    private TableView<Lancamento> tabelaGastos;

    @FXML
    private TableView<Item> tabelaItens;

    @FXML
    private TableColumn<ComparacaoLancamentos, String> tableColumnComparacaoItem;

    @FXML
    private TableColumn<ComparacaoLancamentos, Double> tableColumnComparacaoMes1;

    @FXML
    private TableColumn<ComparacaoLancamentos, Double> tableColumnComparacaoMes2;

    @FXML
    private TableColumn<ComparacaoLancamentos, Double> tableColumnDiferenca;

    @FXML
    private TableColumn<Lancamento, String> tableColumnGastosItem;

    @FXML
    private TableColumn<Lancamento, TipoRecorrencia> tableColumnGastosRecorrencia;

    @FXML
    private TableColumn<Lancamento, TipoLancamento> tableColumnGastosTipo;

    @FXML
    private TableColumn<Lancamento, Double> tableColumnGastosValor;

    @FXML
    private TableColumn<Item, String> tableColumnItem;

    @FXML
    private TableColumn<Lancamento, LocalDate> tableColunmGastosData;

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

    @FXML
    private Button btnExcluirLancamento;

    @FXML
    private ComboBox<Idiomas> cmbIdioma;

    @FXML
    private Label lblDespesaMes;
    @FXML
    private Label lblReceitaMes;
    @FXML
    private Label lblSaldoMes;

    @FXML
    private Label lblAlterarIdioma;

    @FXML
    private Button btnLogOut;


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
        lblColocarNomeUsuario.setText(usuarioLogado.getNome());
        this.tabPane.getSelectionModel().select(this.tabTelaInicial);// SELECIONA QUAL A TELA ATUAL
        cmbIdioma.getItems().clear();
        cmbIdioma.getItems().addAll(Idiomas.values());
        cmbIdioma.setValue(cmbIdioma.getItems().get(0));//Seleciona Português como Primeiro
        this.prepararTelasTab();


    }


    private void voltarTelaLogin(){
        this.tabCadastro.setDisable(true);
        this.tabTelaInicial.setDisable(true);
        this.tabItens.setDisable(true);
        this.tabLancamentos.setDisable(true);
        this.tabGastos.setDisable(true);
        this.tabComparacao.setDisable(true);
        this.tabPane.getSelectionModel().select(this.tabLogin);
        this.tabLogin.setDisable(false);

        txtLoginLogin.setText("");
        txtSenhaLogin.setText("");
        lblErroLogin.setText("");
        lblErroSenha.setText("");

        lblApresentarSaldo.setText("*****");
        lblApresentarDespesa.setText("*****");
        lblApresentarReceita.setText("*****");

    }


    private void prepararTelasTab() throws SQLException {
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
            if(valorNovo.equals(tabComparacao)){
                prepararTelaComparacao();
            }
        });

    }

    private void prepararTelaComparacao() {
        cmbMes1Comparacao.getItems().clear();;
        cmbMes1Comparacao.getItems().addAll(Mes.values());

        cmbMes2Comparacao.getItems().clear();
        cmbMes2Comparacao.getItems().addAll(Mes.values());
        //txt Field Ano Comparação
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("^\\d*?$")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        txtAnoComparacao.setTextFormatter(textFormatter);
    }

    private void carregarTabelaItens() throws SQLException {
        //tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<Item, Integer>("codigo"));
        tableColumnItem.setCellValueFactory(new PropertyValueFactory<Item, String>("descricao"));
        NegocioItem negocioItem = new NegocioItem();
        List<Item> itens = negocioItem.consultarLista(usuarioLogado.getId(), txtPesquisaItem.getText());
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
        cmbTipoLancamento.getItems().clear();
        cmbTipoLancamento.getItems().addAll(TipoLancamento.values()); // TIPO LANÇAMENTO
        cmbRecorrenciaLancamento.getItems().clear();
        cmbRecorrenciaLancamento.getItems().addAll(TipoRecorrencia.values()); // TIPO RECORRÊNCIA

        NegocioItem negocioItem = new NegocioItem(); // ITEM LANÇAMENTO
        List<Item> itens = negocioItem.consultarLista(usuarioLogado.getId(), null);
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
        boolean cadastroInconsistente = false;
        NegocioUsuario negocioUsuario = new NegocioUsuario();
        Usuario usuario = new Usuario();

        usuario.setLogin(txtLoginCadastro.getText());
        usuario.setNome(txtNome.getText());
        usuario.setSenha(txtSenhaCadastro.getText());

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
                exibirAlerta(Alert.AlertType.INFORMATION,"Sucesso!!",
                        "Cadastro realizado com sucesso", "");
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
        lblErroItemCadastro.setText("");
        boolean cadastroInconsistente = false;
        NegocioItem negocioItem = new NegocioItem();

        Item item = itemSelecionado== null ? new Item(): itemSelecionado;
        item.setDescricao(txtDescricaoItem.getText());
        if(txtDescricaoItem.getText().equals("")){
            lblErroItemCadastro.setText("Item obrigatório");
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
                lblErroItemCadastro.setText("");
                this.btnExcluirItem.setDisable(true);
                this.btnCadastrarItem.setText("Cadastrar");
                exibirAlerta(Alert.AlertType.INFORMATION,"Sucesso!!",
                        "Cadastro realizado com sucesso", "");

            } catch (Exception ex){
                lblErroItemCadastro.setText(ex.getMessage());
            }
        }
    }

    @FXML
    void OnclickPesquisarItem(MouseEvent event) throws SQLException {
        carregarTabelaItens();
    }

    @FXML
    void onClickExcluirItem(MouseEvent event) throws Exception {
        NegocioItem negocioItem = new NegocioItem();
        negocioItem.excluir(itemSelecionado);
        tabelaItens.getItems().remove(itemSelecionado);
        itemSelecionado = null;
        txtDescricaoItem.setText("");
        lblErroItemCadastro.setText("");
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
        limparErrosCadastroLancamento();
        boolean cadastroInconsistente = false;
        NegocioLancamento negocioLancamento = new NegocioLancamento();

        Lancamento lancamento = new Lancamento();
        lancamento.setItem(cmbItemLancamento.getSelectionModel().getSelectedItem());
        lancamento.setTipoLancamento(cmbTipoLancamento.getSelectionModel().getSelectedItem());
        if(txtValorLancamento.getText().equals("")){
            lblErroValorLancamento.setText("Digitar valor");
            cadastroInconsistente = true;
        }else{
            try {
                lancamento.setValor(Double.parseDouble(txtValorLancamento.getText()
                        .replace(",", ".")));
            }catch (Exception ex){
                lblErroValorLancamento.setText("Informe um número válido");
            }
        }
        lancamento.setDataLancamento(pickerDataLancamento.getValue());
        lancamento.setTipoRecorrencia(cmbRecorrenciaLancamento.getSelectionModel().getSelectedItem());
        lancamento.setUsuario(usuarioLogado);

        if(cmbItemLancamento.getSelectionModel().getSelectedItem()==null){
            lblErroItemLancamento.setText("Selecionar item");
            cadastroInconsistente = true;
        }
        if(cmbTipoLancamento.getSelectionModel().getSelectedItem()==null){
            lblErroTipoLancamento.setText("Selecionar tipo de lançamento");
            cadastroInconsistente=true;
        }
        /*if(txtValorLancamento.getText().equals("")){
            lblLogin.setText("Digitar valor");
            cadastroInconsistente=true;
        }*/
        if(pickerDataLancamento.getValue()==null){
            lblErroDataLancamento.setText("Selecionar Data");
            cadastroInconsistente=true;
        }
        if(cmbRecorrenciaLancamento.getSelectionModel().getSelectedItem()==null){
            lblErroRecorrenciaLancamento.setText("Selecionar recorrência");
            cadastroInconsistente=true;
        }
        if(!cadastroInconsistente){
            try {
                negocioLancamento.cadastrar(lancamento);
                limparErrosCadastroLancamento();
                limpeCamposCadastroLancamento();
                exibirAlerta(Alert.AlertType.INFORMATION,"Sucesso!!",
                        "Cadastro realizado com sucesso", "");
            } catch (Exception ex){
                exibirAlerta(Alert.AlertType.ERROR,"Erro!!",
                        "Erro ao cadastrar", ex.getMessage());

            }
        }

    }
    private void limparErrosCadastroLancamento(){

        lblErroItemLancamento.setText("");
        lblErroTipoLancamento.setText("");
        lblErroValorLancamento.setText("");
        lblErroDataLancamento.setText("");
        lblErroRecorrenciaLancamento.setText("");
    }

    private void limpeCamposCadastroLancamento(){
        cmbItemLancamento.setValue(null);
        cmbTipoLancamento.setValue(null);
        pickerDataLancamento.setValue(null);
        cmbRecorrenciaLancamento.setValue(null);
        txtValorLancamento.setText("");
    }

//********************************************
// Métodos: Tela Transações (Gastos renomeada)
//********************************************

    @FXML
    void OnclickConsultarGastos(MouseEvent event) throws SQLException {
        tableColumnGastosItem.setCellValueFactory(new PropertyValueFactory<Lancamento, String>("item"));
        tableColumnGastosTipo.setCellValueFactory(new PropertyValueFactory<Lancamento, TipoLancamento>("tipoLancamento"));
        tableColunmGastosData.setCellValueFactory(new PropertyValueFactory<Lancamento, LocalDate>("dataLancamento"));
        tableColumnGastosRecorrencia.setCellValueFactory(new PropertyValueFactory<Lancamento, TipoRecorrencia>("tipoRecorrencia"));
        tableColumnGastosValor.setCellValueFactory(new PropertyValueFactory<Lancamento, Double>("valor"));

        List<Lancamento> lancamentos = getLancamentos();
        if (lancamentos == null) return;
        ObservableList<Lancamento> listaTabelaGastos = FXCollections.observableArrayList(lancamentos);
        tabelaGastos.setItems(listaTabelaGastos);

    }

    @FXML
    void onClickExcluirLancamento (MouseEvent event) throws Exception {
        NegocioLancamento negocioLancamento = new NegocioLancamento();
        Lancamento lancamentoSelecionado = tabelaGastos.getSelectionModel().getSelectedItem();

        if (lancamentoSelecionado == null) {
            exibirAlerta(Alert.AlertType.INFORMATION, "Atenção",
                    "Nenhum lançamento selecionado", "");
            return;
        }

        negocioLancamento.excluir(lancamentoSelecionado);
        tabelaGastos.getItems().remove(lancamentoSelecionado);

        tabelaGastos.getSelectionModel().select(0);
        tabelaGastos.getSelectionModel().clearSelection();
    }

// Extraido método para ser reutilizado
    private List<Lancamento> getLancamentos() throws SQLException {
        lblErroDataInicio.setText("");
        lblErroDataInicio.setText("");
        NegocioLancamento negocioLancamento = new NegocioLancamento();
        LocalDate dataInicio = pickerDataInicioGastos.getValue();
        LocalDate dataFim = pickerDataFimGastos.getValue();
        if(dataInicio==null){
            lblErroDataInicio.setText("Selecione data");
            return null;
        }
        if(dataFim==null){
            lblErroDataInicio.setText("Selecione data");
            return null;
        }

        List<Lancamento> lancamentos = negocioLancamento.consultarLista(usuarioLogado.getId(),dataInicio, dataFim);
        return lancamentos;
    }

//*********************************
// Métodos: Tela Gastos - Relatório
//*********************************

    @FXML
    void onClickEmitirRelatorio(MouseEvent event) throws JRException, SQLException {
        baixarRelatorio();
    }
    private void baixarRelatorio() throws JRException, SQLException {
        List<Lancamento> lancamentos = getLancamentos();
        if(lancamentos == null){
            return;
        }

        LocalDate dataInicio = pickerDataInicioGastos.getValue();
        LocalDate dataFim = pickerDataFimGastos.getValue();
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("dataInicio", formatarData(dataInicio));
        parametros.put("dataFim", formatarData(dataFim));

        List<Map<String, Object>> mapas = lancamentos.stream()
                .map(lancamento -> convertToMap(lancamento))
                .collect(Collectors.toList());

        JRDataSource dataSource = new LancamentoDataSource(mapas);
        JasperPrint jp = JasperFillManager.fillReport(
                "src\\main\\java\\com\\financeiro\\relatorios\\RelatorioFinanceiro.jasper",
                parametros,
                dataSource);

        JasperViewer.viewReport(jp, false);
    }

    public String formatarData(LocalDate data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }

    private Map<String, Object> convertToMap(Lancamento lancamento) {
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("usuario", lancamento.getUsuario());
        mapa.put("idLancamento", lancamento.getIdLancamento());
        mapa.put("tipoLancamento", lancamento.getTipoLancamento().toString());
        mapa.put("valor", lancamento.getValor());
        mapa.put("dataLancamento", formatarData(lancamento.getDataLancamento()));
        mapa.put("tipoRecorrencia", lancamento.getTipoRecorrencia().toString());
        mapa.put("item", lancamento.getItem().getDescricao());
        return mapa;
    }

//*************************
// Métodos: Tela Comparação
//*************************

    @FXML
    void onClickComparar(MouseEvent event) throws SQLException {
        boolean dadosInconsistentes = false;

        Mes mes1 = cmbMes1Comparacao.getSelectionModel().getSelectedItem();
        if(mes1==null){
            lblErroMes1.setText("Selecione mês");
            dadosInconsistentes=true;
        }
        Mes mes2 = cmbMes2Comparacao.getSelectionModel().getSelectedItem();
        if(mes2==null){
            lblErroMes2.setText("Selecione mês");
            dadosInconsistentes=true;
        }
        if(txtAnoComparacao.getText().equals("")){
            lblErroAno.setText("Selecione ano");
            dadosInconsistentes=true;
        }

        tableColumnComparacaoItem.setCellValueFactory(new PropertyValueFactory<ComparacaoLancamentos,String>("item"));
        tableColumnComparacaoMes1.setCellValueFactory(new PropertyValueFactory<ComparacaoLancamentos,Double>("valorLancamento1"));
        tableColumnComparacaoMes2.setCellValueFactory(new PropertyValueFactory<ComparacaoLancamentos,Double>("valorLancamento2"));
        tableColumnDiferenca.setCellValueFactory(new PropertyValueFactory<ComparacaoLancamentos,Double>("diferenca"));

        if(!dadosInconsistentes){
            NegocioLancamento negocioLancamento = new NegocioLancamento();
            Integer ano = Integer.valueOf(txtAnoComparacao.getText());
            List<ComparacaoLancamentos> listaComparacao = negocioLancamento.compararLancamentos(usuarioLogado.getId(),ano, mes1, mes2);
            ObservableList<ComparacaoLancamentos> listaTabelaComparacao = FXCollections.observableArrayList(listaComparacao);
            tabelaComparacao.setItems(listaTabelaComparacao);
        }
    }

//**********************
// Métodos: Tela Inicial
//**********************

    @FXML
    void onClickApresentarDespesa(MouseEvent event) throws SQLException {
        apresentarDadosTelaInicial();
    }

    @FXML
    void onClickApresentarReceita(MouseEvent event) throws SQLException {
        apresentarDadosTelaInicial();
    }

    @FXML
    void onClickApresentarSaldo(MouseEvent event) throws SQLException {
        apresentarDadosTelaInicial();
    }

    void apresentarDadosTelaInicial() throws SQLException {
        if(lblApresentarReceita.getText().equals("*****")) {
            DecimalFormat df = new DecimalFormat("#,##0.00");
            df.setDecimalFormatSymbols(new DecimalFormatSymbols(new Locale("pt", "BR")));

            NegocioLancamento negocioLancamento = new NegocioLancamento();
            LocalDate hoje = LocalDate.now();
            LocalDate inicioMes = LocalDate.of(hoje.getYear(), hoje.getMonth(), 1);
            List<Lancamento> listaLancamento = negocioLancamento
                    .consultarLista(usuarioLogado.getId(), inicioMes, hoje);
            Double receita = listaLancamento.stream().filter(lancamento -> lancamento.getTipoLancamento()
                    .equals(TipoLancamento.Receita)).mapToDouble(lancamento -> lancamento.getValor()).sum();
            lblApresentarReceita.setText(df.format(receita));
            Double despesa = listaLancamento.stream().filter(lancamento -> lancamento.getTipoLancamento()
                    .equals(TipoLancamento.Despesa)).mapToDouble(lancamento -> lancamento.getValor()).sum();
            lblApresentarDespesa.setText(df.format(despesa));
            Double saldo = receita - despesa;
            lblApresentarSaldo.setText(df.format(saldo));
        }else{
            lblApresentarDespesa.setText("*****");
            lblApresentarReceita.setText("*****");
            lblApresentarSaldo.setText("*****");

        }

    }

    @FXML
    void onClickLogOut(MouseEvent event) {
        usuarioLogado=null;
        itemSelecionado=null;
        voltarTelaLogin();
    }

    @FXML
    void onIdiomaSelecionado(ActionEvent event) {
        Idiomas idiomaSelecionado = cmbIdioma.getSelectionModel().getSelectedItem();
        if(idiomaSelecionado!=null){
            switch (idiomaSelecionado){
                case Portugues -> selecionarPortugues();
                case English -> selecionarIngles();
                case Espanol -> selecionarEspanhol();
                case Francais -> selecionarFrances();
                case Deutsch -> selecionarAlemao();
            }
        }

    }
    void selecionarPortugues(){
        lblOla.setText("Olá,");
        lblReceitaMes.setText("Receita do mês:");
        lblDespesaMes.setText("Despesa do mês:");
        lblSaldoMes.setText("Saldo do mês:");
        lblAlterarIdioma.setText("Alterar idioma:");
        btnLogOut.setText("Log out");

        tabLogin.setText("Login");
        tabCadastro.setText("Cadastro");
        tabTelaInicial.setText("Tela Inicial");
        tabItens.setText("Itens");
        tabLancamentos.setText("Lançamentos");
        tabGastos.setText("Transações");
        tabComparacao.setText("Comparação");
    }
    void selecionarIngles(){
        lblOla.setText("Hello,");
        lblReceitaMes.setText("Monthly Income:");
        lblDespesaMes.setText("Monthly Expense:");
        lblSaldoMes.setText("Monthly Balance:");
        lblAlterarIdioma.setText("Change language: ");
        btnLogOut.setText("Log out");

        tabLogin.setText("Login");
        tabCadastro.setText("User Registration");
        tabTelaInicial.setText("Home Screen");
        tabItens.setText("Items");
        tabLancamentos.setText("Entries");
        tabGastos.setText("Transactions");
        tabComparacao.setText("Comparison");
    }
    void selecionarEspanhol(){
        lblOla.setText("Hola,");
        lblReceitaMes.setText("Ingresos del mes:");
        lblDespesaMes.setText("Gastos del mes:");
        lblSaldoMes.setText("Balance del mes:");
        lblAlterarIdioma.setText("Cambiar idioma: ");
        btnLogOut.setText("Cerrar sesión");

        tabLogin.setText("Iniciar sesión");
        tabCadastro.setText("Registro de usuario");
        tabTelaInicial.setText("Pantalla inicial");
        tabItens.setText("Ítems");
        tabLancamentos.setText("Entradas");
        tabGastos.setText("Transacciones");
        tabComparacao.setText("Comparación");
    }
    void selecionarFrances(){
        lblOla.setText("Bonjour,");
        lblReceitaMes.setText("Revenu du mois:");
        lblDespesaMes.setText("Dépenses du mois:");
        lblSaldoMes.setText("Solde du mois:");
        lblAlterarIdioma.setText("Changer de langue:");
        btnLogOut.setText("Se déconnecter");

        tabLogin.setText("Connexion");
        tabCadastro.setText("Inscription utilisateur");
        tabTelaInicial.setText("Écran d'accueil");
        tabItens.setText("Articles");
        tabLancamentos.setText("Entrées");
        tabGastos.setText("Transactions");
        tabComparacao.setText("Comparaison");
    }

    void selecionarAlemao(){
        lblOla.setText("Hallo,");
        lblReceitaMes.setText("Monatseinkommen:");
        lblDespesaMes.setText("Monatsausgaben:");
        lblSaldoMes.setText("Monatssaldo:");
        lblAlterarIdioma.setText( "Sprache ändern: ");
        btnLogOut.setText("Abmelden");

        tabLogin.setText("Anmelden");
        tabCadastro.setText("Benutzerregistrierung");
        tabTelaInicial.setText("Startbildschirm");
        tabItens.setText("Artikel");
        tabLancamentos.setText("Buchungen");
        tabGastos.setText("Transaktionen");
        tabComparacao.setText("Vergleich");
    }

//****************
// Métodos: outros
//****************

    private void exibirAlerta(Alert.AlertType tipoAlerta, String tituloAlerta, String cabecalhoAlerta, String conteudoAlerta){
        Alert alerta = new Alert(tipoAlerta);
        alerta.setTitle(tituloAlerta);//Título
        alerta.setHeaderText(cabecalhoAlerta);//Cabeçalho
        alerta.setContentText(conteudoAlerta);//Alerta
        alerta.showAndWait();
    }
}




