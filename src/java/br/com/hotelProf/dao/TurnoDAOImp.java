/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hotelProf.dao;

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
public class TurnoDAOImp implements TurnoDAO {

    @Override
    public Object salvar(Object obj) throws Exception {
        Turno turno = (Turno) obj;
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String SQL = "INSERT INTO turno(nome, descricao) VALUES(?, ?)";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setString(1, turno.getNome());
            ps.setString(2, turno.getDescricao());
            ps.executeUpdate();

        } catch (SQLException se) {
            throw new Exception("Erro ao tentar inserir dados" + se.getMessage());
        } finally {
            FabricaConexao.closeConnection(conn, ps);
        }
        return turno;
    }

    @Override
    public void alterar(Object obj) throws Exception {
        Turno turno = (Turno) obj;
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String SQL = "UPDATE turno SET nome=?, descricao=? WHERE id=?";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setString(1, turno.getNome());
            ps.setString(2, turno.getDescricao());
            ps.setInt(3, turno.getId());
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
            String SQL = "DELETE FROM turno WHERE id=?";
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
        Turno turno = null;
        try {
            String SQL = "SELECT * FROM turno WHERE id=?";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                turno = new Turno();
                turno.setId(id);
                turno.setNome(rs.getString("nome"));
                turno.setDescricao(rs.getString("descricao"));
            }
        } catch (SQLException se) {
            throw new Exception("Erro ao tentar atualizar dados"
                    + se.getMessage());
        } finally {
            FabricaConexao.closeConnection(conn, ps, rs);
        }
        return turno;
    }

    @Override
    public List<Object> listar() throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs;
        Turno turno;
        List turnos = new ArrayList();
        try {
            String SQL = "SELECT * FROM turno ";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                turno = new Turno();
                turno.setId(rs.getInt("id"));
                turno.setNome(rs.getString("nome"));
                turno.setDescricao(rs.getString("descricao"));
                turnos.add(turno);
            }
        } catch (SQLException se) {
            throw new Exception("Erro ao tentar atualizar dados"
                    + se.getMessage());
        } finally {
            FabricaConexao.closeConnection(conn, ps);
        }
        return turnos;
    }

    @Override
    public List pesquisaPorNome(String nome) throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs;
        Turno turno;
        List turnos = new ArrayList();
        try {
            String SQL = "SELECT * FROM turno where nome like ?";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setString(1, "%" + nome + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                turno = new Turno();
                turno.setId(rs.getInt("id"));
                turno.setNome(rs.getString("nome"));
                turno.setDescricao(rs.getString("descricao"));
                turnos.add(turno);
            }
        } catch (SQLException se) {
            throw new Exception("Erro ao tentar atualizar dados"
                    + se.getMessage());
        } finally {
            FabricaConexao.closeConnection(conn, ps);
        }
        return turnos;
    }
}
