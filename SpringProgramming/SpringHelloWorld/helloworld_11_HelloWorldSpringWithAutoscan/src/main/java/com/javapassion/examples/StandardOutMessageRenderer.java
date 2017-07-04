package com.javapassion.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("renderer")   // This is the same as @Component(value="renderer")
public class StandardOutMessageRenderer implements MessageRenderer {

	@Autowired
    private MessageProvider messageProvider = null;

    public void render() {
        if (messageProvider == null) {
            throw new RuntimeException(
                    "You must set the property messageProvider of class:"
                            + StandardOutMessageRenderer.class.getName());
        }

        System.out.println(messageProvider.getMessage());
    }

    // Not needed anymore since it will be autowired
//    public void setMessageProvider(MessageProvider provider) {
//        this.messageProvider = provider;
//    }

}