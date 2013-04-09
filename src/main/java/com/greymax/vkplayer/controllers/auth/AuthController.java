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
import com.greymax.vkplayer.ui.components.spinner.Spinner;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthController implements Initializable {

  private static final String CLIENT_ID = "3049094";
  private static final String VK_PERMISSIONS = "audio,status,friends,wall,video";
  private static final String ERROR_VALIDATION_STYLE_CLASS = "not-valid";

  @FXML
  public AutoFillTextBox loginField;
  @FXML
  public PasswordField passwordField;
  @FXML
  public Button loginButton;
  @FXML
  public CheckBox rememberMeCheckBox;
  @FXML
  public Spinner spinner;

  private Stage dialogStage;
  private double mousePositionX;
  private double mousePositionY;
  private User user;
  private UserService userService = UserService.getInstance();

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    this.loginField.setData(FXCollections.observableArrayList(UserService.getInstance().getAllUserNames()));
    this.loginField.getListview().setMinWidth(180);
    this.loginField.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
        loginField.getTextbox().getStyleClass().remove(ERROR_VALIDATION_STYLE_CLASS);
      }
    });
    this.loginField.addSelectionListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue observableValue, Object o, Object o2) {
        passwordField.requestFocus();
        String username = loginField.getText();
        User persistentUser = userService.getUserByUsername(username);
        if (null != persistentUser) {
          rememberMeCheckBox.fire();
          passwordField.setText(persistentUser.getPassword());
          passwordField.end();
        }
      }
    });
    this.passwordField.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
        passwordField.getStyleClass().remove(ERROR_VALIDATION_STYLE_CLASS);
      }
    });
  }

  //TODO: refactoring!!!
  public void login(final ActionEvent event) {
    spinner.start();
    loginButton.setDisable(true);
    new Thread(new Runnable() {
      @Override
      public void run() {
        String login = loginField.getText();
        String password = passwordField.getText();

        // validation inputs
        if (!validate(login, password)) return;

        try {
          user = new User(login, password);
          LoginService.logIn(CLIENT_ID, VK_PERMISSIONS, user);

          //TODO: add flag to remember password into User
          if (rememberMeCheckBox.isSelected())
            userService.createOrUpdateUser(user);

          //TODO: move to DB side
          Settings settings = Utils.getSettingsForUser(user);
          user.setSettings(settings);

          Platform.runLater(new Runnable() {
            @Override
            public void run() {
              try {
                spinner.stop();
                loginButton.setDisable(false);
                new VKPlayer().start(((Stage) ((Control) event.getSource()).getScene().getWindow()));
              } catch (Exception ex) {
                ex.printStackTrace();
              }
            }
          });
        } catch (AuthorizationException authEx) {
          System.out.println(authEx.getMessage());
          Platform.runLater(new Runnable() {
            @Override
            public void run() {
              spinner.stop();
              loginButton.setDisable(false);
              Dialogs.showErrorDialog(dialogStage,
                  "Please check that you have entered your login and password correctly or check internet connection",
                  "Unable to log in", "Error");
            }
          });
        }
      }
    }).start();
  }

  private boolean validate(String login, String password) {
    if (null == login || login.isEmpty() || null == password || password.isEmpty()) {
      if (null == login || login.isEmpty()) {
        loginField.getTextbox().getStyleClass().add(ERROR_VALIDATION_STYLE_CLASS);
        loginField.getTextbox().requestFocus();
      } else {
        passwordField.requestFocus();
      }
      if (null == password || password.isEmpty())
        passwordField.getStyleClass().add(ERROR_VALIDATION_STYLE_CLASS);

      Platform.runLater(new Runnable() {
          @Override
          public void run() {
            spinner.stop();
            loginButton.setDisable(false);
          }
      });
      return false;
    }
    return true;
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

  public void loginOnEnter(KeyEvent keyEvent) {
    if (KeyCode.ENTER.equals(keyEvent.getCode()))
      loginButton.fire();
  }
}
