package cn.soc.thinkingesper.common;

public class Apple {

	private int id;
	private int price;
	private int amount;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString(){
		return "id:" + id + ", price:" + price + ", amount:" + amount;
	}
}
