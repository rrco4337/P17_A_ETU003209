<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Models.Depense" %>
<%@ page import="Models.Prevision" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire Dépense</title>
    <h1>ETU003209</h1>
</head>
<body>
    <h1>Ajout d'une nouvelle dépense</h1>
<% if (request.getAttribute("errorMessage") != null) { %>
    <div >
        <%= request.getAttribute("errorMessage") %>
    </div>
<% } %>
    <form action="DepenseServlet" method="post">
        <%
        Depense depense = (Depense)request.getAttribute("depense");
        List<Prevision> previsions = (List<Prevision>)request.getAttribute("previsions");
        %>
        
        <label for="idPrevision">Pour :</label>
        <select id="idPrevision" name="idPrevision" required>
            <option value="">-- Sélectionnez une prévision --</option>
            <% 
            if(previsions != null) {
                for(Prevision p : previsions) {
            %>
                <option value="<%= p.getId() %>" 
                    <%= (depense != null && depense.getIdPrevision() == p.getId()) ? "selected" : "" %>>
                    <%= p.getLibelle() %>
                </option>
            <% 
                }
            }
            %>
        </select>
        
        <br><br>
        
        <label for="montantDepense">Montant :</label>
        <input type="number" id="montantDepense" name="montant" 
               value="<%= (depense != null) ? depense.getMontantDepense() : "" %>" required>
        
        <br><br>
        
        <input type="submit" value="Valider">
    </form>
    <a href="EtatServlet">Voir l'état des prévisions</a>
</body>
</html>