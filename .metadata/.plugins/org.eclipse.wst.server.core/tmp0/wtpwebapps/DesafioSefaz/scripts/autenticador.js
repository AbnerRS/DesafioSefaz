/**
 * Autenticar o Login do Usuário
 */


function autenticar() {

	let login = frmLogin.login.value
	let pwd = frmLogin.pwd.value

	if (login === "") {
		alert('Preencha o campo E-mail')
		frmLogin.login.focus()
		return false

	} else if (pwd === "") {
		alert('Preencha o campo Senha')
		frmLogin.pwd.focus()
		return false
	} else {
		document.forms["frmLogin"].submit()
	}
}
