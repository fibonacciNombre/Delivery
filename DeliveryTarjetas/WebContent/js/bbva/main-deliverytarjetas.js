function irInicio(){
	window.location.reload(true);		
}

function cerrarSession(){	
	document.formlogout.submit();
}

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

function loadPerfiles(idform, idcontrol){
	
	var paramLstPerfil	= new Object();
	
	$.ajax({
		type 		: "POST",
		url 		: "/DeliveryTarjetas/usuario.do"+"?method=lstPerfil",
		cache 		: false ,
		dataType	: "json",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		async 		: false,
		data 		: paramLstPerfil,
		success 	: function(rspLstPerfil){
		
							var status 	= rspLstPerfil.statustx;
							var message = rspLstPerfil.messagetx;

							if(status == 0){													
								if(rspLstPerfil.lstPerfil!= undefined && rspLstPerfil.lstPerfil.lenght > 0){
									var lstPerfil = rspLstPerfil.lstPerfil;
									
									for(var i=0; i<lstPerfil.length; i++){											
											var opcion = '<option value="'+lstPerfil[i].idperfil+'" >'+lstPerfil[i].descripcion+'</option>' ;
											$(idform +" "+ idcontrol).append(opcion);										
									}
									
								}
							}
		},						
		error: function (rsp, xhr, ajaxOptions, thrownError) {
			closeModalCargando();
			loadModalMensaje("Error","ERROR CARGANDO PERFILES DISPONIBLES PARA LOS USUARIOS",null);								
		}			
	});	
	
}