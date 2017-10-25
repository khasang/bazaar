package io.khasang.bazaar.dto;

import org.springframework.stereotype.Component;

@Component
public class NewsTagDTO {
    private Long id;
    private String name;


    public void setName(String name) {
        this.name = name;
    }
}
