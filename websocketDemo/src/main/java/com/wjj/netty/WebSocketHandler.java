package com.wjj.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import redis.clients.jedis.Jedis;


public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    Jedis jedis = null;
    private static String host = "127.0.0.1";
    private static int port = 6379;
//    private static String host = "47.106.107.117";
//    private static int port = 10706;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        final Channel channel = ctx.channel();
        if (msg.text().equals("!!A!A!A!!")) {
            channelGroup.writeAndFlush(new TextWebSocketFrame("!!A!A!A!!nowConnectCount:" + channelGroup.size()));
            channel.writeAndFlush(new TextWebSocketFrame("!!A!A!A!!initHistOryMessage:" + RedisOperation.getAllMessage(jedis)));
            return;
        }
        String[] message = msg.text().split("!!A!A!A!!sOmeBodySay:");
        channelGroup.forEach(ch -> {
            if (ch == channel) {
                RedisOperation.saveMessage(jedis, message[0], message[1]);
                ch.writeAndFlush(new TextWebSocketFrame("我: " + message[1]));
            } else {
                ch.writeAndFlush(new TextWebSocketFrame("某人: " + message[1]));
            }
        });
        System.out.println(channel.remoteAddress() + ": " + msg.text());
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        try {
            jedis = new Jedis(host, port);
//            jedis.auth("Admin@1234");
        }catch (Exception e){
            e.printStackTrace();
        }
        channelGroup.add(ctx.channel());
        channelGroup.writeAndFlush(new TextWebSocketFrame("!!A!A!A!!nowConnectCount:" + channelGroup.size()));
        System.out.println("用户上线" + ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (jedis != null) {
            jedis.disconnect();
        }
        channelGroup.remove(ctx.channel());
        channelGroup.writeAndFlush(new TextWebSocketFrame("!!A!A!A!!nowConnectCount:" + channelGroup.size()));
        System.out.println("用户下线: " + ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
    }
}
