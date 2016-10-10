package cn.soc.thinkingesper;

import java.util.List;  
import java.util.Map;  
  
//update Person set phones('home') = 123456789 where name="luonanqin"
public class C0203Person  
{  
    String name;  
    int age;  
    List<Child> children;  
    Map<String, Integer> phones;  
    Address address;  
  
    public String getName()  
    {  
        return name;  
    }  
  
    public int getAge()  
    {  
        return age;  
    }  
  
    public Child getChildren(int index)  
    {  
        return children.get(index);  
    }  
      
    // 此方法用于phones属性的更新  
    public void setPhones(String name, Integer number){  
        phones.put(name, number);  
    }  
  
    public int getPhones(String name)  
    {  
        return phones.get(name);  
    }  
  
    public Address getAddress()  
    {  
        return address;  
    }  
    // Address，Child不变  
}  