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
<a href='./../search.php'>Search</a>  -    
<a href="login.php">Login</a><br><br>


<?php
// show potential errors / feedback (from login object)
if (isset($login)) {
    if ($login->errors) {
        foreach ($login->errors as $error) {
            echo $error;
        }
    }
    if ($login->messages) {
        foreach ($login->messages as $message) {
            echo $message;
        }
    }
}
?>

<!-- login form box -->
<form method="post" action="login.php" name="loginform">

    <label for="login_input_username">Username</label>
    <input id="login_input_username" class="login_input" type="text" name="user_name" required />

    <label for="login_input_password">Password</label>
    <input id="login_input_password" class="login_input" type="password" name="user_password" autocomplete="off" required />

    <input type="submit"  name="login" value="Log in" />

</form>



</div>
</div>
	
<br/>
</body></html>