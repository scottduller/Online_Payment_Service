/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.thrift.timestampserver;

import com.sd479.webapps2020.thrift.timestamp.TransactionTimestampService;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 *
 * @author Scott
 */
@Startup
@Singleton
public class TimestampServer {

    public static TransactionTimestampHandler handler;
    public static TransactionTimestampService.Processor processor;
    public static TServerTransport serverTransport;
    public static TServer server;

    @PostConstruct
    public void init() {
        handler = new TransactionTimestampHandler();
        processor = new TransactionTimestampService.Processor(handler);
        Runnable simple = new Runnable() {
            @Override
            public void run() {
                simple(processor);
            }
        };

        new Thread(simple).start();
    }

    @PreDestroy
    public void cleanup() {
        server.stop();
    }

    public static void simple(TransactionTimestampService.Processor processor) {
        try {
            serverTransport = new TServerSocket(10001);
            server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
            server.serve();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
