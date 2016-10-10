package cn.soc.thinkingesper;

import com.espertech.esper.client.ConfigurationOperations;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

/**
 * 
 * @author luonanqin
 * 
 */
class Car {
	private int size;
	private String name;
	private int price;

	public void setSize(int size) {
		this.size = size;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSize() {
		return size;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

}

class Auto {
	private int autoSize;
	private String autoName;

	public Auto(int s, String n) {
		this.autoSize = s;
		this.autoName = n;
	}

	public String toString() {
		return "AutoSize: " + autoSize + ", AutoName: " + autoName;
	}
}

class Benz {
	private int benzSize;
	private String benzName;

	public void setBenzSize(int benzSize) {
		this.benzSize = benzSize;
	}

	public void setBenzName(String benzName) {
		this.benzName = benzName;
	}

	public String toString() {
		return "BenzSize: " + benzSize + ", BenzName: " + benzName;
	}
}

class InstantiatePopulateListener implements UpdateListener {
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		if (newEvents != null) {
			Object car = newEvents[0].getUnderlying();
			System.out.println(car);
		}
	}
}

public class C0802InstantiatePopulateTest {
	public static void main(String[] args) throws InterruptedException {
		EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();

		EPAdministrator admin = epService.getEPAdministrator();

		String car = Car.class.getName();
		String auto = Auto.class.getName();
		String benz = Benz.class.getName();

		// auto 包含构造函数, 所以可以随便写select
		String cartToAutoEpl = "insert into " + auto + " select size, name from " + car;
		String autoEpl = "select * from " + auto;
		
		admin.createEPL(cartToAutoEpl);
		EPStatement state1 = admin.createEPL(autoEpl);
		state1.addListener(new InstantiatePopulateListener());
		
		String cartToBenzEpl = "insert into " + benz + " select size as benzSize, name as benzName from " + car;
		String benzEpl = "select * from " + benz;
		String benzEpl2 = "insert into " + benz + "(benzSize,benzName) select size, name from " + car;
		
		admin.createEPL(cartToBenzEpl);
		EPStatement state2 = admin.createEPL(benzEpl);
		state2.addListener(new InstantiatePopulateListener());
		
		EPRuntime runtime = epService.getEPRuntime();
		
		Car c1 = new Car();
		c1.setSize(1);
		c1.setName("car1");
		c1.setPrice(11);
		runtime.sendEvent(c1);
		
		Car c2 = new Car();
		c2.setSize(2);
		c2.setName("car2");
		c2.setPrice(22);
		runtime.sendEvent(c2);
	}
}