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

$(document).on("click", "#btnAdd", function(event){
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

function validateItemForm()
{ 
		// NAME
	if ($("#projectName").val().trim() == "") 
	 { 
	 return "Insert Project name."; 
	 } 
	// GENDER
	if ($('input[name="projectType"]:checked').length === 0) 
	 { 
	 return "Select Project Type."; 
	 } 
	// YEAR
	if ($("#projFund").val() == "") 
	 { 
	 return "Select Amount"; 
	 } 
	return true; 

}

$(document).on("click", "#btnSave", function(event)
{ 
	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 
	// Form validation-------------------
	var status = validateItemForm(); 
	if (status != true) 
	 { 
		 $("#alertError").text(status); 
		 $("#alertError").show(); 
		 return; 
	 } 
	// If valid------------------------
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
	 $.ajax( 
	 { 
		 url : "ProjectAPI", 
		 type : type, 
		 data : $("#formProject").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) 
	 { 
	 onProjectSaveComplete(response.responseText, status); 
 } 
 }); 
});


function onProjectSaveComplete(response, status)
{ 
	if (status == "success") 
	 { 
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") 
		 { 
			 $("#alertSuccess").text("Successfully saved."); 
			 $("#alertSuccess").show(); 
			 $("#finprojectscontainer").html(resultSet.data); 
			$("#unfinprojectscontainer").html(resultSet.data); 

			 $("#formProject")[0].reset;
		 } 
		else if (resultSet.status.trim() == "error") 
		 { 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
		 } 
	} 
	else if (status == "error") 
	{ 
		 $("#alertError").text("Error while saving."); 
		 $("#alertError").show(); 
	} 
	else
	{ 
		 $("#alertError").text("Unknown error while saving.."); 
		 $("#alertError").show(); 
	} 
		 $("#hidItemIDSave").val(""); 
		 $("#formItem")[0].reset(); 
}

$(document).on("click", ".btnRemove", function(event)
{ 
 $.ajax( 
 { 
 url : "ProjectAPI", 
 type : "DELETE", 
 data : "projectID=" + $(this).data("projectid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemDeleteComplete(response.responseText, status); 
 } 
 }); 
});

function onItemDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#finprojectscontainer").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}
