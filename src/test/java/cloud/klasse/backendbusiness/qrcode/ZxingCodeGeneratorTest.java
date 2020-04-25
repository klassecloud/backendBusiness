package cloud.klasse.backendbusiness.qrcode;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.assertThat;

class ZxingCodeGeneratorTest {

    private final QRCodeReader codeReader = new QRCodeReader();

    private final ZxingCodeGenerator classUnderTest = new ZxingCodeGenerator(new QrCodeGeneratorProperties());

    @Test
    void name() throws Exception {
        final var buffer = classUnderTest.createCode("https://klasse.cloud/join?code=2r1mv17am2u9zfs5xKCslQ");
        final var image = ImageIO.read(new ByteArrayInputStream(buffer));
        final var luminanceSource = new BufferedImageLuminanceSource(image);
        final var result = codeReader.decode(new BinaryBitmap(new HybridBinarizer(luminanceSource))).getText();
        assertThat(result).isEqualTo("https://klasse.cloud/join?code=2r1mv17am2u9zfs5xKCslQ");
    }
}
