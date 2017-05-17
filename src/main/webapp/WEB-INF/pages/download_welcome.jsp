<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring 4 MVC File Download Example</title>
<link href="<c:url value='/resources/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/resources/css/app_upload.css' />" rel="stylesheet"></link>
</head>
<body>
	<div class="form-container">
		<h1>Welcome to FileDownloader Example</h1>

		Click on below links to see FileDownload in action.<br />
		<br /> <a href="/HelloSpringMVC/download/internal">Download
			This File (located inside project)</a> <br /> <a
			href="/HelloSpringMVC/download/external">Download This File
			(located outside project, on file system)</a>

	</div>
</body>
</html>