package com.jsd.asyncUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AsyncTaskInfo {
    
    private List<Info> list;
    
    public AsyncTaskInfo() {
        list = new ArrayList<>();
    }
    
    public List<Info> getList() {
        return list;
    }
    
    public void setList(List<Info> list) {
        this.list = list;
    }
    
    
    @Override
    public String toString() {
        return "AsyncTaskInfo [list=" + list + "]";
    }
    
    static Info fromEntry(Map.Entry<String, Long> entry) {
        return new Info()
                .setKey(entry.getKey())
                .setCount(entry.getValue().intValue());
    }
    
    public static class Info {
        
        private String key;
        
        private int count;
        
        private List<Info> list;
        
        public Info() {
            list = new ArrayList<> ();
        }
        
        public String getKey() {
            return key;
        }
        
        public Info setKey(String key) {
            this.key = key;
            return this;
        }
        
        public int getCount() {
            return count;
        }
        
        public Info setCount(int count) {
            this.count = count;
            return this;
        }
        
        public List<Info> getList() {
            return list;
        }
        
        public Info setList(List<Info> list) {
            this.list = list;
            return this;
        }
        
        @Override
        public String toString() {
            return "Info [key=" + key + ", count=" + count + ", list=" + list + "]";
        }
        
    }
    
}

