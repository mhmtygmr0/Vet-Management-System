package com.vetmanagement.core.config.modelMapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for configuring and providing ModelMapper instances for
 * mapping objects in the application. It implements the IModelMapperService
 * interface and provides different configurations for request and response mappings.
 */
@Service
public class ModelManagerService implements IModelMapperService {

    private final ModelMapper modelMapper;

    /**
     * Constructs a ModelManagerService with the provided ModelMapper instance.
     *
     * @param modelMapper the ModelMapper instance to be used for object mappings
     */
    @Autowired
    public ModelManagerService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Provides a ModelMapper instance configured for request mappings.
     * The matching strategy is set to STANDARD and ambiguity is ignored.
     *
     * @return a configured ModelMapper instance for request mappings
     */
    @Override
    public ModelMapper forRequest() {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        return this.modelMapper;
    }

    /**
     * Provides a ModelMapper instance configured for response mappings.
     * The matching strategy is set to LOOSE and ambiguity is ignored.
     *
     * @return a configured ModelMapper instance for response mappings
     */
    @Override
    public ModelMapper forResponse() {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return this.modelMapper;
    }
}
