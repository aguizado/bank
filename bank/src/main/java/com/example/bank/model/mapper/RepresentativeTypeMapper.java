package com.example.bank.model.mapper;

import com.example.bank.model.RepresentativeTypeModel;
import com.example.bank.model.dto.RepresentativeTypeDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * . Class RepresentativeTypeMapper
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Mapper(componentModel = "spring")
public interface RepresentativeTypeMapper {
  
  RepresentativeTypeMapper INSTANCE = Mappers.getMapper(RepresentativeTypeMapper.class);
  
  RepresentativeTypeModel toRepresentativeType(RepresentativeTypeDto representativeTypeDto);
  
  List<RepresentativeTypeModel> toRepresentativeTypes(
      List<RepresentativeTypeDto> representativeTypeDto);
  
  RepresentativeTypeDto toEntity(RepresentativeTypeModel representativeTypeModel);
  
  List<RepresentativeTypeDto> toEntityList(List<RepresentativeTypeModel> representativeTypeModel);

}
