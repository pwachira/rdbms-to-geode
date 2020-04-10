package com.example.springoneplatform.scs.demo.processor.handler;

import com.example.springoneplatform.scs.demo.model.etl.PayloadWrapper;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

public class JdbcEventMessageHandler {

    private final ApplicationContext context;

    public JdbcEventMessageHandler(ApplicationContext context) {
        super();
        this.context = context;
    }

    public Message<?> handleMessage(Message<Map<String, ?>> message) {
        MessageHeaders headers = message.getHeaders();
        Map<String, ?> requestPayload = message.getPayload();
        String srcGroup = (String) requestPayload.get("SRC_GROUP");
        String srcKey = (String) requestPayload.get("SRC_KEY");
        String actionCode = (String) requestPayload.get("ACTION_CODE");
        long originalMessageTimestamp = (long) headers.get(MessageHeaders.TIMESTAMP);
//        long originalEventTimestamp = (long) ((Date) requestPayload.get("EVENT_TS")).getTime();
        String tsFormatted = requestPayload.get("EVENT_TS").toString().replaceFirst("\\d\\d$",":00");
        long originalEventTimestamp = OffsetDateTime.parse(tsFormatted, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toEpochSecond();
        PayloadWrapperDao<?> dao = (PayloadWrapperDao<?>) context.getBean(srcGroup + "Dao");
        PayloadWrapper<?> payloadWrapper = dao.getData(srcKey);
        Message<?> processedMessage = MessageBuilder
                .withPayload(payloadWrapper)
                .copyHeaders(headers)
                .setHeader("srcGroup", srcGroup)
                .setHeader("srcKey", srcKey)
                .setHeader("actionCode", actionCode)
                .setHeader("originalMessageTimestamp", originalMessageTimestamp)
                .setHeader("originalEventTimestamp", originalEventTimestamp)
                .build();
        return processedMessage;
    }

}
