package cn.soc.thinkingesper;

import com.espertech.esper.client.EPAdministrator;  
import com.espertech.esper.client.EPRuntime;  
import com.espertech.esper.client.EPServiceProvider;  
import com.espertech.esper.client.EPServiceProviderManager;  
import com.espertech.esper.client.EPStatement;  
import com.espertech.esper.client.EventBean;  
import com.espertech.esper.client.UpdateListener;  
import cn.soc.thinkingesper.common.*;

//OverLapping和NoOverLapping一样都有两个条件限制，
//但是区别在于OverLapping的初始条件可以被触发多次，并且只要被触发就会新建一个context，
//但是当终结条件被触发时，之前建立的所有context都会被销毁
class OverLappingContextListener implements UpdateListener  
{  
  
    public void update(EventBean[] newEvents, EventBean[] oldEvents)  
    {  
        if (newEvents != null)  
        {  
            EventBean event = newEvents[0];
            //InitialEvent中并没有id, 这个id是如何得到的呢？
            System.out.println("context.id:" + event.get("id") + ", id:" + event.get("id"));  
        }
    }  
}  
  
class OverLappingContextListener2 implements UpdateListener  
{  
  
    public void update(EventBean[] newEvents, EventBean[] oldEvents)  
    {  
        if (newEvents != null)  
        {  
            EventBean event = newEvents[0];  
            System.out.println("Class:" + event.getUnderlying().getClass().getName() + ", id:" + event.get("id"));  
        }
    }
}
  
public class C0404OverLappingContextTest  
{  
    public static void main(String[] args)  
    {  
        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();  
        EPAdministrator admin = epService.getEPAdministrator();  
        EPRuntime runtime = epService.getEPRuntime();  
  
        String initial = InitialEvent.class.getName();  
        String terminate = TerminateEvent.class.getName();  
        String some = SomeEvent.class.getName();  
        // 以InitialEvent事件作为初始事件，TerminateEvent事件作为终结事件  
        String epl1 = "create context OverLapping initiated " + initial + " terminated " + terminate;  
        String epl2 = "context OverLapping select context.id from " + initial;  
        String epl3 = "context OverLapping select * from " + some;  
  
        admin.createEPL(epl1);  
        EPStatement state = admin.createEPL(epl2);  
        state.addListener(new OverLappingContextListener());  
        EPStatement state1 = admin.createEPL(epl3);  
        state1.addListener(new OverLappingContextListener2());  
  
        InitialEvent i = new InitialEvent();  
        System.out.println("sendEvent: InitialEvent");  
        runtime.sendEvent(i);  
  
        SomeEvent s = new SomeEvent();  
        s.setId(2);  
        System.out.println("sendEvent: SomeEvent");  
        runtime.sendEvent(s);  
  
        InitialEvent i2 = new InitialEvent();  
        System.out.println("sendEvent: InitialEvent");  
        runtime.sendEvent(i2);  
  
        TerminateEvent t = new TerminateEvent();  
        System.out.println("sendEvent: TerminateEvent");  
        runtime.sendEvent(t);  
  
        SomeEvent s2 = new SomeEvent();  
        s2.setId(4);  
        System.out.println("sendEvent: SomeEvent");  
        runtime.sendEvent(s2);  
    }
}  