<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    int a= 20 ;
    request.setAttribute("myValue", a);
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
  </head>
  <body>
    Hello world
    <c:out value="${myValue}"/>
    , name=> <c:out value="${name}"/>
    , myParam=> <c:out value="${param.myParam}" />
    <br/>
    Test c:url
    <a href='<c:url value="/mvc/hello1"/>'>
      hello1
    </a>&nbsp;&nbsp;
    <a href='<c:url value="/mvc/hello2"/>'>
      hello2
    </a>
    HELLO.WORLD=><fmt:message key="HELLO.WORLD"/><br/>
    T1=><fmt:message key="TEST_1"/><br/>
    FMT Formatnumber=><fmt:formatNumber value="99999" pattern="#,###"/>
  </body>
</html>
