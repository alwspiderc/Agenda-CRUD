/**
* Validaão de formulário
* @author Ester de Souza
 */

function validar(){
	let nome = frmContato.nome.value;
	let fone = frmContato.fone.value;
	
	if(nome === ""){
		alert("Digite seu nome!");
		frmContato.nome.focus();
		return false;
		
	}
	
	if(fone === ""){
		alert("Digite seu fone!");
		frmContato.fone.focus();
		return false;
	}
	
	document.forms["frmContato"].submit();
	
}