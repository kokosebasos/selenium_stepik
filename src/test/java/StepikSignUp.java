import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import java.util.Set;


public class StepikSignUp {
    WebDriver driver;

    @Before
    public void setup(){


        // Configure Selenium
        System.setProperty("webdriver.chrome.driver","C:\\Users\\ivan7\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://welcome.stepik.org/en");
        driver.manage().window().maximize();

    }


    @Test
    public void Stepik() throws InterruptedException {

        WebDriverWait waitProsto = new WebDriverWait(driver,12);
        waitProsto.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='tn-atom'][normalize-space()='Try it out'][1]")));
        WebElement CheckLogin = driver.findElement(By.xpath("//a[@class='tn-atom'][normalize-space()='Try it out'][1]"));
        if (CheckLogin.isDisplayed())
        {
            driver.findElement(By.xpath("//a[@class='tn-atom'][normalize-space()='Try it out'][1]")).click();
        }else {
            System.out.println("Try it Out Button Not Found");
        }

        WebDriverWait waitProsto2 = new WebDriverWait(driver,50);
        waitProsto2.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-tab-name='registration']")));
        driver.findElement(By.xpath("//a[@data-tab-name='registration']")).click();

        driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);

//      Get the Temporary email address
        WebDriver driver2 =  new ChromeDriver();
        driver2.get("https://mail.tm/en/");
        Thread.sleep(5000);
        driver2.findElement(By.xpath("//span[normalize-space()='DISAGREE']")).click();
        WebDriverWait waitForTempMail = new WebDriverWait(driver2,120);
        waitForTempMail.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='DontUseWEBuseAPI']")));


        String getMail = driver2.findElement(By.id("DontUseWEBuseAPI")).getAttribute("value");
        Thread.sleep(5000);

        WebDriverWait waitForPageLoad = new WebDriverWait(driver,120);
        waitForPageLoad.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='id_registration_full-name']")));
        driver.findElement(By.xpath("//input[@id='id_registration_full-name']")).sendKeys("alextester");
        driver.findElement(By.xpath("//input[@id='id_registration_email']")).sendKeys(getMail);
        driver.findElement(By.xpath("//input[@id='id_registration_password']")).sendKeys("testing");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(5000);


        driver.findElement(By.xpath("//img[@alt='User avatar']")).click();
        driver.get("https://stepik.org/edit-profile/info?auth=login");
        driver.findElement(By.xpath("//a[@data-qa='user_edit__email-link']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[normalize-space()='Email is not verified! Send verification code.' or normalize-space()='Почта не подтверждена! Отправить код подтверждения.']")).click();
        Thread.sleep(5000);

        Thread.sleep(5000);

        driver2.manage().window().maximize();
        driver2.findElement(By.xpath("//div[@class='flex items-center px-4 py-4 sm:px-6']")).click();

        Thread.sleep(5000);
        WebElement iframe = driver2.findElement(By.tagName("iframe"));

        driver2.switchTo().frame(iframe);


        driver2.findElement(By.cssSelector("body:nth-child(2) p:nth-child(2) > a:nth-child(1)")).click();
        Thread.sleep(5000);
        Set<String> windowHandles = driver2.getWindowHandles();
        String mainWindowHandle = driver2.getWindowHandle();


        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(driver2.getWindowHandle())) {
                driver2.switchTo().window(windowHandle);
            }
        }
        driver2.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(3000);

        driver2.findElement(By.xpath("//input[@id='id_login_email']")).sendKeys(getMail);
        driver2.findElement(By.xpath("//input[@id='id_login_password']")).sendKeys("testing");
        driver2.findElement(By.xpath("//button[@type='submit']")).click();


        driver.manage().window().maximize();
        Thread.sleep(10000);
        driver2.quit();
    }


    @After
    public void close(){
        driver.quit();
    }

}
