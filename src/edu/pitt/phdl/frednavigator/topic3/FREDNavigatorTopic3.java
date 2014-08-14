/**
 * FRED Navigator
 * Copyright (c) 2010-2013, University of Pittsburgh Public Health Dynamics Lab
 * This code was created by David Galloway, Jack Paparian, and Kirsten Taing
 * Licensed under the BSD 3-Clause license.  See the file "LICENSE" for 
 * more information.
 */

/**
 * File: FredNavigatorTopic3.java
 * Date last modified: 6/15/13 
 *
 * This is called by FredNavigator.java and starts Topic 3
 */

package edu.pitt.phdl.frednavigator.topic3;

import edu.pitt.phdl.frednavigator.util.FredNavigatorConstants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FREDNavigatorTopic3 extends Application {
  
    /**
     * The start method is needed for all JavaFX applications.
     * This method begins the application and overrides the main method.
     */
    @Override
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource(FredNavigatorConstants.TOPIC_THREE_FXML));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
}
