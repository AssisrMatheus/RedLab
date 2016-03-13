/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;
import model.dao.UsuarioJpaController;

/**
 *
 * @author adowt
 */
@WebServlet(name = "SistemaController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

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
        
         //Se não achar o usuário, quer voltar para a home então já seta
         //Pois se achar usuario, este atributo será alterado
        String pagina = "home";

        //Pega o login preenchido
        Usuario usuario = new Usuario(request);

        //Cria uma conexao e tenta achar se o usuario digitado existe
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RedLabPU");
        Usuario usuarioEncontrado = new UsuarioJpaController(emf).findUsuario(usuario.getLogin());

        //Se não achar nada ele é nulo então já sai
        //Se achar, verifica se o login e senha realmente são como o digitado
        if (usuarioEncontrado != null
                && (usuarioEncontrado.getSenha().equals(usuario.getSenha()))
                && (usuarioEncontrado.getLogin().equals(usuario.getLogin()))) {
            //Então redireciona para dentro do sistema de acordo com seu tipo
            pagina = (usuarioEncontrado.getIsAdmin()) ? "admin" : "usuario" ;
        } 

        //Cria o dispatcher, pega o dispatcher e faz o forward
        RequestDispatcher rd = request.getRequestDispatcher("/home");
        
        //Carrega os parametros no forward para saber lá na frente como funciona
        request.setAttribute("page", pagina);

        rd.forward(request, response);

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