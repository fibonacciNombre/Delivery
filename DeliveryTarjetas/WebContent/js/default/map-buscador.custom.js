var map;
/*
function initialize() {
  var mapOptions = {
    zoom: 8,
    center: new google.maps.LatLng(-12.051262, -77.041857)
  };
  map = new google.maps.Map(document.getElementById('map-custom'),
      mapOptions);

  var myLatlng = new google.maps.LatLng(-12.051262, -77.041857);
  var marker = new google.maps.Marker({
      position: myLatlng,
      map: map,
      title: ' '
  });
}

google.maps.event.addDomListener(window, 'load', initialize);
*/
(function($, window, document, undefined){

	$(function(){
		//initialize();

		$('.js-tooltip').tooltip({
			container: "body"
		});

		$(".js-checkbox-op").change(function(){
			var $this = $(this);
			if($this.is(":checked")){
				$("label[for='"+$(this).attr("id")+"']").addClass("active");
			}else{
				$("label[for='"+$(this).attr("id")+"']").removeClass("active");
			}
		});

		$(".js-radio-op").change(function(){
			var $this = $(this); 
			if($this.is(":checked")){
				$(".js-radio-op[name='"+$this.attr("name")+"']").each(function(){
					$("label[for='"+$(this).attr("id")+"']").removeClass("active");
				});
				$("label[for='"+$(this).attr("id")+"']").addClass("active");
			}
		});

		$(".tab").hide();
		$(".js-btn-tab").each(function(){
			var $this = $(this);
			if($this.parent().hasClass("active")){
				var classTab = $this.attr("data-for");
				$("."+classTab).fadeIn();
			}
		}).click(function(){
			var $this = $(this);
			$(".js-btn-tab").parent().removeClass("active");
			$(".js-btn-tab").removeClass("active");

			$(".tab").hide();
			$this.parent().addClass("active");
			$this.addClass("active");
			var classTab = $this.attr("data-for");
			$("."+classTab).fadeIn();
			
			if(classTab=="tab1")
				goToMap(idMarkerDefault);
			//autoCenter();
		});

		$(".js-btn-more").click(function(){
			$this = $(this);
			if($this.hasClass("active")){
				$(".js-op-avanzadas").slideUp();
				$this.removeClass("active");
			}else{
				$(".js-op-avanzadas").slideDown();
				$this.addClass("active");
			}
		});
		
		$(document).click(function(){
			$(".js-popup-menu").fadeOut("shown");
		});
		
		$(".js-btn-op-buscador").click(function(e){
			
			$(".js-popup-menu").fadeOut("shown");
			
//			$(".js-popup-menu", $(this).parent()).fadeIn("shown");
			$(".js-popup-menu", $(this).parent()).toggle();
			
			e.stopPropagation();
			
			//comportamiento especial para el combo de listado de clinicas
			if($(this).attr("id")=="container-tituloClinica"){
				/*
				var texto = $("#titulo-clinica").text();
				
				if(texto!= 'Seleccionar')
					$("#container-listado-clinicas .custom-combobox-input").autocomplete( "search", texto);					
				else*/
				$("#container-listado-clinicas .custom-combobox-input").autocomplete( "search", "");				
			}
		});

		$(".js-popup-menu").click(function(e){
			e.stopPropagation();
		});
						
		
		$("a", ".js-container-clinicas").click(function(e){
			
			var $this = $(this);
			
			$("a", ".js-popup-menu").removeClass("active");
			$this.addClass("active");

			var texto = $this.text();
			
			$(".js-btn-clinica").html(texto + ' <i class="i-down"></i>').addClass("active");

			$(".js-popup-menu").fadeOut("shown");			
		});
		
		//buscador - vehículo
		$(".js-select-vehiculo").change(function(){
			var $this = $(this); 
			var value = $this.val();
			$(".js-btn-vehiculo").html($(".js-select-vehiculo option[value='"+value+"']").text()+ ' <i class="i-down"></i>').addClass("active");
			$(".js-popup-menu").fadeOut("shown");			
		});
		
		//buscador tipo taller
		$(".js-select-tipo-taller").change(function(){
			var $this = $(this); 
			var value = $this.val();

			$(".js-btn-tipo-taller").html($(".js-select-tipo-taller option[value='"+value+"']").text()+ ' <i class="i-down"></i>').addClass("active");
			
			$(".js-popup-menu").fadeOut("shown");			
		});
		
//		//buscador - ubicacion
//		$(".js-cerca").change(function(){
//			
//			var $this = $(this);
//			
//			if($this.is(":checked")){
//				$(".js-btn-ubicacion").html('Cerca de mi ' + '<i class="i-down"></i>').addClass("active");
//				$(".js-select-ubicacion").val("");
//			}
//			else{
//				$(".js-btn-ubicacion").html('Ubicaci&oacute;n' + ' <i class="i-down"></i>').removeClass("active");	
//			}
//			
//			$(".js-popup-menu").fadeOut("shown");
//		});

//		$(".js-select-ubicacion").change(function(){
//			
//			var vacios = 0;
//			$(".js-select-ubicacion").each(function(){
//				var value = $(this).val();
//				
//				if(value=="0" || value=="")
//					vacios++;				
//			});
//			
//			if(vacios == 0){
//				var nombre = $(".js-select-distrito option[value='"+$(".js-select-distrito").val()+"']").text();
//				$(".js-btn-ubicacion").html(nombre + " <i class=\"i-down\"></i>").addClass("active");
//				$(".js-cerca").prop('checked', false);
//				$(".js-label-cerca").removeClass('active');
//				$(".js-popup-menu").fadeOut("shown");
//			}
//			else{
//				$(".js-btn-ubicacion").html('Seleccionar <i class="i-down"></i>').removeClass("active");
//			}
//		});

		//buscador - especialidad
//		$("a", ".js-container-especialidad").click(function(){
//			
//			var $this = $(this);
//			
//			var texto = $this.text();
//			
//			$("a", ".js-container-especialidad").removeClass("active");
//			
//			$this.addClass("active");
//			
//			$(".js-btn-especialidad").html(texto + ' <i class="i-down"></i>').addClass("active");
//			$(".js-popup-menu").fadeOut("shown");
//		});
		
		
		//buscador - cobertura
		$(".js-select-cobertura, .js-select-de-cobertura").change(function(){
			var vacios = 0;
			$(".js-select-cobertura, .js-select-de-cobertura").each(function(){
				var value = $(this).val();
				if(value==""){
					vacios++;
				}
			});
			if(vacios == 0){
				var $this = $(this);
				var texto = $(".js-select-de-cobertura option[value='"+$(".js-select-de-cobertura").val()+"']").text();
				$(".js-btn-especialidad").html(texto + ' <i class="i-down"></i>').addClass("active");
				$(".js-popup-menu").fadeOut("shown");
			}
			else{
				$(".js-btn-especialidad").html('Seleccionar <i class="i-down"></i>').removeClass("active");
			}
		});
		
		

		//buscador - precio
		$(".js-select-precio").change(function(){
			
			var vacios = 0;
			
			$(".js-select-precio").each(function(){
				var $this = $(this);
				if($this.val() == ""){
					vacios++;
				}
			});
			
			if(vacios == 0){
				
				var $hasta = $(".js-select-hasta").val();
				if($hasta > 0){
					var texto = " Hasta: " + $hasta;
					$(".js-btn-precio").html(texto + ' <i class="i-down"></i>').addClass("active");
					$(".js-popup-menu").fadeOut("shown");
				}
				else{
					$(".js-btn-precio").html('Seleccionar <i class="i-down"></i>').removeClass("active");
				}	
			}else{
				$(".js-btn-precio").html('Costo de <br> atención <i class="i-down"></i>').removeClass("active");
			}

		});

		$(".js-close-popup-menu").click(function(){
			$(".js-popup-menu").fadeOut("shown");
		});
		$(".js-ver-mapa").click(function(){
			$(".js-btn-ubi").click();
		});
		
	
	});
})(jQuery, window, document);
