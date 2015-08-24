package org.fasttrackit.dev.lesson1.numgenerator;

import org.fasttrackit.dev.lesson1.numgenerator.servlet.DataBase;
import org.fasttrackit.dev.lesson1.numgenerator.servlet.SendMail;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by condor on 29/11/14.
 * FastTrackIT, 2015
 */


/* didactic purposes

Some items are intentionally not optimized or not coded in the right way

FastTrackIT 2015

*/

public class NumGeneratorBusinessLogic {

    private static final int MAX_NUMBER = 10;

    private boolean isFirstTime = true;
    private boolean successfulGuess;
    private int numberOfGuesses;
    private int generatedNumber;
    private String hint;
    double startTime;
    double stopTime;
    double totalTime;
    public NumGeneratorBusinessLogic(){
        resetNumberGenerator();
    }

    public boolean getSuccessfulGuess(){
        return successfulGuess;
    }

    public String getHint(){
        return hint;
    }

    public int getNumGuesses(){
        return numberOfGuesses;
    }
    public double getTotalTime(){
        return totalTime;
    }

    public boolean isFirstTime(){
        return isFirstTime;
    }

    public void resetNumberGenerator(){
        isFirstTime = true;
        numberOfGuesses = 0;
        hint = "";
    }

    public boolean determineGuess(int guessNumber){
        if (isFirstTime) {
            startTime = System.currentTimeMillis();
            generatedNumber = NumGenerator.generate(MAX_NUMBER);
            System.out.println("gennr:"+generatedNumber);
            isFirstTime = false;
        }
        numberOfGuesses++;
        if (guessNumber == generatedNumber) {
            hint="";
            successfulGuess = true;
            stopTime=System.currentTimeMillis();
            totalTime=(stopTime-startTime)/1000;
            System.out.println("tt:" + totalTime);
            //SendMail mail=new SendMail(guessNumber,numberOfGuesses,totalTime);
           // mail.sendmail();
            DataBase input=new DataBase(guessNumber);
            try {
                input.demoCreate();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (guessNumber < generatedNumber) {
            hint = "higher";
            successfulGuess = false;
        } else if (guessNumber > generatedNumber) {
            hint = "lower";
            successfulGuess = false;
        }
        return successfulGuess;
    }


}