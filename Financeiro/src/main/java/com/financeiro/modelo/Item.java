package com.financeiro.modelo;
public class Item {

    private Usuario usuario;
    private Integer codigo;
    private String descricao;

    public String toString(){
        return descricao;
    }

    public Usuario getUsuario(){ return usuario; }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setUsuario(Usuario usuario) {this.usuario = usuario; }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}