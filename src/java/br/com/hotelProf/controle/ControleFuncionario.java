/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hotelProf.controle;

import br.com.hotelProf.bean.Endereco;
import br.com.hotelProf.bean.Funcao;
import br.com.hotelProf.bean.Funcionario;
import br.com.hotelProf.bean.Turno;
import br.com.hotelProf.dao.FuncaoDAO;
import br.com.hotelProf.dao.FuncaoDAOImp;
import br.com.hotelProf.dao.FuncionarioDAO;
import br.com.hotelProf.dao.FuncionarioDAOImp;
import br.com.hotelProf.dao.TurnoDAO;
import br.com.hotelProf.dao.TurnoDAOImp;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author silvio
 */
@WebServlet(name = "ControleFuncionario", urlPatterns = {"/crudFuncionario"})
public class ControleFuncionario extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher rd;
    private FuncionarioDAO funcionarioDAO;
    private TurnoDAO turnoDAO;
    private FuncaoDAO funcaoDAO;
    

    protected void processRequest() throws ServletException, IOException {
        String cmd = request.getParameter("cmd");
        switch (cmd) {
            case "cadFu":
                carregaCombo();
                break;
            case "salvar":
                salvar();
                break;
            case "pesq":
                pesquisaNome();
                break;
            case "carregaFunc":
                pesquisaFuncPorId();
                break;
            case "alterar":
                alterar();
                break;
        }
        rd.forward(request, response);
    }
    
    /*
     * Metodo responsável por procurar funcionario pelo id para carregar
     * a tela para a alteração do mesmo
     */
    private void pesquisaFuncPorId(){
        funcionarioDAO = new FuncionarioDAOImp();
        int id = Integer.parseInt(request.getParameter("id"));
        try {
             Funcionario funcionario = (Funcionario) funcionarioDAO.procurar(id);
             Endereco endereco = funcionario.getEndereco();//facilita no mostrar na
             //tela, deixar a variavel endereço e não funcionario.getEndereco();
             request.setAttribute("funcionario", funcionario);
             request.setAttribute("endereco", endereco);
             rd = request.getRequestDispatcher("/admin/altFuncionario.jsp");
            
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar por id na servlet Funcionario: " + e.getMessage());
        }
    }
    
    private void pesquisaNome(){
        funcionarioDAO = new FuncionarioDAOImp();
        try {
            String nome = request.getParameter("nome");
            List funcionarios = funcionarioDAO.pesquisaPorNome(nome);
            request.setAttribute("funcionarios", funcionarios);  
            rd = request.getRequestDispatcher("/admin/pesqFuncionario.jsp");
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar funcionário " + e.getMessage());
        }
    }

    private void salvar() {
        Funcionario funcionario = carregaFuncionario();
        funcionarioDAO = new FuncionarioDAOImp();
        try {
            funcionarioDAO.salvar(funcionario);
            request.setAttribute("msg", "funcionário salvo com sucesso!");
            rd = request.getRequestDispatcher("/admin/index.jsp");
        } catch (Exception ex) {
            System.out.println("Erro ao salvar funcionário " + ex.getMessage());
            //arrumar mensagem de erro
        }
//        rd = request.getRequestDispatcher("index.jsp");
        
    }
    
    private void alterar() {
        Funcionario funcionario = carregaFuncionario();
        funcionarioDAO = new FuncionarioDAOImp();
        try {
            funcionarioDAO.alterar(funcionario);
            request.setAttribute("msg", "funcionário alterado com sucesso!");
            rd = request.getRequestDispatcher("/admin/index.jsp");
        } catch (Exception ex) {
            System.out.println("Erro ao alterar funcionário " + ex.getMessage());
            //arrumar mensagem de erro
        }
//        rd = request.getRequestDispatcher("index.jsp");
        
    }
    
    

    private Funcionario carregaFuncionario() {
        Funcionario funcionario = new Funcionario();
        Endereco endereco = new Endereco();
        Turno turno = new Turno();
        Funcao funcao = new Funcao();

        String idFuncionario = request.getParameter("id");
        if (idFuncionario != null) {
            funcionario.setId(Integer.parseInt(idFuncionario));
        }
        funcionario.setNome(request.getParameter("nome"));
        funcionario.setCpf(request.getParameter("cpf"));
        funcionario.setRg(request.getParameter("rg"));
        funcionario.setEmail(request.getParameter("email"));
        funcionario.setTelefone(request.getParameter("telefone"));
        funcionario.setCelular(request.getParameter("celular"));
        funcionario.setSexo(request.getParameter("sexo"));
//        funcionario.setSexo(request.getParameter("sexo"));
        funcionario.setSalario(Double.parseDouble(request.getParameter("salario")));
        String dataNascimento = request.getParameter("nascimento");
        //tem que converter a String para um tipo Date(util)
        Date dataConvertida = converteData(dataNascimento);
        funcionario.setNascimento(dataConvertida);
        
        endereco.setRua(request.getParameter("rua"));
        endereco.setBairro(request.getParameter("bairro"));
        endereco.setCidade(request.getParameter("cidade"));
        endereco.setEstado(request.getParameter("estado"));
        endereco.setSigla(request.getParameter("sigla"));
        endereco.setCep(request.getParameter("cep"));
        endereco.setPais(request.getParameter("pais"));
        
        turno.setId(Integer.parseInt(request.getParameter("idTurno")));
        funcao.setId(Integer.parseInt(request.getParameter("idFuncao")));
        
        funcionario.setTurno(turno);
        funcionario.setFuncao(funcao);
        funcionario.setEndereco(endereco);

        return funcionario;
    }

    private Date converteData(String dataString) {
        Date data = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            data = format.parse(dataString);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    private void carregaCombo() {
        turnoDAO = new TurnoDAOImp();
        funcaoDAO = new FuncaoDAOImp();
        try {
            List turnos = turnoDAO.listar();
            List funcoes = funcaoDAO.listar();
            HttpSession sessao = request.getSession();
            sessao.setAttribute("turnos", turnos);
            sessao.setAttribute("funcoes", funcoes);
            
            rd = request.getRequestDispatcher("/admin/cadFuncionario.jsp");
        } catch (Exception e) {
            System.out.println("erro ao carregar combo " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        processRequest();
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        processRequest();
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
