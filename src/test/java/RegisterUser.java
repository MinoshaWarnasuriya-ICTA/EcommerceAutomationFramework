import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.testng.*;

public class RegisterUser {
    public static void main(String[] args) {
        //Launch browser
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        //Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");

        //Verify that home page is visible successfully
        Assert.assertTrue(driver.findElement(By.cssSelector(".logo a img")).isDisplayed());

        // Click on 'Signup / Login' button
        driver.findElement(By.xpath("//*[text()=' Signup / Login']")).click();

        //Verify 'New User Signup!' is visible
        Assert.assertTrue(driver.findElement(By.cssSelector("div[class='signup-form'] h2")).isDisplayed());

        //Enter name and email address
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Scott");
        driver.findElement(By.cssSelector("input[data-qa='signup-email']")).sendKeys("scott@gmail.com");

        //Click 'Signup' button
        driver.findElement(By.xpath("//button[text()='Signup']")).click();

        //Verify that 'ENTER ACCOUNT INFORMATION' is visible
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-form']/h2/b")).isDisplayed());

        //Fill details: Title, Name, Email, Password, Date of birth
        driver.findElement(By.xpath("//input[@value='Mrs']")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("Scott");
        driver.findElement(By.id("password")).sendKeys("scot@123");
        WebElement days = driver.findElement(By.id("days"));
        WebElement months = driver.findElement(By.id("months"));
        WebElement years = driver.findElement(By.id("years"));

        Select day = new Select(days);
        day.selectByVisibleText("17");

        Select month = new Select(months);
        month.selectByVisibleText("December");

        Select year = new Select(years);
        year.selectByVisibleText("1998");

        //Select checkbox 'Sign up for our newsletter!'
        driver.findElement(By.cssSelector("#newsletter")).click();

        //Select checkbox 'Receive special offers from our partners!'
        driver.findElement(By.xpath("//input[@id='optin']")).click();

        //Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
        driver.findElement(By.cssSelector("#first_name")).sendKeys("Scott");
        driver.findElement(By.cssSelector("#last_name")).sendKeys("Smith");
        driver.findElement(By.xpath("//*[@data-qa='company']")).sendKeys("IFS");
        driver.findElement(By.id("address1")).sendKeys("No:365");
        driver.findElement(By.id("address2")).sendKeys("Colombo");
        WebElement countryList = driver.findElement(By.xpath("//*[@id='country']"));

        Select country = new Select(countryList);
        country.selectByValue("New Zealand");
        driver.findElement(By.id("state")).sendKeys("Florida");
        driver.findElement(By.id("city")).sendKeys("Alberta");
        driver.findElement(By.id("zipcode")).sendKeys("0023");
        driver.findElement(By.id("mobile_number")).sendKeys("01234567");

        //Click 'Create Account button'
        driver.findElement(By.xpath("//button[text()='Create Account']")).click();

        //Verify that 'ACCOUNT CREATED!' is visible
        Assert.assertTrue(driver.findElement(By.xpath("//div/h2/b")).isDisplayed());

        //Click 'Continue' button
        driver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click();

        //Verify that 'Logged in as username' is visible
        String welcomMsge = "Logged in as ";
        String name = driver.findElement(By.cssSelector("li:last-child a b")).getText();
        Assert.assertEquals(driver.findElement(By.cssSelector("li:last-child a")).getText(),welcomMsge+name);

        //Click 'Delete Account' button
        //driver.findElement(By.xpath("//li[5]/a")).click();

        //Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
       // Assert.assertTrue(driver.findElement(By.xpath("//div/h2/b")).isDisplayed());


    }
}
