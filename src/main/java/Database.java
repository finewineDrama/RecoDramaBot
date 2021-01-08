import com.vdurmont.emoji.EmojiParser;

import java.util.ArrayList;

public class Database {
    private ArrayList<Drama> allDrama = new ArrayList<Drama>();

    public Database() {
        ArrayList<String> genres = new ArrayList<String>();
        genres.add("Romance");
        genres.add("Romcom");
        Drama cloy = new Drama("cloy", EmojiParser.parseToUnicode("South Korea :kr:"),"cast", 2, "nice", genres, "2019");
        allDrama.add(cloy);
    }

    public ArrayList<String> getDramaTitle(String country, String year, String genre) {
        ArrayList<String> selectedDrama = new ArrayList<String>();
        for (Drama drama : allDrama) {
            if (drama.getCountry().equals(country) && drama.getYear().equals(year) && drama.getGenre().contains(genre)) {
                selectedDrama.add(drama.getTitle());
            }
        }
        return selectedDrama;
    }

    public Drama getDrama(String title) {
        for (Drama drama : allDrama) {
            if (drama.getTitle().equals(title)) {
                return drama;
            }
        }
        return null;
    }

}
