package vn.unicloud.fnb.infrastructure.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.unicloud.fnb.infrastructure.QRCodeUtil;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class QRCodeUtilsImpl implements QRCodeUtil {

    @Override
    public ByteArrayOutputStream getQRCode(String content, int width, int height) throws WriterException, IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(genBitMatrix(content, width, height), ImageFormat.PNG.name(), byteArrayOutputStream);
        return byteArrayOutputStream;
    }

    private BitMatrix genBitMatrix(String content, int width, int height) throws WriterException {
        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.MARGIN, 1);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        return qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hintMap);
    }

    public BufferedImage getQRCodeBufferedImage(String content, int width, int height) throws WriterException {
        return MatrixToImageWriter.toBufferedImage(genBitMatrix(content, width, height));
    }

    public String getQRCodeBase64(String content, int width, int height) throws IOException, WriterException {
        return Base64.getEncoder().encodeToString(getQRCode(content, width, height).toByteArray());
    }
}
