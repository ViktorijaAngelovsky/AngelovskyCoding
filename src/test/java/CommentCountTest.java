import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class CommentCountTest{
    private final String URL = "https://delfi.lv";
    private WebDriver driver;
    private final By FIRST_ARTICLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By MAIN_PAGE_ARTICLE_COMMENT_COUNT = By.xpath(".//a[contains(@class, 'comment-count')]");
    private final By ARTICLE_COMMENT_COUNT = By.xpath(".//a[contains (@class, 'text-red-ribbon')]");
    private final By ANONYMOUS_COMMENT_COUNT = By.xpath(".//span[contains(@class, 'type-cnt')]");


    @Test
    public void articleCommentCountCheck(){
        //Set driver path
        System.setProperty("webdriver.chrome.driver","/Users/Angelovsky/Downloads/chromedriver");

        //Open browser
        driver = new ChromeDriver();

        //Full screen
        driver.manage().window().maximize();

        //Open homepage
        driver.get(URL);

        //Find first article
        WebElement homePageFirstArticle = driver.findElement(FIRST_ARTICLE);

        //Find first article+Save to String
        String homePageFirstArticleTxt = homePageFirstArticle.getText();

        //Find first article comment count
        WebElement homePageCommentCount = driver.findElement(MAIN_PAGE_ARTICLE_COMMENT_COUNT);

        //Save to String
        String homePageCommentCountTxt = homePageCommentCount.getText();

        //replace ()
        //String replaceString=s1.replaceAll("a","e");
        String replaceHomePageCommentCountTxt = homePageCommentCountTxt.replaceAll("[()]", "");

        //Save to Integer what replaced
        Integer homePageCommentCountInt = Integer.parseInt(replaceHomePageCommentCountTxt);

        //Click on article
        driver.findElement(FIRST_ARTICLE).click();

        //Find comment count
        WebElement articleCommentCount = driver.findElement(ARTICLE_COMMENT_COUNT);

        //Save to String
        String articleCommentCountTxt = articleCommentCount.getText();

        //replace ()
        String replaceArticleCommentCountTxt = articleCommentCountTxt.replaceAll("[()]", "");

        //Save to Integer what replaced
        Integer articleCommentCountInt = Integer.parseInt(replaceArticleCommentCountTxt);

        //Check comment count
        Assertions.assertEquals(homePageCommentCountInt, articleCommentCountInt, "Wrong comment count!");

        //Find comment count


        //Click on comment count
        driver.findElement(ARTICLE_COMMENT_COUNT).click();

        //Find anonymous comment count
        WebElement anonymousCommentCount = driver.findElement(ANONYMOUS_COMMENT_COUNT);

        //Save to String
        String anonymousCommentCountTxt = anonymousCommentCount.getText();

        //replace
        String replaceAnonymousCommentCountTxt = anonymousCommentCountTxt.replaceAll("[()]", "");

        //Save to Integer what replaced
        Integer anonymouseCommentCountInt = Integer.parseInt(replaceAnonymousCommentCountTxt);

        //Find registered comment count
        List <WebElement> elementsList = driver.findElements(ANONYMOUS_COMMENT_COUNT);
        WebElement registeredCommentCount = elementsList.get(1);

        //Save to String
        String registeredCommentCountTxt = registeredCommentCount.getText();

        //replace
        String replaceRegisteredCommentCountTxt = registeredCommentCountTxt.replaceAll("[()]", "");

        //Save to Integer what replaced
        Integer registeredCommentCountInt = Integer.parseInt(replaceRegisteredCommentCountTxt);

        //Sum registered and anonymous comment count
        Integer sumOfComments = anonymouseCommentCountInt + registeredCommentCountInt;

        //Check comment count
        Assertions.assertEquals(articleCommentCountInt, sumOfComments, "Wrong comment count!");

        //Close browser
    }
    @AfterEach
    public void closeBrowser (){
        driver.close();
    }

}
