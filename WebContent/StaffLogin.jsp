<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Shinimex Program Ver1503</title>

<link rel="shortcut Icon" type="image/x-icon"
	href="images/SHV1_icon.ico" />

<link rel="stylesheet" type="text/css" href="css/reset.css" />
<link rel="stylesheet" type="text/css" href="css/table.css" />

<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/forJsp.js"></script>

<!--[if IE]>
	<script src="js/html5.js"></script>
<![endif]-->

</head>
<body>
	<div id="header"></div>

	<div id="wrapper">
		<div id="content">
			<form action="staffLogin.do" method="post">
			<h1>Log in</h1>
			<p>
				<label>User Name</label>
				<input required="required" name="username" type="text" placeholder="User Name" autofocus/>
			</p>
			<p>
				<label>Password</label>
				<input required="required" name="password" type="password" placeholder="Password" />
			</p>
			<p>
				<input type="submit" value="Login" />
			</p>
			<p>
				Not a staff yet ?
				<a href="">Join Us</a>
			</p>
			
			</form>

			
		</div>
	</div>

	<div id="footer">
		<p>2015 Copyright &copy; SHINIMEX, All Rights Reserved</p>
		<p>│Best Resolution：IE8+, Chrome, Safari or Firefox │ Best View
			use：1024X768 │ Designed by C.P.Y</p>
	</div>
</body>
</html>