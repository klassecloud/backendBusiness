package cloud.klasse.backendbusiness.qrcode;

/**
 * Allows to encode a specified text into a <a href="https://de.wikipedia.org/wiki/QR-Code">QR-Code</a>
 */
public interface QrCodeGenerator {

    /**
     * Create a QR Code for the specified text
     *
     * @param text The text to encode
     * @return A CQ Code image
     */
    byte[] createCode(final String text);

}
