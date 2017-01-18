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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Junior
 */
public class FuncaoDAOImp implements FuncaoDAO {

    @Override
    public Object salvar(Object obj) throws Exception {
        Funcao funcao = (Funcao) obj;
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String SQL = "INSERT INTO funcao(tipo, descricao) VALUES(?, ?)";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setString(1, funcao.getTipo());
            ps.setString(2, funcao.getDescricao());
            ps.executeUpdate();

        } catch (SQLException se) {
            throw new Exception("Erro ao tentar inserir dados" + se.getMessage());
        } finally {
            FabricaConexao.closeConnection(conn, ps);
        }
        return funcao;
    }

    @Override
    public void alterar(Object obj) throws Exception {
        Funcao funcao = (Funcao) obj;
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String SQL = "UPDATE funcao SET tipo=?, descricao=? WHERE id=?";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setString(1, funcao.getTipo());
            ps.setString(2, funcao.getDescricao());
            ps.setInt(3, funcao.getId());
            ps.executeUpdate();
        } catch (SQLException se) {
            throw new Exception("Erro ao tentar atualizar dados" + se.getMessage());
        } finally {
            FabricaConexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            String SQL = "DELETE FROM funcao WHERE id=?";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException se) {
            throw new Exception("Erro ao tentar excluir dados"
                    + se.getMessage());
        } finally {
            FabricaConexao.closeConnection(conn, ps);
        }
    }

    @Override
    public Object procurar(Integer id) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Funcao funcao = null;
        try {
            String SQL = "SELECT * FROM funcao WHERE id=?";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                funcao = new Funcao();
                funcao.setId(id);
                funcao.setTipo(rs.getString("tipo"));
                funcao.setDescricao(rs.getString("descricao"));
            }
        } catch (SQLException se) {
            throw new Exception("Erro ao tentar atualizar dados"
                    + se.getMessage());
        } finally {
            FabricaConexao.closeConnection(conn, ps, rs);
        }
        return funcao;
    }

    @Override
    public List<Object> listar() throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs;
        Funcao funcao;
        List funcoes = new ArrayList();
        try {
            String SQL = "SELECT * FROM funcao ";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                funcao = new Funcao();
                funcao.setId(rs.getInt("id"));
                funcao.setTipo(rs.getString("tipo"));
                funcao.setDescricao(rs.getString("descricao"));
                funcoes.add(funcao);
            }
        } catch (SQLException se) {
            throw new Exception("Erro ao tentar atualizar dados"
                    + se.getMessage());
        } finally {
            FabricaConexao.closeConnection(conn, ps);
        }
        return funcoes;
    }

    @Override
    public List pesquisaPorTipo(String tipo) throws Exception{
       PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs;
        Funcao funcao;
        List funcoes = new ArrayList();
        try {
            String SQL = "SELECT * FROM funcao where tipo like ?";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setString(1, "%" + tipo + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                funcao = new Funcao();
                funcao.setId(rs.getInt("id"));
                funcao.setTipo(rs.getString("tipo"));
                funcao.setDescricao(rs.getString("descricao"));
                funcoes.add(funcao);
            }
        } catch (SQLException se) {
            throw new Exception("Erro ao tentar atualizar dados"
                    + se.getMessage());
        } finally {
            FabricaConexao.closeConnection(conn, ps);
        }
        return funcoes;
    }
    
}
