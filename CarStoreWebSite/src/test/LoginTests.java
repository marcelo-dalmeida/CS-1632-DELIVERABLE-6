import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Philip Ni on 2016.
 * 
 * REQUIRES MOZILLA FIREFOX WEB BROWSER
 * 
 * As a car sales manager
 * I want to be able to securely login to the system
 * So that only account holders can add new user accounts
 * 
 * Login handled by framework.
 */

public class LoginTests {
	static WebDriver wdriver;
	
	// Running the webapp on local machine (localhost);
	// Must have XAMPP or LAMPP stack, JUnit, and Selenium installed
	@Before
	public void setup() {
		wdriver = new FirefoxDriver();
		wdriver.get("http://localhost/CS-1632-DELIVERABLE-6/project/php/login.php");	// navigate to login page
		wdriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}
	
	@After
	public void tearDown() {
		wdriver.close();
	}

	// Helper method to wait for pages/actions to finish loading
    private static void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        }
        catch(InterruptedException ie) {
            System.out.println("ERROR WAITING: Failed to generate a Thread to wait.");
            ie.printStackTrace();
        }
    }
    
    // Helper method to follow login procedures so that I don't have to keep typing it
    private static void loginForMe(String username, String password) {
    	wdriver.findElement(By.id("login_input_username")).sendKeys(username);
		wdriver.findElement(By.id("login_input_password")).sendKeys(password);
		wdriver.findElement(By.name("login")).click();
    }
	
	// Given that I am I on the login page
	// When I enter a username that does not exist
	// Then I should not be able to proceed from the login page
	// And I should be prompted with a message stating what was wrong
	@Test
	public void invalidUser() {
		try {
			loginForMe("asdfasdf", "qwertyuiop");
			
			wait(3);
			
			WebElement newpage = wdriver.findElement(By.className("container"));
			String str = newpage.getText();
			
			// prompt saying that user does not exist
			assertTrue(str.contains("This user does not exist."));
			
			// user still sees username and password text entry fields
			assertTrue(wdriver.getPageSource().contains("login_input_username") && wdriver.getPageSource().contains("login_input_password"));
		}
		catch(NoSuchElementException nsee) {
			fail("Cannot find element(s)");
		}
	}
	
	// Given that I am on the login page
	// When I enter a correct username
	// And an incorrect password
	// Then I should not be able to proceed from the login page
	// And I should be prompted with a message stating what is wrong
	@Test
	public void invalidPassword() {
		try {
			loginForMe("manager", "qwertyuiop");

			wait(3);

			WebElement newpage = wdriver.findElement(By.className("container"));
			String str = newpage.getText();

			// prompt saying that wrong password has been used for a valid account
			assertTrue(str.contains("Wrong password. Try again."));

			// user still sees username and password text entry fields
			assertTrue(wdriver.getPageSource().contains("login_input_username") && wdriver.getPageSource().contains("login_input_password"));
		}
		catch(NoSuchElementException nsee) {
			fail("Cannot find element(s)");
		}
	}
	
	// Given that I am on the login page
	// When I enter valid login credentials
	// Then I should be able to access more features
	// And I should see a message indicating a successful login
	@Test
	public void correctLogin() {
		try {
			loginForMe("manager", "7777777");
			
			wait(3);
			
			WebElement newpage = wdriver.findElement(By.className("container"));
			String str = newpage.getText();
			
			// Insert and Delete are now visible options
			assertTrue(str.contains("Insert") && str.contains("Delete"));
			
			// login success message
			assertTrue(str.contains("You are logged in."));
		}
		catch(NoSuchElementException nsee) {
			fail("Cannot find element(s)");
		}
	}
	
	// Given that I am successfully logged in
	// When I select the "Login" option
	// Then I can create a new account
	//
	// IMPORTANT: please remove all test generated user accounts from database manually before final launch (w/ phpMyAdmin)
	@Test
	public void createAccount() {
		// create a random username to decrease chances of generating a repeated username
		// if you want to run tests multiple times
		Random rndm = new Random();
		int rndm_usrnm = rndm.nextInt(Integer.MAX_VALUE);
		
		try {
			loginForMe("manager", "7777777");
			
			wait(3);
			
			// Select "Register new account" from the Login page
			WebElement new_element = wdriver.findElement(By.linkText("Register new account"));
			new_element.click();
			
			wait(3);
			
			// Enter new user information
			WebElement account_page = wdriver.findElement(By.name("registerform"));
			
			// new username
			account_page.findElement(By.id("login_input_username")).sendKeys(Integer.toString(rndm_usrnm));
			System.out.println(rndm_usrnm);	// print the randomly generated username so you know to remove it from the database for final launch
			
			// new email
			account_page.findElement(By.id("login_input_email")).sendKeys(Integer.toString(rndm_usrnm) + "@asdf.com");	//also random email
			
			// new password (2 times)
			account_page.findElement(By.id("login_input_password_new")).sendKeys("qwertyuiop");
			account_page.findElement(By.id("login_input_password_repeat")).sendKeys("qwertyuiop");
			
			// accept new credentials
			account_page.findElement(By.name("register")).click();
			
			wait(3);
			
			WebElement donePage = wdriver.findElement(By.className("content1"));
			String str = donePage.getText();
			
			assertTrue(str.contains("Your account has been created successfully."));
		}
		catch(NoSuchElementException nsee) {
			fail("Cannot find element(s)");
		}
	}
	
	// Given that I have created a new account
	// When I log out
	// Then I can login and use the newly generated account
	//
	// NOTE: does some things createAccount() does again because IDE may run tests out of order
	// IMPORTANT: please remove all test generated user accounts from database manually before final launch (w/ phpMyAdmin)
	@Test
	public void loginNewAccount() {
		// create a random username to decrease chances of generating a repeated username
		// if you want to run tests multiple times
		Random rndm = new Random();
		int rndm_usrnm = rndm.nextInt(Integer.MAX_VALUE);
		
		try {
			loginForMe("manager", "7777777");
			
			wait(3);
			
			// Select "Register new account" from the Login page
			WebElement new_element = wdriver.findElement(By.linkText("Register new account"));
			new_element.click();
			
			wait(3);
			
			// Enter new user information
			WebElement account_page = wdriver.findElement(By.name("registerform"));
			
			// new username
			account_page.findElement(By.id("login_input_username")).sendKeys(Integer.toString(rndm_usrnm));
			System.out.println(rndm_usrnm);	// print the randomly generated username so you know to remove it from the database for final launch
			
			// new email
			account_page.findElement(By.id("login_input_email")).sendKeys(Integer.toString(rndm_usrnm) + "@asdf.com");
			
			// new password (2 times)
			account_page.findElement(By.id("login_input_password_new")).sendKeys("qwertyuiop");
			account_page.findElement(By.id("login_input_password_repeat")).sendKeys("qwertyuiop");
			
			// accept new credentials
			account_page.findElement(By.name("register")).click();
			
			wait(3);
			
			// logout of old account
			WebElement donePage = wdriver.findElement(By.className("content1"));
			donePage.findElement(By.linkText("Logout")).click();
			
			wait(3);
			
			// login with newly made credentials
			loginForMe(Integer.toString(rndm_usrnm), "qwertyuiop");
			
			wait(3);
			
			WebElement last_page = wdriver.findElement(By.className("container"));
			String str = last_page.getText();
			
			// Insert and Delete are now visible options
			assertTrue(str.contains("Insert") && str.contains("Delete"));
			
			// login success message
			assertTrue(str.contains("Hey, " + Integer.toString(rndm_usrnm) + ". You are logged in."));
		}
		catch(NoSuchElementException nsee) {
			fail("Cannot find element(s)");
		}
	}
	
	// Given that I am successfully logged in
	// When I create a new manager account with the same username/email as an existing account
	// Then the original account will not be overwritten
	// And the new account will not be created
	//
	// NOTE: does some things createAccount() does again because IDE may run tests out of order
	// IMPORTANT: please remove all test generated user accounts from database manually before final launch (w/ phpMyAdmin)
	@Test
	public void createExistingUser() {
		try {
			loginForMe("manager", "7777777");

			wait(3);

			// Select "Register new account" from the Login page
			WebElement new_element = wdriver.findElement(By.linkText("Register new account"));
			new_element.click();

			wait(3);

			// Enter new user information
			WebElement account_page = wdriver.findElement(By.name("registerform"));

			// new username
			account_page.findElement(By.id("login_input_username")).sendKeys("manager");

			// new email
			account_page.findElement(By.id("login_input_email")).sendKeys("asdf@asdf.com");	//also random email

			// new password (2 times)
			account_page.findElement(By.id("login_input_password_new")).sendKeys("qwertyuiop");
			account_page.findElement(By.id("login_input_password_repeat")).sendKeys("qwertyuiop");

			// accept new credentials
			account_page.findElement(By.name("register")).click();

			wait(3);

			WebElement donePage = wdriver.findElement(By.className("content1"));
			String str = donePage.getText();

			assertTrue(str.contains("Sorry, that username / email address is already taken."));
		}
		catch(NoSuchElementException nsee) {
			fail("Cannot find element(s)");
		}
	}
	
	// Given that I am successfully logged in
	// When I try to register a new account
	// And use a username that does not meet the pattern specified
	// Then I cannot proceed to a new page
	@Test
	public void createBadUsername() {
		try {
			loginForMe("manager", "7777777");

			wait(3);

			// Select "Register new account" from the Login page
			WebElement new_element = wdriver.findElement(By.linkText("Register new account"));
			new_element.click();

			wait(3);

			// Enter new user information
			WebElement account_page = wdriver.findElement(By.name("registerform"));

			// new username; too short
			account_page.findElement(By.id("login_input_username")).sendKeys("a");

			// new email
			account_page.findElement(By.id("login_input_email")).sendKeys("asdf@asdf.com");	//also random email

			// new password (2 times)
			account_page.findElement(By.id("login_input_password_new")).sendKeys("qwertyuiop");
			account_page.findElement(By.id("login_input_password_repeat")).sendKeys("qwertyuiop");

			// accept new credentials
			account_page.findElement(By.name("register")).click();

			wait(3);

			WebElement donePage = wdriver.findElement(By.id("login_input_username"));
			String str = donePage.getAttribute("value");

			// can't proceed to a new page because the textbox input conditions have not been met;
			// items in textbox remain
			assertTrue(str.equals("a"));
		}
		catch(NoSuchElementException nsee) {
			fail("Cannot find element(s)");
		}
	}
	
	// Given that I am successfully logged in
	// When I try to register a new account
	// And use an email address that does not meet the pattern specified
	// Then I cannot proceed to a new page
	@Test
	public void createBadEmail() {
		try {
			loginForMe("manager", "7777777");

			wait(3);

			// Select "Register new account" from the Login page
			WebElement new_element = wdriver.findElement(By.linkText("Register new account"));
			new_element.click();

			wait(3);

			// Enter new user information
			WebElement account_page = wdriver.findElement(By.name("registerform"));

			// new username
			account_page.findElement(By.id("login_input_username")).sendKeys("thisisauniqueusername");

			// new email; not an email address
			account_page.findElement(By.id("login_input_email")).sendKeys("asdfasdfcom");	//also random email

			// new password (2 times)
			account_page.findElement(By.id("login_input_password_new")).sendKeys("qwertyuiop");
			account_page.findElement(By.id("login_input_password_repeat")).sendKeys("qwertyuiop");

			// accept new credentials
			account_page.findElement(By.name("register")).click();

			wait(3);

			WebElement donePage = wdriver.findElement(By.id("login_input_username"));
			String str = donePage.getAttribute("value");

			// can't proceed to a new page because the textbox input conditions have not been met;
			// items in textbox remain
			assertTrue(str.equals("thisisauniqueusername"));
		}
		catch(NoSuchElementException nsee) {
			fail("Cannot find element(s)");
		}
	}
	
	// Given that I am successfully logged in
	// When I try to register a new account
	// And the password does not match the repeated password
	// Then I will be informed that the registration has failed 
	@Test
	public void createPswrdNoMatch() {
		// create a random username to decrease chances of generating a repeated username
		// if you want to run tests multiple times
		Random rndm = new Random();
		int rndm_usrnm = rndm.nextInt(Integer.MAX_VALUE);
		
		try {
			loginForMe("manager", "7777777");

			wait(3);

			// Select "Register new account" from the Login page
			WebElement new_element = wdriver.findElement(By.linkText("Register new account"));
			new_element.click();

			wait(3);

			// Enter new user information
			WebElement account_page = wdriver.findElement(By.name("registerform"));

			// new username
			account_page.findElement(By.id("login_input_username")).sendKeys("thisisauniqueusername");

			// new email; not an email address
			account_page.findElement(By.id("login_input_email")).sendKeys(Integer.toString(rndm_usrnm) + "@asdf.com");	//also random email

			// new password (2 times); different
			account_page.findElement(By.id("login_input_password_new")).sendKeys("qwertyuiop");
			account_page.findElement(By.id("login_input_password_repeat")).sendKeys("qwertyuiopagain");

			// accept new credentials
			account_page.findElement(By.name("register")).click();

			wait(3);

			WebElement donePage = wdriver.findElement(By.className("container"));
			String str = donePage.getText();

			// can't proceed to a new page because the textbox input conditions have not been met;
			// items in textbox remain
			assertTrue(str.contains("Password and password repeat are not the same"));
		}
		catch(NoSuchElementException nsee) {
			fail("Cannot find element(s)");
		}
	}
	
	// Given that I am successfully logged in
	// When I try to register a new account
	// And the password is too short
	// Then I cannot proceed to a new page
	@Test
	public void createPswrdTooShort() {
		// create a random username to decrease chances of generating a repeated username
		// if you want to run tests multiple times
		Random rndm = new Random();
		int rndm_usrnm = rndm.nextInt(Integer.MAX_VALUE);
		
		try {
			loginForMe("manager", "7777777");

			wait(3);

			// Select "Register new account" from the Login page
			WebElement new_element = wdriver.findElement(By.linkText("Register new account"));
			new_element.click();

			wait(3);

			// Enter new user information
			WebElement account_page = wdriver.findElement(By.name("registerform"));

			// new username
			account_page.findElement(By.id("login_input_username")).sendKeys("thisisauniqueusername");

			// new email; not an email address
			account_page.findElement(By.id("login_input_email")).sendKeys(Integer.toString(rndm_usrnm) + "@asdf.com");	//also random email

			// new password (2 times); short password
			account_page.findElement(By.id("login_input_password_new")).sendKeys("as");
			account_page.findElement(By.id("login_input_password_repeat")).sendKeys("as");

			// accept new credentials
			account_page.findElement(By.name("register")).click();

			wait(3);

			WebElement donePage = wdriver.findElement(By.id("login_input_username"));
			String str = donePage.getAttribute("value");

			// can't proceed to a new page because the textbox input conditions have not been met;
			// items in textbox remain
			assertTrue(str.equals("thisisauniqueusername"));
		}
		catch(NoSuchElementException nsee) {
			fail("Cannot find element(s)");
		}
	}
}

























