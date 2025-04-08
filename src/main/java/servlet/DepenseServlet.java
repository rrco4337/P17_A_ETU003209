package servlet;

import java.io.IOException;
import java.sql.SQLException;

import Models.Depense;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class DepenseServlet extends HttpServlet {
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String id = request.getParameter("id");
        try {
            if(id!=null){
                Depense depense = Depense.read(Integer.parseInt(id));
                depense.delete();
            }
           
          
            request.setAttribute("depenses", Depense.readAll());
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("listedepense.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            
            throw new ServletException(e.getMessage());
        }
    }

    
    
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    int idPrevision = Integer.parseInt(request.getParameter("idPrevision"));
    int montant = Integer.parseInt(request.getParameter("montant"));
    
    try {
        Depense depense = new Depense();
        depense.setIdPrevision(idPrevision);
        depense.setMontantDepense(montant);
        depense.save();
        response.sendRedirect("FormDeServlet");
    } catch (SQLException e) {
        throw new ServletException("Erreur SQL: " + e.getMessage());
    } catch (Exception e) {
       
        request.setAttribute("errorMessage", e.getMessage());
        RequestDispatcher dispatcher = request.getRequestDispatcher("formDepense.jsp");
        dispatcher.forward(request, response);
    }
}

}

