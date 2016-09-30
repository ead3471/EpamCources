package com.epam.javase06.t03;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Freemind on 2016-09-29.
 */
public class MedicalStaff {
    public static void main(String[] args) {
        Doctor doctor1 = new Doctor();
       // Doctor doctor2 = new MedicalStaff();//Doctor extends MedicalStuff
        Doctor doctor3 = new HeadDoctor();
        Object object1 = new HeadDoctor();
       // HeadDoctor doctor5 = new Object();//Doctor extends MedicalStuff
      //  Doctor doctor6 = new Nurse();//Nurse not extends Doctor
      //  Nurse nurse = new Doctor();//Doctor not extends Nurse
        Object object2 = new Nurse();//Object<-MedicalStuff<-nurse
        List<Doctor> list1= new ArrayList<Doctor>();
      //  List<MedicalStaff> list2 = new ArrayList<Doctor>();
     //   List<Doctor> list3 = new ArrayList<MedicalStaff>();
     //   List<Object> list4 = new ArrayList<Doctor>();
        List<Object> list5 = new ArrayList<Object>();
    }

}


class Doctor extends MedicalStaff{}

class Nurse extends MedicalStaff{}

class HeadDoctor extends Doctor{}


