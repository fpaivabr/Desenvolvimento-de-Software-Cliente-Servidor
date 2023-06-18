package com.financeiro.util;

import com.financeiro.modelo.Item;
import com.financeiro.modelo.enumeradores.Mes;

public class ComparacaoLancamentos {
    private Item item;
    private Double valorLancamento1 = 0d;
    private Double valorLancamento2 = 0d;
    private Integer ano;
    private Mes mesLancamento1;
    private Mes mesLancamento2;

    private Double diferenca;

    public Double getDiferenca() {
        return valorLancamento2-valorLancamento1;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Double getValorLancamento1() {
        return valorLancamento1;
    }

    public void setValorLancamento1(Double valorLancamento1) {
        this.valorLancamento1 = valorLancamento1;
    }

    public Double getValorLancamento2() {
        return valorLancamento2;
    }

    public void setValorLancamento2(Double valorLancamento2) {
        this.valorLancamento2 = valorLancamento2;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Mes getMesLancamento1() {
        return mesLancamento1;
    }

    public Mes getMesLancamento2() {
        return mesLancamento2;
    }

    public void setMesLancamento2(Mes mesLancamento2) {
        this.mesLancamento2 = mesLancamento2;
    }

    public void setMesLancamento1(Mes mesLancamento1) {
        this.mesLancamento1 = mesLancamento1;
    }
}
