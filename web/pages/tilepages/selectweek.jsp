<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <link href="<c:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
        <fmt:setBundle basename="ApplicationResources" />
        <title> <fmt:message key="title.crudschedule"/> </title>
    </head>
    <body>
        <h1><fmt:message key="label.crudschedule"/></h1>
        <c:url var="url" scope="page" value="/nocturne/addeditscheudle" >
            <c:param name="duration" value=""/>
            <c:param name="dateOfProgram" value=""/>
            <c:param name="startTime" value=""/>
            <c:param name="programName" value=""/>
            <c:param name="presenter" value=""/>
            <c:param name="producer" value=""/>
            <c:param name="insert" value="true"/>
        </c:url>
        <a href="${url}"><fmt:message key="label.crudschedule.add"/></a>
        <br/><br/>
        <c:forEach var="week" items="${weeks}" varStatus="status">

            <c:url var="url" scope="page" value="/nocturne/manageschedule">
                <c:param name="week" value="${week}"/>
            </c:url>
            <a href="${url}">${week}</a> <br>
            
        </c:forEach>
        
    </body>
</html>