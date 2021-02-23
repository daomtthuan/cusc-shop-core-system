package cuscshop.coresystem.app.config;

public class AppConfig {

    private final String name;
    private final String description;
    private final String author;
    private final String keywords;

    public AppConfig(String name, String description, String author, String keywords) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.keywords = keywords;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getKeywords() {
        return keywords;
    }

}
