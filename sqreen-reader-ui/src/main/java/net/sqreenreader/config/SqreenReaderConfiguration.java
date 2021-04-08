package net.sqreenreader.config;

import com.google.zxing.MultiFormatReader;
import net.qr.BarCodeReader;
import net.qr.QRCodeReader;
import net.screen.ImageGrabber;
import net.screen.ScreenGrabber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.AWTException;
import java.awt.Desktop;


@Configuration
public class SqreenReaderConfiguration {

    @Bean
    public ImageGrabber imageGrabber() throws AWTException {
        return new ScreenGrabber(new Robot());
    }

    @Bean
    public BarCodeReader barCodeReader() {
        return new QRCodeReader(new MultiFormatReader());
    }

    @Bean
    public Toolkit toolkit() {
        return Toolkit.getDefaultToolkit();
    }

    @Bean
    public Desktop desktop() {
        if (!Desktop.isDesktopSupported()) {
            throw new ExceptionInInitializerError("Desktop not supported");
        }
        return Desktop.getDesktop();
    }
}
