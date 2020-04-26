package cloud.klasse.backendbusiness.file;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileNotFoundException;
import java.util.Optional;

class FileServiceTest {
    public FileService fileService;
    public FileRepository fileRepositoryMock;
    public File fileMockSave;
    public File fileMockGet;

    @BeforeEach
    void setUp() {
        fileRepositoryMock = Mockito.mock(FileRepository.class);
        fileService = new FileService(fileRepositoryMock);
        fileMockSave = Mockito.mock(File.class);
        fileMockGet = Mockito.mock(File.class);
        Mockito.when(fileRepositoryMock.save(Mockito.any(File.class))).thenReturn(fileMockSave).thenReturn(null);
        Mockito.when(fileRepositoryMock.findById(Mockito.any(String.class)))
                .thenReturn(Optional.of(fileMockGet)).thenReturn(null);
    }

    @Test
    void storeFile() {
        final MultipartFile file = Mockito.mock(MultipartFile.class);
        Mockito.when(file.getOriginalFilename()).thenReturn("sampleName.pdf");
        assertThat(fileService.storeFile(file)).isEqualTo(fileMockSave);
    }

    @Test
    void getFile() throws FileNotFoundException {
        assertThat(fileService.getFile("myRandomString ABC")).isEqualTo(fileMockGet);
    }
}