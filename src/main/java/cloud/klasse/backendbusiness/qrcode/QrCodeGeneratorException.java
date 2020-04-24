package cloud.klasse.backendbusiness.qrcode;

/**
 * Generic exception for anything that went wrong with the QR code generation.
 */
public class QrCodeGeneratorException extends RuntimeException {
    QrCodeGeneratorException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
