<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="model.Agenda" %>
    <% Agenda contato = (Agenda) request.getAttribute("contato"); %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Atualizar contato</title>
</head>
<body>
	<h1>Atualizar contato</h1>
	<form name="frmContato" action="atualizar">
		<input type="hidden" name="id" value="<% out.print(contato.getId()); %>">
		<table>
			<tr>
				<td><input type="text" name="nome" value="<% out.print(contato.getNome()); %>"></td>
			</tr>
			<tr>
				<td><input type="text" name="telefone" value="<% out.print(contato.getTelefone()); %>"></td>
			</tr>
			<tr>
				<td><input type="email" name="email" value="<% out.print(contato.getEmail()); %>"></td>
			</tr>
		</table>
		<input type="button" value="Atualizar" onclick="validar()" >
	</form>
	<script type="text/javascript" src="js/validator.js"></script>
</body>
</html>