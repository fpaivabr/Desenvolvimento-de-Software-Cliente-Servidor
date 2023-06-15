package com.financeiro.modelo;

import com.financeiro.modelo.enumeradores.TipoLancamento;
import com.financeiro.modelo.enumeradores.TipoRecorrencia;

import java.time.LocalDate;
public class Lancamento {
    private TipoLancamento tipoLancamento;
    private double valor;
    private LocalDate dataLancamento;
    private TipoRecorrencia tipoRecorrencia;
    private Item item;

    public TipoLancamento getTipoLancamento() {
        return tipoLancamento;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public TipoRecorrencia getTipoRecorrenciarecorrencia() {
        return tipoRecorrencia;
    }

    public Item getItem() {
        return item;
    }

    public void setTipoLancamento(TipoLancamento tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setTipoRecorrencia(TipoRecorrencia tipoRecorrencia) {
        this.tipoRecorrencia = tipoRecorrencia;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}