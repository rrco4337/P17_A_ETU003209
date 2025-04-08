package servlet;
import java.io.*;

import java.sql.SQLException;



import Models.Prevision;
import jakarta.servlet.*; 
import jakarta.servlet.http.*; 
 
public class PrevisionServlet extends HttpServlet { 
 
    @Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException { 
        // try {
        //     // Récupérer la liste des départements
        //     request.setAttribute("depts", Dept.readAll());
        //     // Dispatcher vers index.html
        //     RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
        //     dispatcher.forward(request, response);
        // } catch (SQLException e) {
        //     // Gérer l'erreur
        //     e.printStackTrace();
        //     request.setAttribute("erreur", "Erreur lors de la récupération des départements");
        //     RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
        //     dispatcher.forward(request, response);
        // }
    } 
 
    @Override 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException { 
        String libelle = request.getParameter("libelle");
        String montantStr = request.getParameter("montant");
        if(libelle != null && montantStr != null ){
            try {
                int montant = Integer.parseInt(montantStr);
            
    
                Prevision prevision = new Prevision();
                prevision.setLibelle(libelle);
                prevision.setMontant(montant);
                prevision.save();
        
                response.sendRedirect("index.html");
            } catch (SQLException e) {
                throw new ServletException(e.getMessage());
            }
        }
       
    } 

 
    @Override 
    public String getServletInfo() { 
        return "Short description"; 
    } 
}