package ru.job4j.dreamjob.controller;

import org.springframework.http.ResponseEntity;
import ru.job4j.dreamjob.model.File;
import ru.job4j.dreamjob.service.FileService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.ConcurrentModel;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.dreamjob.dto.FileDto;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.service.CandidateService;
import ru.job4j.dreamjob.service.SimpleFileService;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

class FileControllerTest {
    private FileService fileService;

    private FileController fileController;

    private MultipartFile testFile;

    @BeforeEach
    public void initServices() {
        this.fileService = mock(FileService.class);
        fileController = new FileController(fileService);
        testFile = new MockMultipartFile("testFile.img", new byte[] {1, 2, 3});
    }

    @Test
    public void whenGetFileById() throws IOException {
        File file = new File("File", "files/test_file.txt");
        var fileDto = new FileDto(testFile.getOriginalFilename(), testFile.getBytes());
        when(fileService.getFileById(any(Integer.class))).thenReturn(Optional.of(fileDto));

        var result = fileController.getById(1);
        assertThat(result).isEqualTo(ResponseEntity.ok(fileDto.getContent()));
    }
}