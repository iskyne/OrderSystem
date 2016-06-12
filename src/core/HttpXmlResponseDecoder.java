package core;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;

import java.util.List;

public class HttpXmlResponseDecoder extends AbstractHttpXmlDecoder<FullHttpResponse>{

	public HttpXmlResponseDecoder(Class<?> clazz) {
		super(clazz);
		// TODO Auto-generated constructor stub
	}
	
	public HttpXmlResponseDecoder(Class<?> clazz,boolean isPrint){
		super(clazz,isPrint);
	}

	@Override
	protected void decode(ChannelHandlerContext arg0, FullHttpResponse arg1,
			List<Object> arg2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("hello here ?");
		try{
			HttpXmlResponse response=new HttpXmlResponse(arg1,decode0(arg0,Unpooled.copiedBuffer(arg1.content())));
			System.out.println("decode response");
			arg2.add(response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
