package util;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        String uri = req.getRequestURI();

        //lista branca para sem verificação
        if(uri.endsWith("index.html") || uri.endsWith("login") || uri.contains(".css") || uri.contains(".js")){
            chain.doFilter(request, response);

            return;
        }
        //verifica se tem alguma sessão ativa
        if(session == null || session.getAttribute("usuario") == null) {
            res.sendRedirect(req.getContextPath()+"index.html");
            return;
        }

        String permissao = (String) session.getAttribute("grupoPermissao");
        if(uri.contains("cadastro") && !"admin".equals(permissao)){
            res.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        chain.doFilter(request, response);
    }
}