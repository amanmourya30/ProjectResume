package package1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
 
import io.github.bonigarcia.wdm.WebDriverManager;

public class Draft2 {
	public static void main(String[] args) throws InterruptedException {
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
        
        driver.findElement(By.xpath("//div[contains(text(),'Compose')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector(".agP.aFw")).click();
        driver.findElement(By.cssSelector(".agP.aFw")).sendKeys("adityakumarsept0@gmail.com");
        driver.findElement(By.cssSelector(".Am.Al.editable.LW-avf")).click(); 
        driver.findElement(By.cssSelector(".Am.Al.editable.LW-avf")).sendKeys("Fuck you...bro.......!!");
        WebElement sendElement = driver.findElement(By.cssSelector(".T-I.J-J5-Ji.aoO.v7.T-I-atl.L3"));
        sendElement.click();

        
}
}
