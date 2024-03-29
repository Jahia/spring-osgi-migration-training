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
<%--@elvariable id="MY_SESSION_ATTRIBUTE" type="java.lang.String"--%>
Session attribute: ${MY_SESSION_ATTRIBUTE}<br/>

City: ${currentNode.properties['city'].string}<br/>
Weather: <jcr:nodePropertyRenderer node="${currentNode}" name="city" renderer="city"/>

<c:set var="imageNode" value="${currentNode.properties['image'].node}"/>
<c:if test="${not empty imageNode}">
    <template:addCacheDependency node="${imageNode}"/>
    <c:url var="imageUrl" value="${imageNode.url}"/>
</c:if>
<img class="countDownImage" alt="" src="${imageUrl}?t=thumbnail"/><br/>

<jcr:node var="data" path="${currentNode.path}/data"/>
<c:choose>
    <c:when test="${not empty data}">
        <template:module node="${data}"/>
    </c:when>
    <c:when test="${renderContext.editMode}">
        <template:module path="data" nodeTypes="tnt:sampleData"/>
    </c:when>
</c:choose>

<pre>Content generetad in ##TIMEGENERATION##ms</pre>
