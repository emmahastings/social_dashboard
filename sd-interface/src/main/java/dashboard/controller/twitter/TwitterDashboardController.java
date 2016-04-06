package dashboard.controller.twitter;

import dashboard.component.AccountDetailsServiceFactory;
import dashboard.service.AccountDetailsService;
import model.SearchForm;
import model.TweetDetails;
import model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.social.twitter.api.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emmakhastings on 02/04/2016.
 *
 * @author emmakhastings
 *         <p>
 *         Controller to handle Twiiter functionality
 */
@Controller
@RequestMapping("/twitter_dashboard")
public class TwitterDashboardController {

    private Twitter twitter;

    private AccountDetailsServiceFactory accountDetailsServiceFactory;

    @Autowired
    public TwitterDashboardController(Twitter twitter, AccountDetailsServiceFactory accountDetailsServiceFactory) {
        this.twitter = twitter;
        this.accountDetailsServiceFactory = accountDetailsServiceFactory;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getDashboard(Model model) {
        model.addAttribute("searchForm", new SearchForm());
        return "twitter/twitter_dashboard";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String searchTwitter(@ModelAttribute SearchForm searchForm, Model model) {
        SearchParameters params = new SearchParameters(searchForm.getQuery());
        params.lang("en");

        SearchResults results = twitter.searchOperations().search(params);
        List<TweetDetails> tweets = new ArrayList<>();

        for (Tweet tweet : results.getTweets()) {
            TweetDetails tweetDetails = new TweetDetails();
            tweetDetails.setTweet(tweet.getText());
            tweetDetails.setUserName(tweet.getFromUser());
            tweetDetails.setUserUrl(tweet.getUser().getUrl());
            tweetDetails.setUserLocation(tweet.getUser().getLocation());
            tweets.add(tweetDetails);
        }

        model.addAttribute("searchForm", searchForm);
        model.addAttribute("tweets", tweets);
        return "twitter/twitter_dashboard";
    }

    @RequestMapping(value = "/user_details",
            produces = MediaType.TEXT_HTML_VALUE,
            method = RequestMethod.GET)
    public String getUserDetails(Model model) {
        AccountSettings accountSettings = twitter.userOperations().getAccountSettings();
        AccountDetailsService accountDetailsService = accountDetailsServiceFactory.getService("twitter");
        UserDetails twitterUserDetails = accountDetailsService.createUserDetails(accountSettings);
        model.addAttribute("twitterUserDetails", twitterUserDetails);
        return "twitter/user_details";
    }
}
