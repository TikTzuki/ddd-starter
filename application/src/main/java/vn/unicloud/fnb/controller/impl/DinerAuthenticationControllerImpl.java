package vn.unicloud.fnb.controller.impl;

import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.fnb.controller.DinerAuthenticationController;
import vn.unicloud.fnb.infrastructure.QRCodeUtil;

import java.awt.image.BufferedImage;

@RestController
@RequiredArgsConstructor
public class DinerAuthenticationControllerImpl implements DinerAuthenticationController {
    private final QRCodeUtil qrCodeUtil;

    @Override
    public BufferedImage generateQRCodeImage(String url) throws WriterException {
        return qrCodeUtil.getQRCodeBufferedImage(url, 250, 250);
    }
}
