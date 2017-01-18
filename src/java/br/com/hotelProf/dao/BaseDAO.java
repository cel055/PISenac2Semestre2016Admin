package br.com.hotelProf.dao;

import java.util.List;

public interface BaseDAO {

    Object salvar(Object obj) throws Exception;

    void alterar(Object obj) throws Exception;

    void excluir(int id) throws Exception;

    Object procurar(Integer id) throws Exception;

    List<Object> listar() throws Exception;
    
    
}
