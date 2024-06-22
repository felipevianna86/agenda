/**
 * 
 */

function validar(){
	var nome = frmContato.nome.value;
	var telefone = frmContato.telefone.value;
	var email = frmContato.email.value;
	
	if(nome === "" || telefone === "" || email === ""){
		alert('Campos obrigatórios não preenchidos.');
		return false;
	}
	
	document.forms["frmContato"].submit()
}