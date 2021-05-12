<%@page import="com.Project"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/projects.js"></script>
</head>
<body>


	<div class="container" >
		<button id = "btnAdd" class="btn btn-primary" style = "margin : 20px" >Add New Project</button>
	
		<div class="row" id = "formContainer">
			<div class="col-6"> 
				 <form id="formProject" name="formItem">
					 Project Topic: 
					 <input id="projectName" name="projectName" type="text" 
					 class="form-control form-control-sm">
					 <br> Project Type: <br>
					 <div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="projectType" id="projectType" value="finished">
					  <label class="form-check-label" for="inlineRadio1" checked>Finished</label>
					</div>
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="projectType" id="projectType" value="unfinished">
					  <label class="form-check-label" for="inlineRadio2">Unfinished</label>
					</div><br>
					 <br><label id = "fund">Funds Required: </label><label id = "price">Project Price:  </label>  
					 <input id="projFund" name="projFund" type="text" 
					 class="form-control form-control-sm">
					 <br>
					 <input id="btnSave" name="btnSave" type="button" value="Save" 
					 class="btn btn-primary">
					 <input id="btnCancel" name="btnSave" type="button" value="Cancel" 
					 class="btn btn-danger">
					 <input type="hidden" id="hidItemIDSave" 
					 name="hidItemIDSave" value="">
				</form>
				
				<div id="alertSuccess" class="alert alert-success" style = "margin-top : 20px"></div>
				<div id="alertError" class="alert alert-danger" style = "margin-top : 20px"></div>
				<br>
			</div>
		</div>	
		 <input id="btnViewFinished" name="btnViewFinished" type="button" value="View Finished Projects" class="btn btn-primary">
		 <input id="btnViewUnfinished" name="btnViewUnfinished" type="button" value="View Unfinished Projects" class="btn btn-primary">
		 <br>
		<div id = "unfinprojectscontainer" style = "margin-top : 20px" >
			<%
				Project projObject = new Project();
				out.print(projObject.readUnfinishedProjects("1002"));
			 %>
		</div>
		
		<div id = "finprojectscontainer" style = "margin-top : 20px">
			<%
				out.print(projObject.readFinishedProjects("1002"));
			 %>
		</div>
	</div>
	
	
	
</body>
</html>