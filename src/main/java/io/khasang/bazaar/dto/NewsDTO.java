package io.khasang.bazaar.dto;

import io.khasang.bazaar.entity.EmployeeEntity;
import io.khasang.bazaar.entity.News;
import io.khasang.bazaar.entity.NewsCategory;
import io.khasang.bazaar.entity.NewsTag;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class NewsDTO {
    private Long id;
    private String title;
    private String body;
    private NewsCategory newsCategory;
    private Set<NewsTagDTO> newsTagDTOSet = new HashSet<>();

    public NewsDTO getNewsTagDTOSet(News news) {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(news.getId());
        newsDTO.setTitle(news.getTitle());
        newsDTO.setBody(news.getBody());

        for (NewsTag newsTag : news.getNewsTagList()) {
            NewsTagDTO newsTagDTO = new NewsTagDTO();
            newsTagDTO.setName(newsTag.getName());
            newsTagDTOSet.add(newsTagDTO);
        }

        newsDTO.setNewsTagDTOSet(newsTagDTOSet);

        return newsDTO;
    }

    public void setNewsTagDTOSet(Set<NewsTagDTO> newsTagDTOSet) {
        this.newsTagDTOSet = newsTagDTOSet;
    }


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

}
