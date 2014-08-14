/**
 * FRED Navigator
 * Copyright (c) 2010-2013, University of Pittsburgh Public Health Dynamics Lab
 * This code was created by David Galloway, Jack Paparian, and Kirsten Taing
 * Licensed under the BSD 3-Clause license.  See the file "LICENSE" for 
 * more information.
 */

/**
 * File: FredNavigator.java
 * Date last modified: 8/27/13 
 *
 * This is the main part of the application that starts first and calls other parts.
 */

package edu.pitt.phdl.frednavigator;

import edu.pitt.phdl.frednavigator.quiz.xml.FredNavigatorQuestionType;
import edu.pitt.phdl.frednavigator.quiz.xml.QuizType;
import edu.pitt.phdl.frednavigator.util.FredNavigatorConstants;
import edu.pitt.phdl.frednavigator.util.FredNavigatorProperties;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class FredNavigator extends Application {

  public static final String pathToFRED = FredNavigatorProperties.getProperty("FredNavigator.PATH");

  public static HashMap<String, String> keyToJob = mapKeyToJob();
  public static FredNavigatorQuestionType[] topicOneQuestions = 
          FredNavigator.getPredictionQuestions(TopicType.TOPIC_ONE);
  public static FredNavigatorQuestionType[] topicTwoQuestions = 
          FredNavigator.getPredictionQuestions(TopicType.TOPIC_TWO);
  public static FredNavigatorQuestionType[] topicThreeQuestions = 
          FredNavigator.getPredictionQuestions(TopicType.TOPIC_THREE);
  
  public final static Logger LOGGER = Logger.getLogger(FredNavigator.class.getName());
  public static FileHandler fh = null;
  public static boolean isLoggingEnabled = Boolean.parseBoolean(FredNavigatorProperties.getProperty("FredNavigator.LOGGING_ENABLED"));

  /**
   * This basically compiles all of the data into a HashMap
   * 
   * @return keyToJob   the HashMap
   */
  private static HashMap<String, String> mapKeyToJob()
  {
    HashMap<String, String> tempKeyToJob = null;
    
    try
    {
      Scanner keyScanner = FredNavigator.getFileScanner(FredNavigator.pathToFRED
              + "RESULTS" + FredNavigatorConstants.FS + "KEY");
      
      tempKeyToJob = new HashMap<>();
      
      while(keyScanner.hasNext())
      {
        String key = keyScanner.next();
        String ID = keyScanner.next();
        tempKeyToJob.put(key, ID);
      }
      
    }
    catch(Exception e)
    {
      if(FredNavigator.isLoggingEnabled)
      {
        FredNavigator.LOGGER.log(Level.SEVERE, e.getMessage());
      }
      else
      {
        System.err.print(e.getMessage());
      }

      System.exit(1);
    }
    
    return tempKeyToJob;
  }

  /**
   * Get the questions for the quiz sections
   * 
   * @param     topic
   * @return    topicQuestions[]
   */
  private static FredNavigatorQuestionType[] getPredictionQuestions(TopicType topic)
  {
    try
    {
      ArrayList<FredNavigatorQuestionType> tmpArrList = new ArrayList();
      JAXBContext jaxbContext = JAXBContext.newInstance("edu.pitt.phdl.frednavigator.quiz.xml");

      Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
      InputStream streamInput = null;
      
      switch(topic)
      {
        case TOPIC_ONE:
          streamInput = FredNavigator.class.getResourceAsStream("/edu/pitt/phdl/"
              + "frednavigator/resource/topicOneQuiz.xml");
          break;
        case TOPIC_TWO:
          streamInput = FredNavigator.class.getResourceAsStream("/edu/pitt/phdl/"
              + "frednavigator/resource/topicTwoQuiz.xml");
          break;
        case TOPIC_THREE:
          streamInput = FredNavigator.class.getResourceAsStream("/edu/pitt/phdl/"
              + "frednavigator/resource/topicThreeQuiz.xml");
          break;          
      }

      JAXBElement<QuizType> navigatorQuiz = (JAXBElement<QuizType>)unmarshaller.unmarshal(streamInput);

      QuizType topicQuiz = navigatorQuiz.getValue();

      List<FredNavigatorQuestionType> topicQuestionList = topicQuiz.getQuizQuestion();
      for(FredNavigatorQuestionType tmpElem : topicQuestionList)
      {
        tmpArrList.add(tmpElem);
      }
      Collections.shuffle(tmpArrList);
      return tmpArrList.toArray(new FredNavigatorQuestionType[tmpArrList.size()]);
    }
    catch(JAXBException ex)
    {
      if(FredNavigator.isLoggingEnabled)
      {
        FredNavigator.LOGGER.log(Level.SEVERE, ex.getMessage());
      }
      
      return new FredNavigatorQuestionType[0];
    }

  }

  /**
   * Returns a Scanner object for a file
   * 
   * @param filename
   * @return scanner
   */
  public static Scanner getFileScanner(String filename)
  {
    Scanner scanner = null;
    
    try
    {
      scanner = new Scanner(new java.io.File(filename));
    }
    catch(Exception e)
    {
      if(FredNavigator.isLoggingEnabled)
      {
        FredNavigator.LOGGER.log(Level.SEVERE, e.getMessage());
      }
      return null;
    }
    return scanner;
  }

  /*
   * The start method is needed for all JavaFX applications.
   * This method begins the application and overrides the main method.
   */
  @Override
  public void start(Stage primaryStage)
  {
    try 
    {
      FredNavigator.fh = new FileHandler(FredNavigatorConstants.LOG_FILE, true);
      FredNavigator.fh.setFormatter(new SimpleFormatter());
      FredNavigator.LOGGER.addHandler(FredNavigator.fh);
    }
    catch(IOException | SecurityException e)
    {
      System.err.println("Error: cannot create log file\n" + e.getMessage());
      FredNavigator.isLoggingEnabled = false;
    }
         
    FredNavigatorContext myContext = FredNavigatorContext.getInstance();

    try
    {
      // Each of the windows is a Stage, and the layout for the window is a Scene.
      // Load the first window, the homepage
      Parent homepageLayout = FXMLLoader.load(getClass().getResource(FredNavigatorConstants.HOMEPAGE_FXML));
      primaryStage = new Stage(StageStyle.UTILITY);
      primaryStage.setTitle(FredNavigatorConstants.HOMEPAGE_TITLE_TEXT);
      Scene homepageScene = new Scene(homepageLayout);
      primaryStage.setScene(homepageScene);

      myContext.setFredNavigatorStage(primaryStage);

      // Load the media and html stages
      Stage mediaStage = new Stage(StageStyle.UTILITY);
      mediaStage.initModality(Modality.WINDOW_MODAL);
      mediaStage.initOwner(primaryStage);
      myContext.setFredMediaStage(mediaStage);
      
      Stage htmlStage = new Stage(StageStyle.UTILITY);
      htmlStage.initModality(Modality.WINDOW_MODAL);
      htmlStage.initOwner(primaryStage);
      myContext.setFredHtmlStage(htmlStage);
      
      if(FredNavigator.isLoggingEnabled)
      {
        FredNavigator.LOGGER.log(Level.INFO, "Program successfully started.");
      }
    }
    catch(IOException e)
    {
      if(FredNavigator.isLoggingEnabled)
      {
        FredNavigator.LOGGER.log(Level.SEVERE, e.getMessage());
      }
      else
      {
        System.err.println(e.getMessage());
      }
      
      System.exit(1);
    }

    // Display the homepage
    primaryStage.show();
  }

  // Main method should be ignored
  public static void main(String[] args)
  {
    launch(args);
  }
  
  private enum TopicType { TOPIC_ONE, TOPIC_TWO, TOPIC_THREE }
}
