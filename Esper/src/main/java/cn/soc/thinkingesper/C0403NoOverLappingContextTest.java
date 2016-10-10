package cn.soc.thinkingesper;

import com.espertech.esper.client.EPAdministrator;  
import com.espertech.esper.client.EPRuntime;  
import com.espertech.esper.client.EPServiceProvider;  
import com.espertech.esper.client.EPServiceProviderManager;  
import com.espertech.esper.client.EPStatement;  
import com.espertech.esper.client.EventBean;  
import com.espertech.esper.client.UpdateListener;

import cn.soc.thinkingesper.common.EndEvent;
import cn.soc.thinkingesper.common.OtherEvent;
import cn.soc.thinkingesper.common.StartEvent;

//这类Context有个特点，是由开始和结束两个条件构成context
class NoOverLappingContextListener implements UpdateListener  
{  
  
    public void update(EventBean[] newEvents, EventBean[] oldEvents)  
    {  
        if (newEvents != null)  
        {  
        	for(EventBean event : newEvents){  
        		System.out.println("Class:" + event.getUnderlying().getClass().getName() + ", id:" + event.get("id"));  
        	}
        }  
    }  
}  
  
public class C0403NoOverLappingContextTest  
{  
    public static void main(String[] args)  
    {  
        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();  
        EPAdministrator admin = epService.getEPAdministrator();  
        EPRuntime runtime = epService.getEPRuntime();  
  
        String start = StartEvent.class.getName();  
        String end = EndEvent.class.getName();  
        String other = OtherEvent.class.getName();  
        // 以StartEvent事件作为开始条件，EndEvent事件作为结束条件  
        String epl1 = "create context NoOverLapping start " + start + " end " + end;  
        String epl2 = "context NoOverLapping select * from " + other;  
  
        admin.createEPL(epl1);  
        EPStatement state = admin.createEPL(epl2);  
        state.addListener(new NoOverLappingContextListener());  
  
        StartEvent s = new StartEvent();  
        System.out.println("sendEvent: StartEvent");  
        runtime.sendEvent(s);  
  
        OtherEvent o = new OtherEvent();  
        o.setId(2);  
        System.out.println("sendEvent: OtherEvent");  
        runtime.sendEvent(o);  
  
        EndEvent e = new EndEvent();  
        System.out.println("sendEvent: EndEvent");  
        runtime.sendEvent(e);  
  
        OtherEvent o2 = new OtherEvent();  
        o2.setId(4);  
        System.out.println("sendEvent: OtherEvent");  
        runtime.sendEvent(o2);  
    }
}  