package vn.unicloud.fnb.application.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.unicloud.fnb.application.MeasureTime;

import java.awt.image.BufferedImage;

@RequestMapping("/api/1.0/diners")
public interface DinerAuthenticationController {
    @GetMapping(path = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    BufferedImage generateQRCodeImage(@RequestParam String url) throws Exception;


}
