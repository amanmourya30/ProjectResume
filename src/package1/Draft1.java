package package1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Draft1 {

    public static void main(String[] args) throws InterruptedException {
        // WebDriver set up and go to Gmail URL
        WebDriverManager.chromedriver().setup();

        // Create ChromeOptions instance
        ChromeOptions options = new ChromeOptions();
        String userProfile = "C:\\localhost";
        options.addArguments("user-data-dir=" + userProfile);

        // Set remote debugging port
        options.addArguments("--remote-debugging-port=9111");

        // Initialize ChromeDriver with ChromeOptions
        WebDriver driver = new ChromeDriver(options);
//        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://gmail.com");
        Thread.sleep(2000);
        
        Actions act =new Actions(driver);
        driver.findElement(By.xpath("//div[contains(text(),'Compose')]")).click();
        Thread.sleep(2000);

        act.moveToElement(driver.findElement(By.xpath("//div[contains(text(),'Compose')]"))).click().build().perform();
        driver.switchTo().frame(driver.findElement(By.id("hfcr")));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='New Message']")));
        
        driver.findElement(By.xpath("(//img[@id=':1ar'])[1]")).click();
        
//        WebElement toField=driver.findElement(By.xpath("(//div[@id=':pi'])[1]"));
//        toField.click();
//        toField.sendKeys("amanmourya30@gmail.com");
       
        
    }
}
