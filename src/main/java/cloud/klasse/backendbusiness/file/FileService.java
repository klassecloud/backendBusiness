package cloud.klasse.backendbusiness.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {
    private final FileRepository fileRepository;

    public File storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // TODO: Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            File dbFile = new File();
            dbFile.setFileName(fileName);
            dbFile.setMimeType(file.getContentType());
            dbFile.setContent(file.getBytes());
            dbFile.setCompressed(false);
            dbFile.setTasks(new ArrayList<>());
            dbFile.setAlt("Lorem Ipsum");

            return fileRepository.save(dbFile);
        } catch (Exception ex) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public File getFile(String fileId) throws FileNotFoundException {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }

    public void deleteFile(String fileId) throws FileNotFoundException {
        if(fileRepository.existsById(fileId)) {
            log.warn("File of id " + fileId + " does not exist and thus can not be deleted!");
            throw new FileNotFoundException(fileId);
        }
        fileRepository.deleteById(fileId);
    }

    void deleteAll() {
        fileRepository.deleteAll();
        log.info("Deleted all files in store.");
    }

}
