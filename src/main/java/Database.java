import com.vdurmont.emoji.EmojiParser;
import java.util.ArrayList;

public class Database {
    private ArrayList<Drama> allDrama = new ArrayList<Drama>();

    public Database() {

        //genres
        ArrayList<String> fr_genres = new ArrayList<String>();
        fr_genres.add("Fantasy");
        fr_genres.add("Romance");

        ArrayList<String> fm_genres = new ArrayList<String>();
        fm_genres.add("Fantasy");
        fm_genres.add("Mystery");

        ArrayList<String> hr_genres = new ArrayList<String>();
        hr_genres.add("Historical");
        hr_genres.add("Romance");

        ArrayList<String> m_genres = new ArrayList<String>();
        m_genres.add("Mystery");

        ArrayList<String> r_genres = new ArrayList<String>();
        r_genres.add("Romance");

        ArrayList<String> yr_genres = new ArrayList<String>();
        yr_genres.add("Youth");
        yr_genres.add("Romance");


        //synopsis
        String hotel_synopsis = "Hotel Del Luna is not like any other hotels, but it’s a place for the dead souls. The hotel is owned by Jang Man Weol, and the man of ability, Ku Chan Seong works as a manager. A story of the fussy owner, hoteliers, and the dead guests will unfold in this hotel.";
        String popupbar_synopsis = "Mystic Pop-up Bar tells the story of a mysterious food cart run by an ill-tempered woman named Weol-ju, an innocent part-time employee named Han Kang-bae and a former afterlife detective known as Chief Gwi who visit customers in their dreams to help resolve their problems.";
        String crown_synopsis = "Joseon is in a state of disorder due to uprisings and a power struggle surrounding King Lee Hun. To avoid assassination, a clown named Ha Sun is brought to the palace to take the place of King Lee Hun. Ha Sun looks almost identical to King Lee Hun.";
        String king_synopsis = "It tells the sad love and beautiful challenge of Joseon's best fortune teller who uses fame and wealth as a weapon. He and his love will be threatened by Kingmakers and the struggles to get the throne.";
        String psycho_synopsis = "He is Psychometric is about a man,who can read the secrets of people by touching their skin, meets a woman who is trying to hide her own deep secrets. They team up to solve a mystery surrounding a fire that changed both of their lives when they were young.";
        String repeat_synopsis = "It is a time travel mystery thriller that tells the story of 10 people who get the chance to go back in time one year to change their lives, but things don’t go as planned and a suspenseful mystery is introduced as they begin dying one by one.";
        String camellia_synopsis = "Dongbaek is a single mother. When a potential new love enters her life, she finds ways to defy the social stigmas surrounding her.";
        String cloy_synopsis = "A paragliding mishap drops a South Korean heiress in North Korea -- and into the life of an army officer, who decides he will help her hide.";
        String extra_synopsis = "It tells the story of a girl who realizes that she is a supporting character inside a romance manga named \"Secret\". She tries to change the fixed story for her love and life.";
        String record_synopsis = "Two actors and a makeup artist fight to make their own way in a world that weighs the backgrounds they were born into more than their dreams.";

        //create drama object
        Drama hotel = new Drama("Hotel Del Luna", EmojiParser.parseToUnicode("South Korea :kr:"), "IU, Yeo Jin-goo, Jung Dong-hwan, Lee Tae-sun, Kang Mi-na", 16, hotel_synopsis, fr_genres, "2019");
        Drama popupbar = new Drama("Mystic Pop-up Bar", EmojiParser.parseToUnicode("South Korea :kr:"), "Hwang Jun-eum, Yook Sung-jae, Choi Won-young", 12, popupbar_synopsis, fm_genres, "2020");
        Drama crown = new Drama("The Crowned Clown", EmojiParser.parseToUnicode("South Korea :kr:"), "Yeo Jin-goo, Lee Se-young", 16, crown_synopsis, hr_genres, "2019");
        Drama king = new Drama("King Maker: The Change of Destiny", EmojiParser.parseToUnicode("South Korea :kr:"), "Park Si-hoo, Ko Sung-hee, Sung Hyuk, Jun Kwang-ryul", 21, king_synopsis, hr_genres, "2020");
        Drama psycho = new Drama("He is Psychometric", EmojiParser.parseToUnicode("South Korea :kr:"),"Park Jin-young, Shin Ye-eun, Kim Kwon Kim, Da-som", 16, psycho_synopsis, m_genres, "2019");
        Drama repeat = new Drama("365: Repeat The Year", EmojiParser.parseToUnicode("South Korea :kr:"), "Lee Joon-Hyuk, Nam Ji-Hyun, Kim Ji-Soo", 24, repeat_synopsis, m_genres, "2020");
        Drama camellia = new Drama("When the Camellia Blooms",EmojiParser.parseToUnicode("South Korea :kr:"), "Gong Hyo-jin, Kang Ha-neul, Kim Ji-seok", 20, camellia_synopsis, r_genres, "2019");
        Drama cloy = new Drama("Crash Landing on You", EmojiParser.parseToUnicode("South Korea :kr:"), "Hyun Bin, Son Ye-jin, Seo Ji-hye", 16, cloy_synopsis, r_genres, "2020");
        Drama extra = new Drama("Extraordinary You", EmojiParser.parseToUnicode("South Korea :kr:"), "Kim Hye-yoon, Ro Woon, Lee Jae-wook, Lee Na-eu, Kim Young-dae, Jung Gun-joo, Lee Tae-ri", 32, extra_synopsis, yr_genres, "2019");
        Drama record = new Drama("Record of Youth", EmojiParser.parseToUnicode("South Korea :kr:"), "Park Bo-gum, Park So-dam, Byeon Woo-seok", 16, record_synopsis, yr_genres, "2020");

        //Drama cloy = new Drama("cloy", EmojiParser.parseToUnicode("South Korea :kr:"),"cast", 2, "nice", genres, "2019");
        allDrama.add(hotel);
        allDrama.add(popupbar);
        allDrama.add(crown);
        allDrama.add(king);
        allDrama.add(psycho);
        allDrama.add(repeat);
        allDrama.add(camellia);
        allDrama.add(cloy);
        allDrama.add(extra);
        allDrama.add(record);
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


