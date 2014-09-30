$(document).ready(function() {
   	$('#formulario').on('submit', function() {
   		console.log($("#formulario").serialize());
   		var url = "registrarUsuario.jsp";
	    $.ajax({
           type: "POST",
           url: url,
           data: $("#formulario").serialize(),
	       success: function(data) {
	       		alert(data);
	       }
         });

	    return false;
	});
});