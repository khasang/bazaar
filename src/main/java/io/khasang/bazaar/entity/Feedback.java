package io.khasang.bazaar.entity;

import javax.persistence.*;

/**
 * This class describes a database entity that represents feedback made by users about goods they buy.
 *
 * @author Artem Kovalev
 */
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @Column(name="user_id")
    private Long user_id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "goods_id")
    private Goods good;

    @Column(name="rating")
    private Short feedbackRating;

    public Feedback() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feedback feedback = (Feedback) o;

        if (!id.equals(feedback.id)) return false;
        if (message != null ? !message.equals(feedback.message) : feedback.message != null) return false;
        if (user_id != null ? !user_id.equals(feedback.user_id) : feedback.user_id != null) return false;
        if (good != null ? !good.equals(feedback.good) : feedback.good != null) return false;
        return feedbackRating != null ? feedbackRating.equals(feedback.feedbackRating) : feedback.feedbackRating == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (user_id != null ? user_id.hashCode() : 0);
        result = 31 * result + (good != null ? good.hashCode() : 0);
        result = 31 * result + (feedbackRating != null ? feedbackRating.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Goods getGood() {
        return good;
    }

    public void setGoods(Goods good) {
        this.good = good;
    }

    public Short getFeedbackRating() {
        return feedbackRating;
    }

    public void setFeedbackRating(Short feedbackRating) {
        this.feedbackRating = feedbackRating;
    }
}
