import model.Article;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class TestCommentCountTEMP {
    private final String URL = "http://rus.delfi.lv";
    private WebDriver driver;
    private final By ARTICLE = By.tagName("article"); //find list of all articles
    private final By TITLE = By.tagName("h1");
    private final By COMMENT_COUNTER = By.xpath(".//a[contains (@class, 'text-red')]");
    private final By TITLE_INSIDE_COMMENTS = By.xpath(".//h1[@class = 'article-title']/a");
    private final By ALL_COMMENTS = By.xpath(".//span[contains(@class, 'type-cnt')]");


    @Test
    public void commentCountCheck () {
        System.setProperty("webdriver.chrome.driver","/Users/Angelovsky/Downloads/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);

        List<WebElement> articles = driver.findElements(ARTICLE);
        WebElement article = articles.get(1); //iz massiva articles vzjatj element s indeksom 0, vozvrashajet element s indexom 0

        // Title
        String title = article.findElement(TITLE).getText();

        // Comment count
        List<WebElement> commentCounters = article.findElements(COMMENT_COUNTER); //v najdennom article naxodim spisok kolichestva kommentov
        String commentCountTxt;
        if (commentCounters.isEmpty()) {
            commentCountTxt = "0";
        } else {
            commentCountTxt = commentCounters.get(0).getText(); // (0) <-- 1ij najdennij kommentarij
        }

        // Create and save data to object
        Article currentArticle = new Article();
        currentArticle.setTitle(title);
        currentArticle.setCommentCount(commentCountTxt);

        article.findElement(TITLE).click();

        List<WebElement> articlesInside = driver.findElements(ARTICLE);
        WebElement articleInside = articlesInside.get(0);

        String titleInside = articleInside.findElement(TITLE).getText();

        List<WebElement> commentCountersInside = articleInside.findElements(COMMENT_COUNTER);
        String commentCountInsideTxt;
        if (commentCountersInside.isEmpty()) {
            commentCountInsideTxt = "0";
        } else {
            commentCountInsideTxt = commentCountersInside.get(0).getText();
        }

        Article currentArticleInside = new Article();
        currentArticleInside.setTitle(titleInside);
        currentArticleInside.setCommentCount(commentCountInsideTxt);


        Assertions.assertEquals(currentArticle.getTitle(), currentArticleInside.getTitle(), "Wrong!");
        Assertions.assertEquals(currentArticle.getCommentCount(), currentArticleInside.getCommentCount(), "Wrong!");

        articleInside.findElement(COMMENT_COUNTER).click();

        WebElement articleComments = driver.findElement(TITLE_INSIDE_COMMENTS);

        String titleInsideComments = articleComments.getText();

        List<WebElement> commentsList = driver.findElements(ALL_COMMENTS);
        WebElement anonymousComments = commentsList.get(0);
        String anonymousCommentsTxt = anonymousComments.getText();

        Article anonymComm = new Article();
        anonymComm.setCommentCount(anonymousCommentsTxt);

        WebElement registeredComments = commentsList.get(1);
        String registeredCommentsTxt = registeredComments.getText();

        Article registeredComm = new Article();
        registeredComm.setCommentCount(registeredCommentsTxt);

        Article titleComm = new Article();
        titleComm.setTitle(titleInsideComments);

        Integer sumOfComments = anonymComm.getCommentCount() + registeredComm.getCommentCount();

        Assertions.assertEquals(currentArticleInside.getTitle(), titleComm.getTitle(), "Wrong!");

        Assertions.assertEquals(currentArticleInside.getCommentCount(), sumOfComments, "Wrong!");


    }
    @AfterEach
    public void closeBrowser (){
        driver.close();
    }
}
