package client;

import java.net.InetSocketAddress;

import biz.Order;
import core.HttpXmlRequestEncoder;
import core.HttpXmlResponseDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;

public class Client {
	public void connect(String host,int port) throws InterruptedException{
		EventLoopGroup workers=new NioEventLoopGroup();
		Bootstrap b=null;
		try{
			b=new Bootstrap();
			b.group(workers)
			.channel(NioSocketChannel.class)
			.option(ChannelOption.TCP_NODELAY, true)
			.handler(new ChannelInitializer<SocketChannel>(){

				@Override
				protected void initChannel(SocketChannel arg0) throws Exception {
					// TODO Auto-generated method stub
					arg0.pipeline().addLast(new HttpResponseDecoder());
					arg0.pipeline().addLast(new HttpObjectAggregator(65536));
					arg0.pipeline().addLast(new HttpXmlResponseDecoder(Order.class,true));
					arg0.pipeline().addLast(new HttpRequestEncoder());
					arg0.pipeline().addLast(new HttpXmlRequestEncoder());
					arg0.pipeline().addLast(new HttpXmlClientHandler());
				}
				
			});
					
			ChannelFuture f=b.connect(new InetSocketAddress(host,port)).sync();
			f.channel().close().sync();
		}finally{
			workers.shutdownGracefully();
		}
	}
	
	public static void main(String args[]){
		try {
			new Client().connect("127.0.0.1",8080);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
