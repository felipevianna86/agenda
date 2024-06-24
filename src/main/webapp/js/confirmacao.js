

function confirmar(id){
	
	if(confirm('Confirma a exclusão do contato?')){
		window.location.href = "remover?id=" + id
	}
}