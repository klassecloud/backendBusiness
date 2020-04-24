package cloud.klasse.backendbusiness.qrcode;

import lombok.Data;
import lombok.NonNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * Configurable values for the QR-Code generation with sensible defaults. Available as a bean on the application context
 */
@Data
@Service
@ConfigurationProperties("klasse.cloud.qr-code")
public class QrCodeGeneratorProperties {

    private int width = 100;
    private int height = 100;

    @NonNull
    private Format format = Format.PNG;

    enum Format {
        JPG, PNG
    }

}
