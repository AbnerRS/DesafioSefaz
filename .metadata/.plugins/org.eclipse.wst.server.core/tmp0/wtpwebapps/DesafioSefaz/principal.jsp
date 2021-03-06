<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="br.com.desafiosefaz.model.Usuario"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<Usuario> lista = (ArrayList<Usuario>) request.getAttribute("usuarios");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Lista de Usuários</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Lista de Usuários</h1>

	<table id="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Email</th>
				<th>Telefone</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < lista.size(); i++) {
			%>
			<tr>
				<td><%=lista.get(i).getId()%></td>
				<td><%=lista.get(i).getNome()%></td>
				<td><%=lista.get(i).getEmail()%></td>
				<td><%=lista.get(i).getTelefone().get(i)%></td>
				<td><a href="select?id=<%=lista.get(i).getId()%>" class="Botao">Editar</a>
					<a href="javascript: confirmar(<%=lista.get(i).getId()%>)"
					class="Botao2">Excluir</a></td>
			</tr>
			<%
			}
			%>

		</tbody>
	</table>
	<tr>
		<td><a href="novo.html" class="Botao">Novo contato</a> <a
			href="index.html" id="botaoSair">Sair</a></td>
	</tr>
	<script src="scripts/confirmador.js"></script>
</body>
</html>