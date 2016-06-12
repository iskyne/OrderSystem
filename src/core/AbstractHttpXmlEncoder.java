package core;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.List;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.JiBXException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

public abstract class AbstractHttpXmlEncoder<T> extends MessageToMessageEncoder<T> {
	
	IBindingFactory factory=null;
	StringWriter writer=null;
	final static String CHARSET_NAME="utf-8";
	final static Charset UTF_8=Charset.forName("utf-8");
	
	protected ByteBuf encode0(ChannelHandlerContext ctx,Object obj) throws JiBXException, IOException{
		factory=BindingDirectory.getFactory(obj.getClass());
		writer=new StringWriter();
		IMarshallingContext context=factory.createMarshallingContext();
		context.setIndent(2);
		context.marshalDocument(obj,CHARSET_NAME,null,writer);
		String xmlStr=writer.toString();
		//System.out.println(xmlStr);
		writer.close();
		writer=null;
		ByteBuf buffer=Unpooled.copiedBuffer(xmlStr,UTF_8);
		return buffer;
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		cause.printStackTrace();
		if(writer!=null){
			writer.close();
			writer=null;
		}
	}
}
