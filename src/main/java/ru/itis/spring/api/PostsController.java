package ru.itis.spring.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.spring.persistence.entity.PostEntity;
import ru.itis.spring.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostsController {
    private final PostService service;

    @GetMapping("/{id}")
    public PostEntity getPost(@PathVariable("id") Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<PostEntity> getAllPosts() {
        return service.getAll();
    }

    @PostMapping
    public void savePost(@RequestParam String title, @RequestParam Long authorId) {
        service.save(title, authorId);
    }

    @DeleteMapping("/{id}")
    public PostEntity deletePost(@PathVariable Long id) {
        return service.delete(id);
    }
}
