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
<fmt:message key="tnt_sampleData"/>

<jcr:node var="bigText" path="${currentNode.path}/bigText"/>
<c:choose>
    <c:when test="${not empty bigText}">
        <template:module node="${bigText}"/>
    </c:when>
    <c:when test="${renderContext.editMode}">
        <template:module path="bigText" nodeTypes="jnt:bigText"/>
    </c:when>
</c:choose>

<c:forEach items="${jcr:getChildrenOfType(currentNode, 'jnt:text,jnt:contentReference')}" var="text">
    <template:module node="${text}"/>
</c:forEach>
<c:if test="${renderContext.editMode}">
    <template:module path="*" nodeTypes="jnt:text"/>
</c:if>