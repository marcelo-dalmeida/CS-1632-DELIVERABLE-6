<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>
<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
<link rel='stylesheet' type='text/css' href='./../css/table_layout.css'>

<title>table layout</title>

</head>
<body>


<div class='container'>

<div class='header'><h1> Personal Info </h1></div>
<div class='content1'> Search Personal info <br>
<a href='./../index.php'>Main Menu</a>  -  
<a href='./../insert.php'>Insert</a>  -  
<a href='./../search.php'>Search</a>  -  
<a href='./../delete.php'>Delete</a>  -  
<a href="login.php">Login</a><br><br>


<!-- if you need user information, just put them into the $_SESSION variable and output them here -->
Hey, <?php echo $_SESSION['user_name']; ?>. You are logged in.
Try to close this browser tab and open it again. Still logged in! ;)

<!-- because people were asking: "index.php?logout" is just my simplified form of "index.php?logout=true" -->
<a href="login.php?logout">Logout</a>

<a href="register.php">Register new account</a>

</div>
</div>
	
<br/>
</body></html>