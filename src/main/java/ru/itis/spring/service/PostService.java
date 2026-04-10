package ru.itis.spring.service;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.spring.persistence.entity.PostEntity;
import ru.itis.spring.persistence.entity.UserEntity;
import ru.itis.spring.persistence.repository.PostRepository;
import ru.itis.spring.persistence.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void save(String title, Long authorId) {
        if (StringUtils.isBlank(title)) {
            throw new IllegalArgumentException("Title must not be null or empty");
        }

        UserEntity user = userRepository.findById(authorId).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User doesn't exist");
        }

        PostEntity post = PostEntity.builder()
                .title(title)
                .author(user)
                .build();

        postRepository.save(post);
    }

    public PostEntity get(long id) {
        return postRepository.findById(id).orElse(null);
    }

    public List<PostEntity> getAll() {
        return postRepository.findAll();
    }

    public PostEntity delete(long id) {
        PostEntity post = get(id);
        if (post != null) {
            postRepository.deleteById(id);
        }
        return post;
    }
}
