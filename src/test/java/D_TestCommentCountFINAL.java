import model.D_Article;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class D_TestCommentCountFINAL {
    private final String URL = "http://rus.delfi.lv";
    private WebDriver driver;
    private final By ARTICLE = By.tagName("article"); //find list of all articles
    private final By TITLE = By.tagName("h1");
    private final By TITLE_CLICK = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By COMMENT_COUNTER = By.xpath(".//a[contains (@class, 'text-red')]");
    private final By TITLE_INSIDE_COMMENTS = By.xpath(".//h1[@class = 'article-title']/a");
    private final By ALL_COMMENTS = By.xpath(".//span[contains(@class, 'type-cnt')]");


    @Test
    public void commentCountCheck () {
        System.setProperty("webdriver.chrome.driver", "/Users/Angelovsky/Downloads/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);

        List<WebElement> articles = driver.findElements(ARTICLE);

        D_Article mainArticle = getArticle(articles, 0);
        mainArticle.getTitleElement().click();

        articles = driver.findElements(ARTICLE);
        D_Article insideArticle = getArticle(articles, 0);

        Assertions.assertEquals(mainArticle.getTitle(), insideArticle.getTitle(), "Wrong Title!");
        Assertions.assertEquals(mainArticle.getCommentCount(), insideArticle.getCommentCount(), "Wrong Comment count!");

        if (insideArticle.getCommentCount() > 0) {
            insideArticle.getCommentElement().click();

            WebElement commentsTitle = driver.findElement(TITLE_INSIDE_COMMENTS);
            D_Article commentsArticle = new D_Article();
            commentsArticle.setTitleElement(commentsTitle);

            List<WebElement> commentsList = driver.findElements(ALL_COMMENTS);
            WebElement anonymousComments = commentsList.get(0);
            WebElement registeredComments = commentsList.get(1);

            commentsArticle.setCommentElement(anonymousComments);
            Integer sum = commentsArticle.getCommentCount();
            commentsArticle.setCommentElement(registeredComments);
            sum = sum + commentsArticle.getCommentCount();

            Assertions.assertEquals(insideArticle.getCommentCount(), sum, "Wrong counts SUM!");
            Assertions.assertEquals(insideArticle.getTitle(), commentsArticle.getTitle(), "Wrong title!");
        }
    }

    private D_Article getArticle(List<WebElement> elements, int i) {
        if (!elements.isEmpty()) {
            WebElement element = elements.get(i);

            D_Article article = new D_Article();
            article.setTitleElement(element.findElement(TITLE));
            article.setCommentElement(element.findElement(COMMENT_COUNTER));
            return article;
        }
        return null;
    }

    @AfterEach
    public void closeBrowser (){
        driver.close();
    }
}
