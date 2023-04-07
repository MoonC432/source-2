package au.ntcrs6.utils;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.util.Duration;
import javafx.stage.PopupWindow;

public class PopupUtil {

    @FXML
    private StackPane rootPane = new StackPane();

    @FXML
    private Label messageLabel = new Label();

    public void showPopup(boolean success) {
        String message = success ? "Created Successfully" : "Request failed";
        messageLabel.setText(message);

        Popup popup = new Popup();
        popup.getContent().add(rootPane);
        popup.setAutoHide(true);
        popup.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
        popup.show(rootPane.getScene().getWindow());

        Duration duration = Duration.seconds(2);
        Timeline timeline = new Timeline(new KeyFrame(duration, ev -> popup.hide()));
        timeline.play();
    }
}