package Controllers.FXMLControllers;

import Controllers.Avis_EpisodeController;
import Controllers.Avis_FilmController;
import Controllers.FilmController;
import Utils.DataHolderEpisode;
import Utils.DataHolderFilm;
import com.example.netflixproject.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ActorFilmViewController implements Initializable {

    public ListView opinionList;
    public Label FilmTitle;
    public Label ScoreLabel;
    public Label DebutDateLabel;

    public void onBack(ActionEvent actionEvent) throws Exception {
        DataHolderFilm.setSelectedFilm(null);
        HelloApplication.SetRoot("ActorLandingPage");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        opinionList.setPlaceholder(new Label("No opinions for this film"));

        FilmTitle.setText(DataHolderFilm.getSelectedFilm().getNom());


        //MAKE SURE POURCENTAGE


        ScoreLabel.setText(DataHolderFilm.getSelectedFilm().getScore()+"%");

        DebutDateLabel.setText(DataHolderFilm.getSelectedFilm().getAnnerdesortie().toString());

        List<String> opnions = Avis_FilmController.FindAll(DataHolderFilm.getSelectedFilm());

        opinionList.getItems().addAll(opnions);

        opinionList.setCellFactory(param -> new ListCell<String>(){
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item==null) {
                    setGraphic(null);
                    setText(null);

                }else{

                    // set the width's
                    setMinWidth(getListView().getWidth());
                    setMaxWidth(getListView().getWidth());
                    setPrefWidth(getListView().getWidth());

                    // allow wrapping
                    setWrapText(true);

                    setText(item.toString());


                }
            }
        });



    }

}
