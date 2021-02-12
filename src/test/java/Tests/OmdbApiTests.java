package Tests;

import Models.SearchByIdOrTitleModel;
import Services.SearchByIdOrTitleService;
import Services.SearchByService;
import org.testng.annotations.Test;
import Models.SearchByModel;
import Constants.Constants;

import static org.testng.AssertJUnit.assertEquals;

import java.util.List;


public class OmdbApiTests {
    SearchByService searchByService = new SearchByService();
    SearchByIdOrTitleService searchByIdOrTitleService = new SearchByIdOrTitleService();

    @Test
    public void checkSearchBy() {
        List<SearchByModel> searchByModelList = searchByService.searchBy(Constants.SEARCH_TITLE);
        SearchByModel selectedModel = new SearchByModel();
        for (SearchByModel item : searchByModelList) {
            if (item.getTitle().equals(Constants.EXPECTED_TITLE)) {
                selectedModel = item;
            }
        }
        System.out.println(selectedModel.toString());
        assertEquals(selectedModel.getTitle(), Constants.EXPECTED_TITLE);
    }

    @Test
    public void checkSearchByImdb() {
        String imdbId = searchByService.searchByGetImdbId(Constants.SEARCH_TITLE);
        SearchByIdOrTitleModel model = searchByIdOrTitleService.searchByImdbId(imdbId);
        assertEquals(model.getTitle(), Constants.EXPECTED_TITLE);
        assertEquals(model.getYear(), Constants.EXPECTED_YEAR);
        assertEquals(model.getReleased(), Constants.EXPECTED_RELEASED);
    }
}
