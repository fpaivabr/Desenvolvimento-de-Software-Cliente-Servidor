package com.financeiro.util;

import com.financeiro.modelo.Lancamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ComparadorLancamento {
    public static List<ComparacaoLancamentos> compararLancamentos(
            List<Lancamento> lancamentoMes1,
            List<Lancamento> lancamentoMes2) {

        List<ComparacaoLancamentos> listaComparacaoLancamentos = new ArrayList<>();
        for (Lancamento lancamento : lancamentoMes1 ) {
           Optional< ComparacaoLancamentos> oComparacao = listaComparacaoLancamentos
                    .stream()
                    .filter(comparacao->lancamento.getItem().getCodigo().equals(comparacao.getItem().getCodigo()))
                   .findFirst();
           ComparacaoLancamentos comparacaoLancamentos = oComparacao.isEmpty() ?
                   new ComparacaoLancamentos(): oComparacao.get();

            comparacaoLancamentos.setValorLancamento1(comparacaoLancamentos.getValorLancamento1()+lancamento.getValor());
            comparacaoLancamentos.setAno(lancamento.getDataLancamento().getYear());
            comparacaoLancamentos.setItem(lancamento.getItem());
            listaComparacaoLancamentos.add(comparacaoLancamentos);
        }
        for (Lancamento lancamento : lancamentoMes2 ) {
            Optional< ComparacaoLancamentos> oComparacao = listaComparacaoLancamentos
                    .stream()
                    .filter(comparacao->lancamento.getItem().getCodigo().equals(comparacao.getItem().getCodigo()))
                    .findFirst();
            ComparacaoLancamentos comparacaoLancamentos = oComparacao.isEmpty() ?
                    new ComparacaoLancamentos(): oComparacao.get();

            comparacaoLancamentos.setValorLancamento2(comparacaoLancamentos.getValorLancamento2()+lancamento.getValor());
            comparacaoLancamentos.setAno(lancamento.getDataLancamento().getYear());
            comparacaoLancamentos.setItem(lancamento.getItem());
            listaComparacaoLancamentos.add(comparacaoLancamentos);
        }

        return listaComparacaoLancamentos;
    }
}
