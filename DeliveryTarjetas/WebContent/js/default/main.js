(function($, window, document, undefined){
	$(function(){
		$("#header-menu").on("click", toggleMenu); 

		$(".menu > li > div > ul a").on("click", activeMenuSecondLevel);
		$(".menu > li > a").click(function(){
			$this = $(this);
			var id = $(this).attr("id");
			var hass_class = $this.hasClass("active");
			
			$.each($(".menu > li a"), function(index, obj){
				if($(obj).hasClass("active")){
					$(obj).removeClass("active");
				}
			});
			
			if(hass_class){
				$this.removeClass("active");
			}else{
				$this.addClass("active");
			}
			
			if(id == "panelSeguros" && $(window).width() < 990){
				$("#aside").css("display", "none");
			}
		});
		$("#logout").on("click", logout);
		
		$(window).resize(resizeWindow);
		
		/* 
		 * INI MFARFANR
		var validator = $("form").bind("invalid-form.validate", function() {
		}).validate(); 
		 * FIN MFARFANR
		 */ 

		$('body').on("click", ".popover-dismiss", function() {
			$('.btn-popover').popover('hide');
		});		
	});

	var resizeWindow = function(){
		var $this = $(this);
		$("#header-menu").removeClass("active");
		if($this.width() > 992){
			$("#aside").css("display", "block");
		} else{
			$("#aside").css("display", "none");
		}
	}
 
	var toggleMenu = function(){
		activeToggle($(this));
		$("#aside").toggle();
	}
	var activeMenuSecondLevel = function(){
		var $this = $(this);
		$(".menu > li > div > ul a").removeClass("active");
		$this.addClass("active");

		if($(window).width() < 990){
			$("#aside").css("display", "none");
		}
	}
 
	var logout = function(){
		activeToggle($(this));
	}
	var activeToggle = function(e){
		e.toggleClass("active");
	}
})(jQuery, window, document);



//FILE UPLOAD
/*$(function () {
    'use strict';

    // Initialize the jQuery File Upload widget:
    $('#fileupload').fileupload({
        // Uncomment the following to send cross-domain cookies:
        //xhrFields: {withCredentials: true},
        url: 'http://localhost:8181/PORTALWEB/reembolsos.do?method=uploadFile'
    });

    // Enable iframe cross-domain access via redirect option:
    $('#fileupload').fileupload(
        'option',
        'redirect',
        window.location.href.replace(
            /\/[^\/]*$/,
            '/cors/result.html?%s'
        )
    );

    if (window.location.hostname === 'blueimp.github.io') {
        // Demo settings:
        $('#fileupload').fileupload('option', {
            url: '//jquery-file-upload.appspot.com/',
            // Enable image resizing, except for Android and Opera,
            // which actually support image resizing, but fail to
            // send Blob objects via XHR requests:
            disableImageResize: /Android(?!.*Chrome)|Opera/
                .test(window.navigator.userAgent),
            maxFileSize: 5000000,
            acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i
        });
        // Upload server status check for browsers with CORS support:
        if ($.support.cors) {
            $.ajax({
                url: '//jquery-file-upload.appspot.com/',
                type: 'HEAD'
            }).fail(function () {
                $('<div class="alert alert-danger"/>')
                    .text('Upload server currently unavailable - ' +
                            new Date())
                    .appendTo('#fileupload');
            });
        }
    } else {
        // Load existing files:
        $('#fileupload').addClass('fileupload-processing');
        $.ajax({
            // Uncomment the following to send cross-domain cookies:
            //xhrFields: {withCredentials: true},
            url: $('#fileupload').fileupload('option', 'url'),
            dataType: 'json',
            context: $('#fileupload')[0]
        }).always(function () {
            $(this).removeClass('fileupload-processing');
        }).done(function (result) {
            $(this).fileupload('option', 'done')
                .call(this, $.Event('done'), {result: result});
        });
    }

});*/


(function(a){
	a.createModal=function(b){
		defaults={
				title:"",
				message:"Your Message Goes Here!",
				closeButton:true,
				scrollable:false};
		
		var b=a.extend({},defaults,b);
		
		var c=(b.scrollable===true)?'style="max-height: 420;overflow-y: auto;"':"";
		
		html='<div class="modal fade" id="myModal">';
		
		html+='<div class="modal-dialog">';
		
		html+='<div class="modal-content">';
		if(b.title.length>0){
			html+='<h4 class="modal-title">'+b.title+"</h4>";
		}
		if(b.closeButton===true){
			html+='<div align="right"> <button id="modalClose" type="button" class="close button" data-dismiss="modal" aria-hidden="true" style="float:none">&times;</button> </div>';
		}
		
		html+='<div class="modal-body" '+c+">";
		html+=b.message;
		html+="</div>";
		
		html+="</div>";
		html+="</div>";
		html+="</div>";
		a("body").append(html);
		a("#myModal").modal().on("hidden.bs.modal",function(){
			a(this.parentElement).remove();
			$("div[id=myModal]").remove();
			document.body.className = document.body.className.replace("modal-open","");
			}
		);
	};
})(jQuery);



$(function(){    
    $('.view-pdf').on('click',function(){
    	
    	if($(this).attr('id')=='link-tycdatos'){
    		DIR_PDF_TERMCOND = DIR_PDF_TERMCOND_DATOS;
    	}
    	
    	if($(this).attr('id')=='tycfooter'){
    		DIR_PDF_TERMCOND = DIR_PDF_TERMCOND_GENER;
    	}
    	
        var pdf_link = $(this).attr('href')+DIR_PDF_TERMCOND;
        var iframe = '<div class="iframe-container"><iframe src="'+pdf_link+'"></iframe></div>';
        $.createModal({
        	message: iframe
        });
        return false;        
    });
});
