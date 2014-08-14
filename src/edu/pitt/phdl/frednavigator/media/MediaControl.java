/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.phdl.frednavigator.media;

import edu.pitt.phdl.frednavigator.FredNavigatorContext;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * This class was borrowed from Oracle's AdvancedMedia demo.
 *
 */
public class MediaControl extends BorderPane {

  private MediaPlayer mediaPlayer;
  private MediaView mediaView;
  private final boolean repeat = true;
  private HBox mediaBar;

  @Override
  protected void layoutChildren()
  {
    if(this.mediaView != null && this.getBottom() != null)
    {
      this.mediaView.setFitWidth(this.getWidth());
      this.mediaView.setFitHeight(this.getHeight() - this.getBottom().prefHeight(-1));
    }
    super.layoutChildren();
    if(this.mediaView != null)
    {
      this.mediaView.setTranslateX((((Pane)this.getCenter()).getWidth() - this.mediaView.prefWidth(-1)) / 2);
      this.mediaView.setTranslateY((((Pane)this.getCenter()).getHeight() - this.mediaView.prefHeight(-1)) / 2);
    }
  }

  @Override
  protected double computeMinWidth(double height)
  {
    return this.mediaBar.prefWidth(-1);
  }

  @Override
  protected double computeMinHeight(double width)
  {
    return 200;
  }

  @Override
  protected double computePrefWidth(double height)
  {
    return Math.max(this.mediaPlayer.getMedia().getWidth(), this.mediaBar.prefWidth(height));
  }

  @Override
  protected double computePrefHeight(double width)
  {
    return this.mediaPlayer.getMedia().getHeight() + this.mediaBar.prefHeight(width);
  }

  @Override
  protected double computeMaxWidth(double height)
  {
    return Double.MAX_VALUE;
  }

  @Override
  protected double computeMaxHeight(double width)
  {
    return Double.MAX_VALUE;
  }

  public MediaControl(final MediaPlayer mp)
  {
    this.mediaPlayer = mp;
    setStyle("-fx-background-color: #bfc2c7;"); // TODO: Use css file
    this.mediaView = new MediaView(mp);
    Pane mvPane = new Pane() {
    };
    mvPane.getChildren().add(this.mediaView);
    mvPane.setStyle("-fx-background-color: black;"); // TODO: Use css file
    setCenter(mvPane);
    this.mediaBar = new HBox();
    this.mediaBar.setPadding(new Insets(5, 10, 5, 10));
    this.mediaBar.setAlignment(Pos.CENTER);
    BorderPane.setAlignment(this.mediaBar, Pos.CENTER);

    final Button okButton = new Button("OK");
    okButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e)
      {
        FredNavigatorContext.getInstance().getFredMediaStage().hide();
      }
    });

    this.mediaPlayer.setCycleCount(this.repeat ? MediaPlayer.INDEFINITE : 1);
    this.mediaBar.getChildren().add(okButton);
    setBottom(this.mediaBar);
  }
}