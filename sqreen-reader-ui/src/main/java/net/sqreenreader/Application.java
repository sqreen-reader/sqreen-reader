package net.sqreenreader;

import net.sqreenreader.config.javafx.SqreenReaderApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        javafx.application.Application.launch(SqreenReaderApplication.class, args);
    }
}
