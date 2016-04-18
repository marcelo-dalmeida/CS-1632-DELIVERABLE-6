<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>
<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
<link rel='stylesheet' type='text/css' href='./../css/table_layout.css'>

<title>table layout</title>

<script language="JavaScript">
	function calculate_payment(price_with_taxes, downpayment, year, interest){
	
		var price_with_taxes = parseFloat(price_with_taxes)
		var downpayment = parseFloat(downpayment)
		var year = parseInt(year)
		var interest = parseInt(interest)
	
	
		var total_installment
		var n_of_months
		var total_price_per_month
		var total_price
		
		if (year > 0)
		{
			total_installment = (price_with_taxes - downpayment)*(1+(interest/100))
			n_of_months = year * 12
		}
		else
		{
			total_installment = (price_with_taxes - downpayment)
			n_of_months = 1
		}
		
		total_price = downpayment + total_installment
		total_price_per_month = total_installment / n_of_months
		
		document.getElementById('total_downpayment').value = downpayment.toFixed(2)
		document.getElementById('total_price_per_month').value = total_price_per_month.toFixed(2)
		document.getElementById('n_of_months').value = n_of_months
		document.getElementById('total_price').value = total_price.toFixed(2)
	}
	

</script>		

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

	$conn = mysql_connect("localhost", "client", "client");
	mysql_select_db("project", $conn);
	
	$sql = "SELECT * FROM car WHERE license_plate = '$license_plate';";
	
	$result = mysql_query($sql, $conn);
	
	
	$row = mysql_fetch_assoc($result);
	//get row data as an associative array
	print $row['license_plate']."<br>";
	print $row['manufacturer']."<br>";
	print $row['model']."<br>";
	print $row['year']."<br>";
	print $row['color']."<br>";
	print $row['price']."<br>";
	print "<img src=./../pictures/".$row['picture_name']." width=480 height=320><br>";
	
 ?>

The car's photo is merely illustrative, it doesn't reflect the product. <br><br>

 <form name='payment_form' method='post'>
	
	<!--price of the car (price + , 5-year payment, downpayment, taxes - 7%) (interest/year)-->
	Car price (no taxes):<br>
	<input type=number name="price" value="<?php echo floatval($row['price']); ?>" readonly><br><br>
	
	State taxes:<br>
	<input type=number name="tax" value="7" readonly>%<br><br>
	
	Car price (taxes included)<br>
	<input type=number name="price_with_taxes" value="<?php echo floatval($row['price'])*1.07; ?>" readonly><br><br>
	
	Downpayment:<br>
	<input type=number name="downpayment" step=0.01 value=0 min=0 max="<?php echo floatval($row['price'])*1.07; ?>" ><br><br>
	
	#-year payment:<br>
	<input type=number name="year" value=0  min=0 max=5><br><br>
	
	Interest/year:<br>
	<input type=number name="interest" value="9" readonly>%<br><br><br>
	
	<input type=button name='calculate_payment_button' value='Calculate payment' onClick='calculate_payment(payment_form.price_with_taxes.value, payment_form.downpayment.value,
	payment_form.year.value, payment_form.interest.value)'><br><br>
	
	
	Downpayment:<br>
	<input type=number id="total_downpayment" readonly><br><br>
	
	Cost/Month:<br>
	<input type=number id="total_price_per_month" readonly><br><br>
	
	Number of months:<br>
	<input type=number id="n_of_months" readonly><br><br>
	
	Total price:<br>
	<input type=number id="total_price" readonly><br><br>
	
	
</form>
 
  
</div>
</div>
	
<br/>
</body></html>