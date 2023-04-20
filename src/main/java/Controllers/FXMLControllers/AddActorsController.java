package Controllers.FXMLControllers;

import Controllers.SerieController;
import Entities.Actor;
import Entities.Serie;
import Utils.DataHolder;
import Utils.DataHolderSeries;
import com.example.netflixproject.HelloApplication;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

public class AddActorsController implements Initializable {

    public Text AlertText;
    public TableView<Actor> MainActorTable = new TableView<>();
    public TableView<Actor> SupportingActorTable = new TableView<>();

    @FXML
    public TableColumn<Actor, String> MainActorColumnName = new TableColumn<>();
    public TableColumn<Actor, String> MainActorColumnLastName = new TableColumn<>();
    public TableColumn<Actor, CheckBox> SelectedMainActor = new TableColumn<>();

    public TableColumn<Actor, String> SupportingActorColomnName = new TableColumn<>();
    public TableColumn<Actor, String> SupportingActorColomnLastName = new TableColumn<>();
    public TableColumn<Actor, CheckBox> SelectedSupportingActor = new TableColumn<>();

    //CHANGE ASAP
    ObservableList<Actor> CHANGEMainActorsList = observableArrayList(new Actor(11, "Tom", "Hanks", "dd", "sssa"), new Actor(12, "Salah", "DooDOO", "dd", "sssa"), new Actor(13, "Emm", "", "dd", "sssa"));
    ObservableList<Actor> CHANGESuppActorsList = observableArrayList(new Actor(11, "Jasser", "Hamdi", "dd", "sssa"), new Actor(12, "Salouh", "Mejri", "dd", "sssa"), new Actor(13, "Salem", "El Behi", "dd", "sssa"));


    @FXML
    protected void OnBack() throws Exception {
        HelloApplication.SetRoot("AddSeries");
    }

    @FXML
    protected void OnNext() throws Exception {
        List<Long> selectedMainActors = new ArrayList<>();
        List<Long> selectedSupportingActors = new ArrayList<>();

        for (Actor actor : CHANGEMainActorsList) {
            if (actor.getSelect().isSelected()) {
                selectedMainActors.add(actor.getID());
            }
        }
        for (Actor actor : CHANGESuppActorsList) {
            if (actor.getSelect().isSelected()) {
                selectedSupportingActors.add(actor.getID());
            }
        }

        if (selectedMainActors.isEmpty())
        {
            AlertText.setText("Must select at least 1 main Actor");
        }else
        {
            DataHolderSeries.setMainActorsList(selectedMainActors);
            DataHolderSeries.setSuppActorsList(selectedSupportingActors);
            //PickUp constructor Here
            SerieController.AddSerie(new Serie(DataHolderSeries.getSeriesName(), DataHolder.getProducer().getId(),
                    DataHolderSeries.getDescription(),DataHolderSeries.getDebutDate(),DataHolderSeries.getLanguage(),
                    DataHolderSeries.getCountryOfOrigin(),DataHolderSeries.getGenreList(),DataHolderSeries.getThumbnail(),
                    DataHolderSeries.getSynopsis(),DataHolderSeries.getMainActorsList(),DataHolderSeries.getSuppActorsList()));

            HelloApplication.SetRoot("AddSeason");
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        MainActorColumnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        MainActorColumnLastName.setCellValueFactory(new PropertyValueFactory<>("Prename"));
        SelectedMainActor.setCellValueFactory(new PropertyValueFactory<>("select"));
        SupportingActorColomnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        SupportingActorColomnLastName.setCellValueFactory(new PropertyValueFactory<>("Prename"));
        SelectedSupportingActor.setCellValueFactory(new PropertyValueFactory<>("select"));
        MainActorTable.setItems(CHANGEMainActorsList);
        SupportingActorTable.setItems(CHANGESuppActorsList);

    }
}
