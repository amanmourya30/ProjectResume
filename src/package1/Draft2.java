package package1;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
 
import io.github.bonigarcia.wdm.WebDriverManager;

public class Draft2 {
	public static void main(String[] args) throws InterruptedException, IOException {
        // WebDriver set up and go to Gmail URL
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        String userProfile = "C:\\localhost";
        options.addArguments("user-data-dir=" + userProfile);
        options.addArguments("--remote-debugging-port=9111");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://gmail.com");
        
        driver.findElement(By.xpath("//div[contains(text(),'Compose')]")).click(); //clicks on compose button
        Thread.sleep(2000);

        driver.findElement(By.cssSelector(".agP.aFw")).click(); //clicks on To field
        driver.findElement(By.cssSelector(".agP.aFw")).sendKeys("adityakumarsept0@gmail.com"); //enter email
        driver.findElement(By.cssSelector(".aoT")).click(); //clicks on Subject field
        driver.findElement(By.cssSelector(".aoT")).sendKeys("This is a Subject of Email."); //Enter Subject text
        driver.findElement(By.cssSelector(".Am.Al.editable.LW-avf")).click(); //clicks on Body field
        driver.findElement(By.cssSelector(".Am.Al.editable.LW-avf")).sendKeys("This is Body of Email."); //enter email body
        driver.findElement(By.cssSelector(".T-I.J-J5-Ji.aoO.v7.T-I-atl.L3")).click(); //clicks on send button
        //clicking on Attachment icon
        driver.findElement(By.cssSelector(".a1.aaA.aMZ")).click();
        

//     Runtime.getRuntime().exec("C:\\Coding stuff\\Check\\fileupload.exe");
//	    Execute the AutoIt script using ProcessBuilder
		ProcessBuilder pb = new ProcessBuilder("C:\\Coding stuff\\Check\\fileupload.exe");
		pb.start();
		
		
        

        
	}
	
	
}
