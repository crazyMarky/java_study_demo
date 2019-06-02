package com.jsd.collection.hashmap;

import java.util.*;

/**
 * HashMap遍历方式总结
 */
public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap();
        map.put("name","张三");
        map.put("age",20);
        map.put("sex","man");
        System.out.println("方式一：直接输出map");
        System.out.println(map);
        System.out.println("方式二：keySet()获得map所有键和值");
        Set<String> strings = map.keySet();

        for (String s:strings){
            System.out.print("键："+s);
            System.out.println("值："+map.get(s));
        }

        System.out.println("方式三：values获取所有的值");
        Collection<Object> values = map.values();
        for (Object v :values){
            System.out.println(v);
        }

        System.out.println("方式四：Map内部类EntrySet");
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Object> entry = iterator.next();
            System.out.print("键："+entry.getKey());
            System.out.println("值："+entry.getValue());
        }
        System.out.println("方式五：JDK8Stream表达式");
        map.forEach((k,v)->{
            System.out.println(k);
            System.out.println(v);
        });

        LinkedList<Object> objects = new LinkedList<>();
    }
}
