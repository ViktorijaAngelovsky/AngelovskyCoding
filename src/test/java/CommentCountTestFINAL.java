import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class CommentCountTestFINAL {
    private final String URL = "https://delfi.lv";
    private WebDriver driver;
    private final By FIRST_ARTICLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By MAIN_PAGE_ARTICLE_COMMENT_COUNT = By.xpath(".//a[contains(@class, 'comment-count')]");
    private final By ARTICLE_COMMENT_COUNT = By.xpath(".//a[contains (@class, 'text-red-ribbon')]");
    private final By ANONYMOUS_COMMENT_COUNT = By.xpath(".//span[contains(@class, 'type-cnt')]");

    private final Logger LOGGER = LogManager.getLogger(CommentCountTestFINAL.class); //logging


    @Test
    public void articleCommentCountCheck(){
        LOGGER.info("We are starting our test!");
        System.setProperty("webdriver.chrome.driver","/Users/Angelovsky/Downloads/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);

        WebElement homePageFirstArticle = driver.findElement(FIRST_ARTICLE);
        String homePageFirstArticleTxt = homePageFirstArticle.getText();

        WebElement homePageCommentCount = driver.findElement(MAIN_PAGE_ARTICLE_COMMENT_COUNT);
        String homePageCommentCountTxt = homePageCommentCount.getText();
        String replaceHomePageCommentCountTxt = homePageCommentCountTxt.replaceAll("[()]", "");
        Integer homePageCommentCountInt = Integer.parseInt(replaceHomePageCommentCountTxt);

        driver.findElement(FIRST_ARTICLE).click();

        WebElement articleCommentCount = driver.findElement(ARTICLE_COMMENT_COUNT);
        String articleCommentCountTxt = articleCommentCount.getText();
        String replaceArticleCommentCountTxt = articleCommentCountTxt.replaceAll("[()]", "");
        Integer articleCommentCountInt = Integer.parseInt(replaceArticleCommentCountTxt);

        Assertions.assertEquals(homePageCommentCountInt, articleCommentCountInt, "Wrong comment count!");

        driver.findElement(ARTICLE_COMMENT_COUNT).click();

        WebElement anonymousCommentCount = driver.findElement(ANONYMOUS_COMMENT_COUNT);
        String anonymousCommentCountTxt = anonymousCommentCount.getText();
        String replaceAnonymousCommentCountTxt = anonymousCommentCountTxt.replaceAll("[()]", "");
        Integer anonymouseCommentCountInt = Integer.parseInt(replaceAnonymousCommentCountTxt);

        List <WebElement> elementsList = driver.findElements(ANONYMOUS_COMMENT_COUNT);
        WebElement registeredCommentCount = elementsList.get(1);
        String registeredCommentCountTxt = registeredCommentCount.getText();
        String replaceRegisteredCommentCountTxt = registeredCommentCountTxt.replaceAll("[()]", "");
        Integer registeredCommentCountInt = Integer.parseInt(replaceRegisteredCommentCountTxt);

        Integer sumOfComments = anonymouseCommentCountInt + registeredCommentCountInt;

        Assertions.assertEquals(articleCommentCountInt, sumOfComments, "Wrong comment count!");
    }
    @AfterEach
    public void closeBrowser (){
        driver.close();
    }

}
