<%@page contentType="text/xml"%>
<%@page pageEncoding="UTF-8"%>

<anios>
for(int i=0; i<Anios.size();i++)
{
    out.println("<anio>"+Anios.get(i)+"</anio>");
}
</anios>