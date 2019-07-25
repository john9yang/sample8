<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="address_book/person">
        <H2><xsl:apply-templates/></H2>
    </xsl:template>

</xsl:stylesheet>