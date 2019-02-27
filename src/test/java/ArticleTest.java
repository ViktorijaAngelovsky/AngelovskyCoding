import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.border.TitledBorder;

public class ArticleTest {
    private final String URL = "https://delfi.lv"; //page adress after class
    private final By TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]"); //locator to find element
    private final By ARTICLE_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'd-inline')]"); //locator to find element, find title
    private final By COMMENT_COUNT = By.xpath(".//a[contains(@class, 'text-red-ribbon')]");
    private final By COMMENT_PAGE_TITLE = By.xpath(".//h1[@class = 'article-title']/a");

    private WebDriver driver;

    @Test
    public void articleTitleCheck() {
        //Set driver path
        System.setProperty("webdriver.chrome.driver", "/Users/Angelovsky/Downloads/chromedriver");

        //Open Browser
        driver = new ChromeDriver();

        //Full screen
        driver.manage().window().maximize();

        //Open Homepage
        driver.get(URL); //row 11

        //Find first article title
        WebElement homePageTitle = driver.findElement(TITLE); //new var homePageTitle, row 12

        //Save to String
        String homePageTitleTxt = driver.findElement(TITLE).getText();

        //Click on article
        driver.findElement(TITLE).click();

        //Find title
        WebElement articlePageTitle = driver.findElement(ARTICLE_PAGE_TITLE); //row 13

        //Save to String
        String articlePageTitleTxt = articlePageTitle.getText();

        //Check article title
        Assertions.assertEquals(homePageTitleTxt, articlePageTitleTxt, "Wrong article page title!" ); //expected result, actual result, message

        //Find comment count
        WebElement commentCount = driver.findElement(COMMENT_COUNT); //row 14

        //Click on comment count
        commentCount.click();

        //Find title (//+Save to String, in one row)
        String commentPageTitle = driver.findElement(COMMENT_PAGE_TITLE).getText();

        //Check article title
        Assertions.assertEquals(homePageTitleTxt, commentPageTitle, "Wrong comment page title!");

        //Close browser (in new method)

    }
    @AfterEach //close browser after each test in any case. Чтобы эта функция отработала в любом случае
    public void closeBrowser () {
        driver.close();
    }
}
