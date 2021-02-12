package Services;

import Models.SearchByModel;
import io.restassured.RestAssured;
import Constants.Constants;
import java.util.List;

public class SearchByService {
    public List<SearchByModel> searchBy(String movieName) {
        List<SearchByModel> searchByModelList = RestAssured.given()
                .when()
                .get(Constants.API_URL + "?apikey=" + Constants.API_KEY + "&s=" + movieName)
                .then()
                .statusCode(200)
                .extract()
                .body().jsonPath().getList("Search", SearchByModel.class);
        return searchByModelList;
    }

    public String searchByGetImdbId(String movieName){
        List<SearchByModel> searchByModelList = searchBy(movieName);
        SearchByModel selectedModel = new SearchByModel();
        for (SearchByModel item :searchByModelList) {
            if(item.getTitle().equals(Constants.EXPECTED_TITLE)){
                selectedModel= item;
            }
        }
        String imdbId= selectedModel.getImdbId();
        return  imdbId;
    }
}
