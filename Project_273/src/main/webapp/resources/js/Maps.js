function Maps()
{
	
      return map();
      
      
      function map() {
        		alert("1");
        		var placename=document.getElementById("placename").value;
        	      
        	    var string={ 
        	    		"placename" : placename
        	    };
        	    alert(string);

        	   
        	    	jQuery.ajax({
        	        type: "POST",
        	        url: "http://localhost:8080/maplogin",
        	        data:JSON.stringify(string),
        	        contentType: "application/json; charset=utf-8",
        	        dataType: "json",
        	        success: function (data, status, jqXHR) {
        	             
        	             alert(status);
        	             window.location.href="/success"
        	        },
        	    
        	        error: function (jqXHR, status, textStatus) {            
        	             // error handler
        	        	 
        	             alert("error " + status + " Text" + textStatus);
        	             window.location.href="/Error"
        	        }

        	    });

        	}
          


}



