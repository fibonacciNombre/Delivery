function irInicio(){
	window.location.reload(true);		
}

function cerrarSession(){	
	document.formlogout.submit();
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

function enterLOGIN(e) {
  
	var keycode;

	if (window.event) 
	 keycode = window.event.keyCode;//IE
	else if (e) 
	 keycode = e.which;//otros
	else 
	 return true;
  
	if (keycode == 13){
	  if(loginForm())
		  document.formlogin.submit();
	}		  
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

function llenarCombo(idControl,listaOpciones,emptyElement){
	
	var combo = $('#'+idControl); 
	combo.empty();

	if(emptyElement)
		combo.append('<option value="">'+'Seleccionar'+'</option>');
	
	for(var i=0; i<listaOpciones.length; i++){
		if(listaOpciones[i][1] != "NO DETERMINADO"){
			var opcion = '<option value="'+listaOpciones[i][0]+'" >'+listaOpciones[i][1]+'</option>' ;
			combo.append(opcion);
		}
	}
	
	$('#'+idControl).change();
}

function loadValidateLogin(idForm){
	$(idForm).validate({
		rules			: {
							userlogin	: {
									required 		: true,
									minlength 		: 7,
									maxlength 		: 15,
									alfanumerico	: true
							},
							passlogin	: {
									required 		: true,
									minlength 		: 8,
									maxlength 		: 12,
									sinespacios		: true
							}
					},
		messages 		: {
							userlogin 	: {
									required		: "Campo obligatorio",
									minlength 		: "Ingrese mínimo 7 caracteres",
									maxlength 		: "Ingrese máximo 15 caracteres",
									alfanumerico	: "En este campo sólo se permiten números y letras"
							},
							passlogin 	: {
									required		: "Campo obligatorio",
									minlength 		: "Ingrese mínimo 8 caracteres",
									maxlength 		: "Ingrese máximo 12 caracteres",
									sinespacios		: "No se permiten espacios en blanco"
							}
					}
	});	
}

function loginForm(){
	
	if($("#formlogin").valid()){

		$("#mensajes-login").slideUp(1000);
		
		var param 			= new Object();
		param.mobile 		= $("#formlogin #mobile").val();
		param.useragent	 	= navigator.userAgent+" -*- "+navigator.platform;		
		param.userlogin 	= $("#formlogin #userlogin").val();
		param.passlogin 	= $("#formlogin #passlogin").val();
		
		$.ajax({
			type 		: "POST",
			url 		: "/DeliveryTarjetas/usuario.do"+"?method=login",
			cache 		: false,
			async 		: false,
			dataType 	: 'json',
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			data		: param,
			success 	: function(rsp) {
							
							console.log(rsp);
				
							var indlogin 	= rsp.escenario;	
							
					  	    if (indlogin == 1){
									window.location.href = contextPath + "/"+ rsp.urldestino;
					  	    }else{							  	    	
					  	    	$("#formlogin #passlogin").val('');					  	    	
					  	    	$("#msj-result-login").empty();
					  	    	$("#mensajes-login").slideDown(1000);
					  	    	$("#msj-result-login").html(rsp.mensaje);
					  	    }
			
			},
			error : function(xhr, ajaxOptions, thrownError) {}
		});
				  
	}else
		return false;
	 
}

function obtDatosUsuarioSesion(){
	$.ajax({
		type 		: "POST",
		url 		: "/DeliveryTarjetas/usuario.do"+"?method=obtDatosUsuarioSesion",
		cache 		: false,
		async 		: false,
		dataType 	: 'json',
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		success 	: function(rsp) {
			
						if(rsp.Usuarioweb.length>0){
							CTE_JSON_USUARIOWEB  = rsp.Usuarioweb[0];
							$("#form-datos-usuario #idusuario").val(rsp.indmobile);
						}
						
						if(rsp.Perfil.length>0){
							CTE_JSON_PERFIL     = rsp.Perfil[0];
							$("#form-datos-usuario #idperfil").val(CTE_JSON_PERFIL.idperfil);
						}
						
						if(rsp.Tercero.length>0){
							CTE_JSON_TERCERO    = rsp.Tercero[0];
							$("#form-datos-usuario #idtercero").val(CTE_JSON_TERCERO.idtercero);
							$("#form-datos-usuario #numerodoc").val(CTE_JSON_TERCERO.nrodocumento);
							$("#form-datos-usuario #idptipodocumento").val(CTE_JSON_TERCERO.idptipodocumento);
							
						}
						
						if(rsp.Courier.length>0){
							CTE_JSON_COURIER    = rsp.Courier[0];
							$("#form-datos-usuario #idcourier").val(CTE_JSON_COURIER.idcourier);
						}
						/*
						if($.trim(CTE_JSON_TERCERO.nombres))
							$("#nombreUsuarioHeader").text(toTitleCase(CTE_JSON_TERCERO.nombres));
						*/							
				
		},
		error : function(xhr, ajaxOptions, thrownError) {}
	});			
}

function loadSesionInicial(){
	
	/*
	if((CTE_JSON_USUARIOWEB==undefined || CTE_JSON_USUARIOWEB==null)||
	     (CTE_JSON_TERCERO==undefined || CTE_JSON_TERCERO==null)) 
	)
		cerrarSession();					
	*/
	closeModalCargando();
	
	if(CTE_JSON_USUARIOWEB.estado==USR_STS_RENOVAR_PASSWORD)
		$("#link-renovarcontrasena").click();	
}