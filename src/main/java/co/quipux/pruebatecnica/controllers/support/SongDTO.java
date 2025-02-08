package co.quipux.pruebatecnica.controllers.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongDTO {
    private String titulo;
    private String artista;
    private String album;
    private String anno;
    private String genero;
}
