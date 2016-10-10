package cn.soc.thinkingesper;

import java.util.List;  
import java.util.Map;  
  
// 使用POJO 作为 事件类型
// 对于POJO，Esper要求对每一个私有属性要有getter方法。
// Esper允许不必按照JavaBean规定的格式，但是getter方法是必须的。又或者可以在配置文件中配置可访问的方法来代替getter
// select age,children,address from Person where name="luonanqin"
public class C0201Person  
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
  
    public List<Child> getChildren()  
    {  
        return children;  
    }  
  
    public Map<String, Integer> getPhones()  
    {  
        return phones;  
    }  
  
    public Address getAddress()  
    {  
        return address;  
    }  
      
}  
  
class Child  
{  
    String name;  
    int gender;  
    // 省略getter方法  
}  
  
class Address  
{  
    String road;  
    String street;  
    int houseNo;  
    // 省略getter方法  
}  
