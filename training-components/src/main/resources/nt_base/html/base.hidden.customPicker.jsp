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
<template:addResources type="javascript" resources="picker/picker.bundle.js"/>

<c:set var="targetId" value="customPicker_${fn:replace(currentNode.identifier,'-','_')}"/>
<div id="${targetId}"><fmt:message key="label.customPicker"/>...</div>

<script type="text/javascript">
    window.addEventListener("DOMContentLoaded", () => {
        window.customPickerData = {
            contextualData: {},
            data: [],
            setContextualData: contextualData => {
                this.contextualData = contextualData;
            },
            load: values => {
                console.log(values.d);
                if (values.d !== undefined && Array.isArray(values.d)) {
                    customPickerData.data = Array.from(values.d);
                }
            },
            add: path => this.data.push(path),
            remove: path => {
                this.data = this.data.filter(ele => !ele.endsWith(path));
            },
            removeAt: index => (index !== -1) ? this.data.splice(index, 1) : null,
            removeAll: () => {
                customPickerData.data = [];
            },
            get: () => customPickerData.data,
        };

        contextJsParameters = {};
        <c:choose>
            <c:when test="${renderContext.editMode}" >
                setTimeout(() => {
                    customPickerReactRender(contextJsParameters, '${targetId}');
                    customPickerData.setContextualData(contextJsParameters);
                }, 1000);
            </c:when>
            <c:otherwise>
                customPickerReactRender(contextJsParameters, '${targetId}');
                customPickerData.setContextualData(contextJsParameters);
            </c:otherwise>
        </c:choose>
    });
</script>
