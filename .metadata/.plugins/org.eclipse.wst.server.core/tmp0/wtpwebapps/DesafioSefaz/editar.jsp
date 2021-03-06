<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Editar Usuário</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar Usuário</h1>
	<form name="frmUsuario" action="update">
		<table>
			<tr>
				<td><input type="text" name="id" id="caixa3" readonly
					value="<%out.print(request.getAttribute("id"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="Caixa1"
					value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="email" class="Caixa1"
					value="<%out.print(request.getAttribute("email"));%>"></td>
			</tr>
			<tr>
				<td><input type="tel" name="telefoneDdd" class="Caixa2"
					value="<%out.print(request.getAttribute("telefoneDdd"));%>">

					<input type="text" name="telefoneNumero" class="Caixa5"
					value="<%out.print(request.getAttribute("telefoneNumero"));%>">

					<input type="text" name="telefoneTipo" class="Caixa5"
					value="<%out.print(request.getAttribute("telefoneTipo"));%>"></td>

			</tr>
			<tr>
				<td><input type="password" name="senha" class="Caixa1"
					value="<%out.print(request.getAttribute("senha"));%>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="Botao" onclick="validar()">
	</form>

	<script src="scripts/validador.js"></script>
</body>
</html>