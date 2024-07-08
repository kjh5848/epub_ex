package org.example.epub_ex.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books";
    }

    @GetMapping("/books/upload")
    public String uploadBookForm() {
        return "upload";
    }

    @PostMapping("/books/upload")
    public String uploadBook(@RequestParam("title") String title,
                             @RequestParam("author") String author,
                             @RequestParam("file") MultipartFile file) throws IOException {
        String fileUrl = bookService.saveFile(file);

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setFileUrl(fileUrl);

        bookService.saveMetadata(book);

        return "redirect:/books";
    }
}