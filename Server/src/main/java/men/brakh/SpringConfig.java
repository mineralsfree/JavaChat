package men.brakh;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class SpringConfig {
 private static Server server;
@Bean
   public static Server server(){
        try {
        server = new Server();
            return server;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Server getServer() {
        return server;
   }
}
