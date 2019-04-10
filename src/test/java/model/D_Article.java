package model;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class D_Article {
    private WebElement titleElement;
    private WebElement commentElement;

    private String title;
    private Integer commentCount;

    public WebElement getTitleElement() {
        return titleElement;
    }
    public void setTitleElement(WebElement el) {
        this.titleElement = el;

        if (el != null) {
            this.title = el.getText();
        }
    }

    public WebElement getCommentElement() { return commentElement; }
    public void setCommentElement(WebElement el) {
        this.commentElement = el;

        if (el != null) {
            this.commentCount = Integer.valueOf(el.getText().substring(1, el.getText().length()-1));
        } else {
            this.commentCount = 0;
        }
    }

    public String getTitle() { return title; }
    public Integer getCommentCount() { return commentCount; }
}
