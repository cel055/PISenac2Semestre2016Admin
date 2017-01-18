/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hotelProf.controle;

import br.com.hotelProf.bean.Funcionario;
import br.com.hotelProf.bean.Perfil;
import br.com.hotelProf.bean.Usuario;
import br.com.hotelProf.dao.PerfilDAO;
import br.com.hotelProf.dao.PerfilDAOImp;
import br.com.hotelProf.dao.UsuarioDAO;
import br.com.hotelProf.dao.UsuarioDAOImp;
import br.com.hotelProf.util.EnviaEmail;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
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
@WebServlet(name = "ControleUsuario", urlPatterns = {"/ControleUsuario"})
public class ControleUsuario extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher rd;
    private UsuarioDAO usuarioDAO;

    protected void processRequest() throws ServletException, IOException {
        String cmd = request.getParameter("cmd");
        switch (cmd) {
            case "criaUsuario":
                criarUsuario();
                break;
            case "salvaUsuario":
                salvaUsuario();
                break;
            case "pesq":
                pesquisaNome();
                break;
            case "pesqUsu":
                funcNaoCadastrados();
                break;
            case "logar":
                logar();
                break;
            case "sair":
                logof();
                break;
        }
        rd.forward(request, response);
    }

    private void salvaUsuario() {
        usuarioDAO = new UsuarioDAOImp();
        Usuario usuario = carregaUsuario();
        Funcionario funcionario = new Funcionario();
        funcionario.setId(Integer.parseInt(request.getParameter("id")));
        funcionario.setUsuario(usuario);
        //criar metodo para gerar senha automatica
        usuario.setSenha("11111");
        try {
            usuarioDAO.salvar(funcionario);
            //manda e-mail com dados do usuário
            EnviaEmail enviaEmail = new EnviaEmail();
            try {
                enviaEmail.enviaEmailSimples(funcionario);
            } catch (MessagingException me) {
                //montar mensagem de erro para email
                System.out.println("Erro ao enviar e-mail " + me.getMessage());
            }
            rd = request.getRequestDispatcher("/admin/index.jsp");
        } catch (Exception e) {
            System.out.println("erro ao salvar usuário " + e.getMessage());
        }
    }

    private Usuario carregaUsuario() {

        Usuario usuario = new Usuario();
        Perfil perfil = new Perfil();
        
        usuario.setLogin(request.getParameter("login"));
        String senha = request.getParameter("senha");
        if (senha != null) {
            usuario.setSenha(senha);
        }
        perfil.setId(Integer.parseInt(request.getParameter("idPerfil")));
        usuario.setPerfil(perfil);
        return usuario;
    }

    private void criarUsuario() {
        PerfilDAO perfilDAO = new PerfilDAOImp();
        try {
            List perfis = perfilDAO.listar();
            request.setAttribute("perfis", perfis);
            int idPessoa = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            request.setAttribute("idPessoa", idPessoa);
            request.setAttribute("nome", nome);
            rd = request.getRequestDispatcher("/admin/criaUsuario.jsp");
        } catch (Exception e) {
        }
    }

    private void logar() {
        usuarioDAO = new UsuarioDAOImp();
        try {
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            Funcionario funcionario = usuarioDAO.logar(login, senha);
            if (funcionario.getUsuario().isLogado()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("funcionario", funcionario);
                rd = request.getRequestDispatcher("/admin/index.jsp");
                rd.forward(request, response);
//                response.sendRedirect("admin/index.jsp");
            } else {
                request.setAttribute("mens", "Login ou Senha incorretos!");
                rd = request.getRequestDispatcher("logar.jsp");
            }
        } catch (Exception e) {
            System.out.println("Erro : " + e.getMessage() + " causa: " + e.getCause());
        }
    }

    private void logof() throws IOException {
        HttpSession session = request.getSession(true);
        session.removeAttribute("funcionario");
        rd = request.getRequestDispatcher("/logar.jsp");
    }

    private void funcNaoCadastrados() {
        usuarioDAO = new UsuarioDAOImp();
        try {
            List<Funcionario> funcionarios = usuarioDAO.funcNaoCadastrados();
            request.setAttribute("funcionarios", funcionarios);
            rd = request.getRequestDispatcher("/admin/cadUsuario.jsp");
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }

    private void pesquisaNome() {
        usuarioDAO = new UsuarioDAOImp();
        try {
            List<Funcionario> funcionarios = usuarioDAO.procuraPorNome(request.getParameter("nome"));
            request.setAttribute("funcionarios", funcionarios);
            rd = request.getRequestDispatcher("/admin/pesqUsuario.jsp");

        } catch (SQLException ex) {
            Logger.getLogger(ControleUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ControleUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        processRequest();
    }

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
