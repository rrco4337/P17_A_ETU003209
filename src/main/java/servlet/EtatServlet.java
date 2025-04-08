package servlet;

import Models.Etat;
import Models.Prevision;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EtatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Etat> etats = Prevision.getEtats();
            request.setAttribute("etats", etats);
            request.getRequestDispatcher("etat.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e.getMessage());
        }
    }
}