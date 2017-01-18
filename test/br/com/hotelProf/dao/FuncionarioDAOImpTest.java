/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hotelProf.dao;

import br.com.hotelProf.bean.*;
import java.util.Date;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Junior
 */
public class FuncionarioDAOImpTest {
    
   

//    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        Turno turno = new Turno();
        turno.setId(4);
        
        Funcao funcao = new Funcao();
        funcao.setId(4);
        
        Funcionario funcionario = new Funcionario(null, "crcha", 5100.00, "nome func", "email", "3030-3030", 
                "000.000.000-00", "rg", "9888-9900", new Date(), "Masculino");
        Endereco endereco = new Endereco(null, "teste rua, 09", "88888-888", "bairro teste", "cidade teste", "estado teste", "SI", "Pais Teste");
        
        funcionario.setEndereco(endereco);
        funcionario.setTurno(turno);
        funcionario.setFuncao(funcao);
                
        FuncionarioDAO funcDAO = new FuncionarioDAOImp();
        funcDAO.salvar(funcionario);
    }

    @Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
        Turno turno = new Turno();
        turno.setId(4);
        
        Funcao funcao = new Funcao();
        funcao.setId(5);
        
        Funcionario funcionario = new Funcionario(8, "crcha", 5500.00, "nome func alterado", "email", "3030-3030", 
                "000.000.030-00", "rg", "9888-9900", new Date(), "Masculino");
        Endereco endereco = new Endereco(null, "teste rua alterada, 09", "88888-888", "bairro teste", "cidade teste", "estado teste", "SI", "Pais Teste");
        
        funcionario.setEndereco(endereco);
        funcionario.setTurno(turno);
        funcionario.setFuncao(funcao);
                
        FuncionarioDAO funcDAO = new FuncionarioDAOImp();
        funcDAO.alterar(funcionario);
    }

//    @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        int id = 0;
        FuncionarioDAOImp instance = new FuncionarioDAOImp();
        instance.excluir(id);
    }

//    @Test
    public void testProcurar() throws Exception {
        System.out.println("procurar");
        Integer id = null;
        FuncionarioDAOImp instance = new FuncionarioDAOImp();
        Object expResult = null;
        Object result = instance.procurar(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

//    @Test
    public void testListar() throws Exception {
        System.out.println("listar");
        FuncionarioDAOImp instance = new FuncionarioDAOImp();
        List expResult = null;
        List result = instance.listar();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

//    @Test
    public void testPesquisaPorNome() throws Exception {
        System.out.println("pesquisaPorNome");
        String nome = "";
        FuncionarioDAOImp instance = new FuncionarioDAOImp();
        List expResult = null;
        List result = instance.pesquisaPorNome(nome);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
}
