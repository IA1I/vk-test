package com.example.vktest.controller;

import com.example.vktest.client.AlbumClient;
import com.example.vktest.dto.request.AlbumRequest;
import com.example.vktest.dto.response.AlbumResponse;
import com.example.vktest.entity.Album;
import com.example.vktest.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {
    private final AlbumClient albumClient;
    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumClient albumClient, AlbumService albumService) {
        this.albumClient = albumClient;
        this.albumService = albumService;
    }

    @GetMapping("")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ALBUMS')")
    public ResponseEntity<AlbumResponse[]> getAllAlbums() {
        AlbumResponse[] albumResponses = albumClient.getAllAlbums().block();
        List<Album> albums = albumService.getAll();
        return new ResponseEntity<>(albumResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ALBUMS')")
    public ResponseEntity<AlbumResponse> getAlbumById(@PathVariable Long id) {
        AlbumResponse albumResponse = albumClient.getAlbumById(id).block();
        Optional<Album> album = albumService.get(id);

        return new ResponseEntity<>(albumResponse, HttpStatus.OK);
    }

    @PostMapping("")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ALBUMS')")
    public ResponseEntity<AlbumResponse> createAlbum(@RequestBody AlbumRequest requestBody) {
        AlbumResponse albumResponse = albumClient.createAlbum(requestBody).block();
        Album album = albumService.save(albumResponse);

        return new ResponseEntity<>(albumResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ALBUMS')")
    public ResponseEntity<AlbumResponse> updateAlbum(@PathVariable Long id, @RequestBody AlbumRequest requestBody) {
        AlbumResponse albumResponse = albumClient.updateAlbum(id, requestBody).block();
        Album album = albumService.save(albumResponse);

        return new ResponseEntity<>(albumResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ALBUMS')")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        albumClient.deleteAlbum(id).block();
        albumService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
