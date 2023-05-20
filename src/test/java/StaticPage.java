import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class StaticPage {
    WebDriver driver;

    @Before
    public void setup(){
        // Configure Selenium
        System.setProperty("webdriver.chrome.driver","C:\\Users\\ivan7\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://welcome.stepik.org/en/about");
    }


    @Test
    public void StepikCheck(){
        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl);

        
        String title = driver.getTitle();
        System.out.println(title);
        
    }
    @After
    public void close() {
        driver.quit();
    }
}

