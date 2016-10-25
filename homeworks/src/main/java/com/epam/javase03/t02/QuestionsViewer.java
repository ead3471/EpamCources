package com.epam.javase03.t02;

import java.util.*;

/**
 * Created by Freemind on 2016-10-18.
 */
public class QuestionsViewer {
    public static void main(String[] args) {
        Locale choosedLocale=null;


        String localeString=null;

        ResourceBundle dialogBundle=ResourceBundle.getBundle("quiz/dialogs");
        if(args!=null && args.length>0){
            choosedLocale=tryGetLocale(args[0]);
        };


        Scanner userInputScanner=new Scanner(System.in);

        while(choosedLocale==null){
            System.out.println(dialogBundle.getString("choose_lang"));
            localeString=userInputScanner.next();

            if(localeString.equals("E"))
                choosedLocale=new Locale("en");
            else if(localeString.equals("R"))
                    choosedLocale=new Locale("ru");
        }

        dialogBundle=ResourceBundle.getBundle("quiz/dialogs",choosedLocale);
        ResourceBundle questionsBundle=ResourceBundle.getBundle("quiz/questions",choosedLocale);
        ResourceBundle answersBundle=ResourceBundle.getBundle("quiz/answers",choosedLocale);

        Enumeration<String> questionsKeys=questionsBundle.getKeys();

        while(questionsKeys.hasMoreElements()){
            System.out.println(questionsBundle.getString(questionsKeys.nextElement()));
        }

        String userInputString="";
        while(true){
            System.out.println(dialogBundle.getString("ask_quest_number"));
            try{
                userInputString=userInputScanner.next();
                if(userInputString.equals("exit")) break;
                System.out.println(dialogBundle.getString("answer_dialog")+answersBundle.getString("answer_"+userInputString));}
            catch (MissingResourceException ex){

                System.out.println(dialogBundle.getString("wrong_number"));
            }

        }



    }

    private static Locale tryGetLocale(String localeString ){
        Locale locale=null;
        try{
            locale=new Locale(localeString);
        }
        catch(Exception ex){
        }
        return locale;
    }

}
