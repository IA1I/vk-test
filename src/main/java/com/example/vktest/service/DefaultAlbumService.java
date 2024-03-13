package com.example.vktest.service;

import com.example.vktest.dto.response.AlbumResponse;
import com.example.vktest.entity.Album;
import com.example.vktest.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DefaultAlbumService implements AlbumService {
    private final AlbumRepository albumRepository;

    @Autowired
    public DefaultAlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    @Transactional
    public Album save(AlbumResponse albumResponse) {
        Album album = mapAlbumResponseToAlbum(albumResponse);
        return albumRepository.save(album);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        albumRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Album> get(Long id) {
        return albumRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Album> getAll() {
        var source = albumRepository.findAll();
        List<Album> target = new ArrayList<>();
        source.forEach(target::add);

        return target;
    }

    private Album mapAlbumResponseToAlbum(AlbumResponse albumResponse) {
        Album album = new Album();
        album.setId(albumResponse.id());
        album.setUserId(albumResponse.userId());
        album.setTitle(albumResponse.title());
        return album;
    }
}
