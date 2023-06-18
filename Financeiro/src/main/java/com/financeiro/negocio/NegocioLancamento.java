package com.financeiro.negocio;

import com.financeiro.dao.LancamentoDao;
import com.financeiro.modelo.Lancamento;
import com.financeiro.modelo.enumeradores.Mes;
import com.financeiro.util.ComparacaoLancamentos;
import com.financeiro.util.ComparadorLancamento;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class NegocioLancamento {

    private LancamentoDao lancamentoDao;

    public NegocioLancamento() {
        this.lancamentoDao = new LancamentoDao();
    }

    public Lancamento cadastrar(Lancamento lancamento) throws Exception {
        return lancamentoDao.inserir(lancamento);
    }

    public Lancamento consultar(int idLancamento) throws SQLException {
        return lancamentoDao.consultar(idLancamento);
    }
    public void atualizar(Lancamento lancamento) throws Exception {
        if(lancamentoDao.consultar(lancamento.getIdLancamento())==null){
            throw new Exception("Lançamento não cadastrado");
        }
        lancamentoDao.atualizar(lancamento);
    }
    public void excluir(Lancamento lancamento) throws Exception {
        if(lancamentoDao.consultar(lancamento.getIdLancamento())==null){
            throw new Exception("Lançamento não cadastrado");
        }
        lancamentoDao.excluir(lancamento);
    }
    public List<Lancamento> consultarLista(int idUsuario, LocalDate dataInicio, LocalDate dataFim) throws SQLException {
        return lancamentoDao.listarLancamentos(idUsuario, dataInicio, dataFim);
    }
    public List<ComparacaoLancamentos> compararLancamentos(int idUsuario, Integer ano, Mes mes1, Mes mes2) throws SQLException {
        List<Lancamento> lancamentos1 = lancamentoDao.consultarLancamentosMes(idUsuario, ano, mes1);
        List<Lancamento> lancamentos2 = lancamentoDao.consultarLancamentosMes(idUsuario, ano, mes2);

        return ComparadorLancamento.compararLancamentos(lancamentos1,lancamentos2);
    }
}
