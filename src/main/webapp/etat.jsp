<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Models.Etat" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>État des prévisions et dépenses</title>
    <h1>ETU003209</h1>
    
</head>
<body>
    <h1>État des prévisions et dépenses</h1>
    
    <table border="1">
        <tr>
            <th>Prévision</th>
            <th>Montant Prévision</th>
            <th>Dépenses Total</th>
            <th>Reste</th>
        </tr>
        <% 
        List<Etat> etats = (List<Etat>) request.getAttribute("etats");
        for (Etat etat : etats) { 
        %>
        <tr>
            <td><%= etat.getLibellePrevision() %></td>
            <td><%= etat.getMontantPrevision() %></td>
            <td><%= etat.getTotalDepenses() %></td>
            <td class="<%= etat.getReste() < 0 ? "negatif" : "" %>">
                <%= etat.getReste() %>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>