package pl.bilickib.ms.model;

public class Post {
    private String title;
    private String description;
    private String url;

    public Post(String title) {
        this.title=title;
    }

    public Post description(String desctiption) {
        this.description=desctiption;
        return this;
    }

    public Post url(String url) {
        this.url=url;
        return this;
    }
}
