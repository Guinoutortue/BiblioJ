<%@ page import="biblioj.Livre" %>
<!DOCTYPE html>
<html>
	<head>
	
	</head>
	<body>
	
	<div id="page-body" role="main">
			<h1>Rechercher Livre:</h1>
			<g:form  url="[action:'formulaire',controller:'Livre']" >
				Titre:<g:textField name="titre"/>
				Auteur:<g:textField name="nom"/>
				Type:<g:textField name="typedoc"/>
				<g:submitButton name="formulaire" class="formulaire" value="Rechercher" />
			</g:form>
	</div>
	
	</body>
</html>