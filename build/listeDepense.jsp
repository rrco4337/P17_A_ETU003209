<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Models.Depense" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Employés</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f5f5f5;
        }
        
        h1 {
            color: #333;
            text-align: center;
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            background-color: white;
            box-shadow: 0 1px 3px rgba(0,0,0,0.2);
        }
        
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        
        th {
            background-color: #4CAF50;
            color: white;
        }
        
        tr:hover {
            background-color: #f5f5f5;
        }
        
        .empty-message {
            text-align: center;
            color: #666;
            font-style: italic;
        }
        
        .actions {
            margin-top: 20px;
            text-align: center;
        }
        
        .actions a {
            display: inline-block;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        
        .actions a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h1>ETU003209</h1>
    <h1>Liste des Employés</h1>
    
    <% 
    List<Emp> emps = (List<Emp>)request.getAttribute("emps");
    if(emps != null && !emps.isEmpty()) {
    %>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Département</th>
                    <th>Département ID</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% for(Emp emp : emps) { %>
                    <tr>
                        <td><%= emp.getId() %></td>
                        <td><%= emp.getNomEmp() %></td>
                        <td><%= emp.getDept(emp.getDeptId()).getNomDept() %></td>
                        <td><%= emp.getDeptId() %></td>
                        <td><a href="FormEmpServlet?id=<%= emp.getId() %>">Modifier</a>
                            <a href="EmpServlet?id=<%= emp.getId() %>">Supprimer</a> 
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p class="empty-message">Aucun employé trouvé dans la base de données.</p>
    <% } %>
    
    <div class="actions">
        <a href="FormEmpServlet">Ajouter un nouvel employé</a>
    </div>
</body>
</html>