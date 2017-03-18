import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
/**
 * Created by toshiba on 17.03.2017.
 */
@ComponentScan
@EnableAutoConfiguration
public class Main {
    public static void main(String[] args)
    {
        SpringApplication.run(Main.class, args);
        System.out.println("Hello world");
    }
}
