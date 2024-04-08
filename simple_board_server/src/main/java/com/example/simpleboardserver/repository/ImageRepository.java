package com.example.simpleboardserver.repository;

import com.example.simpleboardserver.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByBoardId(Long id);
}
