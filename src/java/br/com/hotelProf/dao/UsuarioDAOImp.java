/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hotelProf.dao;

import br.com.hotelProf.bean.Funcionario;
import br.com.hotelProf.bean.Perfil;
import br.com.hotelProf.bean.Pessoa;
import br.com.hotelProf.bean.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Junior
 */
public class UsuarioDAOImp implements UsuarioDAO {

    @Override
    public Object salvar(Object obj) throws Exception {
        Pessoa pessoa = (Pessoa) obj;
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "INSERT INTO usuario(login, senha, idPerfil) VALUES(?, ?, ?)";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pessoa.getUsuario().getLogin());
            ps.setString(2, pessoa.getUsuario().getSenha());
            ps.setInt(3, pessoa.getUsuario().getPerfil().getId());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            int idUsuario = rs.getInt(1);
            ps = conn.prepareStatement("UPDATE pessoa SET idUsuario = " + idUsuario + " "
                    + "Where id = ?");
            ps.setInt(1, pessoa.getId());
            ps.executeUpdate();

        } catch (SQLException se) {
            throw new Exception("Erro ao tentar inserir dados" + se.getMessage());
        } finally {
            FabricaConexao.closeConnection(conn, ps, rs);
        }
        return pessoa;
    }

    @Override
    public void alterar(Object obj) throws Exception {
        Pessoa pessoa = (Pessoa) obj;
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String SQL = "UPDATE usuario SET login=?, senha=?, idPerfil=? WHERE idPessoa=?";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setString(1, pessoa.getUsuario().getLogin());
            ps.setString(2, pessoa.getUsuario().getSenha());
            ps.setInt(3, pessoa.getUsuario().getPerfil().getId());
            ps.setInt(4, pessoa.getId());
            ps.executeUpdate();
        } catch (SQLException se) {
            throw new Exception("Erro ao tentar atualizar dados" + se.getMessage());
        } finally {
            FabricaConexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object procurar(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Object> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Funcionario> procuraPorNome(String nome) throws SQLException, Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Usuario usuario = null;
        Perfil perfil = null;
        Funcionario funcionario = null;
        List<Funcionario> funcionarios = new LinkedList();
        try {
            String pesquisa = "SELECT p.*, u.*, pe.* FROM pessoa p "
                    + "JOIN usuario u ON p.idUsuario = u.id "
                    + "Join Perfil pe ON u.idPerfil = pe.id WHERE p.nome like ?";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(pesquisa);
            ps.setString(1, "%" + nome + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                funcionario = new Funcionario();
                usuario = new Usuario();
                perfil = new Perfil();

                perfil.setNome(rs.getString("pe.nome"));
                usuario.setPerfil(perfil);
                usuario.setId(rs.getInt("u.id"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));

                funcionario.setUsuario(usuario);
                funcionario.setNome(rs.getString("p.nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEmail(rs.getString("email"));

                funcionarios.add(funcionario);
            }

        } catch (Exception e) {
            System.out.println("Erro ao procurar usuário " + e.getMessage());
        } finally {
            FabricaConexao.closeConnection(conn, ps, rs);
        }

        return funcionarios;
    }

    @Override
    public List<Funcionario> funcNaoCadastrados() throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Funcionario funcionario = null;
        List<Funcionario> funcionarios = new LinkedList();
        try {
            String pesquisa = "SELECT * FROM pessoa WHERE idUsuario is null";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(pesquisa);
            rs = ps.executeQuery();

            while (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEmail(rs.getString("email"));

                funcionarios.add(funcionario);
            }

        } catch (Exception e) {
            System.out.println("Erro ao procurar funcionarios " + e.getMessage());
        } finally {
            FabricaConexao.closeConnection(conn, ps, rs);
        }

        return funcionarios;
    }

    @Override
    public Funcionario logar(String login, String senha) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Funcionario funcionario = new Funcionario();
        Usuario usuario = new Usuario();
        funcionario.setUsuario(usuario);
        try {
            conn = FabricaConexao.getConnection();
            String consulta = "SELECT nome, login, senha FROM pessoa p "
            + "JOIN usuario u ON p.idUsuario = u.id WHERE login = ? AND "
            + "senha = ?" ;
            ps = conn.prepareStatement(consulta);
            ps.setString(1, login);
            ps.setString(2, senha);
            rs = ps.executeQuery();
            if(rs.next()){                
                usuario.setLogado(true);
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));                
                funcionario.setNome(rs.getString("nome"));
            }
            
        } catch (Exception e) {
            System.out.println("Erro ao tentar logar " + e.getMessage());
        }finally{
            FabricaConexao.closeConnection(conn, ps, rs);
        }

        return funcionario;
    }
}
