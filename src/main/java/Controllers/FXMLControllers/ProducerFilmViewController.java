package Controllers.FXMLControllers;

import Controllers.FilmController;
import Entities.Genre;
import Utils.DataHolderFilm;
import com.example.netflixproject.HelloApplication;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.CheckComboBox;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static Utils.RepeatableFunction.IconSetter;
import static Utils.RepeatableFunction.ImageClipper;
import static javafx.collections.FXCollections.observableArrayList;

public class ProducerFilmViewController implements Initializable {

    public ImageView Thumbnail;
    public Label AlertText;
    public TextField FilmName;
    public Label Title;
    public DatePicker DebutDatePicker;
    public Label DebutDateLabel;
    public CheckComboBox GenreSelector;
    public ComboBox CountrySelector;
    public Label CountryOfOrigin;
    public ComboBox LanguageSelector;
    public TextArea Old_Description;
    public TextArea New_Description;
    public Label Language;
    public Button ChangeDescriptionBtn;
    public Label RatingLabel;
    public Button BackBtn;

    ObservableList<Genre> genres = observableArrayList(
            new Genre("Action"),
            new Genre("Adventure"),
            new Genre("Animation"),
            new Genre("Biography"),
            new Genre("Comedy"),
            new Genre("Crime"),
            new Genre("Documentary"),
            new Genre("Drama"),
            new Genre("Family"),
            new Genre("Fantasy"),
            new Genre("Film-Noir"),
            new Genre("Game-Show"),
            new Genre("History"),
            new Genre("Horror"),
            new Genre("Music"),
            new Genre("Musical"),
            new Genre("Mystery"),
            new Genre("News"),
            new Genre("Reality-TV"),
            new Genre("Romance"),
            new Genre("Sci-Fi"),
            new Genre("Sport"),
            new Genre("Talk-Show"),
            new Genre("Thriller"),
            new Genre("War"),
            new Genre("Western")
    );


    ObservableList<String> genreNames = FXCollections.observableArrayList(
            "Action",
            "Adventure",
            "Animation",
            "Biography",
            "Comedy",
            "Crime",
            "Documentary",
            "Drama",
            "Family",
            "Fantasy",
            "Film-Noir",
            "Game-Show",
            "History",
            "Horror",
            "Music",
            "Musical",
            "Mystery",
            "News",
            "Reality-TV",
            "Romance",
            "Sci-Fi",
            "Sport",
            "Talk-Show",
            "Thriller",
            "War",
            "Western"
    );

    ObservableList<String> languages = FXCollections.observableArrayList(
            "Arabic",
            "Bengali",
            "Chinese (Mandarin)",
            "English",
            "French",
            "German",
            "Hindi",
            "Indonesian",
            "Italian",
            "Japanese",
            "Korean",
            "Malay",
            "Portuguese",
            "Russian",
            "Spanish",
            "Swahili",
            "Thai",
            "Turkish",
            "Vietnamese"
    );


    ObservableList<String> countries = FXCollections.observableArrayList(
            "Australia",
            "Austria",
            "Belgium",
            "Brazil",
            "Canada",
            "China",
            "Denmark",
            "Egypt",
            "France",
            "Germany",
            "Hong Kong",
            "India",
            "Indonesia",
            "Iran",
            "Ireland",
            "Italy",
            "Japan",
            "Malaysia",
            "Mexico",
            "Netherlands",
            "Norway",
            "Philippines",
            "Poland",
            "Russia",
            "Saudi Arabia",
            "Singapore",
            "South Africa",
            "South Korea",
            "Spain",
            "Sweden",
            "Switzerland",
            "Turkey",
            "Tunisia",
            "United Arab Emirates",
            "United Kingdom",
            "United States"
    );




    public void onBack(ActionEvent actionEvent) throws Exception {
        HelloApplication.SetRoot("ProducerLandingPage");
    }

    public void onEditFilmImg(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a Film Thumbnail");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.jpeg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());

            int width = (int) image.getWidth();
            int height = (int) image.getHeight();
            if (width <= 1920 && height <= 1080) {
                Thumbnail.setImage(image);
                FilmController.modifimg(DataHolderFilm.getSelectedFilm(),selectedFile);
                showMessage(AlertText,"Thumbnail Changed successfully");


            } else {
                // If the selected image does not have the required dimensions, display an error message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Image Size");
                alert.setContentText("Please select an image with dimensions of 1920x1080 pixels.");
                alert.showAndWait();
            }
        }


    }

    public void FilmOverviewBtn(MouseEvent mouseEvent) throws Exception {
        HelloApplication.SetRoot("CheckStatsProdFilm");
    }

    public void onEditVideo(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Video");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        FileChooser.ExtensionFilter videoFilter = new FileChooser.ExtensionFilter("MP4 videos", "*.mp4");
        fileChooser.getExtensionFilters().add(videoFilter);
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            String fileName = selectedFile.getName();
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (extension.equals("mp4")) {
                FilmController.modiffilmvedio(DataHolderFilm.getSelectedFilm(),selectedFile);
                showMessage(AlertText,"Video Changed successfully");

            } else {
                // If the selected image does not have the required dimensions, display an error message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Video");
                alert.setContentText("Please select a valid Video");
                alert.showAndWait();
            }
        }
    }


    public void onEdtSynosisBtn(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Synopsis");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        FileChooser.ExtensionFilter videoFilter = new FileChooser.ExtensionFilter("MP4 videos", "*.mp4");
        fileChooser.getExtensionFilters().add(videoFilter);
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            String fileName = selectedFile.getName();
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (extension.equals("mp4")) {
                FilmController.modifsynop(DataHolderFilm.getSelectedFilm(),selectedFile);
                showMessage(AlertText,"Synopsis Changed successfully");

            } else {
                // If the selected image does not have the required dimensions, display an error message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Synopsis");
                alert.setContentText("Please select a valid Synopsis");
                alert.showAndWait();
            }
        }

    }

    public void OnFilmBtn(ActionEvent actionEvent) {
        if (FilmName.getText().isEmpty()) {
            showMessage(AlertText,"Your New Film Title field is empty");
        } else {
            FilmController.modifnom(DataHolderFilm.getSelectedFilm(),FilmName.getText());
            DataHolderFilm.getSelectedFilm().setNom(FilmName.getText());
            Title.setText(FilmName.getText());
            showMessage(AlertText,"Attribute changed successfully");
        }
    }

    public void OnDebutDateBtn(ActionEvent actionEvent) {
        if (DebutDatePicker.getValue().equals(DataHolderFilm.getSelectedFilm().getAnnerdesortie())) {
            showMessage(AlertText, "You didnt change the Debut Date");
        } else if (DebutDatePicker.getValue()==null) {
            showMessage(AlertText, "Your new DebutDate is Empty");
        } else {
            FilmController.modifAnnerdesoritie(DataHolderFilm.getSelectedFilm(),DebutDatePicker.getValue());
            DataHolderFilm.getSelectedFilm().setAnnerdesortie(DebutDatePicker.getValue());
            DebutDateLabel.setText(DebutDatePicker.getValue().toString());
            showMessage(AlertText,"Attribute changed successfully");
        }
    }

    public void onGenreBtn(ActionEvent actionEvent) {
        if (GenreSelector.getCheckModel().getCheckedItems().isEmpty()) {
            showMessage(AlertText,"Film must have at least a genre");
        } else if (GenreSelector.getCheckModel().getCheckedItems().equals(DataHolderFilm.getSelectedFilm().getListegenre())) {
            showMessage(AlertText,"You didn't change the genre");
        } else {
            FilmController.modiflistegenre(DataHolderFilm.getSelectedFilm(),GenreSelector.getCheckModel().getCheckedItems());
            DataHolderFilm.getSelectedFilm().setListegenre(GenreSelector.getCheckModel().getCheckedItems());
            showMessage(AlertText,"Attribute changed successfully");
        }
    }

    public void onCountryBtn(ActionEvent actionEvent) {
        if (CountrySelector.getValue()==null) {
            showMessage(AlertText,"New Film must have a country");
        } else if (CountrySelector.getValue().equals(DataHolderFilm.getSelectedFilm().getPaysorigine())) {
            showMessage(AlertText,"You didn't change the country");
        } else {
            FilmController.modifpaysoregine(DataHolderFilm.getSelectedFilm(), (String) CountrySelector.getValue());
            DataHolderFilm.getSelectedFilm().setPaysorigine((String) CountrySelector.getValue());
            CountryOfOrigin.setText(DataHolderFilm.getSelectedFilm().getPaysorigine());
            showMessage(AlertText,"Attribute changed successfully");

        }
    }

    public void onLanguageBtn(ActionEvent actionEvent) {
        if (LanguageSelector.getValue()==null) {
            showMessage(AlertText,"Film must have a language");
        } else if (LanguageSelector.getValue().equals(DataHolderFilm.getSelectedFilm().getLangue())) {
            showMessage(AlertText,"You didn't change the language");
        } else {
            FilmController.modiflangues(DataHolderFilm.getSelectedFilm(), (String) LanguageSelector.getValue());
            DataHolderFilm.getSelectedFilm().setLangue((String) LanguageSelector.getValue());
            Language.setText(DataHolderFilm.getSelectedFilm().getLangue());
            showMessage(AlertText,"Attribute changed successfully");
        }
    }

    public void ChangeDescriptionBtn(ActionEvent actionEvent) {
        if (New_Description.getText().isEmpty()) {
            showMessage(AlertText,"Film must have a description");
        } else if (New_Description.getText().equals(DataHolderFilm.getSelectedFilm().getDescription())) {
            showMessage(AlertText,"You didn't change the Description");
        } else {
            FilmController.modifdescription(DataHolderFilm.getSelectedFilm(),New_Description.getText() );
            DataHolderFilm.getSelectedFilm().setDescription(New_Description.getText() );
            Old_Description.setText(New_Description.getText());
            showMessage(AlertText,"Attribute changed successfully");

        }
    }

    public void LoadSelectedGenres(){
        List<String> filmGenre= DataHolderFilm.getSelectedFilm().getListegenre();
        System.out.println("GENRES LIST"+filmGenre);
        for(String genre:filmGenre){
            for(Genre genreName:genres){
                if(genre.equals(genreName.getNom())){
                    genreName.getSelect().setSelected(true);
                    break;
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        GenreSelector.getItems().addAll(genreNames);
        GenreSelector.setTitle("Genres");
        LoadSelectedGenres();

        CountrySelector.getItems().addAll(countries);

        LanguageSelector.getItems().addAll(languages);

        Title.setText(DataHolderFilm.getSelectedFilm().getNom());
        CountryOfOrigin.setText(DataHolderFilm.getSelectedFilm().getPaysorigine());
        Language.setText(DataHolderFilm.getSelectedFilm().getLangue());
        Thumbnail.setImage(new Image(DataHolderFilm.getSelectedFilm().getImg().toURI().toString()));
        Thumbnail.setFitWidth(240);
        Thumbnail.setFitHeight(135);
        Old_Description.setText(DataHolderFilm.getSelectedFilm().getDescription());
        DebutDateLabel.setText(DataHolderFilm.getSelectedFilm().getAnnerdesortie().toString());
        RatingLabel.setText(String.valueOf(FilmController.getscorepourcantage(DataHolderFilm.getSelectedFilm()))+" %");
        ImageClipper(Thumbnail);
        IconSetter(BackBtn,"src/main/resources/Images/Design/BackButton.png",70);







    }


    private void showMessage(Label label, String message) {
        label.setText(message);
        label.setOpacity(1);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            label.setOpacity(0);
        }));
        timeline.play();
    }


}
