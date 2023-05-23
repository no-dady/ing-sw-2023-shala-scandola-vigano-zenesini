package client.gui.controller;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public interface GenericInterface {

    default Alert alertCreator() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.DECORATED);
        alert.setTitle("My Shelfie");
        alert.setHeaderText(null);

        return alert;
    }

}
