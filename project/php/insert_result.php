<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>
<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
<link rel='stylesheet' type='text/css' href='./../css/table_layout.css'>

<title>table layout</title>

</head>
<body>

<?php session_start(); ?>

<div class='container'>

<div class='header'><h1> Personal Info </h1></div>
<div class='content1'> Search Personal info <br>
<a href='./../index.php'>Main Menu</a>  -  
<?php 
	if (array_key_exists('user_name', $_SESSION))
	{
		echo "<a href='./../insert.php'>Insert</a>  -  ";
		echo "<a href='./../delete.php'>Delete</a>  -  ";
	}
?>
<a href='./../search.php'>Search</a>  -  
<a href="login.php">Login</a><br><br>

<?php 
	if (array_key_exists('user_name', $_SESSION))
	{
		echo 'Hey, '.$_SESSION['user_name'].'. ';
		echo '<a href="login.php?logout">Logout</a>';
		echo '<br><br>';
	}
	
?>


<?php
	$license_plate = $_REQUEST['license_plate'];
	$manufacturer = $_REQUEST['manufacturer'];
	$model = $_REQUEST['model'];
	$year = $_REQUEST['year'];
	$color = $_REQUEST['color'];
	$price = $_REQUEST['price'];
	
	$picture_name = strtolower($manufacturer) . " " . strtolower($model) . ".jpg";
	$picture_name = str_replace(' ', '_', $picture_name);

	$conn = mysql_connect("localhost", "manager", "7777777");
	mysql_select_db("project", $conn);
	
	$sql = "INSERT INTO car (license_plate, manufacturer, model, year, color, price, picture_name) 
	VALUES ('$license_plate', '$manufacturer', '$model', '$year', '$color', '$price', '$picture_name');";
	$result = mysql_query($sql, $conn);

	if ($result == 1){
		echo "<br/> Inserted";
	}
	else {
		echo "<br/> There is already a car with this license plate";
	}
		
	
 ?>
 
  
</div>
</div>
	
<br/>
</body></html>