package it.polimi.client.gui.controller;

import it.polimi.client.gui.GUI;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * <p>GenericInterface interface.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public interface GenericInterface {

    /**
     * <p>alertCreator.</p>
     *
     * @return a {@link javafx.scene.control.Alert} object
     */
    default Alert alertCreator() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.DECORATED);
        alert.setTitle("My Shelfie");
        alert.setHeaderText(null);

        return alert;
    }

    /**
     * <p>getName.</p>
     *
     * @return a {@link java.lang.String} object
     */
    String getName();
    /**
     * <p>getDimensions.</p>
     *
     * @return a {@link java.lang.String} object
     */
    String getDimensions();
    /**
     * <p>setStage.</p>
     *
     * @param stage a {@link javafx.stage.Stage} object
     */
    void setStage(Stage stage);


    /**
     * <p>update.</p>
     */
    void update();

    /**
     * <p>setGUI.</p>
     *
     * @param gui a {@link it.polimi.client.gui.GUI} object
     */
    void setGUI(GUI gui);
}
