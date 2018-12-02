<?xml version="1.0" encoding="iso-8859-15" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">   
<xsl:output method="html"/>
<xsl:template match="/">
    <html>
        <head>
            <title>Transformaci&oacute;n IML-HTML</title>
            <link rel='stylesheet' type='text/css' href='iml.css'></link>
        </head>
        <body>
            Anio: <xsl:value-of select="Songs/Anio"/>
        </body>
    </html> 
