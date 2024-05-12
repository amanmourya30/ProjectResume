package package1;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        
        String [] emails= {"amanmourya30@gmail.com","amanmourya069@gmail.com"};
        for(String email:emails) {
        	
        	driver.findElement(By.xpath("//div[contains(text(),'Compose')]")).click(); //clicks on compose button
            Thread.sleep(1000);
            //interacting with email send email format elements
         // Click on the 'To' field and enter the email address
            WebElement toField = driver.findElement(By.cssSelector(".agP.aFw"));
            toField.click();
            toField.sendKeys(email , Keys.ENTER);

            // Click on the 'Subject' field and enter the subject text
            WebElement subjectField = driver.findElement(By.cssSelector(".aoT"));
            subjectField.click();
            subjectField.sendKeys("This is a Subject of Email.");

            // Click on the 'Body' field and enter the email body
            WebElement bodyField = driver.findElement(By.cssSelector(".Am.Al.editable.LW-avf"));
            bodyField.click();
            bodyField.sendKeys("This is Body of Email.");

            // Click on the 'Attachment' icon
            driver.findElement(By.cssSelector(".a1.aaA.aMZ")).click();
            // Upload the file
            uploadFile("C:\\check\\fileupload.exe");

            // Wait for the attachment to appear
            waitForElementToAppear(driver, By.xpath("//div[contains(@aria-label,'Attachment')]"));

            // Click on the send button
            driver.findElement(By.cssSelector(".T-I.J-J5-Ji.aoO.v7.T-I-atl.L3")).click();

            // Wait for the 'Undo' message to disappear
            invisibilityOfElementWithText(driver, By.cssSelector("#link_undo"));

            System.out.println(email);
    		
        }
	}
public static void uploadFile(String filePath) throws IOException {
	    // Wait for a short duration before starting the upload process
	    try {
	        Thread.sleep(2000);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }

	    // Execute the file upload process
	    ProcessBuilder pb = new ProcessBuilder(filePath);
	    pb.start();
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
