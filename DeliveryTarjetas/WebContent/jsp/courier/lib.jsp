<%@ page language="java" contentType="text/html; charset=utf-8"%>
 

<script>
function formatNumber(num) {
	num = parseFloat(Math.round(num * 100) / 100).toFixed(2);
	return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ", ");
}

function inspect(obj){
    var s = '', to, v=null;
    for(v in obj){
        to = typeof obj[v];
        switch(to){
            case 'function':
                break;
            default:
                s += v + ' = ' + to + '(' + obj[v] + ')\n';
                break;
        }
    }
    alert(s);
}
</script> 