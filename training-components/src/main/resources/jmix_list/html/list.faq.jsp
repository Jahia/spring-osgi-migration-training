<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="utility" uri="http://www.jahia.org/tags/utilityLib" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--@elvariable id="currentNode" type="org.jahia.services.content.JCRNodeWrapper"--%>
<%--@elvariable id="out" type="java.io.PrintWriter"--%>
<%--@elvariable id="script" type="org.jahia.services.render.scripting.Script"--%>
<%--@elvariable id="scriptInfo" type="java.lang.String"--%>
<%--@elvariable id="workspace" type="java.lang.String"--%>
<%--@elvariable id="renderContext" type="org.jahia.services.render.RenderContext"--%>
<%--@elvariable id="currentResource" type="org.jahia.services.render.Resource"--%>
<%--@elvariable id="url" type="org.jahia.services.render.URLGenerator"--%>
<template:include view="hidden.header"/>

<jsp:useBean id="nodesToDisplay" class="java.util.LinkedHashMap" scope="request"/>
<jsp:useBean id="emptyItems" class="java.util.LinkedHashMap" scope="request"/>
<c:set target="${nodesToDisplay}" property="" value="${emptyItems}"/>

<c:forEach items="${moduleMap.currentList}" var="subchild" begin="${moduleMap.begin}" end="${moduleMap.end}">
    <template:addCacheDependency node="${subchild}"/>

    <c:set var="categories" value="${subchild.properties['j:defaultCategory']}"/>
    <c:choose>
        <c:when test="${not empty categories}">
            <c:forEach items="${categories}" var="category" varStatus="status">
                <c:if test="${not empty category.node}">
                    <template:addCacheDependency node="${category.node}"/>
                    <c:set var="categoryTitle" value="${category.node.properties['jcr:title'].string}"/>
                    <c:set var="items" value="${nodesToDisplay[categoryTitle]}"/>
                    <c:if test="${empty items}">
                        <jsp:useBean id="items" class="java.util.LinkedHashMap" scope="request"/>
                        <c:set target="${nodesToDisplay}" property="${categoryTitle}" value="${items}"/>
                    </c:if>
                    <c:set target="${items}" property="${subchild.identifier}" value="${subchild}"/>
                </c:if>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <c:set var="categoryTitle" value=""/>
            <c:set var="items" value="${nodesToDisplay[categoryTitle]}"/>
            <c:set target="${items}" property="${subchild.identifier}" value="${subchild}"/>
        </c:otherwise>
    </c:choose>
</c:forEach>

<ul>
    <c:forEach items="${nodesToDisplay}" var="itemsByCategory">
        <c:if test="${not empty itemsByCategory.value}">
            <li>${itemsByCategory.key}
                <ul>
                    <c:forEach items="${itemsByCategory.value}" var="item">
                        <li><template:module node="${item.value}"/></li>
                    </c:forEach>
                </ul>
            </li>
        </c:if>
    </c:forEach>

    <c:if test="${moduleMap.editable and renderContext.editMode && !resourceReadOnly}">
        <li><template:module path="*"/></li>
    </c:if>
    <c:if test="${not empty moduleMap.emptyListMessage and renderContext.editMode and isEmpty}">
        <li>${moduleMap.emptyListMessage}</li>
    </c:if>
</ul>

<template:include view="hidden.footer"/>
