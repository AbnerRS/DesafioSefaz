/**
 * Validação de Formulário
 */

function validar() {
	let nome = frmUsuario.nome.value
	let email = frmUsuario.email.value
	let telefoneDdd = frmUsuario.telefoneDdd.value
	let telefoneNumero = frmUsuario.telefoneNumero.value
	let telefoneTipo = frmUsuario.telefoneTipo.value
	let senha = frmUsuario.senha.value

	if (nome === "") {
		alert('Preencha o campo Nome')
		frmUsuario.nome.focus()
		return false
	} else if (email === "") {
		alert('Preencha o campo E-mail')
		frmUsuario.email.focus()
		return false
	} else if (telefoneDdd === "") {
		alert('Preencha o campo de DDD')
		frmUsuario.telefoneDdd.focus()
		return false
	}
	else if (telefoneNumero === "") {
		alert('Preencha o campo de Número')
		frmUsuario.telefoneNumero.focus()
		return false
	} else if (telefoneTipo === "") {
		alert('Preencha o campo de Tipo de Telefone')
		frmUsuario.telefoneTipo.focus()
		return false
	} else if (senha === "") {
		alert('Preencha o campo Senha')
		frmUsuario.senha.focus()
		return false
	} else {
		document.forms["frmUsuario"].submit()
	}
}