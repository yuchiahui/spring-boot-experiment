<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

	<xsl:output
		encoding="UTF-8"
		media-type="text/html"
		method="html"
		indent="no"
		omit-xml-declaration="yes"
	/>

	<xsl:template match="/">
		<HTML>
			<xsl:apply-templates select="document"/>
		</HTML>
	</xsl:template>
	
	<xsl:template match="document">
		<HEAD>
			<TITLE>test.xsl</TITLE>
		</HEAD>
		<BODY>
			<xsl:apply-templates select="articles"/>
		</BODY>
	</xsl:template>
	
	<xsl:template match="articles">
		<xsl:apply-templates select="article"/>
	</xsl:template>
	
	<xsl:template match="article">
		<DIV>
			<xsl:attribute name="id">
				<xsl:value-of select="@id"/>
			</xsl:attribute>
			<SPAN>時間 <xsl:value-of select="@postedAt"/>
			</SPAN>
			<xsl:if test="not(@emotions)">
				<SPAN>123</SPAN>
			</xsl:if>
			<P>
				<A href="javascript:void(0)">
					<xsl:attribute name="title">nickname
					</xsl:attribute>
					<xsl:value-of select="author/nickname"/>
				</A>
			</P>
			<INPUT name="content" value="{content}"/>
			<HR/>
		</DIV>
		<DIV>
			<xsl:if test="(/document/@me)">
				<FORM action="" method="POST" class="mode" style="margin-bottom:-10px">
					<P CLass="lead emoji-picker-container">
						<SPAN>Welcome!</SPAN>
						<BR/>
						<BUTTON type="submit" class="btn btn-lg btn-light">log out</BUTTON>
					</P>
				</FORM>
			</xsl:if>
		</DIV>
	</xsl:template>
</xsl:stylesheet>
