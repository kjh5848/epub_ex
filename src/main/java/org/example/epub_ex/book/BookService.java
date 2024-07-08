package org.example.epub_ex.book;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;


    public String saveFile(MultipartFile file) throws IOException {
        Path copyLocation = Paths.get(uploadDir + "/" + file.getOriginalFilename());
        Files.copy(file.getInputStream(), copyLocation);
        return copyLocation.toString();
    }

    public void saveMetadata(Book book) {
        bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}