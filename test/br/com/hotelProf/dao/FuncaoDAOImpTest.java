/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hotelProf.dao;

import br.com.hotelProf.bean.Funcao;
import br.com.hotelProf.bean.Turno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Junior
 */
public class FuncaoDAOImpTest {

    private Funcao ultimogravado() {
        Funcao funcao = null;
        try {
            Connection con = FabricaConexao.getConnection();
            String sql = "Select * from funcao where id in (Select max(id) from funcao)";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                funcao = new Funcao();
                funcao.setId(rs.getInt("id"));
                funcao.setTipo(rs.getString("tipo"));
                funcao.setDescricao(rs.getString("descricao"));
            }
        } catch (Exception ex) {
            System.out.println("deu erro no utilmo gravado do teste" + ex.getMessage());
        }
        return funcao;
    }

    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        Funcao funcao = new Funcao(null, "admin", "teste descrição");
        FuncaoDAO turnoDAO = new FuncaoDAOImp();
        turnoDAO.salvar(funcao);
        assertEquals(ultimogravado().getTipo(), funcao.getTipo());
    }

//    @Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
        Object obj = null;
        FuncaoDAOImp instance = new FuncaoDAOImp();
        instance.alterar(obj);
        fail("The test case is a prototype.");
    }

//    @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        int id = 0;
        FuncaoDAOImp instance = new FuncaoDAOImp();
        instance.excluir(id);
        fail("The test case is a prototype.");
    }

//    @Test
    public void testProcurar() throws Exception {
        System.out.println("procurar");
        Integer id = null;
        FuncaoDAOImp instance = new FuncaoDAOImp();
        Object expResult = null;
        Object result = instance.procurar(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

//    @Test
    public void testListar() throws Exception {
        System.out.println("listar");
        FuncaoDAOImp instance = new FuncaoDAOImp();
        List expResult = null;
        List result = instance.listar();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
}
