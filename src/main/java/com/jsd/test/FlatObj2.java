package com.jsd.t;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 拍平对象
 */
public class FlatObj2 {
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
     *
     *  { ‘A’: 1, ‘B.A’: 2, ‘B.B’: 3, ‘CC.D.E’: 4, ‘CC.D.F’: 5}.
     */
    /**
     * 拍扁对象
     * @param args
     */
    public static void main(String[] args) {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        LinkedHashMap<String, Object> linkedHashMapResult = new LinkedHashMap<>();
        linkedHashMap.put("A",1);
        LinkedHashMap<String, Object> linkedHashMapB = new LinkedHashMap<>();
        linkedHashMapB.put("A",2);
        linkedHashMapB.put("B",3);
        linkedHashMap.put("B",linkedHashMapB);
        LinkedHashMap<String, Object> linkedHashMapCC = new LinkedHashMap<>();
        LinkedHashMap<String, Object> linkedHashMapD = new LinkedHashMap<>();
        linkedHashMapD.put("E",4);
        linkedHashMapD.put("F",5);
        linkedHashMapCC.put("D",linkedHashMapD);
        linkedHashMap.put("CC",linkedHashMapCC);


        List<String> keys = new ArrayList<>();
        int mapKeysNum = 0 ;
        linkedHashMap.forEach((k,v) -> flat(k,v,linkedHashMapResult,keys,mapKeysNum));
        System.out.println(linkedHashMapResult);
    }

    /**
     *
     * @param k 键
     * @param v 值
     * @param linkedHashMapResult 结果集
     * @param keys 键的缓存列表
     * @param mapKeysNum 同级键的数量
     */
    private static void flat(String k, Object v,LinkedHashMap<String, Object> linkedHashMapResult,List<String> keys,Integer mapKeysNum) {
        if (v instanceof Map){
            keys.add(k);
            mapKeysNum = ((Map<String,Object>) v).size();
            int num2 = mapKeysNum;
            ((Map<String,Object>) v).forEach((k2,v2) -> flat(k2,v2,linkedHashMapResult,keys,num2));
        }else {
            int size = keys.size();
            if (size>0){
                String[] keysArr = keys.toArray(new String[keys.size()]);
                String join = String.join(".", keysArr) ;
                linkedHashMapResult.put("‘"+join+ "." + k+"’",v);
                if (size==mapKeysNum){
                    keys.clear();
                }
            }else {
                linkedHashMapResult.put("‘"+k+"’",v);
            }
        }
    }


}
