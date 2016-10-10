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

class C0302AppleListener implements UpdateListener {

	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		if (newEvents != null) {
			//需要从Double改成Integer，否则运行时通不过
			Integer sum = (Integer) newEvents[0].get("sum(price)");
			System.out.println("newEvents Apple's sum price is " + sum);
		}
		//并没有进入这个oldEvents中，很奇怪
		if ( oldEvents != null ){
			Integer sum = (Integer) newEvents[0].get("sum(price)");
			System.out.println("oldEvents Apple's sum price is " + sum);			
		}
	}

}

public class C0302Test {

	public static void main(String[] args) throws InterruptedException {
		EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();

		EPAdministrator admin = epService.getEPAdministrator();

		String product = Apple.class.getName();
		String epl = "select sum(price) from " + product + ".win:length_batch(3)";

		EPStatement state = admin.createEPL(epl);
		state.addListener(new C0302AppleListener());

		EPRuntime runtime = epService.getEPRuntime();

		Apple a1 = new Apple();
		a1.setId(1);
		a1.setPrice(5);
		runtime.sendEvent(a1);

		Apple a2 = new Apple();
		a2.setId(2);
		a2.setPrice(2);
		runtime.sendEvent(a2);

		Apple a3 = new Apple();
		a3.setId(3);
		a3.setPrice(5);
		runtime.sendEvent(a3);
		
		Apple a4 = new Apple();
		a4.setId(4);
		a4.setPrice(8);
		runtime.sendEvent(a4);
		
		Apple a5 = new Apple();
		a5.setId(5);
		a5.setPrice(5);
		runtime.sendEvent(a5);
		
		Apple a6 = new Apple();
		a6.setId(6);
		a6.setPrice(6);
		runtime.sendEvent(a6);
		
		Apple a7 = new Apple();
		a7.setId(7);
		a7.setPrice(7);
		runtime.sendEvent(a7);
	}
}