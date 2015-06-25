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
								llenarCombo(ideControl, rsp.lst, valorEmpty);
		
		},
		error : function(xhr, ajaxOptions, thrownError) {}
	});
	
}

function llenarCombo(idControl,listaOpciones,emptyElement){
	
	var combo = $('#'+idControl); 
	combo.empty();

	if(emptyElement)
		combo.append('<option value="">'+'Seleccionar'+'</option>');
	
	 
	for ( var i = 0; i < listaOpciones.length; i++) {
		$("#" + idControl).append(
				"<option value='"+listaOpciones[i].codigon+"'>"
						+ listaOpciones[i].abreviatura + "</option>");
	}
	
	$('#'+idControl).change();
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
	
	if (funcion!=null){
		$('#content-mensaje').on('hidden.bs.modal', funcion);
	}
	
	$("#link-content-mensaje").click();
}

function closeModalMensaje(){
	$("#content-mensaje").modal('hide');
};

function cleanDatatable(idTabla){
	$(idTabla).dataTable().fnClearTable();
 	$(idTabla).dataTable().fnDestroy();
}

function mostrarDatatable(idTabla){
	load_event_ajax(); 
	
	var table = $(idTabla).dataTable();
	
	if(table.fnGetData().length == 0)
         table.parent().toggle(false);
}

function rowSelected(idTabla, nomvar){
	 
	if(!window[nomvar])
		window[nomvar] = null;
	
	var table = $(idTabla).DataTable(),
		tbody = idTabla+ " tbody";
	
	$(tbody).on( 'click', 'tr', function () {	
		var data 		= table.column( 0 ).data();
		var r 			= table.row( this ).data();
		var idx 		= table.row( this ).index();
		window[nomvar] 	= {fila : r ,indice : idx};
	});
	
	return false;
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

function cargarCombo(url, method, combo, config, comboPadre) {

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
									llenarCombo2(combo, rsp.lst, true);
		},
		error			: function(xhr, ajaxOptions, thrownError) {
								loadModalMensaje('Lo sentimos',
													'Hubo un error en el procesamiento de datos.',
													function() {});
		}
	});
}

function llenarCombo2(idCombo, listaOpciones, emptyElement) {

	var combo = $('#' + idCombo);
	combo.empty();

	if (emptyElement)
		combo.append('<option value="">' + 'Seleccionar' + '</option>');

	for ( var i = 0; i < listaOpciones.length; i++) { 
		var opcion = '<option value="'+listaOpciones[i].idcourier+'" >'
				+ listaOpciones[i].rznsocial + '</option>';
		combo.append(opcion); 
	}

	$('#' + idCombo).change();
}
