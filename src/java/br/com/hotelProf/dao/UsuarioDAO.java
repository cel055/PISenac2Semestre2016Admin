/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hotelProf.dao;

import br.com.hotelProf.bean.Funcionario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Junior
 */
public interface UsuarioDAO extends BaseDAO {

    List<Funcionario> procuraPorNome(String nome) throws SQLException, Exception;

    List<Funcionario> funcNaoCadastrados() throws Exception;
    
    Funcionario logar(String login, String senha) throws Exception;
}
