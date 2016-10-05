<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <link href="<c:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
            $(function () {
                $("#datepicker").datepicker({
                    dateFormat: "yy-mm-dd"
                });
            });
        </script>
        <script>
            $(document).ready(function () {
                $("#show").click(function () {
                    $("#date").show();
                });
            });
        </script>
        <script>
            $(document).ready(function () {
                $("#confirm").click(function () {
                    $.ajax({url: "/phoenix/nocturne/validateDate?date=" + $("#datepicker").val(), success: function (result, textStatus, request) {
                            var isValid = request.getResponseHeader("isValid");
                            if (isValid === "true") {
                                alert(request.getResponseHeader("week"));
                                $("#dateOfProgram").val($("#datepicker").val());
                                $("#dates").val(request.getResponseHeader("dates"));
                                $("#week").val(request.getResponseHeader("week"));
                                $("#addEdit").trigger("click");
                            } else {
                                alert("Schedule for " + request.getResponseHeader("week") + "th week already existed.");
                            }
                        }});
                });
            });
        </script>
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
        <form action="/phoenix/nocturne/addeditscheudle" method="post">
            <input type="button" value ="Create schedule:" id="show" /><br/>
            <div id="date" hidden="true">
                <p>Date: <input type="text" id="datepicker">&nbsp;&nbsp;&nbsp;<input id="confirm" type="button" value="confirm"></p>
            </div>
            <input type="hidden" name="dates" id="dates" />
            <input type="hidden" name="dateOfProgram" id="dateOfProgram" />
            <input type="hidden" name="week" id="week" />
            <input type="hidden" name="insert" value="true" />
            <input id="addEdit" hidden="true" type="submit"/>
        </form>
        <br/><br/>
        <c:forEach var="annual" items="${annuals}" varStatus="status">
            <c:url var="url2" scope="page" value="/nocturne/selectweek">
                <c:param name="year" value="${annual}"/>
            </c:url>
            <a href="${url2}">${annual}</a> <br>
        </c:forEach>
    </body>
</html>