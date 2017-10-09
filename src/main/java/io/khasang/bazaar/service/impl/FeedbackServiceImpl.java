package io.khasang.bazaar.service.impl;

import io.khasang.bazaar.dao.FeedbackDao;
import io.khasang.bazaar.entity.Feedback;
import io.khasang.bazaar.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackDao feedbackDao;

    @Override
    public Feedback getById(Long id) {
        return feedbackDao.getById(id);
    }

    @Override
    public Feedback addFeedback(Feedback feedback) {
        return feedbackDao.add(feedback);
    }

    @Override
    public Feedback updateFeedback(Feedback feedback) {
        return feedbackDao.update(feedback);
    }

    @Override
    public List<Feedback> getFeedbacksByUser(Long user_id) {
        return feedbackDao.getFeedbacksByUser(user_id);
    }

    @Override
    public List<Feedback> getFeedbacksByGood(Long good_id) {
        return feedbackDao.getFeedbacksByGood(good_id);
    }

    @Override
    public Double getGoodAverageFeedbackRating(Long good_id) {
        return feedbackDao.getGoodAverageFeedbackRating(good_id);
    }

    @Override
    public Feedback deleteFeedback(Long id) {
        Feedback feedback = getById(id);
        return feedbackDao.delete(feedback);
    }
}
