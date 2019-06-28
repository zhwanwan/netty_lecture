package com.lec.netty.example6;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @author zhwanwan
 * @create 2019-06-13 8:16 AM
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {
        System.out.println(msg.getDataType());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        MyDataInfo.MyMessage myMessage = null;

        int randomInt = new Random().nextInt(3);

        if (randomInt == 0) {
            System.out.println("张三");
            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.PersonType)
                    .setPerson(MyDataInfo.Person.newBuilder().setName("张三").setAge(20).setAddress("北京").build())
                    .build();
        } else if (randomInt == 1) {
            System.out.println("一只狗");
            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.DogType)
                    .setDog(MyDataInfo.Dog.newBuilder().setName("一只狗").setAge(20).build())
                    .build();
        } else {
            System.out.println("一只猫");
            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.CatType)
                    .setCat(MyDataInfo.Cat.newBuilder().setName("一只猫").setCity("苏州").build()).build();
        }


        /*MyDataInfo.Person person =
                MyDataInfo.Person.newBuilder().setName("张三").setAge(20).setAddress("北京").build();*/
        //客户端发送消息
        //ctx.channel().writeAndFlush(person);
        ctx.channel().writeAndFlush(myMessage);
    }
}
