package io.khasang.bazaar.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private NewsCategory newsCategory;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<NewsTag> newsTagList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCategory(NewsCategory category) {
        this.newsCategory = category;
    }

    public void addTag(NewsTag newsTag) {
        newsTagList.add(newsTag);
    }

    public List<NewsTag> getNewsTagList() {
        return newsTagList;
    }

    public News() {
    }
}
