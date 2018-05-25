package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class mainMenu  {
    @FXML
    private Canvas canvas;
    private GraphicsContext gc;

    @FXML
    private Button btnPlay;

    private File image;



    @FXML
    public void initialize() {
        this.gc = this.canvas.getGraphicsContext2D();

        this.image = new File("C:\\Users\\User\\Documents\\IntelliJ IDEA\\Chess\\src\\sample\\PieceImages\\mainMenuBackground.jpg");

        this.draw();
    }

    @FXML
    public void playClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Parent root = loader.load();
            Controller con = loader.getController();
            Main.getStage().setScene(new Scene(root, 560, 560));
        }
        catch(IOException e) {
            Logger.getLogger(mainMenu.class.getName()).log(Level.SEVERE, null,
                    e);
        }
    }

    public void draw() {
        this.gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());

        // háttér
        this.gc.setFill(Color.BLACK);
        this.gc.fillRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
        //this.gc.setFill(Color.WHITE);
        //this.gc.fillRect(0, 0, 100, 100);
        // rajzolások

        //this.gc.drawImage(new Image(this.image.toURI().toString()),this.canvas.getWidth(),this.canvas.getHeight());
    }


}

