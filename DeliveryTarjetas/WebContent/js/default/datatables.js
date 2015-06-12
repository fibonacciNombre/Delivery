(function($, window, document){

	var dataSet_Salud = [
	  {
	    "seguro":   "Vida Futuro Protegido Plan Hasta 65 Años",
	    "vigencia": "Del 16/02/2014 al 15/02/2015",
	    "estado": "Al dia",
	    "detalle": "pages/mis-seguros/mis-seguros-salud", //link
	    "digital": "link"
	  },
	  {
	    "seguro":   "Accidentes Personales - Individual",
	    "vigencia": "Del 16/02/2014 al 15/02/2015",
	    "estado": "Al dia",
	    "detalle": "pages/mis-seguros/mis-seguros-salud", //link
	    "digital": "link"
	  },
	  {
	    "seguro":   "Vida Futuro Protegido Plan Hasta 65 Años",
	    "vigencia": "Del 16/02/2014 al 15/02/2015",
	    "estado": "Al dia",
	    "detalle": "pages/mis-seguros/mis-seguros-salud", //link
	    "digital": "link"
	  },
	  {
	    "seguro":   "Accidentes Personales - Individual",
	    "vigencia": "Del 16/02/2014 al 15/02/2015",
	    "estado": "Al dia",
	    "detalle": "pages/mis-seguros/mis-seguros-salud", //link
	    "digital": "link"
	  },
	  {
	    "seguro":   "Vida Futuro Protegido Plan Hasta 65 Años",
	    "vigencia": "Del 16/02/2014 al 15/02/2015",
	    "estado": "Al dia",
	    "detalle": "pages/mis-seguros/mis-seguros-salud", //link
	    "digital": "link"
	  },
	  {
	    "seguro":   "Accidentes Personales - Individual",
	    "vigencia": "Del 16/02/2014 al 15/02/2015",
	    "estado": "Al dia",
	    "detalle": "pages/mis-seguros/mis-seguros-salud", //link
	    "digital": "link"
	  },
	  {
	    "seguro":   "Vida Futuro Protegido Plan Hasta 65 Años",
	    "vigencia": "Del 16/02/2014 al 15/02/2015",
	    "estado": "Al dia",
	    "detalle": "pages/mis-seguros/mis-seguros-salud", //link
	    "digital": "link"
	  },
	  {
	    "seguro":   "Accidentes Personales - Individual",
	    "vigencia": "Del 16/02/2014 al 15/02/2015",
	    "estado": "Al dia",
	    "detalle": "pages/mis-seguros/mis-seguros-salud", //link
	    "digital": "link"
	  },
	  {
	    "seguro":   "Vida Futuro Protegido Plan Hasta 65 Años",
	    "vigencia": "Del 16/02/2014 al 15/02/2015",
	    "estado": "Al dia",
	    "detalle": "pages/mis-seguros/mis-seguros-salud", //link
	    "digital": "link"
	  },
	  {
	    "seguro":   "Accidentes Personales - Individual",
	    "vigencia": "Del 16/02/2014 al 15/02/2015",
	    "estado": "Al dia",
	    "detalle": "pages/mis-seguros/mis-seguros-salud", //link
	    "digital": "link"
	  },
	  {
	    "seguro":   "Vida Futuro Protegido Plan Hasta 65 Años",
	    "vigencia": "Del 16/02/2014 al 15/02/2015",
	    "estado": "Al dia",
	    "detalle": "pages/mis-seguros/mis-seguros-salud", //link
	    "digital": "link"
	  },
	  {
	    "seguro":   "Accidentes Personales - Individual",
	    "vigencia": "Del 16/02/2014 al 15/02/2015",
	    "estado": "Al dia",
	    "detalle": "pages/mis-seguros/mis-seguros-salud", //link
	    "digital": "link"
	  }
	];

	var dataSet_Vida = [
	  {
	    "seguro":   "EPS",
	    "vigencia": "Del 16/02/2014 al 15/02/2015",
	    "estado": "Al dia",
	    "detalle": "pages/mis-seguros/mis-seguros-vida", //link
	    "digital": "link"
	  },
	  {
	    "seguro":   "Onca Integral Individual WS",
	    "vigencia": "Del 16/02/2014 al 15/02/2015",
	    "estado": "Al dia",
	    "detalle": "pages/mis-seguros/mis-seguros-vida", //link
	    "digital": "link"
	  },
	  {
	    "seguro":   "EPS",
	    "vigencia": "Del 16/02/2014 al 15/02/2015",
	    "estado": "Al dia",
	    "detalle": "pages/mis-seguros/mis-seguros-vida", //link
	    "digital": "link"
	  },
	  {
	    "seguro":   "Onca Integral Individual WS",
	    "vigencia": "Del 16/02/2014 al 15/02/2015",
	    "estado": "Al dia",
	    "detalle": "pages/mis-seguros/mis-seguros-vida", //link
	    "digital": "link"
	  },
	  {
	    "seguro":   "EPS",
	    "vigencia": "Del 16/02/2014 al 15/02/2015",
	    "estado": "Al dia",
	    "detalle": "pages/mis-seguros/mis-seguros-vida", //link
	    "digital": "link"
	  },
	  {
	    "seguro":   "Onca Integral Individual WS",
	    "vigencia": "Del 16/02/2014 al 15/02/2015",
	    "estado": "Al dia",
	    "detalle": "pages/mis-seguros/mis-seguros-vida", //link
	    "digital": "link"
	  },
	  {
	    "seguro":   "EPS",
	    "vigencia": "Del 16/02/2014 al 15/02/2015",
	    "estado": "Al dia",
	    "detalle": "pages/mis-seguros/mis-seguros-vida", //link
	    "digital": "link"
	  },
	  {
	    "seguro":   "Onca Integral Individual WS",
	    "vigencia": "Del 16/02/2014 al 15/02/2015",
	    "estado": "Al dia",
	    "detalle": "pages/mis-seguros/mis-seguros-vida", //link
	    "digital": "link"
	  },
	  {
	    "seguro":   "EPS",
	    "vigencia": "Del 16/02/2014 al 15/02/2015",
	    "estado": "Al dia",
	    "detalle": "pages/mis-seguros/mis-seguros-vida", //link
	    "digital": "link"
	  },
	  {
	    "seguro":   "Onca Integral Individual WS",
	    "vigencia": "Del 16/02/2014 al 15/02/2015",
	    "estado": "Al dia",
	    "detalle": "pages/mis-seguros/mis-seguros-vida", //link
	    "digital": "link"
	  },
	  {
	    "seguro":   "EPS",
	    "vigencia": "Del 16/02/2014 al 15/02/2015",
	    "estado": "Al dia",
	    "detalle": "pages/mis-seguros/mis-seguros-vida", //link
	    "digital": "link"
	  },
	  {
	    "seguro":   "Onca Integral Individual WS",
	    "vigencia": "Del 16/02/2014 al 15/02/2015",
	    "estado": "Al dia",
	    "detalle": "pages/mis-seguros/mis-seguros-vida", //link
	    "digital": "link"
	  }
	];

	var dataSet_vehicular = [
		{
		    "marca":    "Hyundai",
		    "modelo":   "Accent",
		    "placa":    "X1W859",
		    "seguro":   "Web Vehículos",
		    "vigencia": "Del 16/02/2014 al 15/02/2015",
		    "estado": 	"Vencido",
		    "detalle": 	"pages/mis-seguros/mis-seguros-vehicular", //link
	    	"digital": 	"link"
		},
		{
		    "marca":    "Hyundai",
		    "modelo":   "Accent",
		    "placa":    "X1W859",
		    "seguro":   "Web Vehículos",
		    "vigencia": "Del 16/02/2014 al 15/02/2015",
		    "estado": 	"Vencido",
		    "detalle": 	"pages/mis-seguros/mis-seguros-vehicular", //link
	    	"digital": 	"link"
		},
		{
		    "marca":    "Hyundai",
		    "modelo":   "Accent",
		    "placa":    "X1W859",
		    "seguro":   "Web Vehículos",
		    "vigencia": "Del 16/02/2014 al 15/02/2015",
		    "estado": 	"Vencido",
		    "detalle": 	"pages/mis-seguros/mis-seguros-vehicular", //link
	    	"digital": 	"link"
		},
		{
		    "marca":    "Hyundai",
		    "modelo":   "Accent",
		    "placa":    "X1W859",
		    "seguro":   "Web Vehículos",
		    "vigencia": "Del 16/02/2014 al 15/02/2015",
		    "estado": 	"Vencido",
		    "detalle": 	"pages/mis-seguros/mis-seguros-vehicular", //link
	    	"digital": 	"link"
		},
		{
		    "marca":    "Hyundai",
		    "modelo":   "Accent",
		    "placa":    "X1W859",
		    "seguro":   "Web Vehículos",
		    "vigencia": "Del 16/02/2014 al 15/02/2015",
		    "estado": 	"Vencido",
		    "detalle": 	"pages/mis-seguros/mis-seguros-vehicular", //link
	    	"digital": 	"link"
		},
		{
		    "marca":    "Hyundai",
		    "modelo":   "Accent",
		    "placa":    "X1W859",
		    "seguro":   "Web Vehículos",
		    "vigencia": "Del 16/02/2014 al 15/02/2015",
		    "estado": 	"Vencido",
		    "detalle": 	"pages/mis-seguros/mis-seguros-vehicular", //link
	    	"digital": 	"link"
		},
		{
		    "marca":    "Hyundai",
		    "modelo":   "Accent",
		    "placa":    "X1W859",
		    "seguro":   "Web Vehículos",
		    "vigencia": "Del 16/02/2014 al 15/02/2015",
		    "estado": 	"Vencido",
		    "detalle": 	"pages/mis-seguros/mis-seguros-vehicular", //link
	    	"digital": 	"link"
		}
	];

	var dataSet_domiciliario = [
		{
		    "direccion":    "c. Jose Galvez 1002 - Miraflores",
		    "seguro":   "WEB VEHICULOS",
		    "vigencia": "Del 16/02/2014 al 15/02/2015",
		    "estado": 	"Vencido",
		    "detalle": 	"pages/mis-seguros/mis-seguros-domiciliario", //link
	    	"digital": 	"link"
		},
		{
		    "direccion":    "c. Jose Galvez 1002 - Miraflores",
		    "seguro":   "WEB VEHICULOS",
		    "vigencia": "Del 16/02/2014 al 15/02/2015",
		    "estado": 	"Vencido",
		    "detalle": 	"pages/mis-seguros/mis-seguros-domiciliario", //link
	    	"digital": 	"link"
		},
		{
		    "direccion":    "c. Jose Galvez 1002 - Miraflores",
		    "seguro":   "WEB VEHICULOS",
		    "vigencia": "Del 16/02/2014 al 15/02/2015",
		    "estado": 	"Vencido",
		    "detalle": 	"pages/mis-seguros/mis-seguros-domiciliario", //link
	    	"digital": 	"link"
		},
		{
		    "direccion":    "c. Jose Galvez 1002 - Miraflores",
		    "seguro":   "WEB VEHICULOS",
		    "vigencia": "Del 16/02/2014 al 15/02/2015",
		    "estado": 	"Vencido",
		    "detalle": 	"pages/mis-seguros/mis-seguros-domiciliario", //link
	    	"digital": 	"link"
		}
	];
	var dataSet_otros = [
		{
		   	"seguro":   "Robo Tarjeta de Crédito BBVA",
		    "vigencia": "Del 16/02/2014 al 15/02/2015",
		    "estado": 	"Al día",
		    "detalle": 	"pages/mis-seguros/mis-seguros-otros", //link
	    	"digital": 	"link"
		}

	];

	$(function(){
		$('#table-mis-seguros-salud').dataTable( {
	        // "ajax": 'url'
	        "dom": '<"toolbar">tpr',
	        "data": dataSet_Salud,
	        "columns": [
	            { "title": "Seguro",   "data": "seguro" },
		 		{ "title": "Vigencia",  "data": "vigencia" },
		 		{ "title": "Estado", "data": "estado", "class":"text-center" },
		 		{ "title": "Detalle",  "data": "detalle", "orderable": false,
		 		  "render": function(data, type, row){
		 		   		return "<a class='method-ajax' href='"+data+"'><i class='i-detalle'></i></a>";
		 		   }
		 		},
		 		{ "title": "Digital", "data": "digital", "orderable": false,
		 		  "render": function(data, type, row){
		 		   		return "<a class='method-ajax' href='"+data+"'><i class='i-digital'></i></a>";
		 		   }
		 		}
	        ],
	        "paging": true,
	        "pageLength": 5,
	        "pagingType": "full_numbers",
	        "language": {
			  "paginate": {
			    "next": ">",
			    "first": "",
			    "last": "",
			    "infoEmpty": "No entries to show",
			    "previous": "<",
			    "infoEmpty": "Showing page _PAGE_ of _PAGES_",
			    "processing": "DataTables is currently busy"
			  }
			}
	    });

 		$('#table-mis-seguros-vida').dataTable( {
	        // "ajax": 'url'
	        "dom": '<"toolbar">tpr',
	        "data": dataSet_Vida,
	        "columns": [
	            { "title": "Seguro",   "data": "seguro" },
		 		{ "title": "Vigencia",  "data": "vigencia" },
		 		{ "title": "Estado", "data": "estado", "class":"text-center" },
		 		{ "title": "Detalle",  "data": "detalle", "orderable": false,
		 		  "render": function(data, type, row){
		 		   		return "<a class='method-ajax' href='"+data+"'><i class='i-detalle'></i></a>";
		 		   }
		 		},
		 		{ "title": "Digital", "data": "digital", "orderable": false,
		 		  "render": function(data, type, row){
		 		   		return "<a class='method-ajax' href='"+data+"'><i class='i-digital'></i></a>";
		 		   }
		 		}
	        ],
	        "paging": true,
	        "pageLength": 5,
	        "pagingType": "full_numbers",
	        "language": {
			  "paginate": {
			    "next": ">",
			    "first": "",
			    "last": "",
			    "infoEmpty": "No entries to show",
			    "previous": "<",
			    "infoEmpty": "Showing page _PAGE_ of _PAGES_",
			    "processing": "DataTables is currently busy"
			  }
			}
	    });

		$('#table-mis-seguros-vehicular').dataTable( {
	        // "ajax": 'url'
	        "dom": '<"toolbar">tpr',
	        "data": dataSet_vehicular,
	        "columns": [
	            { "title": "Marca",   	"data": "marca", "orderable": false },
		 		{ "title": "Modelo",  "data": "modelo", "orderable": false },
		 		{ "title": "Placa",  "data": "placa" },
		 		{ "title": "Seguro",  "data": "seguro" },
		 		{ "title": "Vigencia",  "data": "vigencia" },
		 		{ "title": "Estado", 	"data": "estado", "class":"text-center" },
		 		{ "title": "Detalle",  	"data": "detalle", "orderable": false,
		 		  "render": function(data, type, row){
		 		   		return "<a class='method-ajax' href='"+data+"'><i class='i-detalle'></i></a>";
		 		   }
		 		},
		 		{ "title": "Digital", "data": "digital", "orderable": false,
		 		  "render": function(data, type, row){
		 		   		return "<a class='method-ajax' href='"+data+"'><i class='i-digital'></i></a>";
		 		   }
		 		}
	        ],
	        "paging": true,
	        "pageLength": 5,
	        "pagingType": "full_numbers",
	        "language": {
			  "paginate": {
			    "next": ">",
			    "first": "",
			    "last": "",
			    "infoEmpty": "No entries to show",
			    "previous": "<",
			    "infoEmpty": "Showing page _PAGE_ of _PAGES_",
			    "processing": "DataTables is currently busy"
			  }
			}
	    });

		$('#table-mis-seguros-domiciliario').dataTable( {
	        // "ajax": 'url'
	        "dom": '<"toolbar">tpr',
	        "data": dataSet_domiciliario,
	        "columns": [
		 		{ "title": "Dirección",  "data": "direccion" },
		 		{ "title": "Seguro",  "data": "seguro" },
		 		{ "title": "Vigencia",  "data": "vigencia" },
		 		{ "title": "Estado", 	"data": "estado", "class":"text-center"  },
		 		{ "title": "Detalle",  	"data": "detalle", "orderable": false,
		 		  "render": function(data, type, row){
		 		   		return "<a class='method-ajax' href='"+data+"'><i class='i-detalle'></i></a>";
		 		   }
		 		},
		 		{ "title": "Digital", "data": "digital", "orderable": false,
		 		  "render": function(data, type, row){
		 		   		return "<a class='method-ajax' href='"+data+"'><i class='i-digital'></i></a>";
		 		   }
		 		}
	        ],
	        "paging": true,
	        "pageLength": 5,
	        "pagingType": "full_numbers",
	        "language": {
			  "paginate": {
			    "next": ">",
			    "first": "",
			    "last": "",
			    "infoEmpty": "No entries to show",
			    "previous": "<",
			    "infoEmpty": "Showing page _PAGE_ of _PAGES_",
			    "processing": "DataTables is currently busy"
			  }
			}
	    });

		$('#table-mis-seguros-otros').dataTable( {
	        // "ajax": 'url'
	        "dom": '<"toolbar">tpr',
	        "data": dataSet_otros,
	        "columns": [
		 		{ "title": "Seguro",  "data": "seguro" },
		 		{ "title": "Vigencia",  "data": "vigencia" },
		 		{ "title": "Estado", 	"data": "estado", "class":"text-center"  },
		 		{ "title": "Detalle",  	"data": "detalle", "orderable": false,
		 		  "render": function(data, type, row){
		 		   		return "<a class='method-ajax' href='"+data+"'><i class='i-detalle'></i></a>";
		 		   }
		 		},
		 		{ "title": "Digital", "data": "digital", "orderable": false,
		 		  "render": function(data, type, row){
		 		   		return "<a class='method-ajax' href='"+data+"'><i class='i-digital'></i></a>";
		 		   }
		 		}
	        ],
	        "paging": false,
	        "pageLength": 5,
	        "pagingType": "full_numbers",
	        "language": {
			  "paginate": {
			    "next": ">",
			    "first": "",
			    "last": "",
			    "infoEmpty": "No entries to show",
			    "previous": "<",
			    "infoEmpty": "Showing page _PAGE_ of _PAGES_",
			    "processing": "DataTables is currently busy"
			  }
			}
	    });
	});
})(jQuery, window, document);

