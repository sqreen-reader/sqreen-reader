package net.sqreenreader.config.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.awt.Toolkit;
import java.awt.Taskbar;
import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    private final ApplicationContext applicationContext;
    private final String title;
    private final Toolkit toolkit;

    private Stage stage;

    private static final int WIDTH = 800;
    private static final int LENGTH = 600;

    public StageInitializer(final ApplicationContext applicationContext,
                            final @Value("${spring.application.name}") String title, final Toolkit toolkit) {
        this.applicationContext = applicationContext;
        this.title = title;
        this.toolkit = toolkit;
    }


    @Override
    public synchronized void onApplicationEvent(final StageReadyEvent stageReadyEvent) {
        try {
            stage = stageReadyEvent.getStage();
            FXMLLoader fxmlLoader = new FXMLLoader(new ClassPathResource("/fxml/sqreen-reader.fxml").getURL());
            fxmlLoader.setControllerFactory(applicationContext::getBean);

            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent, WIDTH, LENGTH);
            scene.getStylesheets().add(getClass().getResource("/fxml/sqreen-reader.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle(title);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
            setTaskbarIcon();
            stage.show();
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private void setTaskbarIcon() {
        try {
            Taskbar taskbar = Taskbar.getTaskbar();
            java.awt.Image image = toolkit.getImage(getClass().getResource("/icon.png"));
            taskbar.setIconImage(image);
        } catch (UnsupportedOperationException ignored) {
        }
    }

    public Stage getStage() {
        return stage;
    }
}
