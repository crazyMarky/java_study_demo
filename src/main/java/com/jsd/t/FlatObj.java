package com.jsd.t;

import java.util.*;

/**
 * 拍扁对象
 */
public class FlatObj {

    private static class Obj{
        List<Map<String,Object>> list;

        public List<Map<String, Object>> getList() {
            return list;
        }

        public void setList(List<Map<String, Object>> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return "Obj{" +
                    "list=" + list +
                    '}';
        }
    }

    public static void main(String[] args) {
        /**
         * {
         *   ‘A’: 1,
         *   ‘B’: {
         *     ‘A’: 2,
         *     ‘B’: 3
         *   },
         *   ‘CC’: {
         *     ‘D’: {
         *       ‘E’: 4,
         *       ‘F’: 5
         *     }
         *   }
         * }
         */
        Obj obj = new Obj();
        List<Map<String,Object>> list = new ArrayList<>();
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("A", 1);
        list.add(objectObjectHashMap);
        HashMap<String, Object> objectObjectHashMap2 = new HashMap<>();
        Obj objB = new Obj();
        HashMap<String, Object> objectObjectHashMap3 = new HashMap<>();
        List<Map<String,Object>> list2 = new ArrayList<>();
        objectObjectHashMap3.put("A",2);
        objectObjectHashMap3.put("B",3);
        list2.add(objectObjectHashMap3);
        objB.setList(list2);
        objectObjectHashMap2.put("B",objB);
        list.add(objectObjectHashMap2);
        Obj objCC = new Obj();
        HashMap<String, Object> objectObjectHashMap4 = new HashMap<>();
        objectObjectHashMap4.put("CC",objCC);
        List<Map<String,Object>> list3 = new ArrayList<>();
        HashMap<String, Object> objectObjectHashMap5 = new HashMap<>();

        list.add(objectObjectHashMap4);
        obj.setList(list);

        System.out.println(obj);
    }

}
