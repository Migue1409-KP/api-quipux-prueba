package co.quipux.pruebatecnica.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrackListDomain {
    private UUID id;
    private String name;
    private String description;
    private List<SongDomain> songs;
}
