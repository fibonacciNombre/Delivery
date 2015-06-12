(function($, window, document){
    $(function(){
    	
        $("form").click(function(e){
        	 
            var valid = true;
            $("input, select, textarea", "form").each(function(){
            	$this = $(this);
            	valid = validarFormulario($this) && valid;
            });
            
            if(valid){
                var action = $(this).attr("action");
                initPageLoading(action);
                return false;
            }
            return valid;
        });

        $("input, select, textarea", "form").change(function(){ 
            $this = $(this);
            validarFormulario($this);
        });
    });

    var validarFormulario = function(e){
    	if(e.attr("required") != undefined && e.attr("disabled") == undefined){
    		
    		if(e.val() != ""){
                $(".result", e.parent()).html("<i class='success'></i>");
            }else{
                $(".result", e.parent()).html("<i class='error'></i>");
                return false;
            }
        }
        return true;
    };
    
})(jQuery, window, document);