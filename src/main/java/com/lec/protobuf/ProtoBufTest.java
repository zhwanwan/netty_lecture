package com.lec.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.Arrays;

/**
 * protoc --java_out=src/main/java src/protobuf/Student.proto
 */
public class ProtoBufTest {

    public static void main(String[] args) {
        DataInfo.Student student =
                DataInfo.Student.newBuilder().setName("张三").setAge(20).setAddress("北京").build();

        DataInfo.Student student1 =
                DataInfo.Student.newBuilder().setName("张三").setAge(20).setAddress("北京").build();

        byte[] student2ByteArray = student.toByteArray();

        try {

            //System.out.println(Arrays.toString(student2ByteArray));

            DataInfo.Student student2 = DataInfo.Student.parseFrom(student2ByteArray);
            System.out.println(student2);
            System.out.println(student2.getName());
            System.out.println(student2.getAge());
            System.out.println(student2.getAddress());

            System.out.println("student == student1 ? " + (student == student1));


        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }
}
