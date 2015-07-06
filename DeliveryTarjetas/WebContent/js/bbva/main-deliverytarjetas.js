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
									minlength 		: 5,
									maxlength 		: 15,
									alfanumerico	: true
							},
							passlogin	: {
									required 		: true,
									minlength 		: 5,
									maxlength 		: 12,
									sinespacios		: true
							}
					},
		messages 		: {
							userlogin 	: {
									required		: "Campo obligatorio",
									minlength 		: "Ingrese mínimo 5 caracteres",
									maxlength 		: "Ingrese máximo 15 caracteres",
									alfanumerico	: "En este campo sólo se permiten números y letras"
							},
							passlogin 	: {
									required		: "Campo obligatorio",
									minlength 		: "Ingrese mínimo 5 caracteres",
									maxlength 		: "Ingrese máximo 12 caracteres",
									sinespacios		: "No se permiten espacios en blanco"
							}
					}
	});	
}

function loginForm(){
	
	if($("#formlogin").valid()){

		$("#mensajes-login").slideUp(1000);
		
		loadModalCargando();
		
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
							
							closeModalCargando();
							
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
						
						if($.trim(CTE_JSON_TERCERO.nombres))
							$("#nombreUsuarioHeader").text("Bienvenido "+ toTitleCase(CTE_JSON_TERCERO.nombres));
						
		},
		error : function(xhr, ajaxOptions, thrownError) {}
	});			
}

function loadSesionInicial(){
	
	if((CTE_JSON_USUARIOWEB==undefined || CTE_JSON_USUARIOWEB==null) || (CTE_JSON_TERCERO==undefined || CTE_JSON_TERCERO==null))
		cerrarSession();					
	
	if (CTE_JSON_PERFIL.idperfil != CTE_INIT_IDROL_ADMIN_WEB)
		$(".view-admin").remove();
	
	if(CTE_JSON_USUARIOWEB.indrnvcontrasena=="S"){
		closeModalCargando();
		$("#link-renovarcontrasena").click();	
	}else{	
		
		CTE_LOAD_INIT 					= 1;		
		CTE_INIT_PARAM_ESTADO 			= obtParametrosMaestros('DELWEB_ESTADO');
		CTE_INIT_PARAM_TIPDOCUMENTO 	= obtParametrosMaestros('DELWEB_TIPODOCUMENTO');
		CTE_INIT_PERFILES				= obtMaestroPerfil();
		CTE_INIT_COURIERS				= obtMaestroCouriers();
		
		$("#panelDelivery").click();
		$("#view-lst-entrega").click();
	}
}

function obtMaestroPerfil(){
	
	var rptaLstPerfil;
	var param 	= new Object();
	
	$.ajax({
	type 		: "POST",
	url 		: "/DeliveryTarjetas/perfil.do"+"?method=lstPerfil",
	cache 		: false ,
	dataType	: "json",
	contentType : "application/x-www-form-urlencoded; charset=UTF-8",
	async 		: false,
	data 		: param,
	success 	: function(rsp){
	
						var status 	= rsp.tx.statustx;
						var message = rsp.tx.messagetx;

						if(status == 0)									
								rptaLstPerfil = rsp.lst;						
	},						
	error		: function (rsp, xhr, ajaxOptions, thrownError) {}			
	});		
	
	return rptaLstPerfil;
	
}	

function obtMaestroCouriers(){
	
	var rptaLstPerfil;
	var param 	= new Object();
	
	$.ajax({
	type 		: "POST",
	url 		: "/DeliveryTarjetas/courier.do"+"?method=lstCourier",
	cache 		: false ,
	dataType	: "json",
	contentType : "application/x-www-form-urlencoded; charset=UTF-8",
	async 		: false,
	data 		: param,
	success 	: function(rsp){
	
						var status 	= rsp.tx.statustx;
						var message = rsp.tx.messagetx;

						if(status == 0)									
								rptaLstPerfil = rsp.lst;						
	},						
	error		: function (rsp, xhr, ajaxOptions, thrownError) {}			
	});		
	
	return rptaLstPerfil;
	
}

function obtParametrosMaestros(idParametro){
	
	var rpta;
	var param 					= new Object();
	param.idparametrotipo 		= idParametro;
	
	
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
								rpta = rsp.lst;		
		},
		error : function(xhr, ajaxOptions, thrownError) {}
	});
	
	return rpta;
}

function bsqDeliveryUtil(paramQuery){
	
	var lstDelivery;
	
	$.ajax({
		type 			: "POST",
		url 			: "/DeliveryTarjetas/delivery.do?method=lstDelivery",
		cache 			: false,
		async 			: false,
		dataType 		: 'json',
		contentType 	: "application/x-www-form-urlencoded; charset=UTF-8",
		data 			: paramQuery,
		success 		: function(rsp) {				
								
								var status 	= rsp.tx.statustx;
								var message = rsp.messagetx;
			
								closeModalCargando();
								
								if(status == 0){													
									if(rsp.lst!= undefined && rsp.lst.length > 0){											
										lstDelivery = rsp.lst;
									
									}else
										loadModalMensaje("Atención","No se encontraron resultados por la búsqueda realizada",null);
								}
		},
		error 			: function(xhr, ajaxOptions, thrownError) {
								closeModalCargando();
								loadModalMensaje('Lo Sentimos','<center>Hubo problemas en el procesamiento de datos.</center>',function(){}); 
		}
	});
	
	return lstDelivery;
}

function obtArchivoLstEntregas(paramQuery){
	
	var pathFile		= "";
	
	$.ajax({
		type 			: "POST",
		url 			: "/DeliveryTarjetas/delivery.do?method=obtArchivoLstEntregas",
		cache 			: false,
		async 			: false,
		dataType 		: 'json',
		contentType 	: "application/x-www-form-urlencoded; charset=UTF-8",
		data 			: paramQuery,
		success 		: function(rsp) {
								
								var status 	= rsp.tx.statustx;
								var message = rsp.tx.messagetx;

								closeModalCargando();
								
								console.log(rsp.archivo);
								
								if(status==0){									
									pathFile	= rsp.archivo
									loadModalMensaje('Enhorabuena','Se ha exportado la lista a excel',function(){});	
								}else{
									loadModalMensaje('Atención',message,function(){}); 
								}
								 										 
		},
		error 			: function(xhr, ajaxOptions, thrownError) {
			console.log(xhr);
			console.log(ajaxOptions);
			console.log(thrownError);
								closeModalCargando();
								loadModalMensaje('Lo Sentimos','<center>Hubo problemas en el procesamiento de datos.</center>',function(){}); 
		}
	});
	
	return pathFile;
}

function linkDetalleDelivery(full, formEdit) {
	enlace = "<a data-toggle='modal' "
				+ "data-target='#modalDetalleEntrega' "
				+ "onclick='return rowEntregaSelectedUtil("+ JSON.stringify(JSON.stringify(full)) +","+formEdit+");'>"
					+ "<i class='i-detalle'></i>" 
			+ "</a>";

	return enlace;
}		

function linkPDF(iddelivery) {
	enlace = "<a data-toggle='modal' "
		 + "onclick='return getClickPdf("+ iddelivery + ");'>"
			+ "<i class='i-pdf'></i>" 
	+ "</a>";

return enlace;
}

function rowEntregaSelectedUtil(json, formEdit) {
	
	loadModalCargando();

	json = JSON.parse(json);
	
	$("#tabs-detalle-delivery").tabs();
	
	initDatePicker("fecentregaarch","calendario2");
	
	cargarCombo('/DeliveryTarjetas/courier.do', 'lstCourier','idcourier', ['idcourier','rznsocial'], {form: 'form-detdelivery'});
	
	callCargaControlParam('DELWEB_ESTADO','form-detdelivery #idpestado',false);
	
	callCargaControlParam('DELWEB_ESTADODELIVERY','form-detdelivery #idpestadodelivery',false);
	
	callCargaControlParam('DELWEB_ESTADOCARGA','form-detdelivery #idpestadocarga',false);
	
	cargarComboArray('form-detdelivery #indverificacion',[['S','SI'], ['N','NO']])
	
	/*
	var paramCourier		= new Object();
	paramCourier.idecourier	= json.idcourier;
	
	$.ajax({
		type 		: "POST",
		url 		: "/DeliveryTarjetas/courier.do"+"?method=lstCourier",
		cache 		: false ,
		dataType	: "json",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		async 		: false,
		data 		: paramCourier,		
		success 	: function(rsp){

						var statustx	= rsp.tx.statustx;
						var messagetx	= rsp.tx.messagetx;
						
						if(statustx == 0){																
						}
		}
	});
	
	var paramTercero 		= new Object();
	paramTercero.idtercero	= json.idtercero;
	
	$.ajax({
		type 		: "POST",
		url 		: "/DeliveryTarjetas/tercero.do"+"?method=lstTerceros",
		cache 		: false ,
		dataType	: "json",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		async 		: false,
		data 		: paramTercero,		
		success 	: function(rsp){

						var statustx	= rsp.tx.statustx;
						var messagetx	= rsp.tx.messagetx;
						
						if(statustx == 0){								
						}
		}
	});
	*/ 
	
	$("#form-detdelivery #iddelivery").val(json.iddelivery);
    $("#form-detdelivery #tipodocumento").val(json.tipodocumento);
    $("#form-detdelivery #nrodocumentocli").val(json.nrodocumentocli);
    $("#form-detdelivery #nombrescli").val(json.nombrescli);
    $("#form-detdelivery #tipotarjeta").val(json.tipotarjeta);
    $("#form-detdelivery #pridigtarjeta").val(json.pridigtarjeta);
    $("#form-detdelivery #ultdigtarjeta").val(json.ultdigtarjeta);
    $("#form-detdelivery #nrocontrato").val(json.nrocontrato);
    $("#form-detdelivery #mtoasoctarjeta").val(json.mtoasoctarjeta);
    $("#form-detdelivery #fecentrega").val(json.fecentrega);
    $("#form-detdelivery #horaentrega").val(json.horaentrega);
    $("#form-detdelivery #lugarentrega").val(json.lugarentrega);
    $("#form-detdelivery #indverificacion").val(json.indverificacion);
    $("#form-detdelivery #direccioncli").val(json.direccioncli);
    $("#form-detdelivery #distritocli").val(json.distritocli);
    $("#form-detdelivery #latitudofi").val(json.latitudofi);
    $("#form-detdelivery #longitudofi").val(json.longitudofi);
    $("#form-detdelivery #correocli").val(json.correocli);
    $("#form-detdelivery #telmovilcli").val(json.telmovilcli);
    $("#form-detdelivery #ordenentrega").val(json.ordenentrega);
    $("#form-detdelivery #idcourier").val(json.idcourier);
    $("#form-detdelivery #idtercero").val(json.idtercero);
    $("#form-detdelivery #idpestado").val(json.idpestado);
    $("#form-detdelivery #idarchivo").val(json.idarchivo);
    $("#form-detdelivery #idpestadocarga").val(json.idpestadocarga);
    $("#form-detdelivery #historial").val(json.historial);
    $("#form-detdelivery #grupocarga").val(json.grupocarga);
    $("#form-detdelivery #idcourier").val(json.idcourier);
    $("#form-detdelivery #fecentregaarch").val(json.fecentregaarch);
    $("#form-detdelivery #idpestadodelivery").val(json.idpestadodelivery);
    
    $("#form-detdelivery #idpestadodelivery").attr("disabled","disabled");
    
    if(!formEdit){
    	$("#form-detdelivery *").attr("disabled",true);
    }
    
    closeModalCargando();
}

function getClickPdf(iddelivery){
	
	loadModalCargando();
	
	var param			= new  Object();
	param.codentrega		= iddelivery;
	
	$.ajax({
		type 			: "POST", 
		url 			: "/DeliveryTarjetas/delivery.do?method=getArchivoPDF",
		cache 			: false,
		async 			: false,
		dataType 		: 'json',
		data			: param,
		contentType 	: "application/x-www-form-urlencoded; charset=UTF-8",
		success 		: function(rsp) {
								
								var status 	= rsp.tx.statustx;
								var message = rsp.tx.messagetx;
		
								closeModalCargando();
								
								console.log(rsp.archivopdf);
								
								if(status==0){									
									var archivopdf	= rsp.archivopdf
									
									if(archivopdf.codigo!="" && archivopdf.codigo=="0"){
										loadModalMensaje('Enhorabuena','Se ha generado el PDF correctamente',function(){});
										console.log(archivopdf.archivo);
										window.open("../"+archivopdf.archivo, 'NewWin');
									}
									
									if(archivopdf.codigo!="" && archivopdf.codigo=="1")
											loadModalMensaje('Atención',archivopdf.mensaje,function(){}); 
									
								}else{
									loadModalMensaje('Atención',message,function(){}); 
								}
		},
		error 			: function(xhr, ajaxOptions, thrownError) {						
		}
	});		
}

function obtDescripcionParametro(lstValParam, codigoc, codigon){
	
	var descripcion;
	
	for(var i=0; i<lstValParam.length; i++){
		if(codigoc!=null){
			if(lstValParam[i].codigoc == codigoc){
				descripcion = lstValParam[i].descripcion;			
			}
		}
		
		if(codigon!=null){
			if(lstValParam[i].codigon == codigon){
				descripcion = lstValParam[i].descripcion;			
			}
		}
	}
	
	return descripcion;	
}

function obtDescripcionPerfil(idperfil){
	
	var descripcion;
	
	for(var i=0; i<CTE_INIT_PERFILES.length; i++){
		if(CTE_INIT_PERFILES[i].idperfil == idperfil)
				descripcion = CTE_INIT_PERFILES[i].descripcion;			
		}

	return descripcion;	
}

function obtDescripcionCourier(idcourier){
	
	var descripcion;
	
	for(var i=0; i<CTE_INIT_COURIERS.length; i++){
		if(CTE_INIT_COURIERS[i].idcourier == idcourier)
				descripcion = CTE_INIT_COURIERS[i].rznsocial;			
		}

	return descripcion;	
}
