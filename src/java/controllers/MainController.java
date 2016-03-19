/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.commands.CallCadastroCommand;
import controllers.commands.CommandApp;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adowt
 */
@WebServlet(name = "MainController", urlPatterns = {"/control"})
public class MainController extends HttpServlet {

    public static final Map<String, CommandApp> comandos = new HashMap<>();
    
    static {
        comandos.put("cadastro", new CallCadastroCommand());
        comandos.put("login", new CallCadastroCommand());
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //Pega a página
        String pagina = (request.getAttribute("page")==null) ? request.getParameter("page") : (String)request.getAttribute("page") ;        
        
        request.setAttribute("page", pagina);
        
        try {
            //Tenta se tem o comando
            
            //Se a página for nulo chama a padrão
            if (pagina == null) {
                //Seta o dispatcher
                RequestDispatcher rd = request.getRequestDispatcher("_layout.jsp");
                //No final, redireciona
                rd.forward(request, response);  
            } else {
                //Senão for nula chama o comando correspondente
                comandos.get(pagina).execute(request, response);
            }
        } catch (Exception ex) {
                //Seta o dispatcher
                RequestDispatcher rd = request.getRequestDispatcher("_layout.jsp");
                //No final, redireciona
                rd.forward(request, response);  
        }
    }
        

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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