/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hotelProf.dao;

import br.com.hotelProf.bean.Pessoa;
import java.sql.*;
import java.util.List;

/**
 *
 * @author Junior
 */
public  class PessoaDAOImp implements PessoaDAO{

    @Override
    public Object salvar(Object obj) throws Exception {
        Pessoa pessoa = (Pessoa) obj;
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        EnderecoDAO endDao = new EnderecoDaoImp();
        try {
            String SQL = "INSERT INTO pessoa(nome, email, telefone, cpf, rg, celular, nascimento, sexo) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getEmail());
            ps.setString(3, pessoa.getTelefone());
            ps.setString(4, pessoa.getCpf());
            ps.setString(5, pessoa.getRg());
            ps.setString(6, pessoa.getCelular());
            ps.setDate(7, new java.sql.Date(pessoa.getNascimento().getTime()));
            ps.setString(8, pessoa.getSexo());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();// para entrar na dentro do resultados
            pessoa.setId(rs.getInt(1));
            //chama as daos de endereco e usuario para salvar em sua tabelas 
            //passando o id de pessoa
            endDao.salvar(pessoa);
            
        } catch (SQLException se) {
            throw new Exception("Erro ao tentar inserir dados" + se.getMessage());
        } finally {
            FabricaConexao.closeConnection(conn, ps);
        }
        return pessoa;
    }

    @Override
    public void alterar(Object obj) throws Exception {
        Pessoa pessoa = (Pessoa) obj;
        PreparedStatement ps = null;
        Connection conn = null;
        EnderecoDAO enderecoDAO = new EnderecoDaoImp();
        try {
            String SQL = "UPDATE pessoa SET nome=?, email=?, telefone=?, cpf=?, rg=?,"
                    + "celular=?, nascimento=?, sexo=? WHERE id=?";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getEmail());
            ps.setString(3, pessoa.getTelefone());
            ps.setString(4, pessoa.getCpf());
            ps.setString(5, pessoa.getRg());
            ps.setString(6, pessoa.getCelular());
            ps.setDate(7, new Date(pessoa.getNascimento().getTime()));
            ps.setString(8, pessoa.getSexo());
            ps.setInt(9, pessoa.getId());
            ps.executeUpdate();
            enderecoDAO.alterar(pessoa);
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
            String SQL = "DELETE FROM pessoa WHERE id=?";
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Object> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
