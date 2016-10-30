package model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

public class Device {
   private final String name;
   private final String serialNumber;
   private String passportImgUrl;
   private LocalDate dateOfNextVerification;
   private LocalDate dateOfLastVerification;
   private String mountPlace;


   public Device(String name, String serialNumber, LocalDate dateOfNextVerification, LocalDate dateOfLastVerification, String mountPlace) {
      this.name = name;
      this.serialNumber = serialNumber;
      this.dateOfNextVerification = dateOfNextVerification;
      this.dateOfLastVerification = dateOfLastVerification;
      this.mountPlace=mountPlace;
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

   public void setMountPlace(String mountPlace) {
      this.mountPlace = mountPlace;
   }

   @Override
   public String toString() {
      return "Device{" +
              "name='" + name + '\'' +
              ", serialNumber='" + serialNumber + '\'' +
              ", passportImgUrl='" + passportImgUrl + '\'' +
              ", dateOfNextVerification=" + dateOfNextVerification +
              ", dateOfLastVerification=" + dateOfLastVerification +
              ", mountPlace='" + mountPlace + '\'' +
              '}';
   }


   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Device device = (Device) o;

      if (!getName().equals(device.getName())) return false;
      return getSerialNumber().equals(device.getSerialNumber());

   }

   @Override
   public int hashCode() {
      int result = getName().hashCode();
      result = 31 * result + getSerialNumber().hashCode();
      return result;
   }
}
