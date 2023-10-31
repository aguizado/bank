package com.example.bank.service.impl;

import com.example.bank.model.ProfileModel;
import com.example.bank.model.dto.ProfileDto;
import com.example.bank.model.mapper.ProfileMapper;
import com.example.bank.repository.ProfileRepository;
import com.example.bank.service.IprofileService;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.adapter.rxjava.RxJava3Adapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
  public Single<ProfileDto> createProfile(ProfileDto profile) {
    ProfileModel cardModel = profileMapper.toProfile(profile);
    Mono<ProfileModel> cardMono = profileRepository.save(cardModel);
    return RxJava3Adapter.monoToSingle(cardMono.map(profileMapper::toEntity));
  }
  
  @Override
  public Observable<ProfileDto> getProfiles() {
    Flux<ProfileModel> cardFlux = profileRepository.findAll();
    return RxJava3Adapter.fluxToObservable(cardFlux.map(profileMapper::toEntity));
  }

  @Override
  public Single<ProfileDto> getProfile(Integer profileId) {
    Mono<ProfileModel> cardMono = profileRepository.findById(profileId);
    return RxJava3Adapter.monoToSingle(cardMono.map(profileMapper::toEntity));
  }

  @Override
  public Single<ProfileDto> editProfile(ProfileDto profile) {
    return createProfile(profile);
  }

}
