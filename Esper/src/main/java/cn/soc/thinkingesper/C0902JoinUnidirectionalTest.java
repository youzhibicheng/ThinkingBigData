package cn.soc.thinkingesper;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

class Grapefruit {
	private int price;

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Grapefruit price=" + price;
	}
}

class Tomato {
	private int price;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Tomato price=" + price;
	}
}

class JoinUnidirectionalListener implements UpdateListener {
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		if (newEvents != null) {
			System.out.println(newEvents[0].get("g") + ", " + newEvents[0].get("t"));
		}
	}
}

public class C0902JoinUnidirectionalTest {

	public static void main(String[] args) throws InterruptedException {
		EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();

		EPAdministrator admin = epService.getEPAdministrator();

		String epl1 = "select * from " + Grapefruit.class.getName() + " as g unidirectional, " + Tomato.class.getName()
				+ ".std:lastevent() as t where g.price = t.price";

		EPStatement stat = admin.createEPL(epl1);
		stat.addListener(new JoinUnidirectionalListener());

		EPRuntime runtime = epService.getEPRuntime();

		Grapefruit g1 = new Grapefruit();
		g1.setPrice(1);
		System.out.println("Send Grapefruit1");
		runtime.sendEvent(g1);

		Tomato t1 = new Tomato();
		t1.setPrice(1);
		System.out.println("Send Tomato1");
		runtime.sendEvent(t1);

		Tomato t2 = new Tomato();
		t2.setPrice(2);
		System.out.println("Send Tomato2");
		runtime.sendEvent(t2);

		Grapefruit g2 = new Grapefruit();
		g2.setPrice(2);
		System.out.println("Send Grapefruit2");
		runtime.sendEvent(g2);
	}
}
