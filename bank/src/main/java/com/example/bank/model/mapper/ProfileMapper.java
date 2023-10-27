package com.example.bank.model.mapper;

import com.example.bank.model.ProfileModel;
import com.example.bank.model.dto.ProfileDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * . Class ProfileMapper
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/26
 */
@Mapper(componentModel = "spring")
public interface ProfileMapper {
  
  ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);
  
  ProfileModel toProfile(ProfileDto profileDto);
  
  List<ProfileModel> toProfiles(List<ProfileDto> profileDto);
  
  ProfileDto toEntity(ProfileModel profileModel);
  
  List<ProfileDto> toEntityList(List<ProfileModel> profileModel);

}
