package co.quipux.pruebatecnica.domain.rulesdomain.impl;

import co.quipux.pruebatecnica.domain.rulesdomain.TrackListExists;
import org.springframework.stereotype.Service;

@Service
public class FindTrackListDomain {
    private final TrackListExists trackListExists;

    public FindTrackListDomain(TrackListExists trackListExists) {
        this.trackListExists = trackListExists;
    }

    public void validationRulesDomain(String name) {
        trackListExists.validationRuleNameNotNull(name);
        trackListExists.validationRuleNameNotSpecialSymbols(name);
        trackListExists.validationRuleNameDoesNotExist(name);
    }
}
