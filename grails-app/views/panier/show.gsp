
<%@ page import="biblioj.Panier" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'panier.label', default: 'Panier')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-panier" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<g:include controller="panier" action="showLivres"/>
		<div id="show-panier" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list panier">
				<g:if test="${panierInstance?.username}">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="panier.username.label" default="Username" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${panierInstance}" field="username"/></span>
					
				</li>
				</g:if>			
				<g:if test="${panierInstance?.meslivres}">
					<li class="fieldcontain">
						<span id="meslivres-label" class="property-label"><g:message code="panier.meslivres.label" default="Meslivres" /></span>
					
							<g:each in="${panierInstance.meslivres}" var="r">
							<span class="property-value" aria-labelledby="meslivres-label"><g:link controller="livre" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
							</g:each>
						
					</li>
				</g:if>
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${panierInstance?.id}" />
					<g:link class="edit" action="edit" id="${panierInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
