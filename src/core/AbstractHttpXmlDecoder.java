package core;

import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.List;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;

import biz.Order;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public abstract class AbstractHttpXmlDecoder<T> extends MessageToMessageDecoder<T>{

	private IBindingFactory factory=null;
	private StringReader reader=null;
	private Class<?> clazz=null;
	private boolean isPrint=false;
	private static final String CHARSET_NAME="utf-8";
	private static final Charset UTF_8=Charset.forName(CHARSET_NAME);
	
	protected AbstractHttpXmlDecoder (Class<?> clazz){
		this(clazz,false);
	}
	
	protected AbstractHttpXmlDecoder (Class<?> clazz,boolean isPrint){
		this.clazz=clazz;
		this.isPrint=isPrint;
	}
	
	protected Object decode0(ChannelHandlerContext ctx,ByteBuf body) throws JiBXException{
		System.out.println("step  1");
		factory=BindingDirectory.getFactory(clazz);
		System.out.println("step  2");
		String content=body.toString(UTF_8);
		System.out.println("step  3");
		if(isPrint){
			System.out.println("content "+content);
		}
		reader=new StringReader(content);
		IUnmarshallingContext context=factory.createUnmarshallingContext();
		Object result=null;
		try{
			result=context.unmarshalDocument(reader);
		}catch(Exception e){
			e.printStackTrace();
		}
		reader.close();
		reader=null;
		return result;
	}
	

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		if(reader!=null){
			reader.close();
			reader=null;
		}
	}
}
