<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>File Upload Success</title>
    <link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/resources/css/app_upload.css' />" rel="stylesheet"></link>
</head>
<body>
    <div class="success">
            <c:forEach var="fileName" items="${fileNames}">
                File  <strong>${fileName}</strong> uploaded successfully<br/>
            </c:forEach>
            <br/>
        <a href="<c:url value='/upload/welcome' />">Home</a>
    </div>
</body>
</html>