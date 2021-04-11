/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.thrift.timestampserver;

import com.sd479.webapps2020.thrift.timestamp.TransactionTimestampService;
import org.apache.thrift.TException;

/**
 *
 * @author Scott
 */
public class TransactionTimestampHandler implements TransactionTimestampService.Iface {

    @Override
    public long transactionTimestamp() throws TException {
        long dateTime = System.currentTimeMillis();
        System.out.println("Timestamp() called: " + dateTime + ", in Thread: " + Thread.currentThread().getId());
        return dateTime;
    }

}
