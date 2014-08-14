/**
 * FRED Navigator
 * Copyright (c) 2010-2013, University of Pittsburgh Public Health Dynamics Lab
 * This code was created by David Galloway, Jack Paparian, and Kirsten Taing
 * Licensed under the BSD 3-Clause license.  See the file "LICENSE" for 
 * more information.
 */

/**
 * File: HomepageController.java
 * Date last modified: 8/27/13 
 *
 * The functionality of the homepage
 */


package edu.pitt.phdl.frednavigator;

import edu.pitt.phdl.frednavigator.htmlviewer.HtmlControl;
import edu.pitt.phdl.frednavigator.util.FredNavigatorConstants;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class HomepageController implements Initializable {
        
  // TODO: (maybe) I really feel like the file could be a lot shorter because
  // it's very repetitive. Like maybe the same function for the three topics.

  /**
   * Click on this button to open Topic 1
   * 
   * @param event 
   */
  @FXML
  private void openTopic1Page(ActionEvent event)
  {

    try
    {
      Parent topic1Layout;
      topic1Layout = FXMLLoader.load(getClass().getResource(FredNavigatorConstants.TOPIC_ONE_FXML));
      Scene topic1Scene = new Scene(topic1Layout);
      FredNavigatorContext.getInstance().getFredNavigatorStage().setScene(topic1Scene);
      FredNavigatorContext.getInstance().getFredNavigatorStage().setTitle(FredNavigatorConstants.TOPIC_ONE_TITLE_TEXT);
      FredNavigatorContext.getInstance().getFredNavigatorStage().setResizable(false);
    }
    catch(Exception ex)
    {
      if(FredNavigator.isLoggingEnabled) 
      {
        FredNavigator.LOGGER.log(Level.SEVERE, ex.getMessage());
      }
    }
  }

  /**
   * Click on this button to open Topic 2
   * 
   * @param event 
   */
  @FXML
  private void openTopic2Page(ActionEvent event)
  {

    try
    {
      Parent topic2Layout;
      topic2Layout = FXMLLoader.load(getClass().getResource(FredNavigatorConstants.TOPIC_TWO_FXML));
      Scene topic2Scene = new Scene(topic2Layout);
      FredNavigatorContext.getInstance().getFredNavigatorStage().setScene(topic2Scene);
      FredNavigatorContext.getInstance().getFredNavigatorStage().setTitle(FredNavigatorConstants.TOPIC_TWO_TITLE_TEXT);
      FredNavigatorContext.getInstance().getFredNavigatorStage().setResizable(false);
    }
    catch(IOException ex)
    {
      if(FredNavigator.isLoggingEnabled)
      {
        FredNavigator.LOGGER.log(Level.SEVERE, ex.getMessage());
      }
    }

  }

  /**
   * Click on this button to open Topic 3
   * 
   * @param event 
   */
  @FXML
  private void openTopic3Page(ActionEvent event)
  {

    try
    {
      Parent topic3Layout;
      topic3Layout = FXMLLoader.load(getClass().getResource(FredNavigatorConstants.TOPIC_THREE_FXML));
      Scene topic3Scene = new Scene(topic3Layout);
      FredNavigatorContext.getInstance().getFredNavigatorStage().setScene(topic3Scene);
      FredNavigatorContext.getInstance().getFredNavigatorStage().setTitle(FredNavigatorConstants.TOPIC_THREE_TITLE_TEXT);
      FredNavigatorContext.getInstance().getFredNavigatorStage().setResizable(false);
    }
    catch(IOException ex)
    {
      if(FredNavigator.isLoggingEnabled)
      {
        FredNavigator.LOGGER.log(Level.SEVERE, ex.getMessage());
      }
    }

  }

  /**
   * Opens the tutorial page
   * 
   * @param event 
   */
  @FXML
  private void openTutorialPage(ActionEvent event)
  {
    Scene htmlScene = new Scene(new HtmlControl("tutorial.html"), 800, 700, Color.web("#666970"));
    FredNavigatorContext.getInstance().getFredHtmlStage().setTitle("Tutorial");
    FredNavigatorContext.getInstance().getFredHtmlStage().setScene(htmlScene);
    FredNavigatorContext.getInstance().getFredHtmlStage().show();
    FredNavigatorContext.getInstance().getFredNavigatorStage().toBack();
    FredNavigatorContext.getInstance().getFredHtmlStage().toFront();
  }

  /**
   * Opens the careers page
   * 
   * @param event 
   */
  @FXML
  private void openCareersPage(ActionEvent event)
  {
    Scene htmlScene = new Scene(new HtmlControl("careers.html"), 800, 700, Color.web("#666970"));
    FredNavigatorContext.getInstance().getFredHtmlStage().setTitle("Careers");
    FredNavigatorContext.getInstance().getFredHtmlStage().setScene(htmlScene);
    FredNavigatorContext.getInstance().getFredHtmlStage().show();
    FredNavigatorContext.getInstance().getFredNavigatorStage().toBack();
    FredNavigatorContext.getInstance().getFredHtmlStage().toFront();
  }
  
  /**
   * Opens the About page
   * 
   * @param event 
   */
  @FXML
  private void openAboutFredPage(ActionEvent event)
  {
    Scene htmlScene = new Scene(new HtmlControl("about_fred.html"), 800, 700, Color.web("#666970"));
    FredNavigatorContext.getInstance().getFredHtmlStage().setTitle("About FRED");
    FredNavigatorContext.getInstance().getFredHtmlStage().setScene(htmlScene);
    FredNavigatorContext.getInstance().getFredHtmlStage().show();
    FredNavigatorContext.getInstance().getFredNavigatorStage().toBack();
    FredNavigatorContext.getInstance().getFredHtmlStage().toFront();
  }

  @Override
  public void initialize(URL url, ResourceBundle rb)
  {
  
  }
}
