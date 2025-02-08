package co.quipux.pruebatecnica.service;

import co.quipux.pruebatecnica.data.TrackListEntity;
import co.quipux.pruebatecnica.data.mapper.concrete.ITrackListMapperEntity;
import co.quipux.pruebatecnica.domain.TrackListDomain;
import co.quipux.pruebatecnica.domain.rulesdomain.impl.CreateTrackListDomain;
import co.quipux.pruebatecnica.domain.rulesdomain.impl.DeleteTrackListDomain;
import co.quipux.pruebatecnica.domain.rulesdomain.impl.FindTrackListDomain;
import co.quipux.pruebatecnica.repository.ITrackListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackListService {
    private final ITrackListRepository trackListRepository;
    private final ITrackListMapperEntity trackListMapperEntity;
    private final CreateTrackListDomain createTrackListDomain;
    private final FindTrackListDomain findTrackListDomain;
    private final DeleteTrackListDomain deleteTrackListDomain;

    public TrackListService(
            ITrackListRepository trackListRepository,
            ITrackListMapperEntity trackListMapperEntity,
            CreateTrackListDomain createTrackListDomain,
            DeleteTrackListDomain deleteTrackListDomain,
            FindTrackListDomain findTrackListDomain
    ) {
        this.trackListRepository = trackListRepository;
        this.trackListMapperEntity = trackListMapperEntity;
        this.createTrackListDomain = createTrackListDomain;
        this.deleteTrackListDomain = deleteTrackListDomain;
        this.findTrackListDomain = findTrackListDomain;
    }

    public TrackListDomain createTrackList(TrackListDomain trackList) {
        trackList.setName(trackList.getName().trim().replace(" ", "_"));
        createTrackListDomain.validationRulesDomain(trackList.getName());

        return trackListMapperEntity.toDomain(trackListRepository.save(trackListMapperEntity.toEntity(trackList)));
    }

    public TrackListDomain getTrackList(String name) {
        findTrackListDomain.validationRulesDomain(name);
        return trackListMapperEntity.toDomain(trackListRepository.findByName(name));
    }

    public List<TrackListDomain> getTrackLists() {

        return trackListRepository.findAll().stream()
                .map(trackListMapperEntity::toDomain)
                .toList();
    }

    public void deleteTrackList(String name) {
        deleteTrackListDomain.validationRulesDomain(name);
        TrackListEntity trackListEntity = trackListRepository.findByName(name);
        trackListRepository.deleteById(trackListEntity.getId());
    }
}
