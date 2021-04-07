package net.sqreenreader.service;

import net.screen.ImageGrabber;
import net.sqreenreader.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Application.class)
@Configuration
class BarcodeParserServiceIT {

    @Autowired
    private BarcodeParser barcodeParser;

    @MockBean
    private ImageGrabber imageGrabber;

    @Test
    void test() throws IOException {
        when(imageGrabber.grab(any(Rectangle.class)))
                .thenReturn(ImageIO.read(getClass().getResourceAsStream("/test.jpg")));

        assertEquals("https://www.geeksforgeeks.org", barcodeParser.parse(new Rectangle()));
    }

}
