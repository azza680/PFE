package com.boky.PFE.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WSConfig implements WebSocketConfigurer
{



        private static final String Chat_endpoint="/data";

        @Override
        public void registerWebSocketHandlers(WebSocketHandlerRegistry register) {
            // TODO Auto-generated method stub
            register.addHandler(getdatahandler(), Chat_endpoint)

                    .setAllowedOrigins("http://localhost:4200");


        }

        @Bean
        WebSocketHandler getdatahandler(){
            return new com.boky.PFE.websocket.WebSocketHandler();
        }



}
