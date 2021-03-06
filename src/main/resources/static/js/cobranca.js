$("#confirmacaoExclusaoModal").on("show.bs.modal", function(event){
	var button = $(event.relatedTarget)
	
	var codigoTitulo = button.data('codigo')
	var tituloDescricao = button.data('descricao')
	
	var modal = $(this)
	var form = modal.find('form')
	var action = form.data('url-base')
	if(!action.endsWith('/')){
		action += '/'
	}
	
	form.attr('action', action + codigoTitulo)
	modal.find('.modal-body').html('Tem certeza que deseja excluir o título <strong>' + tituloDescricao + '</strong>?')	
})

$(function(){
	$('[rel="tooltip"]').tooltip();
})