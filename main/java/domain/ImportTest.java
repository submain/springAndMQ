package domain;

public class ImportTest {
	private String des;
	private int orderNum;
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	@Override
	public String toString() {
		return "ImportTest [des=" + des + ", orderNum=" + orderNum + "]";
	}
	
}
