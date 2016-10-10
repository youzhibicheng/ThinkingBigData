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

// 在进入view之前过滤
class C0303AppleListener implements UpdateListener {

	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		if (newEvents != null) {
			for( EventBean event: newEvents){
				// 这个地方为什么跑不通呢?
				Integer id = (Integer)event.get("id");
				Integer price = (Integer) event.get("price");
				Integer amount = (Integer) event.get("amount");
				System.out.println("Apple's id:  " + id + ", price: " + price + ", amount: " + amount);
			}
		}
	}

}

public class C0303FilterBeforeView {

	public static void main(String[] args) throws InterruptedException {
		EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();

		EPAdministrator admin = epService.getEPAdministrator();

		String product = Apple.class.getName();
//		String epl = "select * from " + product + "(amount>200).win:length_batch(3)";
		String epl = "select id, price, amount from " + product + "(amount>200).win:length_batch(3)";
		

		EPStatement state = admin.createEPL(epl);
		state.addListener(new C0303AppleListener());

		EPRuntime runtime = epService.getEPRuntime();

		Apple apple1 = new Apple();
		apple1.setId(1);
		apple1.setPrice(1);
		apple1.setAmount(500);
		runtime.sendEvent(apple1);

		Apple apple2 = new Apple();
		apple2.setId(2);
		apple2.setPrice(2);
		apple2.setAmount(200);
		runtime.sendEvent(apple2);

		Apple apple3 = new Apple();
		apple3.setId(3);
		apple3.setPrice(3);
		apple3.setAmount(300);
		runtime.sendEvent(apple3);
	
		Apple apple4 = new Apple();
		apple4.setId(4);
		apple4.setPrice(4);
		apple4.setAmount(400);
		runtime.sendEvent(apple4);
		
		Apple apple5 = new Apple();
		apple5.setId(5);
		apple5.setPrice(5);
		apple5.setAmount(50);
		runtime.sendEvent(apple5);
		
		Apple apple6 = new Apple();
		apple6.setId(6);
		apple6.setPrice(6);
		apple6.setAmount(600);
		runtime.sendEvent(apple6);
		
		Apple apple7 = new Apple();
		apple7.setId(7);
		apple7.setPrice(7);
		apple7.setAmount(700);
		runtime.sendEvent(apple7);
	}
}