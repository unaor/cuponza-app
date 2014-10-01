<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>CuponZa - Formulario Registro</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="icon" type="image/x-icon" href="/cuponza/dist/favicon.ico">

	<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.5.0/pure-min.css">
	<link rel="stylesheet" href="/cuponza/dist/css/customer.css">
	<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.5.0/grids-responsive-min.css">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="/cuponza/dist/js/customer.js"></script>
</head>
<body>
	<div id="container">
		<div class="logo-hd"></div>
		<div id="errorContainer"><ul></ul></div>
		<form id="customerRegistrationForm" class="pure-form pure-form-stacked" action="/cuponza/customer/add" method='POST'>
		    <fieldset>
		        <legend>Nueva Empresa</legend>

		        <div class="pure-g">
		            <div class="pure-u-1 pure-u-md-1-3">
		                <label for="first-name">nombres</label>
		                <input id="first-name" name="firstName" type="text">
		            </div>

		            <div class="pure-u-1 pure-u-md-1-3">
		                <label for="last-name">apellidos</label>
		                <input id="last-name" name="lastName" type="text">
		            </div>

		            <div class="pure-u-1 pure-u-md-1-3">
		                <label for="email">e-mail</label>
		                <input id="email" name="contactEmail" type="email">
		            </div>
		            
		            <div class="pure-u-1 pure-u-md-1-3">
		                <label for="address">contrase&ntilde;a</label>
		                <input name="password" type="password">
		            </div>
		            
		            <div class="pure-u-1 pure-u-md-1-3">
		                <label for="address">confirma contrase&ntilde;a</label>
		                <input name="passwordConfirmation" type="password">
		            </div>
		           	
		        </div>

		        <label for="terms" class="pure-checkbox">
		            <input id="terms" type="checkbox"> He leido las condiciones
		        </label>

		        <button type="button" class="pure-button pure-button-primary" onclick="verifyForm();">Registrar</button>
		    </fieldset>
		</form>
	</div>
	
</body>
</html>