package vn.unicloud.fnb.infrastructure;

import com.google.zxing.WriterException;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface QRCodeUtil {
    ByteArrayOutputStream getQRCode(String text, int width, int height) throws WriterException, IOException;

    BufferedImage getQRCodeBufferedImage(String content, int width, int height) throws WriterException;

    String getQRCodeBase64(String content, int width, int height) throws IOException, WriterException;

    enum ImageFormat {
        JPEG, PNG, GIF, WEBP, PDF
    }
}
