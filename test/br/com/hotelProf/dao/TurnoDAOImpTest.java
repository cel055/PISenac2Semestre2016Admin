/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hotelProf.dao;

import br.com.hotelProf.bean.Turno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Junior
 */
public class TurnoDAOImpTest {
    
    private Turno ultimogravado(){
        Turno turno = null;
        try {
            Connection con = FabricaConexao.getConnection();
            String sql = "Select * from turno where id in (Select max(id) from turno)";            
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                turno = new Turno();
                turno.setId(rs.getInt("id"));
                turno.setNome(rs.getString("nome"));
                turno.setDescricao(rs.getString("descricao"));
            }
        } catch (Exception ex) {
            System.out.println("deu erro no utilmo gravado do teste" + ex.getMessage());
        }
        return turno;
    }

    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        Turno turno = new Turno(null, "vespertino", "teste descrição");
        TurnoDAO turnoDAO = new TurnoDAOImp();
        turnoDAO.salvar(turno);
        assertEquals(ultimogravado().getNome(), turno.getNome());
    }

//    @Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
        Object obj = null;
        TurnoDAOImp instance = new TurnoDAOImp();
        instance.alterar(obj);
        fail("The test case is a prototype.");
    }

//    @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        int id = 0;
        TurnoDAOImp instance = new TurnoDAOImp();
        instance.excluir(id);
        fail("The test case is a prototype.");
    }

//    @Test
    public void testProcurar() throws Exception {
        System.out.println("procurar");
        Integer id = null;
        TurnoDAOImp instance = new TurnoDAOImp();
        Object expResult = null;
        Object result = instance.procurar(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

//    @Test
    public void testListar() throws Exception {
        System.out.println("listar");
        TurnoDAOImp instance = new TurnoDAOImp();
        List expResult = null;
        List result = instance.listar();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
}
