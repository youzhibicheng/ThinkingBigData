package cn.soc.thinkingesper;

import com.espertech.esper.client.EPAdministrator;  
import com.espertech.esper.client.EPRuntime;  
import com.espertech.esper.client.EPServiceProvider;  
import com.espertech.esper.client.EPServiceProviderManager;  
import com.espertech.esper.client.EPStatement;  
import com.espertech.esper.client.EventBean;  
import com.espertech.esper.client.UpdateListener;  
import cn.soc.thinkingesper.common.*;
  
class SomeAnnotationListener implements UpdateListener  
{  
    public void update(EventBean[] newEvents, EventBean[] oldEvents)  
    {  
        if (newEvents != null)  
        {  
            EventBean event = newEvents[0];  
            // 当加上注解@EventRepresentation(array=true)时，结果事件类型为数组而不是Map。  
            // array=false时，也就是默认情况，结果事件类型为数组是Map。  
            System.out.println("Sum Price: " + event.get("sum(price)") + ", Event Type is " + event.getEventType().getUnderlyingType());  
        }  
    }  
}  
  
public class C0502SomeAnnotationTest  
{  
    public static void main(String[] args) throws InterruptedException  
    {  
        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();  
  
        EPAdministrator admin = epService.getEPAdministrator();  
  
        String appCls = Apple.class.getName();  
        //@Priority(10)
        //@EventRepresentation(array=true)
        String epl1 = "@Priority(10)@EventRepresentation(array=true) select sum(price) from " + appCls + ".win:length_batch(2)"; 
        //@Name(\"EPL2\")
        String epl2 = "@Name(\"EPL2\")select sum(price) from " + appCls + ".win:length_batch(2)";
        //@Drop 指定事件经过此EPL后不再参与其他的EPL计算，该注解无参数。
        String epl3 = "@Drop select sum(price) from " + appCls + ".win:length_batch(2)";  
  
        UpdateListener listenenr = new SomeAnnotationListener();  
        EPStatement state1 = admin.createEPL(epl1);  
        state1.addListener(listenenr);  
        EPStatement state2 = admin.createEPL(epl2);
        state2.addListener(listenenr);  
        System.out.println("epl2's name is " + state2.getName());  
        EPStatement state3 = admin.createEPL(epl3);  
        state3.addListener(listenenr);  
  
        EPRuntime runtime = epService.getEPRuntime();
  
        Apple a1 = new Apple();  
        a1.setPrice(1);  
        runtime.sendEvent(a1);  
  
        Apple a2 = new Apple();  
        a2.setPrice(2);  
        runtime.sendEvent(a2);  
    }  
}  