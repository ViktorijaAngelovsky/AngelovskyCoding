import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class CommentCountTest2 {
    private final String URL = "https://delfi.lv";
    private WebDriver driver;
    private final By FIRST_ARTICLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By MAIN_PAGE_ARTICLE_COMMENT_COUNT = By.xpath(".//a[contains(@class, 'comment-count')]");
    private final By ARTICLE_COMMENT_COUNT = By.xpath(".//a[contains (@class, 'text-red-ribbon')]");
    private final By ANONYMOUS_COMMENT_COUNT = By.xpath(".//span[contains(@class, 'type-cnt')]");


    @Test
    public void articleCommentCountCheck(){
        System.setProperty("webdriver.chrome.driver","/Users/Angelovsky/Downloads/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(URL);
        Integer homePageCommentCountInt = intFromElement(driver.findElement(MAIN_PAGE_ARTICLE_COMMENT_COUNT));

        driver.findElement(FIRST_ARTICLE).click();
        WebElement articleCommentCountElement = driver.findElement(ARTICLE_COMMENT_COUNT);
        Integer articleCommentCountInt = intFromElement(articleCommentCountElement);

        Assertions.assertEquals(homePageCommentCountInt, articleCommentCountInt, "Wrong comment count!");

        articleCommentCountElement.click();
        List <WebElement> elementsList = driver.findElements(ANONYMOUS_COMMENT_COUNT);

        Integer anonymouseCommentCountInt = intFromElement(elementsList.get(0));
        Integer registeredCommentCountInt = intFromElement(elementsList.get(1));

        Integer sumOfComments = anonymouseCommentCountInt + registeredCommentCountInt;
        Assertions.assertEquals(articleCommentCountInt, sumOfComments, "Wrong comment count!");
    }

    public Integer intFromElement (WebElement element) {
        String elementText = element.getText();
        elementText = elementText.replaceAll("[()]", "");
        return Integer.parseInt(elementText);
    }

    @AfterEach
    public void closeBrowser (){
        driver.close();
    }

}
