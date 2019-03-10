import model.Article;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class TestCommentCount {
    private final String URL = "http://rus.delfi.lv";
    private final By ARTICLE = By.tagName("article"); //find list of all articles
    private final By TITLE = By.tagName("h1");
    private final By COMMENT_COUNTER = By.xpath(".//a[contains (@class, 'text-red')]");


    @Test
    public void commentCountCheck () {
        System.setProperty("webdriver.chrome.driver","/Users/Angelovsky/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(URL);

        List<WebElement> articles = driver.findElements(ARTICLE); //list of elements <element type> massiv statej, najdennih to tagu article

        Article article = getArticle(articles, 5);


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
}
