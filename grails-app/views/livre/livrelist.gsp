<%@ page import="biblioj.Livre" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'livre.label', default: 'Livre')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-livre" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<g:include controller="panier" action="showLivres"/>
		<div id="list-livre" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="titre" title="${message(code: 'livre.titre.label', default: 'Titre')}" />
					
						<g:sortableColumn property="auteurs" title="${message(code: 'livre.auteurs.label', default: 'Auteur')}" />
						
						<g:sortableColumn property="typeDocuments" title="${message(code: 'livre.typeDocument.label', default: 'Type Document')}" />
					
						<g:sortableColumn property="nombreExemplairesDisponibles" title="${message(code: 'livre.nombreExemplairesDisponibles.label', default: 'Disponibles')}" />
						
						<g:if test="${session.user!=null}"><g:sortableColumn property="Action" title="Action" /></g:if>
					</tr>
				</thead>
				<tbody>
				<g:each in="${livreInstanceList}" status="i" var="livreInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${livreInstance.id}">${fieldValue(bean: livreInstance, field: "titre")}</g:link></td>
					
						<td><g:each in="${livreInstance.auteurs}" status="j" var="auteur">
                        	${fieldValue(bean: auteur, field: "nom")}
                        </g:each></td>
					
						<td>${fieldValue(bean: livreInstance.getAt("typeDocument"), field: "intitule")}</td>
					
						<td>${fieldValue(bean: livreInstance, field: "nombreExemplairesDisponibles")}</td>
						
						<g:if test="${session.user!=null}">
							<td><g:form url="[action:'ajouter',controller:'Panier']" >
								<g:if test="${livreInstance.nombreExemplairesDisponibles > 0}">
									<g:hiddenField name="cache" value="${livreInstance.titre}"/>
									<g:submitButton name="list" id="${livreInstance.titre}" class="list" value="Ajouter au panier" />
								</g:if>
							</g:form></td>
						</g:if>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${livreInstanceTotal}"  max="5" params="${[titre:params.titre, nom:params.nom, typedoc:params.typedoc]}"/>
			</div>
		</div>
	</body>
</html>
