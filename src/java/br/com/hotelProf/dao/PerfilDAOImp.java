/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hotelProf.dao;

import br.com.hotelProf.bean.Perfil;
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
public class PerfilDAOImp implements PerfilDAO{

    @Override
    public Object salvar(Object obj) throws Exception {
        Perfil perfil = (Perfil) obj;
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String SQL = "INSERT INTO perfil(nome, descricao) VALUES(?, ?)";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setString(1, perfil.getNome());
            ps.setString(2, perfil.getDescricao());
            ps.executeUpdate();

        } catch (SQLException se) {
            throw new Exception("Erro ao tentar inserir dados" + se.getMessage());
        } finally {
            FabricaConexao.closeConnection(conn, ps);
        }
        return perfil;
    }

    @Override
    public void alterar(Object obj) throws Exception {
         Perfil perfil = (Perfil) obj;
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String SQL = "UPDATE perfil SET nome=?, descricao=? WHERE id=?";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setString(1, perfil.getNome());
            ps.setString(2, perfil.getDescricao());
            ps.setInt(3, perfil.getId());
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
            String SQL = "DELETE FROM perfil WHERE id=?";
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
        Perfil perfil = null;
        try {
            String SQL = "SELECT * FROM perfil WHERE id=?";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                perfil = new Perfil();
                perfil.setId(id);
                perfil.setNome(rs.getString("nome"));
                perfil.setDescricao(rs.getString("descricao"));
            }
        } catch (SQLException se) {
            throw new Exception("Erro ao tentar atualizar dados"
                    + se.getMessage());
        } finally {
            FabricaConexao.closeConnection(conn, ps, rs);
        }
        return perfil;
    }

    @Override
    public List<Object> listar() throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs;
        Perfil perfil;
        List perfis = new ArrayList();
        try {
            String SQL = "SELECT * FROM perfil ";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                perfil = new Perfil();
                perfil.setId(rs.getInt("id"));
                perfil.setNome(rs.getString("nome"));
                perfil.setDescricao(rs.getString("descricao"));
                perfis.add(perfil);
            }
        } catch (SQLException se) {
            throw new Exception("Erro ao tentar atualizar dados"
                    + se.getMessage());
        } finally {
            FabricaConexao.closeConnection(conn, ps);
        }
        return perfis;
    }

    @Override
    public List<Perfil> pesquisaPorNome(String nome) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs;
        Perfil perfil;
        List perfis = new ArrayList();
        try {
            String SQL = "SELECT * FROM perfil where nome like ?";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setString(1, "%" + nome + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                perfil = new Perfil();
                perfil.setId(rs.getInt("id"));
                perfil.setNome(rs.getString("nome"));
                perfil.setDescricao(rs.getString("descricao"));
                perfis.add(perfil);
            }
        } catch (SQLException se) {
            throw new Exception("Erro ao tentar atualizar dados"
                    + se.getMessage());
        } finally {
            FabricaConexao.closeConnection(conn, ps);
        }
        return perfis;
    }
    
}
