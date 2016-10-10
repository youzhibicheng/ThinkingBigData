package cn.soc.thinkingesper;

import java.util.Arrays;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
  
import com.espertech.esper.client.EPAdministrator;  
import com.espertech.esper.client.EPServiceProvider;  
import com.espertech.esper.client.EPServiceProviderManager;  
import com.espertech.esper.client.EventType;  
  
// 另外对于Map，Esper只支持增量更新，也就是说只能增加Map中的属性定义，而不能修改或者删除某个属性
//（实际上属性增多并不影响其处理性能，所以没有删除在我看来也没什么。至于修改，也只能是先注销再注册了
public class C0206PersonMap  
{  
    public static void main(String[] args)  
    {  
        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();  
        EPAdministrator admin = epService.getEPAdministrator();  
  
        // Address定义  
        Map<String, Object> address = new HashMap<String, Object>();  
        address.put("road", String.class);  
        address.put("street", String.class);  
        address.put("houseNo", int.class);  
  
        // Person定义  
        Map<String, Object> person = new HashMap<String, Object>();  
        person.put("name", String.class);  
        person.put("age", int.class);  
        person.put("children", List.class);  
        person.put("phones", Map.class);  
        person.put("address", "Address");  
  
        // 注册Address到Esper  
        admin.getConfiguration().addEventType("Address", address);  
        // 注册Person到Esper  
        admin.getConfiguration().addEventType("Person", person);  
  
        // 新增一个gender属性  
        person.put("gender", int.class);  
        admin.getConfiguration().updateMapEventType("Person", person);  
          
        /** 输出结果： 
         * Person props: [address, age, name, children, phones, gender] 
         */  
        EventType event = admin.getConfiguration().getEventType("Person");  
        System.out.println("Person props: " + Arrays.asList(event.getPropertyNames()));  
    }  
}  
