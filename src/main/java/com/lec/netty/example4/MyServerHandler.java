package com.lec.netty.example4;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/** User Defined Handler
 * @author zhwanwan
 * @create 2019-06-12 8:34 AM
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//        super.userEventTriggered(ctx, evt);
        if (evt instanceof IdleStateEvent) {

            IdleStateEvent event = (IdleStateEvent) evt;

            String eventType = null;

            switch (event.state()) {
                case READER_IDLE:
                    eventType = "Read Idle";
                    break;
                case WRITER_IDLE:
                    eventType = "Write Idle";
                    break;
                case ALL_IDLE:
                    eventType = "Read & Write Idle";
                    break;
            }

            System.out.println(ctx.channel().remoteAddress() + " Timeout Event: " + eventType);
        }
    }
}
