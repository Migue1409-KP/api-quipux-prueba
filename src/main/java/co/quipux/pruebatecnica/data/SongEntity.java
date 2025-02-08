package co.quipux.pruebatecnica.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SongEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;

    private String artist;

    private String album;

    @Column(name = "song_year")
    private String year;

    private String genre;
}