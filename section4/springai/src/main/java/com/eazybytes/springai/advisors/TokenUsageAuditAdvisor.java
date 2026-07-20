package com.eazybytes.springai.advisors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class TokenUsageAuditAdvisor implements CallAdvisor {
    private static final Logger logger = LoggerFactory.getLogger(TokenUsageAuditAdvisor.class);

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        //send the request to LLM, ChatClientResponse is a high level container that includes
        //both the AI model's output and runtime context data
        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);
        //ChatResponse is the lower-level object that maps directly to the raw payload returned by the API provider
        ChatResponse chatResponse = chatClientResponse.chatResponse();
        if (chatResponse.getMetadata() != null) {
            Usage usage = chatResponse.getMetadata().getUsage();
            if (usage != null) {
                logger.info("Token usage details: {}", usage.toString());
            }
        }
        return chatClientResponse;
    }

    @Override
    public String getName() {
        return "TokenUsageAuditAdvisor";
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
