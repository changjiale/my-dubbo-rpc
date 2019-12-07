package common.protocol.dubbo;

import common.request.RpcRequest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.concurrent.*;

/**
 * @author: changjiale
 * @create: 2019/12/06 14:42
 * @description:
 */
public class NettyClient {
    private static NettyClientHandler client;


    public Object send(String hostname, Integer port, RpcRequest rpcRequest) {
        client = new NettyClientHandler();

        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline channelPipeline = socketChannel.pipeline();
                        channelPipeline.addLast(new ObjectDecoder(1024 * 1024, ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
                        channelPipeline.addLast(new ObjectEncoder());
                        //netty实现代码
                        channelPipeline.addLast(client);
                    }
                });
        try {
            //connect是异步的，但调用其future的sync则是同步等待连接成功
            ChannelFuture future = b.connect(hostname, port).sync();
            System.out.println("链接成功!" + "host:" + hostname + " port:" + port);
            //同步等待调用信息发送成功
            future.channel().writeAndFlush(rpcRequest).sync();
            //同步等待NettyClientHandler的channelRead0被触发后（意味着收到了调用结果）关闭连接
            future.channel().closeFuture().sync();
            return client.getResponse();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            group.shutdownGracefully();
        }
        return "";
    }
}
