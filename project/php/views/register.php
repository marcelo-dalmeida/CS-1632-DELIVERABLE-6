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
		echo '<a href="insert.php">Insert</a>  -  ';
		echo '<a href="delete.php">Delete</a>  -  ';
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
// show potential errors / feedback (from registration object)
if (isset($registration)) {
    if ($registration->errors) {
        foreach ($registration->errors as $error) {
            echo $error;
        }
    }
    if ($registration->messages) {
        foreach ($registration->messages as $message) {
            echo $message;
        }
    }
}
?>

<!-- register form -->
<form method="post" action="register.php" name="registerform">

    <!-- the user name input field uses a HTML5 pattern check -->
    <label for="login_input_username">Username (only letters and numbers, 2 to 64 characters)</label><br>
    <input id="login_input_username" class="login_input" type="text" pattern="[a-zA-Z0-9]{2,64}" name="user_name" required /><br>

    <!-- the email input field uses a HTML5 email type check -->
    <label for="login_input_email">User's email</label><br>
    <input id="login_input_email" class="login_input" type="email" name="user_email" required /><br>

    <label for="login_input_password_new">Password (min. 6 characters)</label><br>
    <input id="login_input_password_new" class="login_input" type="password" name="user_password_new" pattern=".{6,}" required autocomplete="off" /><br>

    <label for="login_input_password_repeat">Repeat password</label><br>
    <input id="login_input_password_repeat" class="login_input" type="password" name="user_password_repeat" pattern=".{6,}" required autocomplete="off" /><br><br>
    <input type="submit"  name="register" value="Register" />

</form>


</div>
</div>
	
<br/>
</body></html>