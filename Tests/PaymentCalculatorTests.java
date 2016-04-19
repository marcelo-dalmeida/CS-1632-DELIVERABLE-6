import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * REQUIRES MOZILLA FIREFOX WEB BROWSER
 * 
 * As a general user (either a manager or non-account user)
 * I would like to be able to calculate the estimated price of a car
 * So that I know how much it would cost me to buy that car with taxes, interest, and other fees included
 */

public class PaymentCalculatorTests {	
	static WebDriver wdriver;
	
	// Running the webapp on local machine (localhost);
	// Must have XAMPP or LAMPP stack, JUnit, and Selenium installed
	@Before
	public void setup() {
		// get to the first car in the list
		wdriver = new FirefoxDriver();
		wdriver.get("http://localhost/CS-1632-DELIVERABLE-6/project/search.php");
		wdriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		// select search button
		wdriver.findElement(By.name("search_button")).click();
		wait(2);
		
		// get first car in inventory
		WebElement car_view = wdriver.findElement(By.cssSelector(".content1 > table:nth-child(7) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(7) > form:nth-child(1) > input:nth-child(2)"));
		car_view.click();
		wait(2);
	}
	
	@After
	public void tearDown() throws Exception {
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
    
    // Helper method to calculate total installment
    private static double priceWithInterest(double price_tax, double dwnpymnt, double interest, int year) {
    	double pwi = price_tax - dwnpymnt;
    	
    	if(year > 0) {
    		pwi = pwi * (1 + (interest / 100));
    	}
    	
    	return pwi;
    }
    
    // Helper method to calculate number of months
    private static int numMonths(int year) {
    	int mnths = 1;
    	
    	if(year > 0) {
    		mnths = year * 12;
    	}
    	
    	return mnths;
    }
    
    // Helper method to calculate total price
    private static double totalPrice(double price_interest, double dwnpymnt) {
    	return price_interest + dwnpymnt;
    }
    
    // Helper method to calculate total price per month
    private static double totalPricePerMonth(double price_interest, int months) {
    	return price_interest / months;
    }
	
	// Given that I have selected a car
	// And am currently viewing its information page
    // When I don't change any values in the price calculator
	// Then the correct total price will be calculated for the car for 1 month (0 yrs) with no down-payment
	@Test
	public void defaultValues() {
		WebElement new_page = wdriver.findElement(By.name("payment_form"));
		
		double dwnpymnt = Double.valueOf(new_page.findElement(By.name("downpayment")).getAttribute("value"));
		double interest = Double.valueOf(new_page.findElement(By.name("interest")).getAttribute("value"));
		int year = Integer.valueOf(new_page.findElement(By.name("year")).getAttribute("value"));
		double price_tax = Double.valueOf(new_page.findElement(By.name("price_with_taxes")).getAttribute("value"));
		
		double price_interest = priceWithInterest(price_tax, dwnpymnt, interest, year);
		int nMnths = numMonths(year);
		double ttl = totalPrice(price_interest, dwnpymnt);
		double ttl_per_mnth = totalPricePerMonth(price_interest, nMnths);
		
		// click calculate button
		new_page.findElement(By.name("calculate_payment_button")).click();
		wait(2);
		
		WebElement solutions = wdriver.findElement(By.name("payment_form"));
		
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		
		assertEquals(Double.valueOf(twoDForm.format(dwnpymnt)), Double.valueOf(solutions.findElement(By.id("total_downpayment")).getAttribute("value")));
		assertEquals(Double.valueOf(twoDForm.format(ttl_per_mnth)), Double.valueOf(solutions.findElement(By.id("total_price_per_month")).getAttribute("value")));
		assertEquals(Integer.valueOf(nMnths), Integer.valueOf(solutions.findElement(By.id("n_of_months")).getAttribute("value")));
		assertEquals(Double.valueOf(twoDForm.format(ttl)), Double.valueOf(solutions.findElement(By.id("total_price")).getAttribute("value")));
	}
	
	// Given that I have selected a car
	// And am currently viewing its information page
    // When I change only the down-payment in the price calculator
	// Then the correct total price will be calculated for the car for 1 month (0 yrs) with the specified down-payment
	public void changeDownPayment() {
		WebElement new_page = wdriver.findElement(By.name("payment_form"));
		
		//modify down-payment
		double dwnpymnt = 1200.00;
		new_page.findElement(By.name("downpayment")).clear();
		new_page.findElement(By.name("downpayment")).sendKeys("1200.00");
		
		double interest = Double.valueOf(new_page.findElement(By.name("interest")).getAttribute("value"));
		int year = Integer.valueOf(new_page.findElement(By.name("year")).getAttribute("value"));
		double price_tax = Double.valueOf(new_page.findElement(By.name("price_with_taxes")).getAttribute("value"));
		
		double price_interest = priceWithInterest(price_tax, dwnpymnt, interest, year);
		int nMnths = numMonths(year);
		double ttl = totalPrice(price_interest, dwnpymnt);
		double ttl_per_mnth = totalPricePerMonth(price_interest, nMnths);
		
		// click calculate button
		new_page.findElement(By.name("calculate_payment_button")).click();
		wait(2);
		
		WebElement solutions = wdriver.findElement(By.name("payment_form"));
		
		DecimalFormat twoDForm = new DecimalFormat("#.##");

		assertEquals(Double.valueOf(twoDForm.format(dwnpymnt)), Double.valueOf(solutions.findElement(By.id("total_downpayment")).getAttribute("value")));
		assertEquals(Double.valueOf(twoDForm.format(ttl_per_mnth)), Double.valueOf(solutions.findElement(By.id("total_price_per_month")).getAttribute("value")));
		assertEquals(Integer.valueOf(nMnths), Integer.valueOf(solutions.findElement(By.id("n_of_months")).getAttribute("value")));
		assertEquals(Double.valueOf(twoDForm.format(ttl)), Double.valueOf(solutions.findElement(By.id("total_price")).getAttribute("value")));
	}
	
	// Given that I have selected a car
	// And am currently viewing its information page
    // When I change only the number of years in the price calculator
	// Then the correct total price will be calculated for the car for n * 12 months (n yrs) with no down-payment
	@Test
	public void changeNumYears() {
		WebElement new_page = wdriver.findElement(By.name("payment_form"));
		
		double dwnpymnt = Double.valueOf(new_page.findElement(By.name("downpayment")).getAttribute("value"));
		double interest = Double.valueOf(new_page.findElement(By.name("interest")).getAttribute("value"));
		
		// modify number of payment years
		int year = 5;
		new_page.findElement(By.name("year")).clear();
		new_page.findElement(By.name("year")).sendKeys("5");
		
		double price_tax = Double.valueOf(new_page.findElement(By.name("price_with_taxes")).getAttribute("value"));
		
		double price_interest = priceWithInterest(price_tax, dwnpymnt, interest, year);
		int nMnths = numMonths(year);
		double ttl = totalPrice(price_interest, dwnpymnt);
		double ttl_per_mnth = totalPricePerMonth(price_interest, nMnths);
		
		// click calculate button
		new_page.findElement(By.name("calculate_payment_button")).click();
		wait(2);
		
		WebElement solutions = wdriver.findElement(By.name("payment_form"));
		
		DecimalFormat twoDForm = new DecimalFormat("#.##");

		assertEquals(Double.valueOf(twoDForm.format(dwnpymnt)), Double.valueOf(solutions.findElement(By.id("total_downpayment")).getAttribute("value")));
		assertEquals(Double.valueOf(twoDForm.format(ttl_per_mnth)), Double.valueOf(solutions.findElement(By.id("total_price_per_month")).getAttribute("value")));
		assertEquals(Integer.valueOf(nMnths), Integer.valueOf(solutions.findElement(By.id("n_of_months")).getAttribute("value")));
		assertEquals(Double.valueOf(twoDForm.format(ttl)), Double.valueOf(solutions.findElement(By.id("total_price")).getAttribute("value")));
	}
	
	// Given that I have selected a car
	// And am currently viewing its information page
    // When I enter non-numeric characters into the down-payment field
	// Then the results fields should be empty/not change (except for "Number of months" which will revert to its default value of 1)
	@Test
	public void invalidDwnPymnt() {
		WebElement new_page = wdriver.findElement(By.name("payment_form"));
		
		//modify down-payment
		new_page.findElement(By.name("downpayment")).clear();
		new_page.findElement(By.name("downpayment")).sendKeys("abcd");
		
		String dwnpymnt_before, ttl_per_mnth_before, ttl_before;
		dwnpymnt_before = wdriver.findElement(By.id("total_downpayment")).getAttribute("value");
		ttl_per_mnth_before = wdriver.findElement(By.id("total_price_per_month")).getAttribute("value");
		ttl_before = wdriver.findElement(By.id("total_price")).getAttribute("value");
		
		// click calculate button
		new_page.findElement(By.name("calculate_payment_button")).click();
		wait(2);
		
		WebElement solutions = wdriver.findElement(By.name("payment_form"));
		
		String dwnpymnt_after, ttl_per_mnth_after, ttl_after;
		dwnpymnt_after = solutions.findElement(By.id("total_downpayment")).getAttribute("value");
		ttl_per_mnth_after = solutions.findElement(By.id("total_price_per_month")).getAttribute("value");
		ttl_after = solutions.findElement(By.id("total_price")).getAttribute("value");

		assertEquals(dwnpymnt_before, dwnpymnt_after);
		assertEquals(ttl_per_mnth_before, ttl_per_mnth_after);
		assertEquals(ttl_before, ttl_after);
		assertEquals("1", solutions.findElement(By.id("n_of_months")).getAttribute("value"));
	}
	
	// Given that I have selected a car
	// And am currently viewing its information page
    // When I enter non-numeric characters into the number of years field
	// Then the results fields should be the same as the default behavior
	@Test
	public void invalidNumYrs() {
		WebElement new_page = wdriver.findElement(By.name("payment_form"));
		
		double dwnpymnt = Double.valueOf(new_page.findElement(By.name("downpayment")).getAttribute("value"));
		
		// get initial value of interest
		double interest = Double.valueOf(new_page.findElement(By.name("interest")).getAttribute("value"));
		
		// modify number of years to non-numeric characters
		new_page.findElement(By.name("year")).clear();
		new_page.findElement(By.name("year")).sendKeys("abcd");
		
		int year = 0;
		double price_tax = Double.valueOf(new_page.findElement(By.name("price_with_taxes")).getAttribute("value"));
		
		double price_interest = priceWithInterest(price_tax, dwnpymnt, interest, year);
		int nMnths = numMonths(year);
		double ttl = totalPrice(price_interest, dwnpymnt);
		double ttl_per_mnth = totalPricePerMonth(price_interest, nMnths);
		
		// click calculate button
		new_page.findElement(By.name("calculate_payment_button")).click();
		wait(2);
		
		WebElement solutions = wdriver.findElement(By.name("payment_form"));
		
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		
		assertEquals(Double.valueOf(twoDForm.format(dwnpymnt)), Double.valueOf(solutions.findElement(By.id("total_downpayment")).getAttribute("value")));
		assertEquals(Double.valueOf(twoDForm.format(ttl_per_mnth)), Double.valueOf(solutions.findElement(By.id("total_price_per_month")).getAttribute("value")));
		assertEquals(Integer.valueOf(nMnths), Integer.valueOf(solutions.findElement(By.id("n_of_months")).getAttribute("value")));
		assertEquals(Double.valueOf(twoDForm.format(ttl)), Double.valueOf(solutions.findElement(By.id("total_price")).getAttribute("value")));
	}
	
	// Given that I have selected a car
	// And am currently viewing its information page
    // When I enter a negative value into the down-payment field
	// Then the results fields should behave as normal using negative values
	@Test
	public void negativeDwnPymnt() {
		WebElement new_page = wdriver.findElement(By.name("payment_form"));
		
		//modify down-payment
		double dwnpymnt = -1200.00;
		new_page.findElement(By.name("downpayment")).clear();
		new_page.findElement(By.name("downpayment")).sendKeys("-1200.00");
		
		double interest = Double.valueOf(new_page.findElement(By.name("interest")).getAttribute("value"));
		int year = Integer.valueOf(new_page.findElement(By.name("year")).getAttribute("value"));
		double price_tax = Double.valueOf(new_page.findElement(By.name("price_with_taxes")).getAttribute("value"));
		
		double price_interest = priceWithInterest(price_tax, dwnpymnt, interest, year);
		int nMnths = numMonths(year);
		double ttl = totalPrice(price_interest, dwnpymnt);
		double ttl_per_mnth = totalPricePerMonth(price_interest, nMnths);
		
		// click calculate button
		new_page.findElement(By.name("calculate_payment_button")).click();
		wait(2);
		
		WebElement solutions = wdriver.findElement(By.name("payment_form"));
		
		DecimalFormat twoDForm = new DecimalFormat("#.##");

		assertEquals(Double.valueOf(twoDForm.format(dwnpymnt)), Double.valueOf(solutions.findElement(By.id("total_downpayment")).getAttribute("value")));
		assertEquals(Double.valueOf(twoDForm.format(ttl_per_mnth)), Double.valueOf(solutions.findElement(By.id("total_price_per_month")).getAttribute("value")));
		assertEquals(Integer.valueOf(nMnths), Integer.valueOf(solutions.findElement(By.id("n_of_months")).getAttribute("value")));
		assertEquals(Double.valueOf(twoDForm.format(ttl)), Double.valueOf(solutions.findElement(By.id("total_price")).getAttribute("value")));
	}
	
	// Given that I have selected a car
	// And am currently viewing its information page
    // When I enter a negative value into the number of years field
	// Then the results fields should be the same as the default behavior
	@Test
	public void negativeNumYrs() {
		WebElement new_page = wdriver.findElement(By.name("payment_form"));
		
		double dwnpymnt = Double.valueOf(new_page.findElement(By.name("downpayment")).getAttribute("value"));
		double interest = Double.valueOf(new_page.findElement(By.name("interest")).getAttribute("value"));
		
		// modify year to a negative integer
		int year = -5;
		new_page.findElement(By.name("year")).clear();
		new_page.findElement(By.name("year")).sendKeys("-5");
		
		double price_tax = Double.valueOf(new_page.findElement(By.name("price_with_taxes")).getAttribute("value"));
		
		double price_interest = priceWithInterest(price_tax, dwnpymnt, interest, year);
		int nMnths = numMonths(year);
		double ttl = totalPrice(price_interest, dwnpymnt);
		double ttl_per_mnth = totalPricePerMonth(price_interest, nMnths);
		
		// click calculate button
		new_page.findElement(By.name("calculate_payment_button")).click();
		wait(2);
		
		WebElement solutions = wdriver.findElement(By.name("payment_form"));
		
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		
		assertEquals(Double.valueOf(twoDForm.format(dwnpymnt)), Double.valueOf(solutions.findElement(By.id("total_downpayment")).getAttribute("value")));
		assertEquals(Double.valueOf(twoDForm.format(ttl_per_mnth)), Double.valueOf(solutions.findElement(By.id("total_price_per_month")).getAttribute("value")));
		assertEquals(Integer.valueOf(nMnths), Integer.valueOf(solutions.findElement(By.id("n_of_months")).getAttribute("value")));
		assertEquals(Double.valueOf(twoDForm.format(ttl)), Double.valueOf(solutions.findElement(By.id("total_price")).getAttribute("value")));
	}
}
























