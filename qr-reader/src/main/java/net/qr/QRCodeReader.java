package net.qr;

import com.google.zxing.Reader;
import com.google.zxing.Writer;
import com.google.zxing.FormatException;
import com.google.zxing.ChecksumException;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class QRCodeReader implements BarCodeReader {

    private final Reader reader;

    private final Writer writer;

    private static final int WIDTH = 200;
    private static final int LENGTH = 200;

    public QRCodeReader(final Reader reader, final Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public Barcode read(final BufferedImage image) throws IOException {
        try {
            return tryRead(image);
        } catch (NotFoundException e) {
            return null;
        } catch (FormatException | ChecksumException | WriterException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    private Barcode tryRead(final BufferedImage image) throws FormatException, ChecksumException,
            NotFoundException, WriterException {
        BinaryBitmap binaryBitmap = new BinaryBitmap(
                new HybridBinarizer(
                        new BufferedImageLuminanceSource(image)
                )
        );

        String text = reader.decode(binaryBitmap).getText();
        BufferedImage qrCode = generateQRCode(text);
        return new Barcode(text, qrCode);
    }

    private BufferedImage generateQRCode(final String text) throws WriterException {
        BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE, WIDTH, LENGTH);
        return MatrixToImageWriter.toBufferedImage(matrix);
    }
}
