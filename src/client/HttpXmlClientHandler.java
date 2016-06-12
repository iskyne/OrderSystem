package client;

import biz.OrderFactory;
import core.HttpXmlRequest;
import core.HttpXmlResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HttpXmlClientHandler extends SimpleChannelInboundHandler<HttpXmlResponse>{

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("client connect successful");
		HttpXmlRequest request=new HttpXmlRequest(null,OrderFactory.createOrder(111111));
		ctx.writeAndFlush(request);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext arg0, HttpXmlResponse arg1)
			throws Exception {
		System.out.println("message receive");
		// TODO Auto-generated method stub
		System.out.println(arg1.getResponse().headers().names());
		System.out.println(arg1.getBody());
	}
}
