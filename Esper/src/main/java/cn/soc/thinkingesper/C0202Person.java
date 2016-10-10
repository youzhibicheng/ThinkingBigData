package cn.soc.thinkingesper;

import java.util.List;
import java.util.Map;

//如果我不想要所有的child，而是想要第二个。并且我想得到家里的电话号码，那么Person需要改动一下
//select children[1], phones('home'), address.road where Person where name="luonanqin"  
public class C0202Person {
	String name;
	int age;
	List<Child> children;
	Map<String, Integer> phones;
	Address address;

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public Child getChildren(int index) {
		return children.get(index);
	}

	public int getPhones(String name) {
		return phones.get(name);
	}

	public Address getAddress() {
		return address;
	}
	// Address，Child不变
}