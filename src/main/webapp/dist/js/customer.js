   	function verifyForm(){
   		console.log("verifying form");
   		var terms = $('#terms');
   		if(!terms.prop('checked')){
   			alert('debes aceptar los TOS');
   			return false;
   		}
   		$("#errorContainer ul").empty();
   		registerCustomer();
   	}
   	
   	
   	function registerCustomer(){
   		
   		
   		$.ajax({
            type: "POST",
            url: '/cuponza/customer/add',
            data: $("#customerRegistrationForm").serialize(),
 	       success: function(data) {
 	       		console.log(data);
 	       		if(data!="ok"){
 	       			json = $.parseJSON(data)
 	       			$.each(json,function(key,value){
 	       			$('#errorContainer ul').append('<li>Error in '+value.errorHeader +' '+value.errorDescription+'</li>');

 	       		});  //end of iteration
 	       		}else{
 	       			//TODO: just a temp hack
 	       			$('#errorContainer ul').append('<li>mandamos un correo a tu mailbox, por favor siga los instruciones alli</li>');
 	       		}
 	       		
 	       },
 	       error : function(data){
 	       	console.log(data);
 	    	   alert('paso algo muy malo');
 	       }
          });
   	}