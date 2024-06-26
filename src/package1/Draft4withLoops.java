package package1;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Draft4withLoops {
	public static void main(String[] args) throws InterruptedException, IOException {
        // WebDriver set up
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        String userProfile = "C:\\localhost";
        options.addArguments("user-data-dir=" + userProfile);
        options.addArguments("--remote-debugging-port=9111");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://gmail.com");
        
        String [] emails= {"amanmourya30@gmail.com","amanmourya069@gmail.com","adityakumarsept0@gmail.com"};
        for(String email:emails) {
        	
        	driver.findElement(By.xpath("//div[contains(text(),'Compose')]")).click(); //clicks on compose button
            Thread.sleep(1000);
            //interacting with email send email format elements
            driver.findElement(By.cssSelector(".agP.aFw")).click(); //clicks on To field
            driver.findElement(By.cssSelector(".agP.aFw")).sendKeys(email , Keys.ENTER); //enter email
            driver.findElement(By.cssSelector(".aoT")).click(); //clicks on Subject field
            driver.findElement(By.cssSelector(".aoT")).sendKeys("This is a Subject of Email."); //Enter Subject text
            driver.findElement(By.cssSelector(".Am.Al.editable.LW-avf")).click(); //clicks on Body field
            driver.findElement(By.cssSelector(".Am.Al.editable.LW-avf")).sendKeys("This is Body of Email."); //enter email body
            driver.findElement(By.cssSelector(".a1.aaA.aMZ")).click(); //clicks on Attachment icon
            Thread.sleep(2000);
    		ProcessBuilder pb = new ProcessBuilder("C:\\check\\fileupload.exe"); //uploads resume file in check folder
    		pb.start();
    		waitForElementToAppear(driver, By.xpath("//div[contains(@aria-label,'Attachment')]")); //waits for file to upload completely
    		driver.findElement(By.cssSelector(".T-I.J-J5-Ji.aoO.v7.T-I-atl.L3")).click(); //clicks on send button
    		
    		Thread.sleep(1000);
    		invisibilityOfElementWithText(driver, By.cssSelector("#link_undo") );//waits for Undo message to disappear
    		System.out.println(email);
    		
        }
        driver.quit();
	}
	
public static void waitForElementToAppear(WebDriver driver, By locator) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}

public static void invisibilityOfElementWithText(WebDriver driver, By locator) {
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	wait.until(ExpectedConditions.invisibilityOfElementWithText(locator, "Undo"));
}
		
}
