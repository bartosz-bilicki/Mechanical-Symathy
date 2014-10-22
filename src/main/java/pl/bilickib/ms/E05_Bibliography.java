package pl.bilickib.ms;

import pl.bilickib.ms.model.Blog;
import pl.bilickib.ms.model.Book;
import pl.bilickib.ms.model.Post;

public class E05_Bibliography {

    public static void main(String[] args) {
        new Book("Java Performance: The Definitive Guide").writtenBy("Scott Oaks")
                .published(2014)
        .keywords("JIT","java 7/8","garbage collection","performance-ORM","performance-web","threads");

        new Blog("Mechanical Sympathy").subtitle("Hardware and software working together in harmony")
                .url("http://mechanical-sympathy.blogspot.com/");

        new Post("Memory access timings").description("Latency Numbers Every Programmer Should Know")
                .url("https://gist.github.com/bartosz-bilicki/807beb188e76df534d08");

        new Post("The Java HotSpot Performance Engine Architecture")
                .url("http://www.oracle.com/technetwork/java/whitepaper-135217.html#64");
    }
}
