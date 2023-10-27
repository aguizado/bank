package com.example.bank.model.mapper;

import com.example.bank.model.RepresentativeModel;
import com.example.bank.model.dto.RepresentativeDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * . Class RepresentativeMapper
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Mapper(componentModel = "spring")
public interface RepresentativeMapper {
  
  RepresentativeMapper INSTANCE = Mappers.getMapper(RepresentativeMapper.class);
  
  RepresentativeModel toRepresentative(RepresentativeDto representativeDto);
  
  List<RepresentativeModel> toRepresentatives(List<RepresentativeDto> representativeDto);
  
  RepresentativeDto toEntity(RepresentativeModel customerModel);
  
  List<RepresentativeDto> toEntityList(List<RepresentativeModel> customerModel);

}
