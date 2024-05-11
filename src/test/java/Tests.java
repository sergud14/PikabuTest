import jdk.jfr.Timespan;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class Tests {
    WebDriver driver=new ChromeDriver();

    @Before
    public void  setUp()
    {
        System.setProperty("webdriver.chrome.driver","C:\\chdriver\\chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(5000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void  PikabuTest() throws InterruptedException {
        driver.get("https://www.google.com/");
        driver.findElement(By.xpath("//textarea[@title='Поиск']")).click();
        driver.findElement(By.xpath("//textarea[@title='Поиск']")).sendKeys("Сайт компании Победа");
        driver.findElement(By.xpath("//textarea[@title='Поиск']")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("(//cite[text()='https://www.pobeda.aero'])[1]")).click();
        driver.findElement(By.xpath("//button[text()='РУС']")).click();

        waitForVisibilityOfElement("//div[@class='dp-14a08ke-root'][text()='Полетели в Калининград!']");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[text()='English']")))).click();
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//div[@class='dp-c5f2rl-root-root-tabs']//button[1]")),"Ticket search"));
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//div[@class='dp-c5f2rl-root-root-tabs']//button[2]")),"Online check-in"));
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//div[@class='dp-c5f2rl-root-root-tabs']//button[3]")),"Manage my booking"));
    }

    @After
    public void  tearDown()
    {
        driver.quit();
    }

    public void waitForVisibilityOfElement(String elemXPath) {
        float waitingTime = 0;
        float MAX_WAITING_TIME = 35000;
        long startLoadingTime = System.currentTimeMillis();
        while (driver.findElement(By.xpath(elemXPath)) ==null){
            if(waitingTime <= MAX_WAITING_TIME) {
                waitingTime = System.currentTimeMillis() - startLoadingTime;
            } else {
                System.out.println("Condition wasn't executed with time limit");
                break;
            }
        }

        if(driver.findElement(By.xpath(elemXPath)) !=null) {
            System.out.println("Condition was executed in "
                    + waitingTime +
                    " seconds");
        }
    }

}
