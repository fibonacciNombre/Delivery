	var TIPO_TXT 	= "txt" ;
	var TIPO_HORA 	= "hor" ;
	var TIPO_SEL 	= "sel" ;
	var TIPO_CHECK 	= "chk";
	var TIPO_RADIO 	= "rdb";
	var TIPO_MAPA	= "map";
	var origenform;
	var exefunction = "";
	
	/**El origen es de donde viene la solicitud para armar el formulario, si es 1
	   viene de la creación de ficha, sí es 2 es por anulación**/
	
	/** GENERACION DE FICHA TECNICA - DINAMICA **/
	function armarFormularioDP(listaDP,ulForm, grupoficha, origen){
		var htmlElem;
		var htmlFieldSet  = '';
		var Objfieldset;
		var arrayFieldset = [];
		var arrayFieldset2 = [];
		var totalFS 	  = 0;
		var countFS 	  = 0;
		var buscarArray;

		origenform = origen;
		
		ulForm.empty();
		
		//Calculando cuantos array tiene la lista enviada
		for(var i=0; i<listaDP.length ;i++){
			if (listaDP[i].VISIBLE == 'S'){
				buscarArray = arrayFieldset.indexOf(listaDP[i].FIELDSET);
				//Sí el fieldset no es encontrado, se agregar al arrayFieldset 
				if (buscarArray==-1){
					totalFS = totalFS + 1;
					arrayFieldset.push(listaDP[i].FIELDSET);
					arrayFieldset2.push({fieldset : listaDP[i].FIELDSET, orden: listaDP[i].ORDEN});
				}
			}
		}
		
		//Limpiando el array utilizado
		arrayFieldset = [];
		
		//Ordenando array 
	    sorter = function(a, b) {
	      if(a.fieldset && !b.fieldset) {return -1;}
	      if(!a.fieldset && b.fieldset) {return 1;}
	      return a.orden - b.orden;
	    };

	    arrayFieldset2.sort(sorter);
	    
	    for(var z=0; z<arrayFieldset2.length ;z++){
	    	 arrayFieldset.push(arrayFieldset2[z].fieldset);
	    }
	    
		//Dividimos en 2 columnas los fieldset
		var halfFS = Math.round(totalFS/2);

		//Armando array con los fieldset
		for(var i=0; i<arrayFieldset.length ;i++){
			countFS = countFS + 1; 
			var idFS = arrayFieldset[i].replace(/\s/g, "_"); 
			idFS = idFS.replace("(", "");
			idFS = idFS.replace(")", "");

			//Agregando los fieldset al formulario
			htmlFieldSet = '<fieldset id="fs_'+idFS+'"><legend>'+arrayFieldset[i]+'</legend> </fieldset>';
			
			ulForm.append('<div class="col-sm-11">' + htmlFieldSet + '</div>');
			
//			if (halfFS == countFS){
//				ulForm.append('<div class="col-sm-6">' + htmlFieldSet + '</div>');
//				htmlFieldSet = '';
//			}else if (totalFS == countFS){
//				ulForm.append('<div class="col-sm-6">' + htmlFieldSet + '</div>');
//			}
		}
		
		//Agregando los campos de la lista, al fieldset correspondiente
		for(var i=0; i<listaDP.length ;i++){
			for(var z=0; z<arrayFieldset.length ;z++){
				if (arrayFieldset[z] == listaDP[i].FIELDSET){
					var idFS2 = listaDP[i].FIELDSET.replace(/\s/g, "_");
					idFS2 = idFS2.replace("(", "");
					idFS2 = idFS2.replace(")", "");
					Objfieldset = $("#fs_"+idFS2);
					htmlElem = obtenerElemHtml(listaDP[i],listaDP,grupoficha) ;
					if(htmlElem!=null)Objfieldset.append(htmlElem);
				}
			}
		}
	}

	function obtenerElemHtml(jsonElem,hijoList,grupoficha){
		var htmlElem = null ;
		if(jsonElem.ETIQUETA.indexOf('RIMDIR_HIDDEN_')==-1){
			if(jsonElem.TIPO==TIPO_TXT || jsonElem.TIPO==TIPO_HORA){		
				htmlElem = obtenerTxt(jsonElem) ;
			}else if(jsonElem.TIPO==TIPO_SEL){
				htmlElem = obtenerSel(jsonElem,hijoList,grupoficha) ;
			}else if(jsonElem.TIPO==TIPO_CHECK){
				htmlElem = obtenerCheck(jsonElem) ;
			}else if(jsonElem.TIPO==TIPO_RADIO){
				htmlElem = obtenerRadio(jsonElem) ;
			}else if(jsonElem.TIPO==TIPO_MAPA && origenform ==1){
				htmlElem = obtenerMapa(jsonElem) ;
			}
		}else{
			 htmlElem = obtenerHidden(jsonElem) ;
		}
		return htmlElem ;
	}
	
	function obtenerTxt(elem){
		elem.ETIQUETA = elem.ETIQUETA.split("-")[0];
		
		var indObligatorio	="";
		var check			="";
		var style			="";
		var spanFecha		="";
		var classDate 		="";
		var classgroup 		="";
		var divclose 		="";
		var readOnly		="";
		var value			="";
		if(elem.VISIBLE == 'N')
			style = 'style="display:none;"';
		
		if(elem.OBLIGATORIO=='S'){
			indObligatorio 	= "<span class='is-highlight'> </span>";
			
			check 			= '<div class="result">'+
							  '<i class="icon-check" for="check-'+ elem.NOMBRE +'"></i>'+
							  '<i class="icon-error" for="error-'+ elem.NOMBRE +'"></i>'+
							  '</div>';
		}else{
			indObligatorio 	= "<span class='is-highlight'> </span>";
			
			check 			= '<div class="result">'+
							  '<i class="icon-check" for="check-'+ elem.NOMBRE +'"></i>'+
							  '<i class="icon-oblig" for="oblig-'+ elem.NOMBRE +'"></i>'+
							  '</div>';
		}

		//Origen 2 form anular
		//if (origenform == 2){
			value = '" value="'+elem.VALOR;
		//}

		if(elem.INDFECHA=='S'){
			spanFecha = '<span class="input-group-addon">'+
					    '<a href="javascript:void(0);" class="btn-date">'+
	            	    '<span class="glyphicon glyphicon-calendar"></span>'+
	            	    '</a>'+
	            	    '</span>';
			
			classgroup = '<div class="input-group">';
			readOnly   = 'readonly="true"';
			divclose   = '</div>';	
		}
		
		var htmlElem =  classgroup + '<input id="'+elem.IDEFICHAINS+value+'" name="'+elem.NOMBRE+'" type="text" class="form-control" ' + readOnly + '>' + classDate+ 
						spanFecha +check+
						divclose;
		
//		if(elem.AYUDA!="")
//		    htmlElem += '<i class="icon-help"><span><b></b>'+elem.AYUDA+'</span>'+'</i>';
		
		htmlElem = '<label class="col-md-4 control-label" for="'+elem.NOMBRE+'">'+elem.ETIQUETA+indObligatorio+'</label>'+
				   '<div id="dd-'+elem.NOMBRE+'" class="col-md-7">'+
				   		htmlElem +
				   '</div>' ;
	
		return '<div class="form-group" id="dl-'+elem.NOMBRE+'"'+ style+' >'+htmlElem+'</div>';
	}
	
	function obtenerMapa(elem){
		elem.ETIQUETA = elem.ETIQUETA.split("-")[0];
		
		var style			="";
		
		if(elem.VISIBLE == 'N')
			style = 'style="display:none;"';
		
		if(elem.OBLIGATORIO=='S'){
			indObligatorio 	= "<span class='is-highlight'> </span>";
			check 			= '<div class="result">'+
							  '<i class="icon-check" for="check-'+ elem.NOMBRE +'"></i>'+
							  '<i class="icon-error" for="error-'+ elem.NOMBRE +'"></i>'+
							  '</div>';
		}
		
		var htmlElem =  '<input value="'+elem.ETIQUETA+'" type="button" onclick="javascript:cargarMapa(1);" style = "float: right !important;"class="btn btn-success pull-left js-contain-map"'+
						'data-toggle="modal">';

//		if(elem.AYUDA!="")
//		    htmlElem += '<i class="">'+'<span><b></b>'+elem.AYUDA+'</span>'+'</i>';
		
		htmlElem = '<label class="col-md-4 control-label" for="'+elem.NOMBRE+'"></label>'+
				   '<div id="dd-'+elem.NOMBRE+'" class="col-md-7">'+
				   		htmlElem +
				   '</div>' ;
	
		return '<div class="form-group" id="dl-'+elem.NOMBRE+'"'+ style+' >'+htmlElem+'</div>';
	}
	
	function obtenerHidden(elem){
		
		var htmlElem = '<input id="'+elem.IDEFICHAINS+'" name="'+elem.NOMBRE+'" type="hidden" >'+
					   '<input value="'+elem.IDEFICHAINS+'" name="hid-'+elem.NOMBRE+'" type="hidden" >' ;

		htmlElem = '<div id="dt-'+elem.NOMBRE+'" style="display:none;" class="t-label">'+
						'<label class="col-md-4 control-label" for="'+elem.NOMBRE+'">'+elem.ETIQUETA+'</label>'+
				   '</div>'+
				   '<div id="dd-'+elem.NOMBRE+'" style="display:none;">'+ 
				   		htmlElem +
				   '</div>';

		return '<div class="form-group" id="dl-'+elem.NOMBRE+'" style="display:none;">'+htmlElem+'</div>';
	}
	
	function obtenerSel(oPadre,hijoList,grupoficha){
		oPadre.ETIQUETA = oPadre.ETIQUETA.split("-")[0];
		
		var idHijo = null;
		var atrHijo = null ;
		var oHijo = buscarHijo(oPadre,hijoList) ;
		var indObligatorio="";
		var check="";
		var style="";

		if(oPadre.VISIBLE == 'N')
			style = "display:none;";
		
		if(oPadre.OBLIGATORIO=='S'){
			indObligatorio 	= "<span class='is-highlight'> </span>";
			check 			= '<div class="result">'+
							  '<i class="icon-check" for="check-'+ oPadre.NOMBRE +'"></i>'+
							  '<i class="icon-error" for="error-'+ oPadre.NOMBRE +'"></i>'+
							  '</div>';
		}else{
			indObligatorio 	= "<span class='is-highlight'> </span>";
			
			check 			= '<div class="result">'+
							  '<i class="icon-check" for="check-'+ oPadre.NOMBRE +'"></i>'+
							  '<i class="icon-oblig" for="oblig-'+ oPadre.NOMBRE +'"></i>'+
							  '</div>';
		}
		
		if (oPadre.VALORUNICO == "S"){
			CTE_GRUPO_IDEFICHAINS += ','+oPadre.IDEFICHAINS;
		}
		
		if(oHijo!=null){
			idHijo  = "'"+oHijo.IDEFICHAINS+"'" ;
			atrHijo = "'"+oHijo.ATRIBUTO+"'" ;
		}
		
		var opcHtml = '' ;	
		for(var j=0; j<oPadre.OPCIONES.length;j++){
			var seleccionado = '' ;
			if(oPadre.VALOR==oPadre.OPCIONES[j].VALOR) seleccionado = 'selected';		
			opcHtml += '<option '+seleccionado+' value="'+oPadre.OPCIONES[j].VALOR+'">'+oPadre.OPCIONES[j].DESCRIPCION+'</option>' ;
		}	

		if(oPadre.IDEREL !='')
			opcHtml = '';
		
		var htmlElem = 	'<select  class="form-control" name="'+oPadre.NOMBRE+'" id="'+oPadre.IDEFICHAINS+'" onChange="buscarItemsHijo(this,\''+oPadre.ATRIBUTO+'\','+atrHijo+','+idHijo+',\''+grupoficha+'\');" class="form-input form-input-select error" >'+
							'<option value="">'+SELECCIONAR+'</option>'+
							opcHtml+
						'</select>'+check;

//		if(oPadre.AYUDA!="")
//			htmlElem += '<i class="icon-help"><span><b></b>'+oPadre.AYUDA+'</span>'+'</i>';
		
		htmlElem = '<label class="col-md-4 control-label" for="'+oPadre.NOMBRE+'">'+oPadre.ETIQUETA+indObligatorio+'</label>'+
				   '<div class="col-md-7" id="dd-'+oPadre.NOMBRE+'" style="'+ style +'">'+
				   		htmlElem +
				   '</div>';

		//Validación para el formulario de Anular, carga los combos anidados
		if (oHijo != null){
			exefunction += "$(\"#"+oHijo.IDEFICHAINS + "\").val("+oHijo.VALOR+");";
			exefunction += "$(\"#"+oHijo.IDEFICHAINS + "\").change();";
		}

		return '<div class="form-group" id="dl-'+oPadre.NOMBRE+'" style="'+ style +'">'+htmlElem+'</div>';
	}
	
	function obtenerCheck(elem){
		var nombres = elem.ETIQUETA.split("-");
		var htmlElem = "";
		var indObligatorio ="";
		var style="";
		
		if(elem.VISIBLE == 'N')
			style = "display:none;";
		
		if(elem.OBLIGATORIO=='S')
			indObligatorio = "<span class='is-highlight'> </span>";
		
		if(nombres.length > 1){
			var id= nombres[0];
			
			if($("#"+id).length >0){
				for(var j=0; j<elem.OPCIONES.length;j++){
					htmlElem += '<input class="form-control" type="checkbox" name="'+elem.NOMBRE+'" value="'+elem.OPCIONES[j].DESCRIPCION+'" style="'+style+'">'+
								'<input value="'+elem.IDEFICHAINS+'" name="hid-'+elem.NOMBRE+'" type="hidden" >'+
								'<span style="width:50px;text-align:left;" style="'+style+'">'+elem.OPCIONES[j].DESCRIPCION+'</span>';
				}
				$("#"+id).append(htmlElem);
				htmlElem="";
			}
			else{
				htmlElem = '<div id="dt-'+id+'">'+
								'<div style="float:left;width:200px;">'+
									'<label class="col-md-4 control-label" for="'+id+'">'+id+indObligatorio+'</label>'+
								'</div>'+
							'</div>'+
							'<div>'+
								'<div style="float:left;width:450px;padding-left:5px;" id="'+id+'">';
	
				for(var j=0; j<elem.OPCIONES.length;j++){
					htmlElem += '<input type="checkbox" name="'+elem.NOMBRE+'" value="'+elem.OPCIONES[j].DESCRIPCION+'" style="'+style+'">'+
								'<input value="'+elem.IDEFICHAINS+'" name="hid-'+elem.NOMBRE+'" type="hidden" >'+
								'<span style="width:50px;text-align:left;" style="'+style+'">'+elem.OPCIONES[j].DESCRIPCION+'</>';
				}
	
				htmlElem +=		'</div>'+
							'</div>';
			}
		}
		return '<div class="form-group>'+htmlElem+'</div>';
	}
	
	function obtenerRadio(elem){
		elem.ETIQUETA = elem.ETIQUETA.split("-")[0];
		
		var style="";
		
		if(elem.VISIBLE == 'N')
			style = "display:none;";
		
		var htmlElem = 	'<div id="dt-'+elem.NOMBRE+'" style="'+style+'">'+
							'<label class="col-md-12" for="'+elem.NOMBRE+'">'+elem.ETIQUETA+'</label>'+						
						'</div>'+
						'<div id="dd-'+elem.NOMBRE+'" style="'+ style +'">'+
							'<input value="'+elem.IDEFICHAINS+'" name="hid-'+elem.NOMBRE+'" type="hidden" >';
		
		for(var j=0; j<elem.OPCIONES.length;j++){
			htmlElem += '<label class="radio-inline">'+
						'<input type="radio" name="'+elem.NOMBRE+'" value="'+elem.OPCIONES[j].VALOR+'" style="width:30px;" >'+
						'<span class="span_ctrl" id="label-'+elem.OPCIONES[j].VALOR+'" >'+elem.OPCIONES[j].DESCRIPCION+'</span>'+
						'</label">';		
		}
		
		if(elem.AYUDA!="")
			htmlElem += '<a class="ico_help help_onpage"><div class="helper"><p>'+elem.AYUDA+'<p></div></a>';
		
		htmlElem += '</dd>';
		return '<div class="form-group" id="dl-'+elem.NOMBRE+'" style="'+style+'">'+htmlElem+'</div>';
	}
	
	function buscarHijo(oPadre, hijoList){
		var hijo ;
		if(hijoList!=null){
			for(var k=0; k< hijoList.length; k++){
				hijo = hijoList[k] ;
				if(hijo.IDEREL!=null && hijo.IDEREL==oPadre.ATRIBUTO){
					return hijo ; 
				}
			}
		}
		return null ;
	}
	
	function buscarItemsHijo(padre, atrPadre, atrHijo, idHijo, grupoficha){
		if(idHijo!=null){			
			var param 		  = new Object();
			param.descripcion 	= padre.value; 
			param.ideatributo 	= atrHijo;
			param.ideatributorel = atrPadre;
			param.grupoficha 	= grupoficha;

			$.ajax({
				type : "POST",
				url : SERVICIO_ASISTENCIA+"?method=cboAtributoHijo",
				async : false,
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				data : {datos:JSON.stringify(param)},
				success : function(rsp) {				
					var aRsp = rsp.split('<>');				
					var marcList = JSON.parse(aRsp[0]);
					$('#'+idHijo).empty();
					$('#'+idHijo).append('<option value="">Seleccionar</option>');
					for(var j=0; j<marcList.length;j++){
						opcs = '<option value="'+marcList[j][0]+'">'+marcList[j][1]+'</option>' ;
						$('#'+idHijo).append(opcs);
						if (marcList.length ==1){
							$("#"+idHijo).val(marcList[j][0]);
							$("#"+idHijo).change();
						}
					}
				},			
				error: function (xhr, ajaxOptions, thrownError) {

				}
			});
		}
	}

	function exefunctionDyn(){
		 return (new Function(exefunction))();
	}
	
	function cargarComboEspeciales(){
		//Seleccionar la categoría por defecto
		$('select[name="'+CTE_TAG_CATEGORIA+'"] option:eq(1)').attr('selected', 'selected');
		$('#'+$('select[name="'+CTE_TAG_CATEGORIA+'"]').attr("id")).change();
		
		//Escoger los valores por defecto	
		var aRsp = CTE_GRUPO_IDEFICHAINS.split(',');

		for(var j=0; j<aRsp.length;j++){
			var idefichains = aRsp[j];
			var name = $("#"+idefichains).attr("name");
					  
		    if ($("#"+idefichains+"option").size() == 1){
			   $('select[name="'+name+'"] option:eq(1)').attr('selected', 'selected');
			   $("#"+idefichains).change();
		    }
		}
	}
