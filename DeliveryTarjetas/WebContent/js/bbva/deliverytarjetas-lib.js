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

function callCargaControlParam(ideParametro,ideControl){
	
	var param 		= new Object();
	param.tipo 		= ideParametro;
	
	$.ajax({
		type 		: "POST",
		url 		: "/DeliveryTarjetas/comun.do"+"?method=comboParametro",
		cache 		: false,
		async 		: false,
		dataType 	: 'json',
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		data		: param,
		success 	: function(rsp) {
		
			llenarCombo(ideControl, rsp, true);
		
		},
		error : function(xhr, ajaxOptions, thrownError) {}
	});
	
}