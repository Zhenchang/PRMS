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
        <table class="borderAll">
            <tr>
                <th><fmt:message key="label.crudschedule.duration"/></th>
                <th><fmt:message key="label.crudschedule.dateOfProgram"/></th>
                <th><fmt:message key="label.crudschedule.startTime"/></th>
                <th><fmt:message key="label.crudschedule.programName"/></th>
                <th><fmt:message key="label.crudschedule.presenter"/></th>
                <th><fmt:message key="label.crudschedule.producer"/></th>
                <th><fmt:message key="label.crudschedule.edit"/> <fmt:message key="label.crudschedule.delete"/><fmt:message key="label.crudschedule.copy"/></th>
            </tr>
            <c:forEach var="crudschedule" items="${programsolts}" varStatus="status">
                <tr class="${status.index%2==0?'even':'odd'}">
                    <td class="nowrap">${crudschedule.duration}</td>
                    <td class="nowrap">${crudschedule.dateOfProgram}</td>
                    <td class="nowrap">${crudschedule.startTime}</td>
                    <td class="nowrap">${crudschedule.programName}</td>
                    <td class="nowrap">${crudschedule.presenter.name}</td>
                    <td class="nowrap">${crudschedule.producer.name}</td>
                    <td class="nowrap">
                        <c:url var="updurl" scope="page" value="/nocturne/addeditscheudle">
                            <c:param name="duration" value="${crudschedule.duration}"/>
                            <c:param name="dateOfProgram" value="${crudschedule.dateOfProgram}"/>
                            <c:param name="startTime" value="${crudschedule.startTime}"/>
                            <c:param name="programName" value="${crudschedule.programName}"/>
                            <c:param name="presenter" value="${crudschedule.presenter.id}"/>
                            <c:param name="producer" value="${crudschedule.producer.id}"/>
                            <c:param name="insert" value="false"/>
                        </c:url>
                        <a href="${updurl}"><fmt:message key="label.crudschedule.edit"/></a>
                        &nbsp;&nbsp;&nbsp;
                        <c:url var="delurl" scope="page" value="/nocturne/deleteschedule">
                            <c:param name="duration" value="${crudschedule.duration}"/>
                            <c:param name="dateOfProgram" value="${crudschedule.dateOfProgram}"/>
                        </c:url>
                        <a href="${delurl}"><fmt:message key="label.crudschedule.delete"/></a>
                        <c:url var="copyurl" scope="page" value="/nocturne/copyscheudle">
                            <c:param name="duration" value="${crudschedule.duration}"/>
                            <c:param name="dateOfProgram" value="${crudschedule.dateOfProgram}"/>
                            <c:param name="startTime" value="${crudschedule.startTime}"/>
                            <c:param name="programName" value="${crudschedule.programName}"/>
                            <c:param name="presenter" value="${crudschedule.presenter.id}"/>
                            <c:param name="producer" value="${crudschedule.producer.id}"/>
                            <c:param name="insert" value="true"/>
                        </c:url>
                        <a href="${copyurl}"><fmt:message key="label.crudschedule.copy" /></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>