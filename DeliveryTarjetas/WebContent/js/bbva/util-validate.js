/*INI MFARFANR*/

function agregarMetodoAlfanumerico(nombreMetodo){
	jQuery.validator.addMethod(""+nombreMetodo+"", function(value, element) {
        return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
	});
}

function agregarMetodoExceptoEspacios(nombreMetodo){
	jQuery.validator.addMethod(nombreMetodo, function(value, element) {
        return this.optional(element) || /(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{8,10})$/.test(value);
	});
}

/*FIN MFARFANR*/

function validateRimac(){
	jQuery.validator.addMethod("alphanumeric", function(value, element) {
        return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
	});
	
	jQuery.validator.addMethod("time", function(value, element) {  
		return this.optional(element) || /^(([0-1]?[0-9])|([2][0-3])):([0-5]?[0-9])(:([0-5]?[0-9]))?$/i.test(value);  
	});
	
	jQuery.validator.addMethod("dateformat", function(value, element) {
		return this.optional(element) || /\b\d{1,2}[\/-]\d{1,2}[\/-]\d{4}\b/.test(value);
	});
}

function validateItems(id_form,element){
	
	var namElement	= $(element).attr("name");
	var idElement	= $(element).attr("id");
	
	namElement 		= namElement.replace('$','\\\$');
	namElement 		= namElement.replace('?','\\\?');
	namElement 		= namElement.replace('¿','\\\¿');
	
	
	if($("#"+id_form +" #"+ namElement).valid()){
		$(".result", $("#"+idElement).parent()).html("<i class='success'></i>");
	}
	else{
		$(".result", $("#"+idElement).parent()).html("<i class='error'></i>");
	}
}

function activarChecksValidate(id_form){
	
	$("#"+id_form+" select").change(function(){
		validateItems(id_form,this);
	});

	$("#"+id_form+" input[type=text]").change(function(){
		validateItems(id_form,this);
	});

	$("#"+id_form+" select").blur(function(){
		validateItems(id_form,this);
	});

	$("#"+id_form+" input[type=text]").blur(function(){
		validateItems(id_form,this);
	});
	
	$("#"+id_form+" textarea").change(function(){
		validateItems(id_form,this);
	});
	
	$("#"+id_form+" textarea").blur(function(){
		validateItems(id_form,this);
	});
	
}

function limpiarFormulario(idFormulario){
	
	$('#'+idFormulario)[0].reset();
	
	var formulario = $('#'+idFormulario).validate();
	
	formulario.resetForm();
	
	$('#'+idFormulario + " .result").empty();
}
