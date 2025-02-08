package co.quipux.pruebatecnica.controllers;

import co.quipux.pruebatecnica.controllers.mapper.concrete.ITrackListMapperDTO;
import co.quipux.pruebatecnica.controllers.support.Response;
import co.quipux.pruebatecnica.controllers.support.SongDTO;
import co.quipux.pruebatecnica.controllers.support.TrackListDTO;
import co.quipux.pruebatecnica.domain.TrackListDomain;
import co.quipux.pruebatecnica.service.TrackListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/lists")
public class TrackListController {

    private final TrackListService trackListService;
    private final ITrackListMapperDTO trackListMapperDTO;

    public TrackListController(TrackListService trackListService, ITrackListMapperDTO trackListMapperDTO) {
        this.trackListService = trackListService;
        this.trackListMapperDTO = trackListMapperDTO;
    }

    @GetMapping("/dummy")
    public TrackListDTO dummy() {
        TrackListDTO trackListDTO = new TrackListDTO();
        trackListDTO.setCanciones(new ArrayList<>(List.of(new SongDTO())));
        return trackListDTO;
    }

    @PostMapping
    public ResponseEntity<Response<TrackListDTO>> createTrackList(@RequestBody TrackListDTO trackList) {
        try {
            TrackListDomain trackListDomain = trackListService.createTrackList(trackListMapperDTO.toDomain(trackList));
            return ResponseEntity.created(new URI("/api/v1/lists/" + trackListDomain.getName()))
                    .body(new Response<TrackListDTO>(HttpStatus.CREATED, "Track list created successfully", trackListMapperDTO.toDTO(trackListDomain)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Response<>(HttpStatus.BAD_REQUEST, e.getMessage(), null));
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<Response<TrackListDTO>> getTrackList(@PathVariable String name) {
        try {
            return ResponseEntity.ok(new Response<>(HttpStatus.OK, "Track list retrieved successfully",
                    trackListMapperDTO.toDTO(trackListService.getTrackList(name))));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Response<>(HttpStatus.BAD_REQUEST, e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<Response<List<TrackListDTO>>> getTrackLists() {
        try {
            return ResponseEntity.ok(new Response<>(HttpStatus.OK, "Track lists retrieved successfully",
                    trackListService.getTrackLists().stream()
                            .map(trackListMapperDTO::toDTO)
                            .toList()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Response<>(HttpStatus.BAD_REQUEST, e.getMessage(), null));
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Response<Void>> deleteTrackList(@PathVariable String name) {
        try {
            trackListService.deleteTrackList(name);
            return ResponseEntity.ok(new Response<>(HttpStatus.NO_CONTENT, "Track list deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Response<>(HttpStatus.BAD_REQUEST, e.getMessage(), null));
        }
    }
}
