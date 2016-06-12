package test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;

import biz.Order;
import biz.OrderFactory;

public class BindTest {
	IBindingFactory factory;
	StringReader reader;
	StringWriter writer;
	final static String CHARSET_NAME="utf-8";
	
	public String encode(Order order) throws JiBXException, IOException{
		factory=BindingDirectory.getFactory(order.getClass());
		writer=new StringWriter();
		IMarshallingContext context=factory.createMarshallingContext();
		context.setIndent(2);
		context.marshalDocument(order, CHARSET_NAME, null, writer);
		String result=writer.toString();
		writer.close();
		return result;
	}
	
	public Order decode(String encodeStr) throws JiBXException{
		reader=new StringReader(encodeStr);
		IUnmarshallingContext context=factory.createUnmarshallingContext();
		return (Order) context.unmarshalDocument(reader, CHARSET_NAME);
		
	}
	public static void main(String args[]){
		try {
			BindTest b=new BindTest();
			String encodeStr=b.encode(OrderFactory.createOrder(123456));
			System.out.println(encodeStr);
			System.out.println(b.decode(encodeStr));
		} catch (JiBXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
