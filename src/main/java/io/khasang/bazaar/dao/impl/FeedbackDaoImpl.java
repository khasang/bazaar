package io.khasang.bazaar.dao.impl;

import io.khasang.bazaar.dao.FeedbackDao;
import io.khasang.bazaar.entity.Feedback;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class FeedbackDaoImpl extends BasicDaoImpl<Feedback> implements FeedbackDao{
    public FeedbackDaoImpl(Class<Feedback> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Feedback> getFeedbacksByUser(Long user_id) {
        List<Feedback> results = getFeedbackSelectQuery("user_id", user_id).getResultList();
        return results;
    }

    @Override
    public List<Feedback> getFeedbacksByGood(Long good_id) {
        List<Feedback> results = getFeedbackSelectQuery("good_id", good_id).getResultList();
        return results;
    }

    @Override
    public Double getGoodAverageFeedbackRating(Long good_id) {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Feedback> criteriaQuery = builder.createQuery(Feedback.class);
        Root feedback= criteriaQuery.from(Feedback.class);
        criteriaQuery.select(builder.avg(feedback.get("rating")));
        Predicate where = builder.equal(feedback.get("good_id"),good_id);
        criteriaQuery.where(where);
        Query query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
        Double result = (Double)query.getSingleResult();
        return result;
    }

    private Query getFeedbackSelectQuery(String idName, Long idVal) {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Feedback> criteriaQuery = builder.createQuery(Feedback.class);
        Root<Feedback> root = criteriaQuery.from(Feedback.class);
        criteriaQuery.select(root);
        Predicate where = builder.equal(root.get(idName),idVal);
        criteriaQuery.where(where);
        return sessionFactory.getCurrentSession().createQuery(criteriaQuery);
    }
}