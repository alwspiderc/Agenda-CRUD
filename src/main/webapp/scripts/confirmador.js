/**
	Confirma��o de exclus�o de contato 
	@author Ester de Souza
 */
 
 function confirmar (idcon){
	let resposta = confirm("Confirma a exclus�o deste contato? ");
	
	if(resposta === true){
		//alert(idcon);
		window.location.href="delete?idcon="+idcon
	}
	
}