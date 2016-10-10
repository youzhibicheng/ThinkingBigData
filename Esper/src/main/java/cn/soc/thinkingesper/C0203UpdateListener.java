package cn.soc.thinkingesper;

import com.espertech.esper.client.EventBean;  

//进程模型

public interface C0203UpdateListener  
{  
    public void update(EventBean[] newEvents, EventBean[] oldEvents);  
}