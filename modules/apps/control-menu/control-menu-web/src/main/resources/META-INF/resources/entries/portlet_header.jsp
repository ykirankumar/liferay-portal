<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
String portletTitle = (String)request.getAttribute("portletTitle");
String portletDescription = (String)request.getAttribute("portletDescription");
%>

<span class="header-toolbar-title" data-qa-id="headerOptions"><%= HtmlUtil.escape(portletTitle) %></span>

<c:if test="<%= Validator.isNotNull(portletDescription) %>">
	<liferay-ui:icon-help message="<%= HtmlUtil.escape(portletDescription) %>" />
</c:if>