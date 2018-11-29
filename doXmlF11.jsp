<%@page contentType="text/xml"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<anios>
<c:forEach var = "i" items = "${aniBean.hanio}">
<anio>${i.getValue()}</anio>
</c:forEach>
</anios>