package io.khasang.bazaar.service;

import io.khasang.bazaar.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    /**
     * Receive Feedback by id
     *
     * @param id - feedback's id what we want to receive
     * @return feedback
     */
    Feedback getById(Long id);

    /**
     * Create feedback at database
     *
     * @param feedback - feedback for creation
     * @return feedback
     */
    Feedback addFeedback(Feedback feedback);

    /**
     * Update feedback at database
     *
     * @param feedback - feedback to update
     * @return feedback
     */
    Feedback updateFeedback(Feedback feedback);

    /**
     * Receive all feedback records from database made by user specified by his id
     *
     * @param user_id - user's id
     * @return list of feedback records
     */
    List<Feedback> getFeedbacksByUser(Long user_id);

    /**
     * Receive all feedback records from database about some good item specified by it's id
     *
     * @param good_id - good's id
     * @return list of feedback records
     */
    List<Feedback> getFeedbacksByGood(Long good_id);

    /**
     * Receive average feedback rating for good item specified by it's id
     *
     * @param good_id - good's id
     * @return list of feedback records
     */
    Double getGoodAverageFeedbackRating(Long good_id);

    /**
     * Delete feedbacks from database
     *
     * @param id - feedbacks's id for delete
     * @return feedback
     */
    Feedback deleteFeedback (Long id);
}
