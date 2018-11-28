<%@page contentType="text/xml"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<anios>
<c:forEach var = "i" items = "${aniBean.anio}" begin = "0" end = "3">
<anio>${i}</anio>
</c:forEach>
</anios>