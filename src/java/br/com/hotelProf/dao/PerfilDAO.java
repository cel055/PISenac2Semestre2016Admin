/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hotelProf.dao;

import br.com.hotelProf.bean.Perfil;
import java.util.List;

/**
 *
 * @author Junior
 */
public interface PerfilDAO extends BaseDAO {

    List<Perfil> pesquisaPorNome(String nome) throws Exception;
}
