package core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

import java.nio.charset.Charset;
import java.util.List;

public class HttpXmlResponseEncoder extends AbstractHttpXmlEncoder<HttpXmlResponse>{

	@Override
	protected void encode(ChannelHandlerContext arg0, HttpXmlResponse arg1,
			List<Object> arg2) throws Exception {
		// TODO Auto-generated method stub
		Object body=arg1.getBody();
		ByteBuf content=encode0(arg0,body);
		FullHttpResponse response=arg1.getResponse();
		if(response==null){
			response=new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK,content);
		}else{
			response=new DefaultFullHttpResponse(response.getProtocolVersion(),response.getStatus(),content);
		}
		response.headers().set(HttpHeaders.Names.CONTENT_TYPE,"text/xml");
		response.headers().set(HttpHeaders.Names.CONTENT_LENGTH,content.readableBytes());
		System.out.println(response.headers().toString()+response.content().toString(Charset.forName(CHARSET_NAME)));
		arg2.add(response);
	}

}
