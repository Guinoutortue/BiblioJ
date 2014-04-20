<html>
	<head name="layout" content="main">
		<title></title>
		<style type="text/css">
			label{
				float:left;
				width:65px;
			}
		</style>
	</head>
	<body>
		
		<g:if test="${flash.message}">
			<div class="message">
				${flash.message}
			</div>
		</g:if>
		<g:if test="${session.user}">
			Login as : ${session.user} | <g:link action="logout">Logout</g:link>
		</g:if>
		<g:else>
			<g:form action="login" style="padding-left:200px">
				<div style="width:220px">
					<label>Name:</label><input type="text" name="username"/><br/>
					<label>&nbsp;</label><input type="submit" value="login"/>
				</div>
			</g:form>
		</g:else>
	
	</body>
</html>