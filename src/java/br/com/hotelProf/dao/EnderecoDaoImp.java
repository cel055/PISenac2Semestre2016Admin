/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hotelProf.dao;

import br.com.hotelProf.bean.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Junior
 */
public class EnderecoDaoImp implements EnderecoDAO{

    @Override
    public Object salvar(Object obj) throws Exception {
        Pessoa pessoa = (Pessoa) obj;
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String SQL = "INSERT INTO endereco(rua, bairro, cidade, cep, idPessoa, estado, sigla, pais) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setString(1, pessoa.getEndereco().getRua());
            ps.setString(2, pessoa.getEndereco().getBairro());
            ps.setString(3, pessoa.getEndereco().getCidade());
            ps.setString(4, pessoa.getEndereco().getCep());
            ps.setInt(5, pessoa.getId());
            ps.setString(6, pessoa.getEndereco().getEstado());
            ps.setString(7, pessoa.getEndereco().getSigla());
            ps.setString(8, pessoa.getEndereco().getPais());
            ps.executeUpdate();

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

        try {
            String SQL = "UPDATE endereco SET rua=?, bairro=?, cidade=?, cep=?, estado=?, sigla=?, pais=? WHERE idPessoa=?";
            conn = FabricaConexao.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setString(1, pessoa.getEndereco().getRua());
            ps.setString(2, pessoa.getEndereco().getBairro());
            ps.setString(3, pessoa.getEndereco().getCidade());
            ps.setString(4, pessoa.getEndereco().getCep());
            ps.setString(5, pessoa.getEndereco().getEstado());
            ps.setString(6, pessoa.getEndereco().getSigla());
            ps.setString(7, pessoa.getEndereco().getPais());
            ps.setInt(8, pessoa.getId());
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
    
}
