package com.example.bank.controller;

import com.example.bank.model.dto.ProfileDto;
import com.example.bank.service.IprofileService;
import com.example.bank.util.Constants;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * . Class ProfileController
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/26
 */
@RestController
@Log4j2
@RequiredArgsConstructor
public class ProfileController {
  
  @Autowired
  IprofileService profileService;
  
  /**
   * . This method is to save Profile
   *
   * @param profile This is the first parameter
   * @return a HTTP Status
   */
  @PostMapping("/profile")
  public Single<ResponseEntity<ProfileDto>> createProfile(@RequestBody ProfileDto profile) {
    return profileService.createProfile(profile)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }
  
  /**
   * . This method is to get Profile
   *
   * @return a HTTP Status
   */
  @GetMapping("/profile/getAll")
  public Observable<ResponseEntity<ProfileDto>> getProfiles() { 
    return profileService.getProfiles()
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable));
  }
  
  /**
   * . This method is to get Profile
   *
   * @param profileId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/profile/{profileId}")
  public Single<ResponseEntity<ProfileDto>> getProfile(
      @PathVariable("profileId") Integer profileId) {
    return profileService.getProfile(profileId)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }
  
  /**
   * . This method is to update Profile
   *
   * @param profileId This is the first parameter
   * @param profile   This is the second parameter
   * @return a HTTP Status
   */
  @PutMapping("/profile/{profileId}")
  public Single<ResponseEntity<ProfileDto>> editCard(@PathVariable("profileId") Integer profileId,
      @RequestBody ProfileDto profile) {
    return profileService.editProfile(profile)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }

}
