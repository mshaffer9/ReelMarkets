package com.cmdev.reelmarkets.model;

import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by Vishal on 2/6/2017.
 */

public class Poll {

    public enum PollType { SERIES, SEASON, EPISODE };

    private PollType pollType;
    private int currency;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    private int pid;
    private String pollName, genre;
    private String pollAuthor;
    private String startDate, endDate;

    private String showName;
    private int season, episode;

    /**
     * Constructor with defailt poll type as series
     * @param pollName
     * @param pollAuthor
     * @param currency
     * @param startDate
     * @param endDate
     */
    public Poll(String pollName, String pollAuthor, int currency, String startDate, String endDate){
        new Poll(pollName, pollAuthor, currency, startDate, endDate, PollType.SERIES);
    }

    public Poll(String pollName, String pollAuthor, int currency, String startDate, String endDate, PollType pollType){
        this.pollName = pollName;
        this.pollAuthor = pollAuthor;

    }

    public PollType getPollType() {
        return pollType;
    }

    public void setPollType(PollType pollType) {
        this.pollType = pollType;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public String getPollName() {
        return pollName;
    }

    public void setPollName(String pollName) {
        this.pollName = pollName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPollAuthor() {
        return pollAuthor;
    }

    public void setPollAuthor(String pollAuthor) {
        this.pollAuthor = pollAuthor;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }



}
