package cloud.klasse.backendbusiness.file;

/**
 * Reports an error during file upload processing.
 */
public class FileUploadException extends RuntimeException {
    FileUploadException(final String message) {
        super(message);
    }

    FileUploadException(final String message, final Throwable ex) {
        super(message, ex);
    }
}
