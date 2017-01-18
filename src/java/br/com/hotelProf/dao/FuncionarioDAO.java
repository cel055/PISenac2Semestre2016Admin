/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hotelProf.dao;

import br.com.hotelProf.bean.Funcionario;
import java.util.List;

/**
 *
 * @author Junior
 */
public interface FuncionarioDAO extends BaseDAO {

    List<Funcionario> pesquisaPorNome(String nome) throws Exception;
}
