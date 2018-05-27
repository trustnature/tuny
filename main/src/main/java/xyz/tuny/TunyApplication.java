package xyz.tuny;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * 启动程序
 * 
 * @author tuny
 */
@SpringBootApplication
@ImportResource(locations={"classpath:applicationContext.xml"})
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class TunyApplication
{
    public static void main(String[] args)
    {
        SpringApplication app = new SpringApplication(TunyApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        System.out.println("\n" +
                "----------  -----       ---\n" +
                "----------  | |\\ \\      | |      \n" +
                "    | |     | | \\ \\     | |      \n" +
                "    | |     | |  \\ \\    | |      \n" +
                "    | |     | |   \\ \\   | |      \n" +
                "    | |     | |    \\ \\  | |      \n" +
                "    | |     | |     \\ \\ | |      \n" +
                "    | |     | |      \\ \\| |      \n"
        );
    }
}