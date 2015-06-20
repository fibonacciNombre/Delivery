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
			
			/*
			CTE_JSON_TELFSECPWEB = rsp.TelefonoSecweb[0];
			
			if(rsp.Correoweb.length>0)
				CTE_JSON_CORREOPWEB  = rsp.Correoweb[0];
			
			if(rsp.Direccionweb.length>0)
				CTE_JSON_DIRPWEB     = rsp.Direccionweb[0];
			
			if(rsp.Telefonoweb.length>0)
				CTE_JSON_TELFPWEB    = rsp.Telefonoweb[0];

			if(rsp.Usuarioweb.length>0)
				CTE_JSON_USUARIOPWEB = rsp.Usuarioweb[0];
			
			if(rsp.Personaweb.length>0)
				CTE_JSON_PERSONAPWEB = rsp.Personaweb[0];
			
			$("#form-datos-cliente #validacionlogin").val(rsp.validacion);
			$("#form-datos-cliente #idetercero").val(rsp.idetercero);
			$("#form-datos-cliente #codexterno").val(rsp.codexterno);
			$("#form-datos-cliente #numerodoc").val(rsp.numerodoc);
			$("#form-datos-cliente #indmobile").val(rsp.indmobile);

			CTE_MOBILE_IND = rsp.indmobile;
			
			if($.trim(CTE_JSON_PERSONAPWEB.nombre))
				$("#nameTitularHeader").text(toTitleCase(CTE_JSON_PERSONAPWEB.nombre));							
			*/				
		},
		error : function(xhr, ajaxOptions, thrownError) {}
	});			
}

function loadSesionInicial(){
	
	/*
	if(
		((CTE_JSON_USUARIOPWEB==undefined || CTE_JSON_USUARIOPWEB==null)||
	     (CTE_JSON_PERSONAPWEB==undefined || CTE_JSON_PERSONAPWEB==null)||
	     //(CTE_JSON_TELFPWEB==undefined 	|| CTE_JSON_TELFPWEB==null)||
	     //(CTE_JSON_DIRPWEB==undefined 	|| CTE_JSON_DIRPWEB==null)||
	     (CTE_JSON_CORREOPWEB==undefined 	|| CTE_JSON_CORREOPWEB==null)||
	     ($("#form-datos-cliente #idetercero").val()=="" || $("#form-datos-cliente #idetercero").val()=="null")||
	     ($("#form-datos-cliente #codexterno").val()=="" || $("#form-datos-cliente #codexterno").val()=="null")
	    ) && 
		   $("#form-datos-cliente #validacionlogin").val()!=RSP_LOGIN_REG_DIRECCION
	){
		cerrarSession();					
	}else{
		CTE_IDETERCERO 		= $("#form-datos-cliente #idetercero").val();
		CTE_NUM_DOC 		= $("#form-datos-cliente #numerodoc").val();
		CTE_RESP_VAL_LOGIN 	= $("#form-datos-cliente #validacionlogin").val();
	*/	
		if(CTE_RESP_VAL_LOGIN==USR_STS_RENOVAR_PASSWORD){
			closeModalCargando();						 
			$("#link-renovarcontrasena").click();
		}
		
	//}	
}