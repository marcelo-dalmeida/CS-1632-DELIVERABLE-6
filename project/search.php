<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./css/table_layout.css">

<title>table layout</title>

</head>
<body>

<?php session_start(); ?>

<div class="container">

	<div class="header"><h1> Personal Info </h1></div>
	<div class="content1"> Search Personal info <br>
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
	
	
	<form action="./php/search_result.php" method="post" name="search_form" method="post">
		
		License Plate:<br>
		<input type=text name="license_plate"><br>
		
		Manufacturer:<br>
		<select name="manufacturer_logical">
			<option value=" AND "></option>
			<option value=" AND ">and</option>
			<option value=" OR ">or</option>
		</select>
		<input type=text name="manufacturer"><br>
		
		Model:<br>
		<select name="model_logical">
			<option value=" AND "></option>
			<option value=" AND ">and</option>
			<option value=" OR ">or</option>
		</select>
		<input type=text name="model"><br>
		
		Year:<br>
		<select name="year_logical">
			<option value=" AND "></option>
			<option value=" AND ">and</option>
			<option value=" OR ">or</option>
		</select>
		<input type=number min="1800" name="year">
		<select name="year_comparative">
			<option value="=">= selected price</option>
			<option value=">">> than selected price</option>
			<option value=">=">>= than selected price</option>
			<option value="<">< than selected price</option>
			<option value="<="><= than selected price</option>
		</select><br>
		
		Color:<br>
		<select name="color_logical">
			<option value=" AND "></option>
			<option value=" AND ">and</option>
			<option value=" OR ">or</option>
		</select>
		<input type=text name="color"><br>
		
		Price:<br>
		<select name="price_logical">
			<option value=" AND "></option>
			<option value=" AND ">and</option>
			<option value=" OR ">or</option>
		</select>
		<input type=number min="0" step="0.01" name="price">
		<select name="price_comparative">
			<option value="=">= selected price</option>
			<option value=">">> than selected price</option>
			<option value=">=">>= than selected price</option>
			<option value="<">< than selected price</option>
			<option value="<="><= than selected price</option>
		</select><br>
		
		
		<br>
		<input type=submit name="search_button" value="search" onClick="(search_form.license_plate.value, search_form.manufacturer.value, search_form.model.value,
		search_form.year.value, search_form.color.value, search_form.price.value,
		search_form.manufacturer_logical.value, search_form.model_logical.value, search_form.year_logical.value, search_form.color_logical.value, search_form.price_logical.value,
		search_form.year_comparative.value, search_form.price_comparative.value)"><br><br>
		
	</form>
	</div>
</div>


</body></html>