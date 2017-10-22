package io.khasang.bazaar.entity;

import javax.persistence.*;

@Entity
@Table(name = "news_categories")
public class NewsCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "cat_id")
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    public NewsCategory(String name) {
        this.name = name;
    }

    public NewsCategory() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
