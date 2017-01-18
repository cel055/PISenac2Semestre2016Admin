/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hotelProf.dao;

import java.util.List;

/**
 *
 * @author Junior
 */
public interface TurnoDAO extends BaseDAO{

    public List pesquisaPorNome(String nome) throws Exception;
    
}
