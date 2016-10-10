package cn.soc.thinkingesper.common;

import com.espertech.esper.client.hook.SQLColumnTypeContext;
import com.espertech.esper.client.hook.SQLColumnTypeConversion;  
import com.espertech.esper.client.hook.SQLColumnValueContext;  
import com.espertech.esper.client.hook.SQLInputParameterContext;  
  
/** 
 *  
 * MySQLColumnTypeConvertor必须为public类，不然无法实例化。 
 * Esper会为每一个EPL实例，即EPStatement提供一个Convertor实例 
 * 
 * 该例子没有做任何转换。 
 * Created by Luonanqin on 2/9/14. 
 */

public class MySQLColumnTypeConvertor implements SQLColumnTypeConversion{  
  
    // 转换列的类型  
    public Class getColumnType(SQLColumnTypeContext context) {  
        Class clazz = context.getColumnClassType();  
        return clazz;  
    }  
  
    // 转换列的值  
    public Object getColumnValue(SQLColumnValueContext context) {  
        Object obj = context.getColumnValue();  
        return obj;  
    }  
  
    // 转换传入的参数值  
    public Object getParameterValue(SQLInputParameterContext context) {  
        Object obj = context.getParameterValue();  
        return obj;  
    }  
}  