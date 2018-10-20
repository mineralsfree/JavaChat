package men.brakh;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class SpringConfig {
    private static Server server;
    @Bean
    Server server(){
        try {
            server = new Server();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return server;
    }

    public static Server getServer() {
        return server;
    }
}
