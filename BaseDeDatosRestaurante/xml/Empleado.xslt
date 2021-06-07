<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html> 
<body>
  <h2>Empleados Pollos Hermanos</h2>
  <table border="1">
    <tr bgcolor="#9acd32">
      <th style="text-align:left">Nombre</th>
      <th style="text-align:left">Salario</th>
      <th style="text-align:left">Cargo_id</th>
    </tr>
    <xsl:for-each select="empleado">
    <tr>
      <td><xsl:value-of select="nombre"/></td>
      <td><xsl:value-of select="salario"/></td>
      <td><xsl:value-of select="cargoId"/></td> 
    </tr>
    </xsl:for-each>
  </table>
</body>
</html>
</xsl:template>
</xsl:stylesheet>

