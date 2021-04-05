package net.sqreenreader.config.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class SqreenReaderApplication extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void start(Stage stage) {
        context.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void init() {
        context = new SpringApplicationBuilder()
                .headless(false)
                .sources(net.sqreenreader.Application.class)
                .run();
    }

    @Override
    public void stop() {
        context.close();
        Platform.exit();
    }
}
