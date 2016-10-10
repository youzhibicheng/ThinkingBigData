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
// output基本语法
// output [after suppression_def] [[all | first | last | snapshot] every time_period | output_rate events]

class SnapDiffAllListener implements UpdateListener {
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		if (newEvents != null) {
			int price = (Integer) newEvents[0].get("sPrice");
			System.out.println("Apple's sum price is " + price);
		}
	}
}


public class C0702SnapshotDiffAll {
	public static void main(String[] args) throws InterruptedException {
		EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();

		EPAdministrator admin = epService.getEPAdministrator();

		String appStr = Apple.class.getName();
		// 请比较C0701的示例
		// 统计最新3个Apple事件的sum price，并且从EPL可用起，等待第一个事件进入后，以每两个事件进入的频率输出统计结果
		// 这里不使用 snapshot，而是使用all，比较查看一下区别
		// snapshot相当于对计算结果拍了一张照片，把结果复制出来并输出，而all是把计算结果直接输出，不会复制
		// 这里很奇怪，不知道怎么回事，完全不能解释清楚
		// C0701的语句
		//snapshot 表示输出EPL所保持的所有事件计算结果，通常用来查看view或者window中现存的事件计算结果。
		//select * from Fruit.win:time(5 sec) output snapshot every 2 events  
		//上面的句子表示每进入两个事件输出5 sec内的所有事件，且不会讲这些事件从5 sec范围内移除
		//select * from Fruit.win:time(5 sec) output all every 2 events  
		//上面的句子表示每进入两个事件输出5 sec内包含的所有事件，输出的事件不再保留于5 sec范围内。
		//String epl = "select sum(price) as sPrice from " + appleStr + ".win:length(3) output after 1 events snapshot every 2 events";
		String epl = "select sum(price) as sPrice from " + appStr + ".win:length(3) output after 1 events every 2 events";

		EPStatement state = admin.createEPL(epl);
		state.addListener(new SnapDiffAllListener());

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