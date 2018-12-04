<?xml version="1.0"?>
<xsl:stylesheet version="1.0" 
                    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html"/>

    <xsl:template match="/">
    <html>
        <head>
            <title><xsl:value-of select="Songs/Anio"/></title>
        </head>
        <body>            
            <h1>Anio <xsl:value-of select="Songs/Anio"/></h1>
            <xsl:for-each select="Songs/Pais">
                <h2>Pais: <xsl:value-of select="@pais"/> Idioma: <xsl:value-of select="@lang"/></h2>
                <xsl:for-each select="Disco">
                    <h3>DISCO Idd: <xsl:value-of select="@idd"/> Idioma: <xsl:value-of select="@langs"/></h3>
                    <table border="1">
                        <tr>
                            <th>Titulo</th><th>Premios</th><th>Interprete</th><th>Canciones</th>
                        </tr>
                        <tr>
                            <td><xsl:value-of select="Titulo"/></td>                                                        
                            <td>
                                <xsl:value-of select="Premios"/>
                                <xsl:for-each select="Premio">
                                    <xsl:value-of select="Premio"/>
                                </xsl:for-each>
                            </td>
                            <td><xsl:value-of select="Interprete"/></td>                        
                            <td>                                
                                <table border = "1">
                                <xsl:for-each select="Cancion">
                                <tr><xsl:value-of select="@idc"/></tr>                                                          
                                <tr>
                                    <table border="1">
                                        <tr><th>Titulo</th><th>Duracion</th><th>Genero</th><th>Version</th><th>Descripcion</th></tr>                                                                
                                        <tr><td><xsl:value-of select="Titulo"/></td><td><xsl:value-of select="Duracion"/></td><td><xsl:value-of select="Genero"/></td>
                                            <td><table border ="1">
                                                <tr><xsl:value-of select="Version/@anio"/></tr>
                                                <tr><th>Titulo</th><th>IML</th><th>Idc</th></tr>
                                                <tr><td><xsl:value-of select="Version/Titulo"/></td><td><xsl:value-of select="Version/IML"/></td><td><xsl:value-of select="Version/Idc"/></td></tr>
                                            </table></td>                                                                                    
                                        </tr>
                                    </table>
                                </tr>
                                </xsl:for-each>
                                </table>                                
                            </td>
                        </tr>                    
                    </table>
                </xsl:for-each>
            </xsl:for-each>                   
        </body>
    </html>
    </xsl:template>
</xsl:stylesheet>