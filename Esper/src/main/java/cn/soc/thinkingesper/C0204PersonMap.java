package cn.soc.thinkingesper;

import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
  
import com.espertech.esper.client.EPAdministrator;  
import com.espertech.esper.client.EPServiceProvider;  
import com.espertech.esper.client.EPServiceProviderManager;  
  

// 使用 Map 作为 事件类型
// Esper支持原生Java Map结构的事件。相对于POJO来说，Map的结构更利于事件类型的热加载，毕竟不是class，所以不需要重启JVM。
// 所以如果系统对重启比较敏感，建议使用Map来定义事件的结构。
// Map的结构很简单，主要分为事件定义名和事件属性列表
// select age,children from Person where name="luonanqin" 
public class C0204PersonMap  
{  
    public static void main(String[] args)  
    {  
        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();  
        EPAdministrator admin = epService.getEPAdministrator();  
          
        // Person定义  
        Map<String,Object> person = new HashMap<String,Object>();  
        person.put("name", String.class);  
        person.put("age", int.class);  
        person.put("children", List.class);  
        person.put("phones", Map.class);  
          
        // 注册Person到Esper  
        admin.getConfiguration().addEventType("Person", person);  
    }
}
