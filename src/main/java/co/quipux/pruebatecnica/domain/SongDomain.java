package co.quipux.pruebatecnica.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongDomain {
    private UUID id;
    private String title;
    private String artist;
    private String album;
    private String year;
    private String genre;
}
