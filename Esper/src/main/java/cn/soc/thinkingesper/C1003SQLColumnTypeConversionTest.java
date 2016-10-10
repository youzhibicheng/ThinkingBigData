package cn.soc.thinkingesper;
  
import com.espertech.esper.client.Configuration;  
import com.espertech.esper.client.EPAdministrator;  
import com.espertech.esper.client.EPServiceProvider;  
import com.espertech.esper.client.EPServiceProviderManager;  
import com.espertech.esper.client.EPStatement;  
import com.espertech.esper.client.EventBean;

import cn.soc.thinkingesper.common.MySQLColumnTypeConvertor;

import java.util.Iterator;  
  
/** 
 * MySQLColumnTypeConvertor必须为public类，不然无法实例化。 Esper会为每一个EPL提供一个Convertor实例 
 *  
 * Created by Luonanqin on 2/9/14. 
 */

public class C1003SQLColumnTypeConversionTest {  
  
    public static void main(String[] args) throws InterruptedException {  
        Configuration config = new Configuration();  
        config.configure("esper.xml");  
        config.addVariable("vari", Integer.class, 1);  
        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider(config);  
  
        EPAdministrator admin = epService.getEPAdministrator();  
        // id=1, name="luonq"  
        String epl1 = "@Hook(type=HookType.SQLCOL, hook='" + MySQLColumnTypeConvertor.class.getName()  
                + "')select id, name from sql:test['select id, name from test1 where id=${vari}']";  
        System.out.println(epl1);  
        EPStatement state1 = admin.createEPL(epl1);  
  
        Iterator<EventBean> iter = state1.iterator();  
        while (iter.hasNext()) {  
            EventBean eventBean = iter.next();  
            System.out.println(eventBean.get("id") + " " + eventBean.get("name"));  
        }  
    }  
}  