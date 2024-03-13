package com.example.vktest.service;

import com.example.vktest.dto.response.AlbumResponse;
import com.example.vktest.entity.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    Album save(AlbumResponse albumResponse);

    void delete(Long id);

    Optional<Album> get(Long id);

    List<Album> getAll();
}
