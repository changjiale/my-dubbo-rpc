package common.proxy.server;

import common.annotation.RpcAnnotation;
import common.registry.ServiceRegisterCenter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: changjiale
 * @create: 2019/11/25 19:06
 * @description:
 */
public class RpcServerProxy {

    //维护一个待注册列表
    private Map<String, Object> handlerMap = new HashMap<>(16);

    private ServiceRegisterCenter serviceRegisterCenter;
    private String serviceAddress;

    public RpcServerProxy(ServiceRegisterCenter serviceRegisterCenter, String serviceAddress) {
        this.serviceAddress = serviceAddress;
        this.serviceRegisterCenter = serviceRegisterCenter;
    }

    public void publisher() {
        for (String serviceName : handlerMap.keySet()) {
            serviceRegisterCenter.register(serviceName, serviceAddress);
        }

        try {
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            //启动netty
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    ChannelPipeline channelPipeline = channel.pipeline();
                    channelPipeline.addLast(new ObjectDecoder(1024 * 1024, ClassResolvers.weakCachingResolver(
                            this.getClass().getClassLoader())));
                    channelPipeline.addLast(new ObjectEncoder());

                    //io数据交互 通过handler
                    channelPipeline.addLast(new RpcServerHandler(handlerMap));
                }
            }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
            String[] addr = serviceAddress.split(":");
            String ip = addr[0];
            int port = Integer.valueOf(addr[1]);
            ChannelFuture future = bootstrap.bind(ip, port).sync();
            System.out.println("服务启动");
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 扫描 方法和类的关系
     * @param services
     */
    public void bind(Object... services) {
        //将实现类通过注解获取实现类的名称、实现类的实现放入map集合中。
        for (Object service : services) {
            RpcAnnotation annotation = service.getClass().getAnnotation(RpcAnnotation.class);
            String serviceName = annotation.value().getName();
            handlerMap.put(serviceName, service);
        }
    }
}
