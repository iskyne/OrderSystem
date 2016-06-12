package server;

import biz.Order;
import core.HttpXmlRequestDecoder;
import core.HttpXmlResponseEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class Server {
	public void bind(int port) throws InterruptedException{
		EventLoopGroup boss=new NioEventLoopGroup();
		EventLoopGroup worker=new NioEventLoopGroup();
		
		ServerBootstrap b=new ServerBootstrap();
		try{
			b.group(boss,worker)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.TCP_NODELAY, true)
			.childHandler(new ChannelInitializer<SocketChannel>(){

				@Override
				protected void initChannel(SocketChannel arg0) throws Exception {
					// TODO Auto-generated method stub
					arg0.pipeline().addLast(new HttpRequestDecoder());
					arg0.pipeline().addLast(new HttpObjectAggregator(65536));
					arg0.pipeline().addLast(new HttpXmlRequestDecoder(Order.class,true));
					arg0.pipeline().addLast(new HttpResponseEncoder());
					arg0.pipeline().addLast(new HttpXmlResponseEncoder());
					arg0.pipeline().addLast(new HttpXmlServerHandler());
				}
			});
			
			ChannelFuture f=b.bind(port).sync();
			f.channel().closeFuture().sync();
		}finally{
			boss.shutdownGracefully();
			worker.shutdownGracefully();
		}
		
	}
	
	public static void main(String args[]){
		try {
			new Server().bind(8080);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
