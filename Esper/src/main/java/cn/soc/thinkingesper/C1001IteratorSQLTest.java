package cn.soc.thinkingesper;

import com.espertech.esper.client.Configuration;  
import com.espertech.esper.client.EPAdministrator;  
import com.espertech.esper.client.EPRuntime;  
import com.espertech.esper.client.EPServiceProvider;  
import com.espertech.esper.client.EPServiceProviderManager;  
import com.espertech.esper.client.EPStatement;  
import com.espertech.esper.client.EventBean;  
  
import java.util.Iterator;  
  
/** 
 * Created by Luonanqin on 4/17/14. 
 */  
public class C1001IteratorSQLTest {  
  
    public static void main(String[] args) throws InterruptedException {  
        Configuration config = new Configuration();  
        config.configure("esper.xml");  
        config.addVariable("vari", Integer.class, 2);  
        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider(config);  
  
        EPAdministrator admin = epService.getEPAdministrator();  
        EPRuntime runtime = epService.getEPRuntime();  
        // id=1, name="luonq"
        // create table test1(id int, name varchar(32));
        // insert into test1(id, name) values (1, 'JamesZOU');
        // insert into test1(id, name) values (2, 'GraceLI');
        String epl1 = "select id, name from sql:test['select id, name from webmanage.test1 where id=${vari}']";  
  
        EPStatement state = admin.createEPL(epl1);  
  
        Iterator<EventBean> iter = state.iterator(); // 也可以调用safeIterator方法，该方法以线程安全方式查询DB  
        while (iter.hasNext()) {  
            EventBean eventBean = iter.next();  
            System.out.println(eventBean.get("id") + " " + eventBean.get("name"));  
        }  
    }  
}  