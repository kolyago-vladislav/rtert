package com.itm.space.taskservice.mapper;

import com.itm.space.taskservice.domain.view.AttachmentsView;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;
import java.util.UUID;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ViewMapper {
    UUID map(AttachmentsView view);

}
