package biz;

public class Order {
	private long orderNum;
	private Customer customer;
	private Address billTo;
	private Shipping shipping;
	private Address shppingTo;
	private float total;
	public long getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(long orderNum) {
		this.orderNum = orderNum;
	}
	@Override
	public String toString() {
		return "Order [orderNum=" + orderNum + ", customer=" + customer
				+ ", billTo=" + billTo + ", shipping=" + shipping
				+ ", shppingTo=" + shppingTo + ", total=" + total + "]";
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Address getBillTo() {
		return billTo;
	}
	public void setBillTo(Address billTo) {
		this.billTo = billTo;
	}
	public Shipping getShipping() {
		return shipping;
	}
	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}
	public Address getShppingTo() {
		return shppingTo;
	}
	public void setShppingTo(Address shppingTo) {
		this.shppingTo = shppingTo;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
}
