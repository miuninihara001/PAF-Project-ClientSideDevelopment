<%@page import="model.Units"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
if (request.getParameter("recordID") != null) 
{ 
 Units readObj = new Units(); 
 String stsMsg = ""; 
//Insert----------------------------------------------------------------
if (request.getParameter("hidRecIDSave") == "") 
 { 
 stsMsg = readObj.insertUnits(request.getParameter("accountNo"),  
 request.getParameter("consumerName"), 
 request.getParameter("address"), 
 request.getParameter("district"), 
 request.getParameter("consumedUnits"), 
 request.getParameter("year"), 
 request.getParameter("month")); 
 } 
else      //Update-------------------------------------------------------
 { 
 stsMsg = readObj.updateUnit(request.getParameter("hidRecIDSave"), 
 request.getParameter("accountNo"), 
 request.getParameter("consumerName"), 
 request.getParameter("address"), 
 request.getParameter("district"), 
 request.getParameter("consumedUnits"), 
 request.getParameter("year"), 
 request.getParameter("month")); 
 } 
 session.setAttribute("statusMsg", stsMsg); 
} 

//Delete-----------------------------------------------------------------
if (request.getParameter("hidRecIDDelete") != null) 
{ 
	Units readObj = new Units();
	String stsMsg = 
	readObj.deleteUnit(request.getParameter("hidRecIDDelete")); 
	session.setAttribute("statusMsg", stsMsg); 
}
%>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">


<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<link rel="stylesheet" type="text/css" href="css\footer.css"> 
<script src="components/jquery-3.6.0.js"></script>
<script src="components/main.js"></script>

               
<title>Unit Management</title>
</head>
<body>

<div class="container"><div class="row"><div class="col-6">
<h1>Unit Management</h1>
	
	<form  id="formItem" name="formItem" method="post" action="units.jsp">
		AccountNo: <input id="acc" name="acc" type="text" class="form-control form-control-sm"> <br>
		Consumer Name: <input id="name" name="name" type="text" class="form-control form-control-sm"> <br>
		Address: <input id="add" name="add" type="text" class="form-control form-control-sm"> <br>
		District: <input id="district" name="district" type="text" class="form-control form-control-sm"> <br>
		Consumed Units: <input id="units" name="units" type="text" class="form-control form-control-sm"> <br>
		Year: <input id="year" name="year" type="text" class="form-control form-control-sm"> <br>
		Month: <input id="month" name="month" type="text" class="form-control form-control-sm"> <br>
		
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidRecIDSave" name="hidRecIDSave" value="">
		
	</form>
	
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	
<div id="divItemsGrid">
<%
 Units readObj = new Units();
 out.print(readObj.readUnits());
%>
</div>


</div></div></div>
</body>
</html>