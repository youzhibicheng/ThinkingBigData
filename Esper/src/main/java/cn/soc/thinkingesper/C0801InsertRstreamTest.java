package cn.soc.thinkingesper;

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
class Asus {
	private int id;
	private int size;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String toString() {
		return "id: " + id + ", size: " + size;
	}
}

class InsertRstreamListener implements UpdateListener {
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		if (newEvents != null) {
			for (int i = 0; i < newEvents.length; i++) {
				Object id = newEvents[i].get("cid");
				System.out.println("Insert Asus: cid: " + id);
			}
		}
		if (oldEvents != null) {
			for (int i = 0; i < oldEvents.length; i++) {
				Object id = oldEvents[i].get("cid");
				System.out.println("Remove Asus: cid: " + id);
			}
		}
		System.out.println();
	}
}

public class C0801InsertRstreamTest {

	public static void main(String[] args) throws InterruptedException {
		EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();

		EPAdministrator admin = epService.getEPAdministrator();

		String asus = Asus.class.getName();
		String insertEPL = "insert rstream into Computer(cid,csize) select id,size from " + asus + ".win:length(1)";
//		String insertEPL = "insert into Computer(cid,csize) select id,size from " + asus + ".win:length(1)";
//		String insertEPL = "insert irstream into Computer(cid,csize) select id,size from " + asus + ".win:length(1)";
		String selectEPL = "select cid from Computer.win:length_batch(2)";

		//state没有定义listener吗？
		EPStatement state = admin.createEPL(insertEPL);
		EPStatement state1 = admin.createEPL(selectEPL);
		state1.addListener(new InsertRstreamListener());

		EPRuntime runtime = epService.getEPRuntime();

		Asus apple1 = new Asus();
		apple1.setId(1);
		apple1.setSize(1);
		System.out.println("Send Asus: " + apple1);
		runtime.sendEvent(apple1);

		Asus apple2 = new Asus();
		apple2.setId(2);
		apple2.setSize(1);
		System.out.println("Send Asus: " + apple2);
		runtime.sendEvent(apple2);

		Asus apple3 = new Asus();
		apple3.setId(3);
		apple3.setSize(3);
		System.out.println("Send Asus: " + apple3);
		runtime.sendEvent(apple3);

		Asus apple4 = new Asus();
		apple4.setId(4);
		apple4.setSize(4);
		System.out.println("Send Asus: " + apple4);
		runtime.sendEvent(apple4);

		Asus apple5 = new Asus();
		apple5.setId(5);
		apple5.setSize(3);
		System.out.println("Send Asus: " + apple5);
		runtime.sendEvent(apple5);

		Asus apple6 = new Asus();
		apple6.setId(6);
		apple6.setSize(4);
		System.out.println("Send Asus: " + apple6);
		runtime.sendEvent(apple6);
	}
}