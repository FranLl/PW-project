<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ERROR</title>
		<link rel="stylesheet" type="text/css" href="/practica3/css/back_n_fonts.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/box_style.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/submit_input.css">
	</head>
	
	<body>
	<fieldset>
		<legend>ERROR ${pageContext.errorData.statusCode}</legend>
		<p>Ha ocurrido un error</p>
		<form id="volver" method="get" action="/practica3/mvc/controller/logOutController.jsp">
			<input type="submit" value="De acuerdo"/>
		</form>
	</fieldset>
	</body>
</html>