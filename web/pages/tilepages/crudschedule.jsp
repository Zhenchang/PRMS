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
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
            $(document).ready(function () {
                $("#show").click(function () {
                    $("#date").show();
                });
            });
        </script>
        <script>
            $(function () {
                $("#datepicker").datepicker({
                    dateFormat: "yy-mm-dd"
                });
            });
        </script>
        <script>
            $(document).ready(function () {
                $("#confirm").click(function () {
                    $.ajax({url: "/phoenix/nocturne/copyWeeklySchedule?targetWeek=" + $("#datepicker").val() + "&originWeek=" + $("#originWeek").val(),
                        success: function (result, textStatus, request) {
                            alert("Copy weekly schedul successful!");
                        }});
                });
            });
        </script>
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
        <input type="button" value ="Copy weekly schedule" id="show" /><br/>
        <div id="date" hidden="true">
            <p>Select a week:<input type="text" id="datepicker">&nbsp;&nbsp;&nbsp;<input id="confirm" type="button" value="confirm"></p>
            <input type="hidden" id="originWeek" name="originWeek" value="${param['week']}"> 
        </div>
        <br/><br/>
        <table class="borderAll">
            <tr>
                <th><fmt:message key="label.crudschedule.duration"/></th>
                <th><fmt:message key="label.crudschedule.dateOfProgram"/></th>
                <th><fmt:message key="label.crudschedule.startTime"/></th>
                <th><fmt:message key="label.crudschedule.programName"/></th>
                <th><fmt:message key="label.crudschedule.presenter"/></th>
                <th><fmt:message key="label.crudschedule.producer"/></th>
                <th><fmt:message key="label.crudschedule.edit"/> <fmt:message key="label.crudschedule.delete"/></th>
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
                            <c:param name="startTime" value="${crudschedule.startTime}"/>
                            <c:param name="dateOfProgram" value="${crudschedule.dateOfProgram}"/>
                        </c:url>
                        <a href="${delurl}"><fmt:message key="label.crudschedule.delete"/></a>
                        <c:url var="copyurl" scope="page" value="/nocturne/addeditscheudle">
                            <c:param name="duration" value="${crudschedule.duration}"/>
                            <c:param name="dateOfProgram" value="${crudschedule.dateOfProgram}"/>
                            <c:param name="startTime" value="${crudschedule.startTime}"/>
                            <c:param name="programName" value="${crudschedule.programName}"/>
                            <c:param name="presenter" value="${crudschedule.presenter.id}"/>
                            <c:param name="producer" value="${crudschedule.producer.id}"/>
                            <c:param name="insert" value="true"/>
                        </c:url>
<!--                        <a href="${copyurl}"><fmt:message key="label.crudschedule.copy" /></a>-->
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>