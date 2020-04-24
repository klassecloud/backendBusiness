package cloud.klasse.backendbusiness.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * {@link QrCodeGenerator} based on the Google zxing library
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ZxingCodeGenerator implements QrCodeGenerator {

    private final QRCodeWriter codeWriter = new QRCodeWriter();

    private final QrCodeGeneratorProperties properties;

    @Override
    public byte[] createCode(String text) {
        try {
            final var matrix = codeWriter.encode(text, BarcodeFormat.QR_CODE, properties.getWidth(), properties.getHeight());
            final var buffer = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(matrix, properties.getFormat().name(), buffer);
            return buffer.toByteArray();
        } catch (final IOException | WriterException ex) {
            throw new QrCodeGeneratorException(ex.getMessage(), ex);
        }
    }

}
