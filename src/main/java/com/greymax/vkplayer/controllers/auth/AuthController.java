package com.greymax.vkplayer.controllers.auth;

import com.greymax.vkplayer.Utils;
import com.greymax.vkplayer.VKPlayer;
import com.greymax.vkplayer.objects.Settings;
import com.greymax.vkplayer.objects.User;
import com.greymax.vkplayer.objects.auth.AuthorizationException;
import com.greymax.vkplayer.services.auth.LoginService;
import com.greymax.vkplayer.services.auth.UserService;
import com.greymax.vkplayer.ui.components.autocomplete.AutoFillTextBox;
import com.greymax.vkplayer.ui.components.autocomplete.AutoFillTextBoxSkin;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthController implements Initializable {

  private static final String CLIENT_ID = "3049094";

  @FXML
  public AutoFillTextBox loginField;
  @FXML
  public PasswordField passwordField;
  @FXML
  public Button loginButton;
  @FXML
  public CheckBox rememberMeCheckBox;

  private Stage dialogStage;
  private double mousePositionX;
  private double mousePositionY;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    this.loginField.setData(FXCollections.observableArrayList(UserService.getInstance().getAllUserNames()));
    this.loginField.getListview().setMinWidth(180);
//    this.loginField.getTextbox().setOnMouseClicked(new EventHandler<MouseEvent>() {
//      @Override
//      public void handle(MouseEvent mouseEvent) {
//        if (mouseEvent.getClickCount() == 2)
//          ((AutoFillTextBoxSkin) loginField.getSkin()).showPopupWithAllItems();
//      }
//    });
  }

  public void login(final ActionEvent event) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        String login = loginField.getText();
        String password = passwordField.getText();
        if (null == login || login.isEmpty() || null == password || password.isEmpty()) {
          Dialogs.showErrorDialog(dialogStage,
              "Login and password are required fields",
              "Error Dialog", "Unable to log in");   // TODO: add validation on fields
          return;
        }

        User user = new User(login, password);
        try{
          LoginService.logIn(CLIENT_ID, "audio,status,friends,wall,video", user);

          // TODO: move this block to DB
          Utils.addUserEmailToFile(user.getLogin());
          if(rememberMeCheckBox.isSelected())
            Utils.savePasswordForUser(user);
          Settings settings = Utils.getSettingsForUser(user);
          user.setSettings(settings);

          new VKPlayer().start(((Stage) ((Control) event.getSource()).getScene().getWindow()));
//          VkPlayerForm.getInstance().createPlayerWindow(user);
        }catch (AuthorizationException authEx) {
          System.out.println(authEx.getMessage());
          Dialogs.showErrorDialog(dialogStage,
              "Please check that you have entered your login and password correctly \n" +
              "or check internet connection",
              "Error Dialog", "Unable to log in");
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public void exit(ActionEvent event) {
    Platform.exit();
  }

  public void minimize(ActionEvent event) {
    ((Stage) ((Control) event.getSource()).getScene().getWindow()).setIconified(true);
  }

  public void clickOnHeader(MouseEvent mouseEvent) {
    mousePositionX = mouseEvent.getScreenX();
    mousePositionY = mouseEvent.getScreenY();
  }

  public void moveWindow(MouseEvent mouseEvent) {
    Stage primaryStage = (Stage) ((Control) mouseEvent.getSource()).getScene().getWindow();
    double shiftX = mouseEvent.getScreenX() - mousePositionX;
    double shiftY = mouseEvent.getScreenY() - mousePositionY;
    primaryStage.setX(primaryStage.getX() + shiftX);
    primaryStage.setY(primaryStage.getY() + shiftY);

    mousePositionX = mouseEvent.getScreenX();
    mousePositionY = mouseEvent.getScreenY();
  }

  public void showUsersPopup(MouseEvent mouseEvent) {
    if (mouseEvent.getClickCount() == 2)
      ((AutoFillTextBoxSkin) loginField.getSkin()).showPopupWithAllItems();
  }
}
