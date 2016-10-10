package cn.soc.thinkingesper;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

class Pear {
	private int price;
	private int size;

	public void setPrice(int price) {
		this.price = price;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPrice() {
		return price;
	}

	public int getSize() {
		return size;
	}
}

class Fruit {

}

public class C0901SubqueryTest {

	public static void main(String[] args) throws InterruptedException {
		EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();

		EPAdministrator admin = epService.getEPAdministrator();

		String epl1 = "select (select sum(price), sum(size) from " + Pear.class.getName() + ".std:lastevent()) from "
				+ Fruit.class.getName();
		/*
		 * 当然，对不同的属性使用不同的聚合函数也是可以的
		 */
		// String epl1 = "select (select sum(price), avg(size) from " + Pear.class.getName() + ".std:lastevent()) from " + Fruit.class.getName();

		/*
		 * 注意：size没有使用聚合函数，会导致创建epl失败。文档中注明了“The properties of the subselect stream must all be within aggregation functions”.
		 * 即子查询中的select子句使用聚合函数时，所查询的属性都要使用聚合函数
		 */
		// String epl1 = "select (select sum(price), size from " + Pear.class.getName() + ".std:lastevent()) from " + Fruit.class.getName();

		admin.createEPL(epl1);
		System.out.println("Create epl successfully!");
	}
}