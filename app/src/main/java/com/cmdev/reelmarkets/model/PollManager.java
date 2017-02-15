package com.cmdev.reelmarkets.model;

import java.util.HashMap;

/**
 * Created by Vishal on 2/10/2017.
 */

public class PollManager {

    private static HashMap<Integer, Poll> pollMap;

    private static void initMap() {
        pollMap = new HashMap<>();
        // add some default polls here
    }

    public static void addNewPoll(Poll poll)
    {
        if (pollMap == null) {
            initMap();
        }
        pollMap.put( poll.getPid(), poll);
    }

    public static void removePoll(int pid)
    {
        if (pollMap == null) {
            initMap();
        }
        pollMap.remove(pid);
    }

    public static Poll getPoll(int pid)
    {
        if (pollMap == null) {
            initMap();
        }
        if (isExistingPoll(pid))
        {
            return pollMap.get(pid);
        } else
        {
            return null;
        }
    }

    public static boolean isExistingPoll(int pid)
    {
        if (pollMap == null) {
            initMap();
        }
        return pollMap.containsKey(pid);
    }

}
