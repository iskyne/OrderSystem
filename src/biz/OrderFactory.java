package biz;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OrderFactory {
	public static Order createOrder(final long orderId){
		Order order=new Order();
		order.setOrderNum(orderId);
		
		Address billTo=new Address();
		billTo.setCity("Guangzhou");
		billTo.setCountry("China");
		billTo.setPostCode("620000");
		billTo.setState("Guangdong");
		billTo.setStreet1("Zhujiang East Road");
		order.setBillTo(billTo);
		
		Customer customer=new Customer();
		customer.setCustomerNum(666666);
		customer.setFirstName("shen");
		customer.setLastName("jun");
		List<String> list=new LinkedList<String>();
		list.add("guo");
		customer.setMiddleNames(list);
		order.setCustomer(customer);
		order.setShipping(Shipping.DOMESTIC_EXPRESS);
		
		Address shippingTo=new Address();
		shippingTo.setCity("Guangzhou");
		shippingTo.setCountry("China");
		shippingTo.setPostCode("620000");
		shippingTo.setState("Guangdong");
		shippingTo.setStreet1("Zhujiang East Road");
		order.setShppingTo(shippingTo);
		order.setTotal(100.24f);
		
		return order;
	}
}
