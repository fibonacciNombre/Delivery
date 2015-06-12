var now = new Date();
var dftMonth = now.getMonth();
var dftDate = now.getDate();
var minYear = now.getFullYear()-80;
var maxYear = now.getFullYear()-18;
var rangeYear = minYear+':'+maxYear;

$("input[name*=fecnacimiento]").datepicker({changeYear:true,
												changeMonth: true,
												yearRange:rangeYear,
												defaultDate:new Date(minYear,00,01),
												minDate:new Date(minYear,00,01),
												maxDate:new Date(maxYear,11,31),
												beforeShow: function() {
											        setTimeout(function(){
											            $('.ui-datepicker').css({'z-index':9999, 'border': '1px solid #ccc'});
											        }, 0);
											    }});
	 	
/*	 	
$("input[name*='fecnacimiento']").datepicker({
	changeMonth: true, changeYear: true, yearRange: '-80:-18',defaultDate : '-18y',
	maxDate :'-18y',minDate :'-80y',
	beforeShow: function() {
        setTimeout(function(){
            $('.ui-datepicker').css({'z-index':9999, 'border': '1px solid #ccc'});
        }, 0);
    }
});
*/

$(".btn-date").click(function(){
   $("input[name*='fecnacimiento']", $(this).parent().parent()).datepicker("show"); 
});