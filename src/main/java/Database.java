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

        ArrayList<String> mr_genres = new ArrayList<String>();
        mr_genres.add("Mystery");
        mr_genres.add("Romance");

        ArrayList<String> m_genres = new ArrayList<String>();
        m_genres.add("Mystery");

        ArrayList<String> r_genres = new ArrayList<String>();
        r_genres.add("Romance");

        ArrayList<String> h_genres = new ArrayList<String>();
        h_genres.add("Historical");

        ArrayList<String> y_genres = new ArrayList<String>();
        y_genres.add("Youth");

        ArrayList<String> yr_genres = new ArrayList<String>();
        yr_genres.add("Youth");
        yr_genres.add("Romance");

        ArrayList<String> hfm_genres = new ArrayList<String>();
        hfm_genres.add("Historical");
        hfm_genres.add("Fantasy");
        hfm_genres.add("Mystery");


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

        //cdrama synopsis
        String untamed_synopsis = "Wei Wu Xian and Lan Wang Ji, two talented disciples of respectable clans, meet during a cultivation training and accidentally discover a secret carefully hidden for many years. Taking on the legacy of their ancestors, they decide to rid the world of the ominous threat, but in a dramatic turn of events, Wei Wu Xian dies. Sixteen years later, Wei Wu Xian is brought back to life through a self-sacrificing ritual. He conceals himself behind a mask and assumes the identity of his summoner. Soon Wei Wu Xian reunites with Lan Wang Ji, and the pair starts working together to solve the mysteries of the present and unravel the truth behind the events of the past.";
        String legend_synopsis = "In the Five Dynasties and Ten Kingdoms era, young Lu Ping escapes the wicked forces of Shanhai Tower and discovers his spiritual identity in a moment of enlightenment – or rather, his spiritual identities, as he becomes aware of six distinct souls revolving through his being. When Lu later begins to train and grow under his teacher’s masterful guidance, he meets eternal brethren and earns the affection of the young miss Qin along the way. All is going well until a vision reveals his relationship to an unresolved crime in the martial arts world of a decade earlier.";
        String detective_synopsis = "Set in the Republican Era in Shanghai in the 1930s: An intriguing crime case emerges amidst the bustling city. A beautiful new graduate of the police academy, Qin Xiao Man, joins the investigation unit. The famous detective Luo Fei becomes her colleague and neighbour. Together they solve case after case with a growing conspiracy appearing.";
        String roommate_synopsis = "Set in the mid-1920s. A resourceful young police officer named Qiao Chu Sheng and a genius, Lu Yao and a reporter, Bai You Ning form a small detective squad that specializes in solving strange and unsettling murder mysteries.";
        String squid_synopsis = "A cute romance between an adorable singer who develops a crush on a guy that she is too scared to approach. He is a genius in the field of computer engineering and a legend in the world of professional gaming.";
        String tiger_synopsis = "Chen Xiaoqian, a scriptwriter accidentally entered her own script where she became the third princess of Xuanhu City, a minor supporting character who would be killed anytime. Thus begins her journey of survival, where she meets two guys - an unpredictable rich young master named Han Shuo and a perfect guy who is the Minister of Education named Pei Heng.";
        String head_synopsis = "Situ Mo is a graduating student. As someone who got used to having her whole life planned out for her, she is at a loss about her future and can't find the courage to make a decision for herself. Because the school districts have merged, physics major Gu Weiyi barges into her life. They clash repeatedly without noticing that they are slowly walking into each other's hearts. To their surprise, they accidentally end up having to live together.";
        String go_synopsis = "The story revolves around three troubled youths who find solace in their common experiences to become the best family that they can be for each other. Growing up in dysfunctional households, three individuals who are unrelated by blood treat each other like family as they yearn for the love that they cannot find at home. Eldest brother Ling Xiao, second brother He Zi Qiu, and youngest sister Li Jian Jian grow up together, experiencing life’s joy and strife as they support each other’s chosen paths.";

        //cdrama object
        Drama untamed = new Drama("The Untamed", EmojiParser.parseToUnicode("China :cn:"), "Sean Xiao, Wang Yi Bo", 50, untamed_synopsis, hfm_genres, "2019");
        Drama legend = new Drama("Legend of Awakening", EmojiParser.parseToUnicode("China :cn:"), "Arthur Chen, Cheng Xiao, Dylan Xiong, Ancy Deng, Jerry Yu, Shang Xuan", 48, legend_synopsis, h_genres, "2020");
        Drama detective = new Drama("Detective L", EmojiParser.parseToUnicode("China :cn:"), "Johnny Bai, Una You", 24, detective_synopsis,  m_genres, "2019");
        Drama roommate = new Drama("My Roommate is a Detective", EmojiParser.parseToUnicode("China :cn:"), "Hu Yi Tian, Zhang Yun Long, Xiao Yan", 36, roommate_synopsis, mr_genres, "2020");
        Drama squid = new Drama("Go Go Squid!", EmojiParser.parseToUnicode("China :cn:"), "Yang Zi, Li Xian", 41, squid_synopsis, yr_genres, "2019");
        Drama tiger = new Drama("The Romance of Tiger and Rose",EmojiParser.parseToUnicode("China :cn:"), "Rosy Zhao, Ryan Ding", 24,  tiger_synopsis, hr_genres,"2020");
        Drama head = new Drama("Put Your Head on My Shoulder", EmojiParser.parseToUnicode("China :cn:"), "Xing Fei, Ling Yi, Tang Xiaotian, Zheng Yingchen", 24, head_synopsis,yr_genres, "2019");
        Drama go = new Drama("Go Ahead",EmojiParser.parseToUnicode("China :cn:"), "Seven Tan, Song Wei Long, Steven Zhang", 46,  go_synopsis, y_genres,"2020");


        //cdrama
        allDrama.add(untamed);
        allDrama.add(legend);
        allDrama.add(detective);
        allDrama.add(roommate);
        allDrama.add(squid);
        allDrama.add(tiger);
        allDrama.add(head);
        allDrama.add(go);


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


