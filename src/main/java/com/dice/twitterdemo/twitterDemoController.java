package com.dice.twitterdemo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.parser.Entity;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@RequestMapping("/dice/twitterdemo")
public class twitterDemoController {
    String AccessToken="Your aceess token";
    //api2->get tweets for the selected user.
    @GetMapping("/getUserTweeets/{userId}")
    public String getTweetsOfTheUser(@PathVariable Long userId){
        RestTemplate restTemplate=new RestTemplate();
        String uri="https://api.twitter.com/2/users/%s/tweets";
        HttpHeaders headers=new HttpHeaders();
        headers.add("Authorization","Bearer "+AccessToken);
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(String.format(uri,userId),String.class,headers);
        String tweetResponse = "Given User does not have any tweet";
        if (null != responseEntity) {
             tweetResponse = responseEntity.toString();
        }
        return tweetResponse;
    }
    //api1->get tweet user
    @GetMapping("/getTweetUsers/{tweetID}")
    public String getTweetUsers(@PathVariable Long tweetID) {

        // Set the params values
        // For full list of params visit - https://github.com/twitterdev/twitter-api-java-sdk/blob/main/docs/TweetsApi.md#findTweetById
        Set<String> expansions = new HashSet<>(Arrays.asList("author_id"));
        //Set<String> mediaFields = new HashSet<>(Arrays.asList("type", "duration_ms"));

        try {
            SingleTweetLookupResponse result = apiInstance.tweets().findTweetById(tweetID, expansions, null, null, null, null, null);
         //   System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling TweetsApi#findTweetById");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
        return null;
    }
    }
}
