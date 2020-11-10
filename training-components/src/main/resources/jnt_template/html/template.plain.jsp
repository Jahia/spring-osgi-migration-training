<%@ page language="java" contentType="text/html;charset=UTF-8" 
%><%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" 
%><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" 
%><html lang="${renderContext.mainResourceLocale.language}">

<head>
    <meta charset="utf-8">
    <title>${fn:escapeXml(renderContext.mainResource.node.displayableName)}</title>
</head>


<body style="margin: 0px">
    <template:area path="pagecontent"/>
</body>