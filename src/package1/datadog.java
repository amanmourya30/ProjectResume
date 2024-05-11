package package1;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class datadog {
    public static void main(String[] args) throws InterruptedException {
    	WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, null);
        Actions actions = new Actions(driver);

        // Navigate to the URL
        driver.get("https://us5.datadoghq.com/rum/sessions?query=%40type%3Asession&cols=&fromUser=false&from_ts=1709635272606&to_ts=1709721672606&live=true");

        // Select ':us: US5 - Central' from the dropdown if not already selected
        WebElement dropdown = driver.findElement(By.id("your-dropdown-element-id")); // Update with actual dropdown element ID or locator
        Select select = new Select(dropdown);
        select.selectByVisibleText(":us: US5 - Central");

        // Click on 'Sign in with Gmail'
        driver.findElement(By.id("your-sign-in-button-id")).click(); // Update with actual button ID or locator

        // Enter Email ID and Password, then click on 'Log in'
        driver.findElement(By.id("identifierId")).sendKeys("your-email@gmail.com");
        driver.findElement(By.id("identifierNext")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        driver.findElement(By.name("password")).sendKeys("your-password");
        driver.findElement(By.id("passwordNext")).click();

        // Give permissions by clicking 'Continue'
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit_approve_access")));
        driver.findElement(By.id("submit_approve_access")).click();

        // Hover over 'UX Monitoring' & Click on 'Sessions Explorer'
        WebElement uxMonitoring = driver.findElement(By.id("your-ux-monitoring-element-id")); // Update with actual element ID or locator
        actions.moveToElement(uxMonitoring).perform();
        driver.findElement(By.linkText("Sessions Explorer")).click();

        // Click on Date selection bar and Select From Calendar
        driver.findElement(By.id("your-date-selection-bar-id")).click(); // Update with actual element ID or locator
        // Assuming you have a way to select start and end dates, typically by clicking on date elements

        // Enter text in Query Box and press Enter
        WebElement queryBox = driver.findElement(By.id("your-query-box-id")); // Update with actual element ID or locator
        queryBox.sendKeys("@session.has_replay:true @session.time_spent:>100s -@usr.email:xtenav");
        queryBox.sendKeys(Keys.ENTER);

        // Sort videos in descending order of time spent
        driver.findElement(By.id("your-time-sort-icon-id")).click(); // Update with actual icon ID or locator

        // Click on the Play icon, this will open the video in a new tab
        driver.findElement(By.id("your-play-icon-id")).click(); // Update with actual icon ID or locator

        // Switch to the new tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        // Click Checkbox of Extended Retention and wait for success, then close this tab
        WebElement extendedRetentionCheckbox = driver.findElement(By.id("your-checkbox-id")); // Update with actual checkbox ID or locator
        extendedRetentionCheckbox.click();
        // Add wait for success message here
//        driver.close();

        // Switch back to the original tab and continue as needed
//        driver.switchTo().window(tabs.get(0));

        // Add any further steps as needed

        // Remember to close the browser at the end of your test

    }
}