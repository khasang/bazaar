package io.khasang.bazaar.dao;

import io.khasang.bazaar.entity.Feedback;

import java.util.List;

public interface FeedbackDao extends  BasicDao<Feedback> {
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
}
