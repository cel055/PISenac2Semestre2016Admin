/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hotelProf.dao;

import br.com.hotelProf.bean.*;
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
public class FuncionarioDAOImp implements FuncionarioDAO {

    @Override
    public Object salvar(Object obj) throws Exception {
        PessoaDAO peDao = new PessoaDAOImp();
        PreparedStatement ps = null;
        Connection conn = null;
        Funcionario funcionario = (Funcionario) peDao.salvar(obj);
        try {
            String SQL = "INSERT INTO funcionario(cracha, salario, idFuncao, idTurno, idPessoa) "
                    + "VALUES(?, ?, ?, ?, ?)";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setString(1, funcionario.getCracha());
            ps.setDouble(2, funcionario.getSalario());
            ps.setInt(3, funcionario.getFuncao().getId());
            ps.setInt(4, funcionario.getTurno().getId());
            ps.setInt(5, funcionario.getId());
            ps.executeUpdate();

        } catch (SQLException se) {
            throw new Exception("Erro ao tentar inserir dados" + se.getMessage());
        } finally {
            FabricaConexao.closeConnection(conn, ps);
        }
        return funcionario;
    }

    @Override
    public void alterar(Object obj) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        PessoaDAO peDao = new PessoaDAOImp();
        peDao.alterar(obj);
        Funcionario funcionario = (Funcionario) obj;
        try {
            String SQL = "UPDATE funcionario SET cracha=?, salario=?, idTurno=?, idFuncao=? WHERE idPessoa=?";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setString(1, funcionario.getCracha());
            ps.setDouble(2, funcionario.getSalario());
            ps.setInt(3, funcionario.getTurno().getId());
            ps.setInt(4, funcionario.getFuncao().getId());
            ps.setInt(5, funcionario.getId());
            ps.executeUpdate();
        } catch (SQLException se) {
            throw new Exception("Erro ao tentar atualizar dados" + se.getMessage());
        } finally {
            FabricaConexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        PessoaDAO peDao = new PessoaDAOImp();
        peDao.excluir(id);
    }

    @Override
    public Object procurar(Integer id) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Funcionario funcionario = new Funcionario();
        Turno turno = new Turno();
        Endereco endereco = new Endereco();
        Funcao funcao = new Funcao();
        try {
            String SQL = "SELECT p.*, f.*, e.*, fu.*, t.* "
                    + "FROM pessoa p join funcionario f on p.id = f.idPessoa "
                    + "join endereco e on e.idPessoa = p.id "
                    + "join funcao fu on fu.id = f.idFuncao "
                    + "join turno t on t.id = f.idTurno WHERE p.id=?";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                funcionario.setId(rs.getInt("p.id"));
                funcionario.setNome(rs.getString("p.nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setCelular(rs.getString("celular"));
                funcionario.setSexo(rs.getString("sexo"));
                funcionario.setNascimento(rs.getDate("nascimento"));
                funcionario.setCracha(rs.getString("cracha"));
                funcionario.setSalario(rs.getDouble("salario"));
                endereco.setId(rs.getInt("e.id"));
                endereco.setRua(rs.getString("rua"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setCep(rs.getString("cep"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setSigla(rs.getString("sigla"));
                endereco.setPais(rs.getString("pais"));
                funcionario.setEndereco(endereco);
                turno.setId(rs.getInt("t.id"));
                turno.setNome(rs.getString("t.nome"));
                turno.setDescricao(rs.getString("t.descricao"));
                funcionario.setTurno(turno);
                funcao.setId(rs.getInt("fu.id"));
                funcao.setTipo(rs.getString("fu.tipo"));
                funcionario.setFuncao(funcao);

            }
        } catch (SQLException se) {
            throw new Exception("Erro ao tentar atualizar dados"
                    + se.getMessage());
        } finally {
            FabricaConexao.closeConnection(conn, ps, rs);
        }
        return funcionario;
    }

    @Override
    public List listar() throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Funcionario funcionario = new Funcionario();
        Turno turno = new Turno();
        Endereco endereco = new Endereco();
        Funcao funcao = new Funcao();
        List funcionarios = new ArrayList();
        try {
            String SQL = "SELECT p.*, f.*, e.*,fu.*, t.* "
                    + "FROM pessoa p join funcionario f on p.id = f.idPessoa "
                    + "join endereco e on e.idPessoa = p.id "
                    + "join funcao fu on fu.id = f.idFuncao "
                    + "join turno t on t.id = f.idTurno ";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                funcionario.setId(rs.getInt("p.id"));
                funcionario.setNome(rs.getString("p.nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setCelular(rs.getString("celular"));
                funcionario.setSexo(rs.getString("sexo"));
                funcionario.setNascimento(rs.getDate("nascimento"));
                funcionario.setCracha(rs.getString("cracha"));
                funcionario.setSalario(rs.getDouble("salario"));
                endereco.setId(rs.getInt("e.id"));
                endereco.setRua(rs.getString("rua"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setCep(rs.getString("cep"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setSigla(rs.getString("sigla"));
                endereco.setPais(rs.getString("pais"));
                funcionario.setEndereco(endereco);
                turno.setId(rs.getInt("t.id"));
                turno.setNome(rs.getString("t.nome"));
                turno.setDescricao(rs.getString("t.descricao"));
                funcionario.setTurno(turno);
                funcao.setId(rs.getInt("fu.id"));
                funcionario.setFuncao(funcao);
                funcionarios.add(funcionario);
            }
        } catch (SQLException se) {
            throw new Exception("Erro ao tentar atualizar dados"
                    + se.getMessage());
        } finally {
            FabricaConexao.closeConnection(conn, ps, rs);
        }
        return funcionarios;

    }

    @Override
    public List pesquisaPorNome(String nome) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Funcionario funcionario;
        Turno turno;
        Endereco endereco;
        Funcao funcao;
        List funcionarios = new ArrayList();
        try {
            String SQL = "SELECT p.*, f.*, e.*, fu.*, t.* "
                    + "FROM pessoa p join funcionario f on p.id = f.idPessoa "
                    + "join endereco e on e.idPessoa = p.id "
                    + "join funcao fu on fu.id = f.idFuncao "
                    + "join turno t on t.id = f.idTurno WHERE p.nome like ?";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setString(1, "%" + nome + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                funcionario = new Funcionario();
                turno = new Turno();
                endereco = new Endereco();
                funcao = new Funcao();
                funcionario.setId(rs.getInt("p.id"));
                funcionario.setNome(rs.getString("p.nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setCelular(rs.getString("celular"));
                funcionario.setNascimento(rs.getDate("nascimento"));
                funcionario.setCracha(rs.getString("cracha"));
                funcionario.setSalario(rs.getDouble("salario"));
                endereco.setId(rs.getInt("e.id"));
                endereco.setRua(rs.getString("rua"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setCep(rs.getString("cep"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setSigla(rs.getString("sigla"));
                endereco.setPais(rs.getString("pais"));
                funcionario.setEndereco(endereco);
                turno.setId(rs.getInt("t.id"));
                turno.setNome(rs.getString("t.nome"));
                turno.setDescricao(rs.getString("t.descricao"));
                funcionario.setTurno(turno);
                funcao.setId(rs.getInt("fu.id"));
                funcionario.setFuncao(funcao);
                funcionarios.add(funcionario);
            }
        } catch (SQLException se) {
            throw new Exception("Erro ao tentar atualizar dados"
                    + se.getMessage());
        } finally {
            FabricaConexao.closeConnection(conn, ps, rs);
        }
        return funcionarios;
    }
}
