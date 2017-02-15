package com.cmdev.reelmarkets.model;

import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Vishal on 2/6/2017.
 */

public class Poll {

    public enum PollType { SERIES, SEASON, EPISODE };

    private PollType pollType;

    private int pid;
    private String pollName, genre;
    private String pollAuthor;
    private String startDate, endDate;

    private String showName;
    private int season;
    private int episode;

    private int series_id;
    private List<String> options;

    /**
     * Constructor with defailt poll type as series
     * @param pollName
     * @param pollAuthor
     * @param startDate
     * @param endDate
     */
    public Poll(String pollName, int pid, String pollAuthor, String startDate, String endDate){
        new Poll(pollName, pid, pollAuthor, startDate, endDate, PollType.SERIES, options);
    }

    public Poll(String pollName, int pid, String pollAuthor, String startDate, String endDate, PollType pollType, List<String> options){
        this.pollName = pollName;
        this.pid = pid;
        this.pollAuthor = pollAuthor;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pollType = pollType;
        this.options = options;

    }

    public PollType getPollType() {
        return pollType;
    }

    public void setPollType(PollType pollType) {
        this.pollType = pollType;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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

    public void setEpisode(int episode) { this.episode = episode; }

    public int getSeries_id() { return series_id; }

    public void setSeries_id(int series_id) { this.series_id = series_id; }

    public List<String> getOptions() { return options; }

    public void setOptions(List<String> options) { this.options = options; }

}
