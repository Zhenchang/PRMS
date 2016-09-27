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
    </head>
    
        <form action="${pageContext.request.contextPath}/nocturne/enterschedule" method=post>
            
            <center>
                <table cellpadding=4 cellspacing=2 border=0>
                    <tr>
                        <td><fmt:message key="label.crudschedule.duration" /></td>
                        <td><input id="duration" type="text" name="duration" value="${param['duration']}" maxlength=20000></td> </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="label.crudschedule.dateOfProgram" /></td>
                        <td><input id="dateOfProgram" type="text" name="dateOfProgram" value="${param['dateOfProgram']}" maxlength=2000></td>
                    </tr>
                    <tr>	
                        <td><fmt:message key="label.crudschedule.startTime" /></td>
                        <td><input type="text" name="startTime" value="${param['startTime']}" maxlength=21000></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="label.crudschedule.programName" /></td>
                        <td>
                            <select name="programName">
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
                            <select name="presenter">
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
                            <select name="producer">
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
                <input type="hidden" name="ins" value="true" />
            </c:if> 
            <c:if test="${param['insert']=='false'}">
                <input type="hidden" name="ins" value="false" />
            </c:if>
            <input type="hidden" name="duration" value="${param['duration']}">
            <input type="hidden" name="dateOfProgram" value="${param['dateOfProgram']}">
            <input type="submit" value="Submit"> <input type="reset" value="Reset">
        </form>
    
</html>