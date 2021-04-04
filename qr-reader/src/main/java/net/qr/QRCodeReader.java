package net.qr;

import com.google.zxing.Reader;
import com.google.zxing.FormatException;
import com.google.zxing.ChecksumException;
import com.google.zxing.NotFoundException;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class QRCodeReader implements BarCodeReader {

    private final Reader reader;

    public QRCodeReader(final Reader multiFormatReader) {
        this.reader = multiFormatReader;
    }

    @Override
    public String read(final BufferedImage image) throws IOException {
        try {
            return tryRead(image);
        } catch (FormatException | ChecksumException | NotFoundException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    private String tryRead(final BufferedImage image) throws FormatException, ChecksumException, NotFoundException {
        BinaryBitmap binaryBitmap = new BinaryBitmap(
                new HybridBinarizer(
                        new BufferedImageLuminanceSource(image)
                )
        );
        return reader.decode(binaryBitmap).getText();
    }
}