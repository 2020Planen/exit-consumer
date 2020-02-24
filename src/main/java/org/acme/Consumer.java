/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.acme;

import com.google.gson.Gson;
import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException; 
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.inject.Inject;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.reactive.messaging.Incoming;

/**
 *
 * @author Mathias
 */ 
public class Consumer {

    @Incoming("exit")

    public void handle(String content) {
        try {
            consumeEntry(content);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("----------- in catch --------------- \n\n");

        }
    }

    @Inject
    ProducerTemplate camelProducer; 

    public void consumeEntry(String content) throws IOException, InterruptedException, ExecutionException, TimeoutException, Exception {
        Gson gson = new Gson();
        Message msg = gson.fromJson(content, Message.class);
        msg.startLog("exit-consumer");

        msg.endLog();
        //Store in database
        System.out.println("\n------------- Sending entry to database... Time: " + LocalTime.now() + "-------------\n");
        CompletableFuture future = camelProducer.asyncSendBody("couchdb:http://cis-x.convergens.dk:5984/finished?username=admin&password=password", content);
        System.out.println(" future resolved " + future.get(5, TimeUnit.SECONDS));
        if (future.isDone()) {
            System.out.println("\n------------- Succesfully sent to database -------------\n");

        } else {
            System.out.println("Ikke sendt til databasen ---------------");
            //TODO find ud af hvad der skal ske
        }

    }

}
