package cn.soc.thinkingesper;

import com.espertech.esper.client.EPAdministrator;  
import com.espertech.esper.client.EPRuntime;  
import com.espertech.esper.client.EPServiceProvider;  
import com.espertech.esper.client.EPServiceProviderManager;  
import com.espertech.esper.client.EPStatement;  
import com.espertech.esper.client.EventBean;  
import com.espertech.esper.client.UpdateListener;  
import cn.soc.thinkingesper.common.*;

/** 
 * 可用cast函数将其他的数值数据类型转为BigDecimal。 
 * EPL语法
[annotations]
[expression_declarations]
[context context_name]
[insert into insert_into_def]
select select_list
from stream_def [as name] [, stream_def [as name]] [,...]
[where search_conditions]
[group by grouping_expression_list]
[having grouping_search_conditions]
[output output_specification]
[order by order_by_expression_list]
[limit num_rows]
 * @author luonanqin 
 * 
 */

class CastDataTypeListener implements UpdateListener  
{  
    public void update(EventBean[] newEvents, EventBean[] oldEvents)  
    {  
        if (newEvents != null)  
        {  
            EventBean event = newEvents[0];  
            // cast(avg(price), int)中间的空格在EPL中可以不写，但是event.get的时候必须加上，建议用as一个别名来代表转换后的值  
            System.out.println("Average Price: " + event.get("cast(avg(price),int)") + ", DataType is "  
                    + event.get("cast(avg(price),int)").getClass().getName());  
        }  
    }  
}  
  
class CastDataTypeListener2 implements UpdateListener  
{  
    public void update(EventBean[] newEvents, EventBean[] oldEvents)  
    {  
        if (newEvents != null)  
        {  
            EventBean event = newEvents[0];  
            System.out.println("Average Price: " + event.get("avg(price)") + ", DataType is " + event.get("avg(price)").getClass().getName());  
        }  
    }  
}  
  
public class C0501CastDataTypeTest  
{  
    public static void main(String[] args) throws InterruptedException  
    {  
        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();  
  
        EPAdministrator admin = epService.getEPAdministrator();  
  
        String app = Apple.class.getName();  
        String epl1 = "select cast(avg(price),int) from " + app + ".win:length_batch(2)";  
        String epl2 = "select avg(price) from " + app + ".win:length_batch(2)";  
  
        EPStatement state1 = admin.createEPL(epl1);  
        state1.addListener(new CastDataTypeListener());  
        EPStatement state2 = admin.createEPL(epl2);  
        state2.addListener(new CastDataTypeListener2());  
  
        EPRuntime runtime = epService.getEPRuntime();  
  
        Apple b1 = new Apple();  
        b1.setPrice(1);  
        runtime.sendEvent(b1);  
  
        Apple b2 = new Apple();  
        b2.setPrice(2);  
        runtime.sendEvent(b2);  
    }  
}  
