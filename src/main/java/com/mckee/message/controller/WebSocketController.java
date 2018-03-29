package com.mckee.message.controller;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RestController;

import com.mckee.message.entity.Greeting;
import com.mckee.message.entity.HelloMessage;

@RestController
public class WebSocketController {
	
	public SimpMessagingTemplate template;  

	/**
	 * 广播，对所有用户
	 * @param message
	 * @return
	 * @throws Exception
	 */
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
		Thread.sleep(1000); // simulated delay
		return new Greeting("Hello, " + message.getName() + "!");
	}

	/**
	 * 点对点，对单一用户
	 * @param userMessage
	 * @return
	 * @throws Exception
	 */
	@MessageMapping("/message")  
    @SendToUser("/message")  
    public HelloMessage userMessage(HelloMessage userMessage) throws Exception {  
        return userMessage;  
    }
	
//	在程序中的其他地方要动态发送消息，就是下面这两句代码：
//	webSocketController.template.convertAndSend("/topic/hello",greeting) //广播  
//	webSocketController.template.convertAndSendToUser(userId, "/message",userMessage) //一对一发送，发送特定的客户端 

}
