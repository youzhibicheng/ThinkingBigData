package cn.soc.thinkingesper;

import java.util.HashMap;  
import java.util.Map;  
  
/** 
 * 返回Map类型的外部数据 
 * 
 * Created by Luonanqin on 2/16/14. 
 */  
public class C1102InvocationMethodMap {  
  
    public static Map<String, Object> getMapObject() {  
        Map<String, Object> map = new HashMap<String, Object>();  
        map.put("name", "mapObject1");  
        map.put("size", 1);  
  
        return map;  
    }  
  
    public static Map<String, Class> getMapObjectMetadata() {  
        Map<String, Class> map = new HashMap<String, Class>();  
        map.put("name", String.class);  
        map.put("size", int.class);  
  
        return map;  
    }  
}  
  
