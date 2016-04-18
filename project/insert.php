<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./css/table_layout.css">

<title>table layout</title>

</head>
<body>

<?php session_start(); ?>

<div class="container">

	<div class="header"><h1> Personal Info </h1></div>
	<div class="content1"> Insert Personal info <br>
	<a href="index.php">Main Menu</a>  -  
	<?php 
	if (array_key_exists('user_name', $_SESSION))
	{
		echo '<a href="insert.php">Insert</a>  -  ';
		echo '<a href="delete.php">Delete</a>  -  ';
	}
	?>
	<a href="search.php">Search</a>  -  
	<a href="./php/login.php">Login</a><br><br>             
	
	<?php 
		if (array_key_exists('user_name', $_SESSION))
		{
			echo 'Hey, '.$_SESSION['user_name'].'. ';
			echo '<a href="./php/login.php?logout">Logout</a>';
			echo '<br><br>';
		}
	?>
	
	
	<form action="./php/insert_result.php" method="post" name="insert_form" method="post">
		License Plate:<br>
		<input type=text name="license_plate"><br>
		Manufacturer:<br>
		<input type=text name="manufacturer"><br>
		Model:<br>
		<input type=text name="model"><br>
		Year:<br>
		<input type=number min="1800" name="year"><br>
		Color:<br>
		<input type=text name="color"><br>
		Price:<br>
		<input type=number min="0" step="0.01" name="price"><br>
		<br>
		<input type=submit name="insert" value="insert" onClick="insert(insert_form.license_plate.value, insert_form.manufacturer.value, insert_form.model.value,
		insert_form.year.value, insert_form.color.value, insert_form.price.value)"><br><br>
	</form>
	
	
	</div>
</div>


</body></html>