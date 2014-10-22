package pl.bilickib.ms.model;

public class Book {
    String title;
    String author;
    int publishedYear;
    String[] keywords;

    public Book(String title) {
        this.title=title;
    }

    public Book writtenBy(String author) {
        this.author=author;
        return this;
    }

    public Book published(int year) {
        this.publishedYear=year;
        return this;
    }

    public Book keywords(String... keywords) {
        this.keywords=keywords;
        return this;
    }
}
