package com.gantang.common.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 给list排序
 * @author Abner
 *
 */
public class ListSortUtil {

	/**
	 * 由小到大
	 * @param list
	 * @param key
	 * @return
	 */
	public static List<Map<String, Object>> getListSortAsc(List<Map<String, Object>> list,String key){
		Collections.sort(list, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Integer name1 = Integer.valueOf(o1.get(key).toString()) ;//name1是从你list里面拿出来的一个 
                Integer name2 = Integer.valueOf(o2.get(key).toString()) ; //name1是从你list里面拿出来的第二个name
                return name1.compareTo(name2);
            }
        });
		return list;
	}
	
	/**
	 * 由大到小
	 * @param list
	 * @param key
	 * @return
	 */
	public static List<Map<String, Object>> getListSortDesc(List<Map<String, Object>> list,String key){
		Collections.sort(list, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Integer name1 = Integer.valueOf(o1.get(key).toString()) ;//name1是从你list里面拿出来的一个 
                Integer name2 = Integer.valueOf(o2.get(key).toString()) ; //name1是从你list里面拿出来的第二个name
                return name2.compareTo(name1);
            }
        });
		return list;
	}
}
