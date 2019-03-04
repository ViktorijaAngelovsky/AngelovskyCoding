package model;

public class Article {
    private String title;
    private Integer commentCount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount (String commentCount) {
        commentCount = commentCount.substring(1, commentCount.length()-1); //substring - iz odnoj stroki virezajet druguju stroku. Prinimaet 2 paramenta - s kakogo simvola po kakoj virezajem. Rezuljtat - to, 4to virezali, 4to nado sohranitj
        this.commentCount = Integer.valueOf(commentCount); //iz stroki commentCount konvertirujem v Integer i zapisivajem v nash objekt

    }
}
