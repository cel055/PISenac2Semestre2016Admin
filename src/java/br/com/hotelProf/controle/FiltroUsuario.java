/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hotelProf.controle;

import br.com.hotelProf.bean.Funcionario;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Junior
 */
@WebFilter(filterName = "filtraLogin", value = "/admin/*")
public class FiltroUsuario implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Aqui chegou");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String cmd = request.getParameter("cmd");
        HttpServletRequest httpReq = null;
            httpReq = (HttpServletRequest) request;
            System.out.println(httpReq.getServletPath());
        if (cmd == null) {
            cmd = "";
        }
        HttpSession session = ((HttpServletRequest) request).getSession();
        RequestDispatcher rd;
        Funcionario funcionario = (Funcionario) 
                session.getAttribute("funcionario");
        if(httpReq.getServletPath().equals("/logar.jsp")){
            chain.doFilter(request, response);
        }else if (funcionario == null && cmd.equals("logar")) {
            chain.doFilter(request, response);
        } else if (funcionario != null) {
            chain.doFilter(request, response);
        } else {
                request.setAttribute("mens", "Você não está logado no sistema!");
            rd = request.getRequestDispatcher("/PISenac/logar.jsp");
            rd.forward(request, response);
        }

    }

    @Override
    public void destroy() {
    }
}
