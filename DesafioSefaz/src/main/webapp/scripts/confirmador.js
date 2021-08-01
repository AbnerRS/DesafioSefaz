/**
 * Confirmação de exclusão de um usuário
 */


function confirmar(id) {
	let resposta = confirm("Você deseja realmente excluir este usuário ?")
	if (resposta === true) {
		window.location.href = "delete?id=" + id
	}
}