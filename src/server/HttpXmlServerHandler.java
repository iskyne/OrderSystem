package server;

import biz.Order;
import core.HttpXmlRequest;
import core.HttpXmlResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

public class HttpXmlServerHandler extends SimpleChannelInboundHandler<HttpXmlRequest>{

	@Override
	protected void channelRead0(ChannelHandlerContext arg0, HttpXmlRequest arg1)
			throws Exception {
		// TODO Auto-generated method stub
		Object body=arg1.getBody();
		Order order=(Order) body;
		System.out.println("Order "+order);
		
		order.setTotal(11111111111111111f);
		arg1.setBody(order);
		arg0.writeAndFlush(new HttpXmlResponse(
				new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK), order));
	}
}
