package dev.veterinary.veterinary_app.core.config.modelMapper;

import org.modelmapper.ModelMapper;

public interface IModelMapperService {
    ModelMapper forRequest();

    ModelMapper forResponse();
}
