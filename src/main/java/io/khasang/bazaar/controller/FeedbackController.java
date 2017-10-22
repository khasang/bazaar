package io.khasang.bazaar.controller;

import io.khasang.bazaar.entity.Feedback;
import io.khasang.bazaar.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Feedback getFeedbackById(@PathVariable(value = "id") String id) {
        return feedbackService.getById(Long.parseLong(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Feedback addFeedback(@RequestBody Feedback feedback) {
        return feedbackService.addFeedback(feedback);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Feedback updateFeedback(@RequestBody Feedback feedback) {
        return feedbackService.updateFeedback(feedback);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Feedback deleteFeedback(@RequestParam(value = "id") String id){
        return feedbackService.deleteFeedback(Long.parseLong(id));
    }

    @RequestMapping(value = "/get/byuser/{user_id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Feedback> getFeedbacksByUser(@PathVariable(value = "user_id") String user_id){
        return feedbackService.getFeedbacksByUser(Long.parseLong(user_id));
    }

    @RequestMapping(value = "/get/bygood/{good_id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Feedback> getFeedbacksByGood(@PathVariable(value = "good_id") String good_id){
        return feedbackService.getFeedbacksByGood(Long.parseLong(good_id));
    }

    @RequestMapping(value = "/get/avgratingbygood/{good_id}", method = RequestMethod.GET)
    @ResponseBody
    public Double getGoodAverageFeedbackRating(@PathVariable(value = "good_id") String good_id){
        return feedbackService.getGoodAverageFeedbackRating(Long.parseLong(good_id));
    }
}
