package com.lec.thrift;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import thrift.generated.PersonService;

/**
 * @author zhwanwan
 * @create 2019-06-21 8:30 AM
 */
public class ThriftServer {

    public static void main(String[] args) throws Exception {

        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());

        arg.protocolFactory(new TCompactProtocol.Factory());    //协议层:高层
        arg.transportFactory(new TFramedTransport.Factory());   //传输层:底层
        arg.processorFactory(new TProcessorFactory(processor)); //

        TServer server = new THsHaServer(arg);
        System.out.println("Thrift Server Started");
        server.serve();

    }
}
