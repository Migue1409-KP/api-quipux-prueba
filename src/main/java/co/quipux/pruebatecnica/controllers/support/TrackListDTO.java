package co.quipux.pruebatecnica.controllers.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrackListDTO {
    private String nombre;
    private String descripcion;
    private List<SongDTO> canciones;
}
