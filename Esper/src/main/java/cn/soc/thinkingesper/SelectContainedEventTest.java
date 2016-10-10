package cn.soc.thinkingesper;

import java.util.ArrayList;
import java.util.List;

import com.espertech.esper.client.EPAdministrator;  
import com.espertech.esper.client.EPOnDemandQueryResult;  
import com.espertech.esper.client.EPRuntime;  
import com.espertech.esper.client.EPServiceProvider;  
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;  

class SelectContainedListener implements UpdateListener {  
	  
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {  
        if (newEvents != null) {  
            for (int i = 0; i < newEvents.length; i++) {  
                if (newEvents[i] == null) {  
                    continue;  
                }  
                System.out.println(newEvents[i].getUnderlying());  
            }  
        }  
    }  
}  
  
public class SelectContainedEventTest {  
  
    public static void main(String[] args) {  
        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();  
        EPAdministrator admin = epService.getEPAdministrator();  
        EPRuntime runtime = epService.getEPRuntime();  
  
        Review r1 = new Review();  
        r1.setReviewId(1);  
        r1.setComment("r1");  
  
        Book b1 = new Book();  
        b1.setAuthor("b1");  
        b1.setBookId(1);  
        b1.setReview(r1);  
  
        Book b2 = new Book();  
        b2.setAuthor("b2");  
        b2.setBookId(2);  
  
        Item i1 = new Item();  
        i1.setItemId(1);  
        i1.setProductId(1);  
        i1.setPrice(1.11);  
        i1.setAmount(2);  
  
        MediaOrder mo1 = new MediaOrder();  
        Books bs = new Books();  
        Items is = new Items();  
        List<Item> items = new ArrayList<Item>();  
        List<Book> books = new ArrayList<Book>();  
        items.add(i1);  
        books.add(b1);  
        books.add(b2);  
        mo1.setOrderId(1);  
        bs.setBook(books);  
        is.setItem(items);
        // 为什么这样不会覆盖原来的值?
        mo1.setItems(is);  
        mo1.setBooks(bs);  
  
        String mediaOrder = MediaOrder.class.getName();  
        String epl = "select * from " + mediaOrder + "[books.book]";  
        EPStatement stat1 = admin.createEPL(epl);  
        stat1.addListener(new SelectContainedListener());  
  
        runtime.sendEvent(mo1);  
    }  
}  
