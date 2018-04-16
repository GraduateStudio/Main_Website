package com.wjj.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;


public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        final Channel channel = ctx.channel();
        if(msg.text().equals("!!A!A!A!!")){
            channelGroup.writeAndFlush(new TextWebSocketFrame("!!A!A!A!!nowConnectCount:"+channelGroup.size()));
            return;
        }
        channelGroup.forEach(ch->{
            if(ch == channel){
               ch.writeAndFlush(new TextWebSocketFrame("我: "+msg.text() ));
            }else{
                ch.writeAndFlush(new TextWebSocketFrame("某人: "+msg.text() ));
            }
        });
        System.out.println(channel.remoteAddress() + ": " + msg.text());
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channelGroup.add(ctx.channel());
        channelGroup.writeAndFlush(new TextWebSocketFrame("!!A!A!A!!nowConnectCount:"+channelGroup.size()));
        System.out.println("用户上线" + ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        channelGroup.remove(ctx.channel());
        channelGroup.writeAndFlush(new TextWebSocketFrame("!!A!A!A!!nowConnectCount:"+channelGroup.size()));
        System.out.println("用户下线: " + ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
    }
}
