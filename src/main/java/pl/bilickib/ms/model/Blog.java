package pl.bilickib.ms.model;

public class Blog {
    private String title;
    private String url;
    private String subtitle;

    public Blog(String title) {
        this.title=title;
    }

    public Blog subtitle(String subtitle) {
        this.subtitle=subtitle;
        return this;
    }

    public Blog url(String url) {
        this.url=url;
        return this;
    }
}
