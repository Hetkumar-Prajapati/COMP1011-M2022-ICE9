package ca.georgiancollege.comp1011m2022ice9;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DetailsViewController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private ImageView imageView;

    @FXML
    private ListView<Movie> resultsListView;

    @FXML
    private TextField searchTextField;
    @FXML
    private void backButtonClicked(ActionEvent event) throws IOException {
        SceneManager.Instance().changeScene(event, "search-view.fxml");
    }

    @FXML
    private void detailsButtonClicked(ActionEvent event) throws IOException
    {
        var searchResults = APIManager.Instance().getMovieFromOMDBBySearchTerm(searchTextField.getText());

        if(searchResults.getMovies() != null)
        {
            resultsListView.getItems().addAll(searchResults.getMovies());
        }
        else
        {

        }
    }
    @FXML
    void searchTextFieldSubmitted(ActionEvent event) throws IOException {
        detailsButtonClicked(event);
        resultsListView.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        var posterNotFoundImage = new Image(getClass().getResourceAsStream("poster-not-found.png"));

        resultsListView.getSelectionModel().selectedItemProperty().addListener((obs, oldMovieSelected, newMovieSelected) ->{
            System.out.println(newMovieSelected);
            try
            {
                imageView.setImage(new Image(newMovieSelected.getPoster()));
            }catch(Exception e)
            {
                imageView.setImage(posterNotFoundImage);
            }
        });
    }


}




