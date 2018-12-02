<?xml version="1.0" encoding="iso-8859-15" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">   
<xsl:output method="html"/>
<xsl:template match="/">    
Anio: <xsl:value-of select="Songs/Anio"/>
</xsl:template>
</xsl:stylesheet>