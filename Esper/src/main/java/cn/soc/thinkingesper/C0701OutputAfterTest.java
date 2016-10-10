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
 * 
 * @author luonanqin
 * 
 */

class OutputAfterListener implements UpdateListener {
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		if (newEvents != null) {
			int price = (Integer) newEvents[0].get("sPrice");
			System.out.println("Apple's sum price is " + price);
		}
	}
}


public class C0701OutputAfterTest {
	public static void main(String[] args) throws InterruptedException {
		EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();

		EPAdministrator admin = epService.getEPAdministrator();

		String appleStr = Apple.class.getName();
		// 统计最新3个Apple事件的sum price，并且从EPL可用起，等待第一个事件进入后，以每两个事件进入的频率输出统计结果
		String epl = "select sum(price) as sPrice from " + appleStr + ".win:length(3) output after 1 events snapshot every 2 events";

		EPStatement state = admin.createEPL(epl);
		state.addListener(new OutputAfterListener());

		EPRuntime runtime = epService.getEPRuntime();

		Apple b1 = new Apple();
		b1.setId(1);
		b1.setPrice(6);
		System.out.println("Send Apple Event: " + b1);
		runtime.sendEvent(b1);

		Apple b2 = new Apple();
		b2.setId(2);
		b2.setPrice(3);
		System.out.println("Send Apple Event: " + b2);
		runtime.sendEvent(b2);

		Apple b3 = new Apple();
		b3.setId(3);
		b3.setPrice(1);
		System.out.println("Send Apple Event: " + b3);
		runtime.sendEvent(b3);

		Apple b4 = new Apple();
		b4.setId(4);
		b4.setPrice(2);
		System.out.println("Send Apple Event: " + b4);
		runtime.sendEvent(b4);

		Apple b5 = new Apple();
		b5.setId(5);
		b5.setPrice(4);
		System.out.println("Send Apple Event: " + b5);
		runtime.sendEvent(b5);
		
		Apple b6 = new Apple();
		b6.setId(6);
		b6.setPrice(6);
		System.out.println("Send Apple Event: " + b6);
		runtime.sendEvent(b6);
		
		Apple b7 = new Apple();
		b7.setId(7);
		b7.setPrice(7);
		System.out.println("Send Apple Event: " + b7);
		runtime.sendEvent(b7);
		
		Apple b8 = new Apple();
		b8.setId(8);
		b8.setPrice(8);
		System.out.println("Send Apple Event: " + b8);
		runtime.sendEvent(b8);
	}
}