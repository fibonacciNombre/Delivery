(function(window, document, undefined){

	$(function(){
		
		var current_url;
		
		var defaultPage = '';
		
		var pageTarget 	= document.URL;
		
		if(pageTarget != undefined){
			
			current_url = getFileName("" + pageTarget);
			
			if(pageTarget.search(".jsp")<0)
				defaultPage = current_url+".html"; 	
			else
				defaultPage = current_url;
			
			var path = defaultPage;
			
			if(path.search("inicio.jsp")<0){
				CTE_PAGEINIT = path;				
				initPageLoading(path);
			}
			
	 		$(".menu a").on("click", loadPage);
	 		
		}				
	});

	function loadPage(){
		
		$this 		= $(this);
		var href 	= $this.attr("data-url");
		
		if(href  != undefined){
			
			var path = getFileName(href);
			
			initPageLoading(path);			
		}		
	}

	function getFileName(url){
		
		var paths = url.split("?page=");
		
		return paths[paths.length - 1];
	}

	
})(window, document);

function load_event_ajax(){
	
	$(".method-ajax").unbind("click");
	
	$(".method-ajax").click(function(e){
		
		e.preventDefault();
		var page = $(this).attr("href");
		var path = page;// + ".html";
		initPageLoading(path);
	});
}
function initPageLoading(path){
		$(".content-rws").load(path, function(){
			eval("load_event_ajax();");
		});
	}
