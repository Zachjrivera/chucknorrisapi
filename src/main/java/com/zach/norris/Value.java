package com.zach.norris;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class Value {
    private static final Logger log = LoggerFactory.getLogger(Value.class);

    private Long id;
    private String joke;
    private ArrayList<String> catigories;

    public Value() {
    }

    public ArrayList<String> getcatigories() {
        return catigories;
    }

    public void setcatigories(ArrayList<String> catigories) {
        this.catigories = catigories;
    }

    public Long getId() {
        return this.id;
    }

    public String getjoke() {
        return this.joke;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setjoke(String joke) {
        this.joke = joke;
    }

    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", joke='" + joke + '\'' +
                ", catigories=" + catigories +
                '}';
    }

@Scheduled(fixedRate = 5000)
    public void jokes(){
        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        log.info(quote.toString());
    }


}