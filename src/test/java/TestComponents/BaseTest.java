package TestComponents;

import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;
import io.github.bonigarcia.wdm.*;
import org.apache.commons.io.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;
import org.testng.*;
import org.testng.annotations.*;
import pageObjects.*;

import java.io.*;
import java.nio.charset.*;
import java.util.*;


public class BaseTest {
    public WebDriver driver;
    public HomePage homePage;

    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//resources//globalData.properties");
        prop.load(fis);
        String browser = prop.getProperty("browser");
        //Launch browser
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeMethod
    public void launchApplication() throws IOException {
        WebDriver driver = initializeDriver();
        homePage = new HomePage(driver);
        // Navigate to application
        homePage.goTo();
        //Verify that home page is visible successfully
        Assert.assertTrue(homePage.getLogo().isDisplayed());

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ss = (TakesScreenshot) driver;
        File src = ss.getScreenshotAs(OutputType.FILE);
        File dest = new File(System.getProperty("user.dir") + "//report//" + testCaseName + ".png");
        FileUtils.copyFile(src, dest);
        String path = System.getProperty("user.dir") + "//report//" + testCaseName + ".png";
        return path;
    }

    public List<HashMap<String, Object>> getJsonDataToMap(String filepath) throws IOException {
        //read json file to string
        String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + filepath), StandardCharsets.UTF_8);
        //read string json to list of hashmaps
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, Object>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, Object>>>() {
        });

        return data;


    }

}
