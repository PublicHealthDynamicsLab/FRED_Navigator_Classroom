/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.phdl.frednavigator.util;

import java.io.FileInputStream;
import java.util.Properties;

/**
 *
 * @author ddg5
 */
public class FredNavigatorProperties {
  
  private static Properties p = null;

  /**
   * 
   * @param propertyName
   * @return
   */
  public static String getProperty(String propertyName)
  {
    try
    {
      if (p == null)
      {
        p = new Properties();
        p.load(new FileInputStream(FredNavigatorConstants.PROPERTY_FILE));
      }    
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return p.getProperty(propertyName);
  }
  
}
