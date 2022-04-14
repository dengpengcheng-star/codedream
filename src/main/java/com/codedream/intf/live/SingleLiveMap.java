package com.codedream.intf.live;

/**
 * @author DengPengcheng
 * @create 2022/4/1412:57
 */
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;



public class SingleLiveMap {
    private static class SingletonHolder {
        private static final SingleLiveMap INSTANCE = new SingleLiveMap();
    }
    private SingleLiveMap (){}
    public static final SingleLiveMap getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private Map<Integer, Set<Integer>> map=new ConcurrentHashMap<>();
    public synchronized void startLive(int lid){
        map.put(lid,new HashSet<>());
    }
    public synchronized int updateLive(int lid,int uid,boolean up){
        if(map.containsKey(lid)){
            Set<Integer> uSet=map.get(lid);
            if(up){
                uSet.add(uid);
            }else {
                uSet.remove(uid);
            }
            return uSet.size();
        }else {
            return -1;
        }
    }
    public Set<Integer> getOnlineUsers(int lid){
        return map.getOrDefault(lid, null);
    }
}



