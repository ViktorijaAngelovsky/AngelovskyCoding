import model.Article;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class TestCommentCountFINAL {
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


        List<WebElement> articles = driver.findElements(ARTICLE); //list of elements <element type> massiv statej, najdennih to tagu article

        Article article = getArticle(articles, 5);

        WebElement articleMain = articles.get(5);
        articleMain.findElement(TITLE_CLICK).click();

        List<WebElement> articlesInside = driver.findElements(ARTICLE);

        Article articleInside = getArticle(articlesInside, 0);

        Assertions.assertEquals(article.getTitle(), articleInside.getTitle(), "Wrong!");
        Assertions.assertEquals(article.getCommentCount(), articleInside.getCommentCount(), "Wrong!");

        if (articleInside.getCommentCount() > 0) {
            WebElement commInside = articlesInside.get(0);
            commInside.findElement(COMMENT_COUNTER).click();

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

            Assertions.assertEquals(articleInside.getTitle(), titleComm.getTitle(), "Wrong!");

            Assertions.assertEquals(articleInside.getCommentCount(), sumOfComments, "Wrong!");
        }
    }

    private Article getArticle(List<WebElement> elements, int i) {
        WebElement article = elements.get(i); //iz spiska vitaskivajem element i zapisivajem v WebElement article

        Article currentArticle = new Article(); //sozdajem kopiju objekta Article
        currentArticle.setTitle(article.findElement(TITLE).getText()); //ishem

        List<WebElement> commentCounters = article.findElements(COMMENT_COUNTER);

        if (commentCounters.isEmpty()) { //proverjajem pustoj li prishel massiv, vernet "true" esli spisok pustoj
            currentArticle.setCommentCount("0"); //esli spisok pustoj, to peredajem tuda 0
        } else {
            currentArticle.setCommentCount(commentCounters.get(0).getText());
        }

        return currentArticle;
    }

    @AfterEach
    public void closeBrowser (){
        driver.close();
    }
}






