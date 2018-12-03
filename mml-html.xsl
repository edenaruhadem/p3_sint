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
                    <h3>Idd: <xsl:value-of select="@idd"/> Idioma: <xsl:value-of select="@langs"/></h3>
                    <table border="1"><tr><th>Titulo</th><th>Premios</th><th>Interprete</th><th>Cancion</th></tr>
                        <tr>
                            <td><xsl:value-of select="Titulo"/></td>
                            <xsl:value-of select="Premios"/>
                            <td><xsl:for-each select="Premio">
                            <xsl:value-of select="Premio"/>
                            </xsl:for-each></td>
                            <!--<td><xsl:for-each select="Premios/Premio">
                            
                            </xsl:for-each></td>-->                                 
                        </tr>
                    </table>
                </xsl:for-each>
            </xsl:for-each>
            <!--<xsl:for-each select="Songs/Pais/Disco">
                    <h3>Idd: <xsl:value-of select="@idd"/> Idioma: <xsl:value-of select="@langs"/></h3>
                    <table border="1"><th>A.Idd</th><th>A.Langs</th><th>Titulo</th><th>Premios</th><th>Interprete</th><th>Cancion</th>
                        <xsl:apply-templates select="Songs/Pais/Disco"/>
                    </table>
            </xsl:for-each>-->        
        </body>
    </html>
    </xsl:template>

    <xsl:template match="Disco">
    <tr> <td></td>  </tr>
    </xsl:template>

</xsl:stylesheet>