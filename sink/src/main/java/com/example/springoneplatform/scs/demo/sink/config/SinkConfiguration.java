package com.example.springoneplatform.scs.demo.sink.config;

import com.example.springoneplatform.scs.demo.model.etl.PayloadWrapper;
import com.example.springoneplatform.scs.demo.model.pdx.Customer;
import com.example.springoneplatform.scs.demo.model.pdx.CustomerOrder;
import com.example.springoneplatform.scs.demo.model.pdx.Item;
import com.example.springoneplatform.scs.demo.sink.extractor.CustomerExtractor;
import com.example.springoneplatform.scs.demo.sink.extractor.CustomerOrderExtractor;
import com.example.springoneplatform.scs.demo.sink.extractor.ItemExtractor;
import com.example.springoneplatform.scs.demo.sink.extractor.PayloadWrapperExtractor;
import com.example.springoneplatform.scs.demo.sink.messaging.GeodeMessageHandler;
import org.apache.geode.cache.client.ClientCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.Map;
import java.util.function.Consumer;


@Configuration
public class SinkConfiguration {
/*
    @Value("${aggregator.groupCount}")
    private int aggregatorGroupCount;

    @Value("${aggregator.batchSize}")
    private int aggregatorBatchSize;

    @Value("${aggregator.batchTimeout}")
    private int aggregatorBatchTimeout;

    @Value("${geode.locator}")
    private String geodeLocator;

    @Value("${geode.port}")
    private int geodeLocatorPort;

*/
    private static final Logger logger = LoggerFactory.getLogger(SinkConfiguration.class);

/*
    @Bean
    GeodeMessageAggregator geodeAggregator(ApplicationContext context) {
        return new GeodeMessageAggregator(context, aggregatorGroupCount,
                aggregatorBatchSize);
    }

    @Bean
    Consumer<AggregatorSpec> geodeAggregateConsumer(GeodeMessageAggregator geodeAggregator) {
        return aggregatorSpec -> {
            aggregatorSpec.processor(geodeAggregator, null);
            aggregatorSpec.groupTimeout(aggregatorBatchTimeout);
            aggregatorSpec.sendPartialResultOnExpiry(true);
            aggregatorSpec.expireGroupsUponCompletion(true);
            aggregatorSpec.expireGroupsUponTimeout(true);
            // TODO: persisted message store
        };
    }
*/
    @Bean
    GeodeMessageHandler geodeMessageHandler(ClientCache clientCache, ApplicationContext context){
        return new GeodeMessageHandler(clientCache, context);
    }


    @Bean
    public Consumer<Message<PayloadWrapper>> sink( ClientCache clientCache, ApplicationContext context,
                                                    GeodeMessageHandler geodeMessageHandler){
        return message -> {
            logger.debug("Received Message: "+message.getPayload());
            geodeMessageHandler.handleMessage(message);
        }
        ;
    }

    @Bean
    PayloadWrapperExtractor<Map, Customer> customerExtractor() {
        return new CustomerExtractor();
    }

    @Bean
    PayloadWrapperExtractor<Map, CustomerOrder> customerOrderExtractor() {
        return new CustomerOrderExtractor();
    }

    @Bean
    PayloadWrapperExtractor<Map, Item> itemExtractor() {
        return new ItemExtractor();
    }
}
