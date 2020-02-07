//package org.acme;
//
//import io.smallrye.reactive.messaging.annotations.Channel;
//import org.reactivestreams.Publisher;
//
//import javax.inject.Inject;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import org.apache.camel.ProducerTemplate;
//import org.jboss.resteasy.annotations.SseElementType;
//
///**
// * A simple resource retrieving the in-memory "my-data-stream" and sending the
// * items as server-sent events.
// */
//@Path("/consumer")
//public class ExampleResource {
//
//    @Inject
//    @Channel("exit")
//    Publisher<String> elements;
//
//    @Inject
//    ProducerTemplate camelProducer;
//
//    @GET
//    @Path("/stream")
//    @Produces(MediaType.SERVER_SENT_EVENTS)
//    @SseElementType("application/json")
//    public Publisher<String> stream() {
//
//        return elements;
//    }
//}
