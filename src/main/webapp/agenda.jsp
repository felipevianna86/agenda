<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="model.Agenda" %>
    <%@ page import="java.util.List" %>
    <% List<Agenda> contatos = (List<Agenda>) request.getAttribute("contatos"); %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Agenda</title>
</head>
<body>
	<h1>Agenda de Contatos</h1>
	<a href="/agenda/novo.html">Criar contato</a>
	<table>
		<thead>
			<tr>
				<th>Nome</th>
				<th>Telefone</th>
				<th>E-mail</th>
			</tr>
		</thead>
		<tbody>
			<% if(contatos != null){ %>
				<% for(int i = 0; i < contatos.size(); i++) { %>
					<tr>
						<td><%= contatos.get(i).getNome()  %></td>
						<td><%= contatos.get(i).getTelefone()  %></td>
						<td><%= contatos.get(i).getEmail()  %></td>
					</tr>
				<% }  %>
			<% }  %>
		</tbody>
	</table>
	
</body>
</html>