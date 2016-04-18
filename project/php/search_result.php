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
	
	$manufacturer_logical = $_REQUEST['manufacturer_logical'];
	$model_logical = $_REQUEST['model_logical'];
	$year_logical = $_REQUEST['year_logical'];
	$color_logical = $_REQUEST['color_logical'];
	$price_logical = $_REQUEST['price_logical'];
	
	$year_comparative = $_REQUEST['year_comparative'];
	$price_comparative = $_REQUEST['price_comparative'];
	
	$conn = mysql_connect("localhost", "client", "client");
	mysql_select_db("project", $conn);
	
	$sql = "SELECT license_plate, manufacturer, model, year, color, price FROM car ";
	$sql_parameters = "";
	
	$search_field_count = 0;
	
	if ($license_plate != "")
	{	
		if ($search_field_count == 0)
		{
			$sql = $sql . "WHERE ";
		}

		$search_field_count = $search_field_count + 1;
	
		$sql_parameters = $sql_parameters . "license_plate = '$license_plate'";
		
	}
	
	if ($manufacturer != "")
	{
		if ($search_field_count == 0)
		{
			$sql = $sql . "WHERE ";
		}
		else
		{
			$sql_parameters = $sql_parameters . $manufacturer_logical;
		}
		
		$search_field_count = $search_field_count + 1;
		
		$sql_parameters = $sql_parameters . "manufacturer = '$manufacturer'";
	}
	
	if ($model != "")
	{
		if ($search_field_count == 0)
		{
			$sql = $sql . "WHERE ";
		}
		else
		{
			$sql_parameters = $sql_parameters . $model_logical;
		}
		
		$search_field_count = $search_field_count + 1;
		
		$sql_parameters = $sql_parameters . "model = '$model'";
	}
	
	if ($year != "")
	{
		if ($search_field_count == 0)
		{
			$sql = $sql . "WHERE ";
		}
		else
		{
			$sql_parameters = $sql_parameters . $year_logical;
		}
		
		$search_field_count = $search_field_count + 1;
		
		$sql_parameters = $sql_parameters . "year $year_comparative '$year'";
	}
	
	if ($color != "")
	{
		if ($search_field_count == 0)
		{
			$sql = $sql . "WHERE ";
		}
		else
		{
			$sql_parameters = $sql_parameters . $color_logical;
		}
		
		$search_field_count = $search_field_count + 1;
		
		$sql_parameters = $sql_parameters . "color = '$color'";
	}
	
	if ($price != "")
	{
		if ($search_field_count == 0)
		{
			$sql = $sql . "WHERE ";
		}
		else
		{
			$sql_parameters = $sql_parameters . $price_logical;
		}
		
		$search_field_count = $search_field_count + 1;
		
		$sql_parameters = $sql_parameters . "price $price_comparative '$price'";
	}
	
	echo $sql_parameters;
	
	$sql = $sql . $sql_parameters . ";";
	
	
	
	$result = mysql_query($sql, $conn);
	
	
	print "<table border = 1>\n";

	//get field names
	print "<tr>\n";
	while ($field = mysql_fetch_field($result)){
	print " <th>$field->name</th>\n";
	} // end while
	print "</tr>\n\n";

	//get row data as an associative array
	while ($row = mysql_fetch_assoc($result)){
		print "<tr>\n";
		//look at each field
		foreach ($row as $col=>$val){
			print " <td>$val</td>\n";
		} // end foreach
		print 
		"<td>
			<form action='view_car.php' method='post' name='view_car_form' method='post'>
				<input type=hidden name='license_plate' value=".$row['license_plate'].">
				<input type=submit name='view_car' value='View car' onClick='view_car(view_car_form.license_plate.value'>
			</form>
		</td>\n";
		print "</tr>\n\n";
	}// end while

	print "</table>\n";
 ?>
 
 
</div>
</div>
	
<br/>
</body></html>