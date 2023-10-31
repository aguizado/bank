package com.example.bank.service;

import com.example.bank.model.dto.ProfileDto;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/**
 * . Interface IprofileService
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/26
 */
public interface IprofileService {
  
  public Single<ProfileDto> createProfile(ProfileDto profile);
  
  public Observable<ProfileDto> getProfiles();
  
  public Single<ProfileDto> getProfile(Integer profileId);
  
  public Single<ProfileDto> editProfile(ProfileDto profile);

}
