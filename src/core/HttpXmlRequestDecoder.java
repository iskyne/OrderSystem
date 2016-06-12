package core;

import java.util.List;

import biz.Order;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

public class HttpXmlRequestDecoder extends AbstractHttpXmlDecoder<FullHttpRequest>{

	public HttpXmlRequestDecoder(Class<?> clazz) {
		super(clazz);
		// TODO Auto-generated constructor stub
	}
	
	public HttpXmlRequestDecoder(Class<?> clazz,boolean isPrint){
		super(clazz,isPrint);
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, FullHttpRequest request,
			List<Object> list) throws Exception {
		// TODO Auto-generated method stub
		if(!request.getDecoderResult().isSuccess()){
			sendError(ctx,HttpResponseStatus.BAD_REQUEST);
			return;
		}
		HttpXmlRequest outRequest=new HttpXmlRequest(request,decode0(ctx,request.content()));
		list.add(outRequest);
	}
	
	private void sendError(ChannelHandlerContext ctx,HttpResponseStatus status){
		FullHttpResponse response=
				new DefaultFullHttpResponse(HttpVersion.HTTP_1_1
						,status
						,Unpooled.copiedBuffer("Failure"+status.toString()
						, CharsetUtil.UTF_8));
		
		response.headers().set(HttpHeaders.Names.CONTENT_TYPE,"text/plain;charset=utf-8");
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}
}
