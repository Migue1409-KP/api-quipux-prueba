package co.quipux.pruebatecnica;

import co.quipux.pruebatecnica.data.TrackListEntity;
import co.quipux.pruebatecnica.data.mapper.concrete.ITrackListMapperEntity;
import co.quipux.pruebatecnica.domain.TrackListDomain;
import co.quipux.pruebatecnica.domain.rulesdomain.impl.CreateTrackListDomain;
import co.quipux.pruebatecnica.domain.rulesdomain.impl.DeleteTrackListDomain;
import co.quipux.pruebatecnica.domain.rulesdomain.impl.FindTrackListDomain;
import co.quipux.pruebatecnica.repository.ITrackListRepository;
import co.quipux.pruebatecnica.service.TrackListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestTrackListService {

    @Mock
    private ITrackListRepository trackListRepository;
    @Mock
    private ITrackListMapperEntity trackListMapperEntity;
    @Mock
    private CreateTrackListDomain createTrackListDomain;
    @Mock
    private FindTrackListDomain findTrackListDomain;
    @Mock
    private DeleteTrackListDomain deleteTrackListDomain;

    @InjectMocks
    private TrackListService trackListService;

    private TrackListDomain trackListDomain;
    private TrackListEntity trackListEntity;
    private String trackListName;

    @BeforeEach
    void setUp() {
        trackListName = "Mi_Lista";
        trackListDomain = new TrackListDomain();
        trackListDomain.setName(trackListName);

        trackListEntity = new TrackListEntity();
        trackListEntity.setId(UUID.randomUUID());
        trackListEntity.setName(trackListName);
    }

    @Test
    void testCreateTrackList() {
        when(trackListMapperEntity.toEntity(any(TrackListDomain.class))).thenReturn(trackListEntity);
        when(trackListRepository.save(any(TrackListEntity.class))).thenReturn(trackListEntity);
        when(trackListMapperEntity.toDomain(any(TrackListEntity.class))).thenReturn(trackListDomain);

        TrackListDomain result = trackListService.createTrackList(trackListDomain);

        verify(createTrackListDomain).validationRulesDomain(trackListName);
        verify(trackListRepository).save(any(TrackListEntity.class));
        verify(trackListMapperEntity).toDomain(trackListEntity);

        assertNotNull(result);
        assertEquals(trackListDomain.getName(), result.getName());
    }

    @Test
    void testGetTrackList() {
        when(trackListRepository.findByName(trackListName)).thenReturn(trackListEntity);
        when(trackListMapperEntity.toDomain(trackListEntity)).thenReturn(trackListDomain);

        TrackListDomain result = trackListService.getTrackList(trackListName);

        verify(findTrackListDomain).validationRulesDomain(trackListName);
        verify(trackListRepository).findByName(trackListName);
        verify(trackListMapperEntity).toDomain(trackListEntity);

        assertNotNull(result);
        assertEquals(trackListDomain.getName(), result.getName());
    }

    @Test
    void testGetTrackLists() {
        List<TrackListEntity> entityList = List.of(trackListEntity);
        List<TrackListDomain> domainList = List.of(trackListDomain);

        when(trackListRepository.findAll()).thenReturn(entityList);
        when(trackListMapperEntity.toDomain(trackListEntity)).thenReturn(trackListDomain);

        List<TrackListDomain> result = trackListService.getTrackLists();

        verify(trackListRepository).findAll();
        verify(trackListMapperEntity, times(1)).toDomain(trackListEntity);

        assertEquals(domainList.size(), result.size());
        assertEquals(domainList.get(0).getName(), result.get(0).getName());
    }

    @Test
    void testDeleteTrackList() {
        when(trackListRepository.findByName(trackListName)).thenReturn(trackListEntity);

        trackListService.deleteTrackList(trackListName);

        verify(deleteTrackListDomain).validationRulesDomain(trackListName);
        verify(trackListRepository).findByName(trackListName);
        verify(trackListRepository).deleteById(trackListEntity.getId());
    }
}