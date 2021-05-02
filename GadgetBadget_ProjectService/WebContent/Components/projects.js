$(document).ready(function()
{ 
 $("#formContainer").hide(); 
 $("#unfinprojectscontainer").hide(); 
	$("#finprojectscontainer").hide(); 
		$("#fund").hide(); 




if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
 
}); 

$(document).on("click", "#btnSave", function(event){
	 $("#formContainer").show(); 

	
});

$(document).on("click", "#btnCancel", function(event){
	 $("#formContainer").hide(); 

});

$(document).on("click", "#btnViewFinished", function(event){
		
	$("#unfinprojectscontainer").hide(); 
	$("#finprojectscontainer").show(); 
  	$("#btnViewFinished").addClass("btn btn-danger");
  	$("#btnViewUnfinished").removeClass("btn btn-danger");
  	$("#btnViewUnfinished").addClass("btn btn-primary");


});

$(document).on("click", "#btnViewUnfinished", function(event){
		
	$("#unfinprojectscontainer").show(); 
	$("#finprojectscontainer").hide(); 
	$("#btnViewUnfinished").addClass("btn btn-danger");
  	$("#btnViewFinished").removeClass("btn btn-danger");
  	$("#btnViewFinished").addClass("btn btn-primary");
});

$(document).on("click", "#projectType", function(){
		
	if($(this).val() == "finished"){	
	$("#fund").hide(); 
	$("#price").show(); 

	}
	if($(this).val() == "unfinished"){	
	$("#price").hide();
	$("#fund").show(); 
 
	}
});



