package com.example.bank.service.impl;

import com.example.bank.model.ProfileModel;
import com.example.bank.model.dto.ProfileDto;
import com.example.bank.model.mapper.ProfileMapper;
import com.example.bank.repository.ProfileRepository;
import com.example.bank.service.IprofileService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * . Class ProfileServiceImpl
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/26
 */
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements IprofileService {
  
  private final ProfileRepository profileRepository;
  
  private final ProfileMapper profileMapper;
  
  @Override
  public ProfileDto createProfile(ProfileDto profile) {
    ProfileModel cardModel = profileMapper.toProfile(profile);
    return profileMapper.INSTANCE.toEntity(profileRepository.save(cardModel));
  }
  
  @Override
  public List<ProfileDto> getProfiles() {
    return profileMapper.toEntityList(profileRepository.findAll());
  }

  @Override
  public Optional<ProfileDto> getProfile(Integer profileId) {
    return Optional.of(profileMapper.toEntity(profileRepository.findById(profileId).get()));
  }

  @Override
  public ProfileDto editProfile(ProfileDto profile) {
    return createProfile(profile);
  }

}
