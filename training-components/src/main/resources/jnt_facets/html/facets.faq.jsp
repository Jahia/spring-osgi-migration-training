<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="query" uri="http://www.jahia.org/tags/queryLib" %>
<%@ taglib prefix="facet" uri="http://www.jahia.org/tags/facetLib" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions" %>
<%@ taglib prefix="uiComponents" uri="http://www.jahia.org/tags/uiComponentsLib" %>
<%--@elvariable id="currentNode" type="org.jahia.services.content.JCRNodeWrapper"--%>
<%--@elvariable id="out" type="java.io.PrintWriter"--%>
<%--@elvariable id="script" type="org.jahia.services.render.scripting.Script"--%>
<%--@elvariable id="scriptInfo" type="java.lang.String"--%>
<%--@elvariable id="workspace" type="java.lang.String"--%>
<%--@elvariable id="renderContext" type="org.jahia.services.render.RenderContext"--%>
<%--@elvariable id="currentResource" type="org.jahia.services.render.Resource"--%>
<%--@elvariable id="url" type="org.jahia.services.render.URLGenerator"--%>
<%--@elvariable id="acl" type="java.lang.String"--%>

<%-- AFFICHAGE PRINCIPAL DES FILTRES --%>

<template:addResources type="css" resources="facetsFAQ.css"/>


<c:set var="boundComponent"
       value="${uiComponents:getBindedComponent(currentNode, renderContext, 'j:bindedComponent')}"/>
<jcr:nodeProperty var="facetListNodeType" node="${currentNode}" name="j:type"/>

<c:if test="${not empty boundComponent}">
    <c:set var="facetParamVarName" value="N-${boundComponent.name}"/>
    <c:set var="facetTargetTypeName" value="N-type-${boundComponent.name}"/>
    <c:set var="activeFacetMapVarName" value="afm-${boundComponent.name}"/>
    <c:if test="${not empty param[facetParamVarName] and empty activeFacetsVars[facetParamVarName]}">
        <c:if test="${activeFacetsVars == null}">
            <jsp:useBean id="activeFacetsVars" class="java.util.HashMap" scope="request"/>
        </c:if>
        <c:set target="${activeFacetsVars}" property="${facetParamVarName}"
               value="${functions:decodeUrlParam(param[facetParamVarName])}"/>
        <c:set target="${activeFacetsVars}" property="${activeFacetMapVarName}"
               value="${facet:getAppliedFacetFilters(activeFacetsVars[facetParamVarName])}"/>
    </c:if>

    <%-- These maps are populated by facet:setupQueryAndMetadata tag --%>
    <jsp:useBean id="facetLabels" class="java.util.HashMap" scope="request"/>
    <jsp:useBean id="facetValueLabels" class="java.util.HashMap" scope="request"/>
    <jsp:useBean id="facetValueFormats" class="java.util.HashMap" scope="request"/>
    <jsp:useBean id="facetValueRenderers" class="java.util.HashMap" scope="request"/>
    <jsp:useBean id="facetValueNodeTypes" class="java.util.HashMap" scope="request"/>

    <template:option node="${boundComponent}" nodetype="${boundComponent.primaryNodeTypeName},jmix:list"
                     view="hidden.load">
        <template:param name="queryLoadAllUnsorted" value="true"/>
        <template:param name="facetListNodeType" value="${facetListNodeType}"/>
    </template:option>

    <facet:setupQueryAndMetadata var="listQuery" boundComponent="${boundComponent}"
                                 existingQuery="${moduleMap.listQuery}"
                                 activeFacets="${activeFacetsVars[activeFacetMapVarName]}"/>
    <jcr:jqom var="result" qomBeanName="listQuery" scope="request"/>
    <c:if test="${not result.facetResultsEmpty or !empty activeFacetsVars[activeFacetMapVarName]}">
        <div class="facetsList">
                <%-- ==================================================================================================== --%>
                <%-- Libellé --%>
                <%-- ==================================================================================================== --%>
            <span class="glyphicon glyphicon-filter"></span>
            &nbsp;&nbsp;Filtres
            <span class="vertical-line"></span>

                <%-- ==================================================================================================== --%>
                <%-- FACETs ACTIFS --%>
                <%-- ==================================================================================================== --%>
            <c:if test="${not empty activeFacetsVars}">
                <c:forEach items="${activeFacetsVars[activeFacetMapVarName]}" var="facet" varStatus="activeFacetStat">
                    <c:forEach items="${facet.value}" var="facetValue">
                        <c:set var="facetParam"
                               value="${facet:getDeleteFacetUrl2(facetValue, activeFacetsVars[facetParamVarName])}"/>
                        <c:url var="facetUrl" value="${url.mainResource}">
                            <c:if test="${not empty facetParam}">
                                <c:param name="${facetTargetTypeName}"
                                         value="${functions:encodeUrlParam(facetListNodeType)}"/>
                                <c:param name="${facetParamVarName}" value="${functions:encodeUrlParam(facetParam)}"/>
                            </c:if>
                        </c:url>
                        <c:choose>
                            <%--hack to avoid url rewriting from filters--%>
                            <c:when test="${empty facetParam}">
                                <a class="btn btn-default actif"
                                   onclick="window.location='${facetUrl}'"><i
                                        class="glyphicon glyphicon-remove"></i><facet:facetValueLabel
                                        currentActiveFacet="${facet}" currentActiveFacetValue="${facetValue}"
                                        facetValueLabels="${facetValueLabels}"/></a>
                            </c:when>
                            <c:otherwise>
                                &nbsp;<a class="btn btn-default actif" href="${facetUrl}"><i class="glyphicon glyphicon-remove"></i><facet:facetValueLabel
                                    currentActiveFacet="${facet}" currentActiveFacetValue="${facetValue}"
                                    facetValueLabels="${facetValueLabels}"/></a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:forEach>
            </c:if>

                <%-- ==================================================================================================== --%>
                <%-- filtre des FACETS --%>
                <%-- ==================================================================================================== --%>
            <c:forEach items="${result.facetFields}" var="currentFacet">
                <c:forEach items="${currentFacet.values}" var="facetValue">
                    <c:if test="${not facet:isFacetValueApplied(facetValue, not empty activeFacetsVars ? activeFacetsVars[activeFacetMapVarName] : null)}">
                        <c:url var="facetUrl" value="${url.mainResource}">
                            <c:param name="${facetTargetTypeName}"
                                     value="${functions:encodeUrlParam(facetListNodeType)}"/>
                            <c:param name="${facetParamVarName}"
                                     value="${functions:encodeUrlParam(facet:getFacetDrillDownUrl(facetValue, not empty activeFacetsVars ? activeFacetsVars[facetParamVarName] : null))}"/>
                        </c:url>
                        <a href="${facetUrl}" class="btn btn-default inactif">
                            <facet:facetValueLabel currentFacetFieldName="${currentFacet.name}"
                                                   facetValueCount="${facetValue}"
                                                   facetValueLabels="${facetValueLabels}"
                                                   facetValueFormats="${facetValueFormats}"
                                                   facetValueRenderers="${facetValueRenderers}"
                                                   facetValueNodeTypes="${facetValueNodeTypes}"/>
                        </a>
                    </c:if>
                </c:forEach>
            </c:forEach>
        </div>
    </c:if>
</c:if>

<template:addResources type="inlinejavascript">
    <script type="text/javascript">
        $(function () {
            const btns = $('.facetsList .btn');
            btns.sort((a, b) => {
                if (a.innerText === undefined) {
                    return 0;
                }
                return a.innerText.localeCompare(b.innerText);
            }).appendTo('.facetsList');
            btns.each(a => {
                if (a.innerText !== undefined) {
                    a.innerText = a.innerText.substring(0, 1).toUpperCase() + a.innerText.substring(1)
                }
            });
        });
    </script>
</template:addResources>

<%-- POUR LE MODE EDITEUR, AFFICHAGE DES INFOS et EDITION DES DIFFERENS FACETS --%>
<c:if test="${editableModule}">
    <fmt:message key="facets.facetsSet"/> :
    <c:forEach items="${jcr:getNodes(currentNode, 'jnt:facet')}" var="facet">
        <template:module node="${facet}"/>
    </c:forEach>
    <template:module path="*"/>
</c:if>
