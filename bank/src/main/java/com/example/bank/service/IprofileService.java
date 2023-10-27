package com.example.bank.service;

import com.example.bank.model.dto.ProfileDto;
import java.util.List;
import java.util.Optional;

/**
 * . Interface IprofileService
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/26
 */
public interface IprofileService {
  
  public ProfileDto createProfile(ProfileDto profile);
  
  public List<ProfileDto> getProfiles();
  
  public Optional<ProfileDto> getProfile(Integer profileId);
  
  public ProfileDto editProfile(ProfileDto profile);

}
