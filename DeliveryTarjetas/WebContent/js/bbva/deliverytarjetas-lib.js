function callService(methodType, queryUrl, isAsync, dataToSend, callbackSuccess, callComplete, callError){
	$.ajax({
		type : methodType ,
		url : queryUrl ,
		cache : false ,
		dataType: 'json',
		async : isAsync ,
		data: dataToSend ,
		success : function(rsp) {
			callbackSuccess(rsp);
		},
		complete : function (rsp){		
			callComplete(rsp);
		},
		error: function (rsp) {
			callError(rsp);
		}
	});	
}

function callServiceByGET(queryUrl, isAsync, dataToSend, callbackSuccess, callComplete, callError){
	callService("GET", queryUrl, isAsync, dataToSend, callbackSuccess,callComplete, callError);
}

function callServiceByPOST(queryUrl, isAsync, dataToSend, callbackSuccess, callComplete, callError){
	callService("POST", queryUrl, isAsync, dataToSend, callbackSuccess,callComplete, callError);
}

function callCargaControlParam(ideParametro,ideControl, valorEmpty){
	
	var param 		= new Object();
	param.idparametrotipo 		= ideParametro;
	
	$.ajax({
		type 		: "POST",
		url 		: "/DeliveryTarjetas/comun.do"+"?method=lstParametro",
		cache 		: false,
		async 		: false,
		dataType 	: 'json',
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		data		: param,
		success 	: function(rsp) {
							var status 	= rsp.tx.statustx;
							var message = rsp.tx.messagetx;
							
							if(status==0)
								llenarCombo(ideControl, rsp.lst, ['codigon','abreviatura'], valorEmpty);
		
		},
		error : function(xhr, ajaxOptions, thrownError) {}
	});
	
}

function llenarCombo(idCombo, listaOpciones, arrayFields,  emptyElement) {

	var combo = $('#' + idCombo);
	combo.empty();

	if (emptyElement)
		combo.append('<option value="">' + 'Seleccionar' + '</option>');

	for ( var i = 0; i < listaOpciones.length; i++) { 
		var opcion = '<option value="'+listaOpciones[i][arrayFields[0]]+'" >'
				+ listaOpciones[i][arrayFields[1]] + '</option>';
		combo.append(opcion); 
	}

	$('#' + idCombo).change();
}

function cargarComboArray(idCombo, arrayItems) {
	for ( var i = 0; i < arrayItems.length; i++) {

	 	$("#" + idCombo).children('option:not(:first)').remove();
		$('#mySelect')
	    
	    .val('whatever')
	;
		$("#" + idCombo).append(
				'<option value="'+ arrayItems[i][0] +'">'
						+ arrayItems[i][1] + '</option>');
	}
}

function cargarCombo(url, method, combo, arrayFields, config, comboPadre) {

	var param = new Object();

	for ( var key in config)
		param[key] = config[key];
	
	if (config.argPadre && comboPadre)
		param[config.argPadre] = comboPadre.value;

	combo = config.form ? config.form + ' #' + combo : '#' + combo;

	$.ajax({
		type 			: "POST",
		url 			: url + "?method=" + method,
		cache 			: false,
		dataType 		: "json",
		contentType 	: "application/x-www-form-urlencoded; charset=UTF-8",
		async 			: false,
		data 			: param,
		success 		: function(rsp) {
								var status 	= rsp.tx.statustx;
								if(status==0)
									llenarCombo(combo, rsp.lst, arrayFields,  true);
		},
		error			: function(xhr, ajaxOptions, thrownError) {
								loadModalMensaje('Lo sentimos',
													'Hubo un error en el procesamiento de datos.',
													function() {});
		}
	});
}

function toTitleCase(str){
	var strTittleCase = str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
    return strTittleCase;
}

function slug(str) {
	str = str.replace(/^\s+|\s+$/g, ''); // trim
	str = str.toLowerCase();

	// remove accents, swap ñ for n, etc
	var from = "ãàáäâẽèéëêìíïîõòóöôùúüûñç·/_,:;";
	var to   = "aaaaaeeeeeiiiiooooouuuunc------";
	
	for (var i=0, l=from.length ; i<l ; i++) {
		str = str.replace(new RegExp(from.charAt(i), 'g'), to.charAt(i));
	}

	str = str.replace(/[^a-z0-9 -]/g, '') // remove invalid chars
				.replace(/\s+/g, '-') // collapse whitespace and replace by -
				.replace(/-+/g, '-'); // collapse dashes

	return str;
};

function replaceAll(str, find, replace){
	return str.replace(new RegExp(find, 'g'), replace);
}

function bindBreadcrumb(){
    $("#accordion li a").each(function(index){
    	 
    	$(this).click(function(event){
    		
    		$("#content-breadcrumb").show();
    		$("#breadcrumb_list li").removeClass("active");
    		var indRemove		= "";
    		
            var attrHREF 		= $(this).attr('data-url');
            var attrTEXT 		= $(this).text();
            var attrDATA_PARENT	= $(this).attr('data-parent');
            
            if(attrDATA_PARENT != null && attrDATA_PARENT == "#accordion")	            	
            	$("#breadcrumb_list").empty();            	
            else
            	indRemove = "remove";
            	
            $("#breadcrumb_list .remove").remove();
            
            $("#breadcrumb_list").append("<li class='"+ indRemove +"'><a class='method-ajax' href='"+attrHREF+"' >"+attrTEXT+"</a><span class='divider'></span></li>");
            
            $("#breadcrumb_list li").last().addClass("active");
        });
        
    });
}

function loadModalCargando(){
	$("#link-content-cargando").click();
}

function closeModalCargando(){
	$("#content-cargando").modal('hide');
}

function loadModalMensaje(titulo,contenido,funcion){
	
	$("#titulo-mensaje").empty();
	$("#titulo-mensaje").html(titulo);
	
	$("#contenido-mensaje").empty();
	$("#contenido-mensaje").html(contenido);
	
	$('#content-mensaje').unbind( "hidden.bs.modal" );
	
	if (funcion!=null)
		$('#content-mensaje').on('hidden.bs.modal', funcion);
	
	$("#link-content-mensaje").click();
}

function closeModalMensaje(){
	$("#content-mensaje").modal('hide');
};

function cleanDatatable(idTabla){
	$("#"+idTabla).dataTable().fnClearTable();
 	$("#"+idTabla).dataTable().fnDestroy();
}

function mostrarDatatable(idTabla){
	load_event_ajax(); 
	
	var table = $(idTabla).dataTable();
	
	if(table.fnGetData().length == 0)
         table.parent().toggle(false);	
}

function validateAlfanumerico(){
	jQuery.validator.addMethod("alfanumerico", function(value, element) {
        return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
	});
}

function validateSinEspacios(){
	jQuery.validator.addMethod("sinespacios", function(value, element) {
        return this.optional(element) || /^[^\s]+$/.test(value);
	});
}

function initDatePicker(idCtrl, classCtrl){
	$("#"+idCtrl).datepicker({ 
		beforeShow 	: function() {
			setTimeout(function() {
				$('.ui-datepicker').css({'z-index' : 9999, 'border' : '1px solid #ccc'});
			}, 0);
		},
		onSelect : function(dateText, inst) {
			$("#"+idCtrl).removeClass("error");
		}
	});
				
	$("."+classCtrl).click(function() {
		$("#"+idCtrl).datepicker("show");
	});
}