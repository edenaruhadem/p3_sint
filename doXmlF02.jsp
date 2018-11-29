<%@page contentType="text/xml"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>

<errores>
<warnings>
<c:forEach var = "i" items = "${errBean.warnings}" begin = "0" end = "1">
<warning>
<file>${i.getKey()}</file>
<cause>${i.getValue()}</cause>
</warning>
</c:forEach>
</warnings>
<errors>
<c:forEach var = "i" items = "${errBean.errores}" begin = "0" end = "10">
<error>
<file>${i.getKey()}</file>
<cause>${i.getValue()}</cause>
</error>
</c:forEach>
</errors>
<fatalerrors>
<c:forEach var = "i" items = "${errBean.erroresf}" begin = "0" end = "1">
<fatalerror>
<file>${i.getKey()}</file>
<cause>${i.getValue()}</cause>
</fatalerror>
</c:forEach>
</fatalerrors>
</errores>                      	              
        