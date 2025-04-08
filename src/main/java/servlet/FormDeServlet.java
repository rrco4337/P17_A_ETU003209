package servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.*;
import java.sql.SQLException;


import Models.Depense;
import Models.Prevision;
import jakarta.servlet.*;
public class FormDeServlet extends HttpServlet{
    @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        try {
           
            request.setAttribute("depenses", Depense.readAll());
            
 
            request.setAttribute("previsions", Prevision.readAll()); 
            
            if (idParam != null) {
                Depense depense = Depense.read(Integer.parseInt(idParam));
                request.setAttribute("depense", depense);
            }
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("formDepense.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
