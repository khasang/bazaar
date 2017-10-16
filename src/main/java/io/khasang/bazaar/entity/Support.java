package io.khasang.bazaar.entity;


import javax.persistence.*;

@Entity
@Table(name = "support")
public class Support {

    public Support() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String response;
    private Boolean isClose;
    private Long countRequests;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Support support = (Support) o;

        if (id != null ? !id.equals(support.id) : support.id != null) return false;
        if (question != null ? !question.equals(support.question) : support.question != null) return false;
        if (response != null ? !response.equals(support.response) : support.response != null) return false;
        if (isClose != null ? !isClose.equals(support.isClose) : support.isClose != null) return false;
        return countRequests != null ? countRequests.equals(support.countRequests) : support.countRequests == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (response != null ? response.hashCode() : 0);
        result = 31 * result + (isClose != null ? isClose.hashCode() : 0);
        result = 31 * result + (countRequests != null ? countRequests.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Boolean getClose() {
        return isClose;
    }

    public void setClose(Boolean close) {
        isClose = close;
    }

    public Long getCountRequests() {
        return countRequests;
    }

    public void setCountRequests(Long countRequests) {
        this.countRequests = countRequests;
    }
}