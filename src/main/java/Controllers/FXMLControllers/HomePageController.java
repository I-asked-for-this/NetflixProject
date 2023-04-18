package Controllers.FXMLControllers;

import Controllers.SerieController;
import Entities.*;
import Utils.DataHolder;
import com.example.netflixproject.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    public HBox ThumbnailViewer;
    public Button ProfileBtn;

    public AnchorPane ImageAnchor = new AnchorPane();
    public Button homeButton;
    public Button seriesButoon;
    public Button filmButton;
    public Label welcome;

    private boolean homeSelected = true;
    private boolean SeriesSeleced = false;

    private boolean filmsSelected = false;


    @FXML

    public void handleImageClick(javafx.scene.input.MouseEvent event) {
        try {
            ImageView clickedImage = (ImageView) event.getSource();
            System.out.println("Clicked image: " + clickedImage.getImage().getUrl());
            HelloApplication.SetRoot("VideoPlayer");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void OnProfileClick() throws Exception {
        HelloApplication.SetRoot("ProfilePage");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final int IV_Size = 40;
        List<Serie> serie = new ArrayList<>();
        if(DataHolder.getUser()!=null){
            welcome.setText("Welcome Back "+ DataHolder.getUser().getPrename()+"!");
        }

        Image homeBut=new Image(new File("src/main/resources/Images/HomePage/HomeButton.png").toURI().toString());
        ImageView homeView=new ImageView(homeBut);
        homeView.setFitHeight(IV_Size);
        homeView.setFitWidth(IV_Size);
        homeButton.setGraphic(homeView);

        try {
            serie = SerieController.GetSerieByName("The Witcher");
            serie.addAll(SerieController.GetSerieByName("breaking bad"));
            serie.addAll(SerieController.GetSerieByName("Dark"));
            serie.addAll(SerieController.GetSerieByName("The Witcher"));
            System.out.println(serie);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Serie s : serie) {
            ImageView imageView = null;
            try {
                imageView = new ImageView(String.valueOf(s.getImg().toURL()));
                imageView.setCursor(Cursor.cursor("hand"));
                imageView.setFitHeight(100);
                imageView.setFitWidth(150);
                imageView = ImageClipper(imageView);
                imageView.setOnMouseClicked(this::handleImageClick);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            ThumbnailViewer.getChildren().add(imageView);
        }
    }

    public ImageView ImageClipper(ImageView imageView){
        Rectangle imageClip=new Rectangle(imageView.getFitWidth(),imageView.getFitHeight());
        imageClip.setArcHeight(20);
        imageClip.setArcWidth(20);
        imageClip.setStroke(Color.BLACK);
        imageClip.setStrokeType(StrokeType.INSIDE);
        imageClip.setFill(Color.WHITE);
        imageView.setClip(imageClip);
        return imageView;

    }

}

