package cn.soc.thinkingesper;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

import cn.soc.thinkingesper.common.Apple;

/**
 * 
 * @author luonanqin
 * 
 */

class C0301AppleListener implements UpdateListener {

	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		if (newEvents != null) {
			Double avg = (Double) newEvents[0].get("avg(price)");
			System.out.println("newEvents Apple's average price is " + avg);
		}
		// 每次进入newEvents中，也会进入到oldEvents中，这是为什么呢?
		if ( oldEvents != null ){
			Double avg = (Double) newEvents[0].get("avg(price)");
			System.out.println("oldEvents Apple's average price is " + avg);			
		}
	}

}

public class C0301Test {

	public static void main(String[] args) throws InterruptedException {
		EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();

		EPAdministrator admin = epService.getEPAdministrator();

		String product = Apple.class.getName();
		// 这个时候只有 newEvent有值
//		String epl = "select avg(price) from " + product + ".win:length_batch(3)";
		
		// 这个时候只有 newEvent有值
//		String epl = "select istream avg(price) from " + product + ".win:length_batch(3)";
		
		// 这种情况非常特殊
		// 1. 只有newEvents有值, oldEvents没有值
		// 2. newEvents中的值是remove的值
//		String epl = "select rstream avg(price) from " + product + ".win:length_batch(3)";
		
		// 这个比较奇怪, newEvents和oldEvents都有值，并且是一模一样的
		String epl = "select irstream avg(price) from " + product + ".win:length_batch(3)";

		EPStatement state = admin.createEPL(epl);
		state.addListener(new C0301AppleListener());

		EPRuntime runtime = epService.getEPRuntime();

		Apple Apple1 = new Apple();
		Apple1.setId(1);
		Apple1.setPrice(1);
		System.out.println("Sending Apple1");
		runtime.sendEvent(Apple1);

		Apple Apple2 = new Apple();
		Apple2.setId(2);
		Apple2.setPrice(2);
		System.out.println("Sending Apple2");
		runtime.sendEvent(Apple2);

		Apple Apple3 = new Apple();
		Apple3.setId(3);
		Apple3.setPrice(3);
		System.out.println("Sending Apple3");
		runtime.sendEvent(Apple3);
		
		Apple Apple4 = new Apple();
		Apple4.setId(4);
		Apple4.setPrice(4);
		System.out.println("Sending Apple4");
		runtime.sendEvent(Apple4);
		
		Apple Apple5 = new Apple();
		Apple5.setId(5);
		Apple5.setPrice(5);
		System.out.println("Sending Apple5");
		runtime.sendEvent(Apple5);
		
		Apple Apple6 = new Apple();
		Apple6.setId(6);
		Apple6.setPrice(6);
		System.out.println("Sending Apple6");
		runtime.sendEvent(Apple6);
		
		Apple Apple7 = new Apple();
		Apple7.setId(7);
		Apple7.setPrice(7);
		System.out.println("Sending Apple7");
		runtime.sendEvent(Apple7);
		
		Apple Apple8 = new Apple();
		Apple8.setId(8);
		Apple8.setPrice(8);
		System.out.println("Sending Apple8");
		runtime.sendEvent(Apple8);
		
		Apple Apple9 = new Apple();
		Apple9.setId(9);
		Apple9.setPrice(9);
		System.out.println("Sending Apple9");
		runtime.sendEvent(Apple9);
		
		Apple Apple10 = new Apple();
		Apple10.setId(10);
		Apple10.setPrice(10);
		System.out.println("Sending Apple10");
		runtime.sendEvent(Apple10);
	}
}