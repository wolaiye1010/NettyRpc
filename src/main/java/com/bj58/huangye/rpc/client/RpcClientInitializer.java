package com.bj58.huangye.rpc.client;

import com.bj58.huangye.rpc.protocol.RpcDecoder;
import com.bj58.huangye.rpc.protocol.RpcEncoder;
import com.bj58.huangye.rpc.protocol.RpcRequest;
import com.bj58.huangye.rpc.protocol.RpcResponse;
import com.bj58.huangye.rpc.registry.Constant;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * Created by luxiaoxun on 2016-03-16.
 */
public class RpcClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline cp = socketChannel.pipeline();
        cp.addLast(new RpcEncoder(RpcRequest.class));
        cp.addLast(new LengthFieldBasedFrameDecoder(Constant.maxFrameLength, 0, 4, 0, 0));
        cp.addLast(new RpcDecoder(RpcResponse.class));
        cp.addLast(new RpcClientHandler());
    }
}
