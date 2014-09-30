   	function verifyForm(){
   		console.log("verifying form");
   		var terms = $('#terms');
   		
   		$.ajax({
            type: "POST",
            url: '/cuponza/customer/add',
            data: $("#customerRegistrationForm").serialize(),
 	       success: function(data) {
 	       		alert(data);
 	       },
 	       failure : function(data){
 	    	   alert(data);
 	       }
          });
   		
   	}
