/**
 * FRED Navigator
 * Copyright (c) 2010-2013, University of Pittsburgh Public Health Dynamics Lab
 * This code was created by David Galloway, Jack Paparian, and Kirsten Taing
 * Licensed under the BSD 3-Clause license.  See the file "LICENSE" for 
 * more information.
 */

/**
 * File: FredNavigatorContext.java
 * Date last modified: 6/12/13 
 *
 * This class defines the context of the whole application.
 */
package edu.pitt.phdl.frednavigator;

import javafx.stage.Stage;

public class FredNavigatorContext {
  
  private final static FredNavigatorContext instance = new FredNavigatorContext();

  public static FredNavigatorContext getInstance()
  {
    return instance;
  }
  
  private Stage fredNavigatorStage = null;
  private Stage fredMediaStage = null;
  private Stage fredHtmlStage = null;

  public Stage getFredNavigatorStage()
  {
    return fredNavigatorStage;
  }
  
  public void setFredNavigatorStage(Stage inStage)
  {
    this.fredNavigatorStage = inStage;
  }

  public Stage getFredMediaStage()
  {
    return fredMediaStage;
  }

  public void setFredMediaStage(Stage inStage)
  {
    this.fredMediaStage = inStage;
  }

  public Stage getFredHtmlStage()
  {
    return fredHtmlStage;
  }

  public void setFredHtmlStage(Stage fredHtmlStage)
  {
    this.fredHtmlStage = fredHtmlStage;
  }
  
}
