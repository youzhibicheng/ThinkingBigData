package cn.soc.thinkingesper;

import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
  
import com.espertech.esper.client.EPAdministrator;  
import com.espertech.esper.client.EPServiceProvider;  
import com.espertech.esper.client.EPServiceProviderManager;  

// Map对于嵌套类的定义比较特别。如果嵌套的类是POJO，那就如上面所示。如果嵌套的还是Map，那么定义方式就需要改变。我们为Person加上Address
// 如果Person有多个Address，则以数组方式定义Person的多个Address时，代码又变成下面的样子了
// person.put("addresses", "Address[]"); 

public class C0205PersonMap  
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
    }  
}  