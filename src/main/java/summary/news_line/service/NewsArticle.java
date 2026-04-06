package summary.news_line.service;

public class NewsArticle {
    private final String title;
    private final String link;

    public NewsArticle(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }
}

