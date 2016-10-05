<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

        <fmt:setBundle basename="ApplicationResources" />

        <title><fmt:message key="title.setupschedule" /></title>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
            $(document).ready(function () {
                $("#submit").click(function () {
                    $.post("/phoenix/nocturne/enterschedule",
                            {
                                duration: $("#duration").val(),
                                startTime: $("#startTime").val(),
                                programName: $("#programName").val(),
                                presenter: $("#presenter").val(),
                                producer: $("#producer").val(),
                                ins: $("#ins").val(),
                                week: $("#week").val(),
                                startDate: $("#startDate").val(),
                                dateOfProgram: $("#dateOfProgram").val()
                            },
                            function (data, status) {
                                alert("Success.");
                                $("#reset").click();
                            }
                    );
                });
                $("#programName").change(function() {
                    $.post("/phoenix/nocturne/getProgramDuration",
                            {
                                programName: $("#programName").val()
                            },
                            function (data, status, request) {
                                $("#duration").val(request.getResponseHeader("duration"));
                            }
                    );
                });
                $("#programName").change();
            });
        </script>
        <script>

        </script>
    </head>
    <form action="${pageContext.request.contextPath}/nocturne/enterschedule" method=post>
        <center>
            <table cellpadding=4 cellspacing=2 border=0>
                <tr>
                    <td><fmt:message key="label.crudschedule.duration" /></td>
                    <td><input readonly id="duration" type="text" name="duration" value="${param['duration']}" maxlength=20000></td> </td>
                </tr>
                <tr>
                    <td><fmt:message key="label.crudschedule.dateOfProgram" /></td>
                    <td>
                        <c:set var="dateParts" value="${fn:split(param['dates'], ';')}" />
                        <c:if test="${ empty param['dates']}">
                            <c:set var="dateParts" value="${fn:split(dates, ';')}" />
                        </c:if>
                        <select id="dateOfProgram" name="dateOfProgram">
                            <c:forEach var="date" items="${dateParts}">
                                <c:if test="${date == param['dateOfProgram']}">
                                    <option selected value="${date}">${date}</option>
                                </c:if>
                                <c:if test="${date != dateOfProgram}">
                                    <option value="${date}">${date}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>	
                    <td><fmt:message key="label.crudschedule.startTime" /></td>
                    <td><input type="text" id="startTime" name="startTime" value="${param['startTime']}" maxlength=21000></td>
                </tr>
                <tr>
                    <td><fmt:message key="label.crudschedule.programName" /></td>
                    <td>
                        <select id="programName" name="programName">
                            <c:forEach var="program" items="${program}" varStatus="status">
                                <c:if test="${param['programName'] == program.name}">
                                    <option selected="selected" value="${program.name}">${program.name}</option>
                                </c:if> 
                                <c:if test="${param['programName'] != program.name}">
                                    <option value="${program.name}">${program.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="label.crudschedule.presenter" /></td>
                    <td>
                        <select id="presenter" name="presenter">
                            <c:forEach var="presenter" items="${presenter}" varStatus="status">
                                <c:if test="${param['presenter'] == presenter.id}">
                                    <option selected="selected" value="${presenter.id}">${presenter.name}</option>
                                </c:if> 
                                <c:if test="${param['presenter'] != presenter.id}">
                                    <option value="${presenter.id}">${presenter.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="label.crudschedule.producer" /></td>
                    <td>
                        <select id="producer" name="producer">
                            <c:forEach var="producer" items="${producer}" varStatus="status">
                                <c:if test="${param['producer'] == producer.id}">
                                    <option selected="selected" value="${producer.id}">${producer.name}</option>
                                </c:if> 
                                <c:if test="${param['producer'] != producer.id}">
                                    <option value="${producer.id}">${producer.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </table>
        </center>
        <c:if test="${param['insert'] == 'true'}">
            <input type="hidden" id="ins" name="ins" value="true" />
        </c:if> 
        <c:if test="${param['insert']=='false'}">
            <input type="hidden" id="ins" name="ins" value="false" />
        </c:if>
        <input type="hidden" id="week" name="week" value="${param['week']}" />
        <input type="hidden" id="startDate" name="startDate" value="${dateParts[0]}" />
<!--        <input type="hidden" name="duration" value="${param['duration']}">
        <input type="hidden" name="dateOfProgram" value="${param['dateOfProgram']}">-->
        <input type="button" id="submit" value="Submit"> <input hidden="true" id="reset" type="reset" value="Reset">
    </form>

</html>