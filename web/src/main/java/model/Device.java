package model;

import java.time.Duration;
import java.time.LocalDate;

public class Device {
   private final String name;
   private final String serialNumber;
   private String passportImgUrl;
   private LocalDate dateOfNextVerification;
   private LocalDate dateOfLastVerification;
   private Duration betweenVerificationTime;
   private String mountPlace;

   public Device(String name, String serialNumber) {
      this.name = name;
      this.serialNumber = serialNumber;
   }

   public Device(String name, String serialNumber, LocalDate dateOfNextVerification, LocalDate dateOfLastVerification, Duration betweenVerificationTime) {
      this.name = name;
      this.serialNumber = serialNumber;
      this.dateOfNextVerification = dateOfNextVerification;
      this.dateOfLastVerification = dateOfLastVerification;
      this.betweenVerificationTime = betweenVerificationTime;
   }

   public String getName() {
      return name;
   }

   public String getSerialNumber() {
      return serialNumber;
   }

   public String getPassportImgUrl() {
      return passportImgUrl;
   }

   public LocalDate getDateOfNextVerification() {
      return dateOfNextVerification;
   }

   public LocalDate getDateOfLastVerification() {
      return dateOfLastVerification;
   }

   public Duration getBetweenVerificationTime() {
      return betweenVerificationTime;
   }

   public String getMountPlace() {
      return mountPlace;
   }

   public void setPassportImgUrl(String passportImgUrl) {
      this.passportImgUrl = passportImgUrl;
   }

   public void setDateOfNextVerification(LocalDate dateOfNextVerification) {
      this.dateOfNextVerification = dateOfNextVerification;
   }

   public void setDateOfLastVerification(LocalDate dateOfLastVerification) {
      this.dateOfLastVerification = dateOfLastVerification;
   }

   public void setBetweenVerificationTime(Duration betweenVerificationTime) {
      this.betweenVerificationTime = betweenVerificationTime;
   }

   public void setMountPlace(String mountPlace) {
      this.mountPlace = mountPlace;
   }
}
