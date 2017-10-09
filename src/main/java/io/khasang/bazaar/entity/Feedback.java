package io.khasang.bazaar.entity;

import javax.persistence.*;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @Column(name="user_id")
    private Long user_id;

    @Column(name="good_id")
    private Long good_id;

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
        if (good_id != null ? !good_id.equals(feedback.good_id) : feedback.good_id != null) return false;
        return feedbackRating != null ? feedbackRating.equals(feedback.feedbackRating) : feedback.feedbackRating == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (user_id != null ? user_id.hashCode() : 0);
        result = 31 * result + (good_id != null ? good_id.hashCode() : 0);
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

    public Long getGood_id() {
        return good_id;
    }

    public void setGood_id(Long good_id) {
        this.good_id = good_id;
    }

    public Short getFeedbackRating() {
        return feedbackRating;
    }

    public void setFeedbackRating(Short feedbackRating) {
        this.feedbackRating = feedbackRating;
    }
}
