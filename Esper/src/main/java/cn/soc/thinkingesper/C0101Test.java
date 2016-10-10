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

class AppleListener implements UpdateListener {

	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		if (newEvents != null) {
			Double avg = (Double) newEvents[0].get("avg(price)");
			System.out.println("Apple's average price is " + avg);
		}
	}

}

public class C0101Test {

	public static void main(String[] args) throws InterruptedException {
		EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();

		EPAdministrator admin = epService.getEPAdministrator();

		//疑问： 将对象作为event, sent过去，如何与EPL语句建立管理呢？
		//EPL语句中有对象的名字 
		//String product = Apple.class.getName();
		String product = Apple.class.getName();
		String epl = "select avg(price) from " + product + ".win:length_batch(3)";
		//如果写成这样就不行了
//		String epl = "select avg(price) from Apple.win:length_batch(3)";
//		String epl = "select avg(price) from Apple.class.win:length_batch(3)";
		//写成这样就可以了
//		String epl = "select avg(price) from cn.soc.thinkingesper.common.Apple.win:length_batch(3)";
		

		EPStatement state = admin.createEPL(epl);
		state.addListener(new AppleListener());

		EPRuntime runtime = epService.getEPRuntime();

		Apple apple1 = new Apple();
		apple1.setId(1);
		apple1.setPrice(1);
		runtime.sendEvent(apple1);

		Apple apple2 = new Apple();
		apple2.setId(2);
		apple2.setPrice(2);
		runtime.sendEvent(apple2);

		Apple apple3 = new Apple();
		apple3.setId(3);
		apple3.setPrice(3);
		runtime.sendEvent(apple3);
	
		Apple apple4 = new Apple();
		apple4.setId(4);
		apple4.setPrice(4);
		runtime.sendEvent(apple4);
		
		Apple apple5 = new Apple();
		apple5.setId(5);
		apple5.setPrice(5);
		runtime.sendEvent(apple5);
		
		Apple apple6 = new Apple();
		apple6.setId(6);
		apple6.setPrice(6);
		runtime.sendEvent(apple6);
		
		Apple apple7 = new Apple();
		apple7.setId(7);
		apple7.setPrice(7);
		runtime.sendEvent(apple7);
	}
}