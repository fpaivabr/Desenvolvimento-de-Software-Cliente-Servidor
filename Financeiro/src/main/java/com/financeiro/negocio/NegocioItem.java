package com.financeiro.negocio;

import com.financeiro.dao.ItemDao;
import com.financeiro.modelo.Item;

import java.sql.SQLException;
import java.util.List;

public class NegocioItem {

    private ItemDao itemDao;

    public NegocioItem() {
        this.itemDao = new ItemDao();
    }
    public Item cadastrar(Item item) throws Exception {
        if(itemDao.consultar(item.getDescricao(), item.getUsuario().getId())!=null){
            throw new Exception("Item já cadastrado");
        }
        return itemDao.inserir(item);
    }
    public Item consultar(int codigoItem) throws SQLException {
        return itemDao.consultar(codigoItem);
    }
    public void atualizar(Item item) throws Exception {
        if(itemDao.consultar(item.getCodigo())==null){
            throw new Exception("Item não cadastrado");
        }
        itemDao.atualizar(item);
    }
    public void excluir(Item item) throws Exception {
        if(itemDao.consultar(item.getCodigo())==null){
            throw new Exception("Item não cadastrado");
        }
        itemDao.excluir(item);
    }
    public List<Item> consultarLista(int idUsuario) throws SQLException {
        return itemDao.listarItens(idUsuario);
    }
}
