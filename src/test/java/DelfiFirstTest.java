import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DelfiFirstTest {
    @Test
    public void delfiFirstTest(){
        System.setProperty("webdriver.chrome.driver", "/Users/Angelovsky/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();   //new var
        driver.manage().window().maximize();
        driver.get("http://delfi.lv");
    }

}
