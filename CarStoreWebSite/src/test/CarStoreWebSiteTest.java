package test;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

public class CarStoreWebSiteTest {

	static WebDriver driver = new HtmlUnitDriver(true);

	@Before
	public void setUp() throws Exception {
		driver.get("http://localhost/project");
	}
	
	//User Story A: Insert on database:
	//	As a manager
	//	I want to be able to insert new cars to the database
	//	So I can let new cars be available for the client

	//	Scenario 1A – Insert a new car correctly:
	//	Given a unique license plate 
	//	And all other car related information
	//	When the manager tries to insert the car information to the database
	//	Then the car is inserted in the database and it’s available for both manager and client to search
	@Test
	public void testInsertNewCar() {
		
		String username = "manager";
		String password = "7777777";
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		
		// Look for the submit button (in the login div) and click
		// to attempt to login 		
		WebElement loginButton = driver.findElement(By.name("login"));
		loginButton.submit();
		
		driver.findElement(By.linkText("Insert")).click();
	
		
		String license_plate = "ABC0000";
		String manufacturer = "Toyota";
		String model = "Camry";
		String year = "2012";
		String color = "red";
		String price = "19000.00";
		
		driver.findElement(By.name("license_plate")).sendKeys(license_plate);
		driver.findElement(By.name("manufacturer")).sendKeys(manufacturer);
		driver.findElement(By.name("model")).sendKeys(model);
		driver.findElement(By.name("year")).sendKeys(year);
		driver.findElement(By.name("color")).sendKeys(color);
		driver.findElement(By.name("price")).sendKeys(price);
		
		driver.findElement(By.name("insert_button")).click();
		
		String correct_value = "Inserted";
		String newPage = driver.getPageSource();
		
		assertTrue(newPage.contains(correct_value));
		
		driver.findElement(By.linkText("Delete")).click();
		
		driver.findElement(By.name("license_plate")).sendKeys(license_plate);
		
		driver.findElement(By.name("delete_button")).click();
		
		driver.findElement(By.linkText("Logout")).click();
	}
	
	//Scenario 2A – Insert a car with existing license plate:
	//	Given a license plate
	//	And all other car related information
	//	When the manager tries to insert the car information
	//	Then a message appears saying that this license plate is already in the database
	@Test
	public void testInsertCarThatExists() {
		
		String username = "manager";
		String password = "7777777";
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		
		// Look for the submit button (in the login div) and click
		// to attempt to login 		
		WebElement loginButton = driver.findElement(By.name("login"));
		loginButton.submit();
		
		driver.findElement(By.linkText("Insert")).click();
	
		
		String license_plate = "ABC0001";
		String manufacturer = "Toyota";
		String model = "Camry";
		String year = "2012";
		String color = "red";
		String price = "19000.00";
		
		driver.findElement(By.name("license_plate")).sendKeys(license_plate);
		driver.findElement(By.name("manufacturer")).sendKeys(manufacturer);
		driver.findElement(By.name("model")).sendKeys(model);
		driver.findElement(By.name("year")).sendKeys(year);
		driver.findElement(By.name("color")).sendKeys(color);
		driver.findElement(By.name("price")).sendKeys(price);
		
		driver.findElement(By.name("insert_button")).click();
		
		String correct_value = "There is already a car with this license plate";
		String newPage = driver.getPageSource();
		
		assertTrue(newPage.contains(correct_value));
		
		driver.findElement(By.linkText("Logout")).click();
	}
	
	//User Story B: Delete on database:
	//	As a manager
	//	I want to be able to delete cars from the database
	//	So I can let cars be unavailable for the client

	//	Scenario 1B – Delete a car correctly:
	//	Given a unique license plate 
	//	When the manager tries to delete the car information from the database
	//	Then the car is deleted from the database and it’s unavailable for search
	@Test
	public void testDeleteCarThatExists() {
		
		String username = "manager";
		String password = "7777777";
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		
		// Look for the submit button (in the login div) and click
		// to attempt to login 		
		WebElement loginButton = driver.findElement(By.name("login"));
		loginButton.submit();
		
		driver.findElement(By.linkText("Delete")).click();
	
		String license_plate = "ABC9999";
		
		driver.findElement(By.name("license_plate")).sendKeys(license_plate);
		
		driver.findElement(By.name("delete_button")).click();
		
		String correct_value = "There isn't a car with this license plate";
		String newPage = driver.getPageSource();
		
		assertTrue(newPage.contains(correct_value));
		
		driver.findElement(By.linkText("Logout")).click();
	}
	
	//Scenario 2B – Delete a car that is not in the database:
	//	Given a unique license plate 
	//	When the manager tries to delete the car information from the database
	//	Then a message appears saying that there is not any car with that license plate
	@Test
	public void testDeleteCarThatDontExists() {
		
		String username = "manager";
		String password = "7777777";
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		
		// Look for the submit button (in the login div) and click
		// to attempt to login 		
		WebElement loginButton = driver.findElement(By.name("login"));
		loginButton.submit();
		
		String license_plate = "ABC9999";
		
		driver.findElement(By.linkText("Insert")).click();
		
		driver.findElement(By.name("license_plate")).sendKeys(license_plate);
		
		driver.findElement(By.name("insert_button")).click();
		
		
		driver.findElement(By.linkText("Delete")).click();
		
		driver.findElement(By.name("license_plate")).sendKeys(license_plate);
		
		driver.findElement(By.name("delete_button")).click();
		
		String correct_value = "Deleted";
		String newPage = driver.getPageSource();
		
		assertTrue(newPage.contains(correct_value));
		
		driver.findElement(By.linkText("Logout")).click();
	}
	
	//User Story C: Search on database:
	//	As a client
	//	I want to be able to filter for cars through a defined parameter such as color
	//	So I can choose the right car for me

	//	Scenario 1C – Search for a car that is not in the database:
	//	Given a not registered license plate
	//	When the client tries to search
	//	Then a message appears saying that no results were found
	@Test
	public void testSearchCarThatNotExists(){
		
		String license_plate = "ABC9779";
		
		driver.findElement(By.linkText("Search")).click();
		driver.findElement(By.name("license_plate")).sendKeys(license_plate);
		driver.findElement(By.name("search_button")).click();
		
		String correct_value = "There is no car with this criteria";
		String newPage = driver.getPageSource();
		
		assertTrue(newPage.contains(correct_value));
	}
	
	//Scenario 2C – Search for a car without filtering:
	//	Given an empty list of filters
	//	When the client tries to search
	//	Then a list containing all the cars from database appears
	@Test
	public void testSearchWithoutFilter(){
		driver.findElement(By.linkText("Search")).click();
		driver.findElement(By.name("search_button")).click();

		String newPage = driver.getPageSource();
		String correct_value;
		String complement;
		
		for (int i = 1; i < 11; i++) {
			complement = i >= 10 ? "" + i:"0" + i ;
			correct_value = "ABC00" + complement;
			assertTrue(newPage.contains(correct_value));
		}
		
		
	}
	
	//Scenario 3C – Search for a car through a single field filtering: 
	//	Given a year
	//	When the client tries to search
	//	Then a list of cars corresponding the filtering appears
	@Test 
	public void testSearchWithSingleFilter(){
		
		String year = "2013";
		
		driver.findElement(By.linkText("Search")).click();
		driver.findElement(By.name("year")).sendKeys(year);
		driver.findElement(By.name("search_button")).click();
		
		String newPage = driver.getPageSource();
		
		String correct_value = "ABC0009";
		assertTrue(newPage.contains(correct_value));
	}
	
	//Scenario 4C – Search for a car through multiple field filtering (AND): 
	//	Given a car model
	//	And a color
	//	When the client tries to search
	//	Then a list of cars corresponding the filtering appears
	@Test 
	public void testSearchWithMultipleFilterAnd(){
		
		String model = "Veloster";
		String color = "red";
		
		driver.findElement(By.linkText("Search")).click();
		driver.findElement(By.name("model")).sendKeys(model);
		driver.findElement(By.name("color")).sendKeys(color);
		Select dropdown = new Select(driver.findElement(By.name("color_logical")));
		dropdown.selectByValue(" AND ");
		driver.findElement(By.name("search_button")).click();
		
		String newPage = driver.getPageSource();
		
		String correct_value = "ABC0006";
		assertTrue(newPage.contains(correct_value));
	}
	
	//Scenario 5C – Search for a car through multiple field filtering (OR):
	//	Given a color
	//	Or a price 
	//	When the client search
	//	Then list of cars corresponding the filtering appears
	@Test 
	public void testSearchWithMultipleFilterOR(){
		
		String color = "silver";
		String price = "18000.00";
		
		driver.findElement(By.linkText("Search")).click();
		driver.findElement(By.name("color")).sendKeys(color);
		driver.findElement(By.name("price")).sendKeys(price);
		Select dropdown = new Select(driver.findElement(By.name("price_logical")));
		dropdown.selectByValue(" OR ");
		driver.findElement(By.name("search_button")).click();
		
		String newPage = driver.getPageSource();
		System.out.println(newPage);
		
		String correct_value;
		correct_value = "ABC0006";
		assertTrue(newPage.contains(correct_value));
		correct_value = "ABC0007";
		assertTrue(newPage.contains(correct_value));
	}
	
	
	//User Story D: Select a car:
	//	As a client
	//	I want to select the car that I chose to see more information such as a picture of the car 
	//	So I can decide better if I want to buy it

	//	Scenario 1D – Choosing a car:
	//	Given a list of cars
	//	When the client chooses a car
	//	Then all information about the selected car and picture(s) appears
	@Test
	public void testChooseCar() {
		driver.findElement(By.linkText("Search")).click();
		driver.findElement(By.name("search_button")).click();
		driver.findElement(By.name("view_car_button")).click(); 

		String correct_value = "The car's photo is merely illustrative, it doesn't reflect the product.";
		String newPage = driver.getPageSource();
		
		assertTrue(newPage.contains(correct_value));
	
	}
}
	
	