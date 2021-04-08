package net.sqreenreader.config.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    private final ApplicationContext applicationContext;
    private final String title;

    private static final int WIDTH = 800;
    private static final int LENGTH = 600;

    public StageInitializer(final ApplicationContext applicationContext,
                            final @Value("${spring.application.name}") String title) {
        this.applicationContext = applicationContext;
        this.title = title;
    }


    @Override
    public void onApplicationEvent(final StageReadyEvent stageReadyEvent) {
        try {
            Stage stage = stageReadyEvent.getStage();
            FXMLLoader fxmlLoader = new FXMLLoader(new ClassPathResource("/fxml/sqreen-reader.fxml").getURL());
            fxmlLoader.setControllerFactory(applicationContext::getBean);

            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent, WIDTH, LENGTH);
            scene.getStylesheets().add(getClass().getResource("/fxml/sqreen-reader.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
