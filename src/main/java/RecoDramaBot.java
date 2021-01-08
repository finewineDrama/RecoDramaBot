import java.util.concurrent.CompletableFuture;
import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class RecoDramaBot extends TelegramLongPollingBot {
    private static final Logger LOG = Logger.getLogger(RecoDramaBot.class.getName());
    public static String countryChosen;
    public static String yearChosen;
    public static String genreChosen;
    public static String dramaSelectedTitle;
    private static final String korea = EmojiParser.parseToUnicode("South Korea :kr:");
    private static final String china = EmojiParser.parseToUnicode("China :cn:");


    public static void main(String[] args) {

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new RecoDramaBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()) {
            CallbackQuery callback = update.getCallbackQuery();
            String data = callback.getData();
            processCallBackQuery(data, callback.getMessage().getChatId());


            // We check if the update has a message and the message has text
        } else if (update.hasMessage() && update.getMessage().hasText()) {

            Message message = update.getMessage();
            Long chatId = message.getChatId();
            String input = message.getText();

            if ("/start".equals(input)) {
                findDrama(chatId);

            } else if (input.equals(korea) || input.equals(china)) {
                processCountry(input, chatId);

            } else if (input.equals("2019") || input.equals("2020")) {
                processYear(input, chatId);

                //returns list of dramas

            } else if (input.equals("Fantasy") || input.equals("Historical")
                    || input.equals("Mystery") || input.equals("Romance") || input.equals("Youth")) {
                processGenre(input, chatId);
            } else {
                processRequest(input, chatId);
            }
        }
    }


    @Override
    public String getBotUsername() {
        return "RecoDrama_Bot";
    }

    @Override
    public String getBotToken() {
        return "1469593034:AAHBnNJdUvF0AVw1BiSMa7UqIO0Ou3svD3I";
    }

    //creates the list of options for user to choose
    //returns ReplyKeyboardMarkup Object
    public ReplyKeyboardMarkup createOptions(ReplyKeyboardMarkup replyKeyboardMarkup,
                                             List<KeyboardRow> keyboardRowList,
                                             List<String> options) {
        for (String option : options) {
            KeyboardRow keyboardRow = new KeyboardRow();
            KeyboardButton keyboardButton = new KeyboardButton();
            keyboardButton.setText(option);
            keyboardRow.add(keyboardButton);
            keyboardRowList.add(keyboardRow);
        }
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return replyKeyboardMarkup;
    }

    public void findDrama(Long chatId) {
        SendMessage request = new SendMessage();
        request.setChatId(chatId.toString());
        request.setText("Hi there! First, choose the country of production.");
        request.setParseMode(ParseMode.MARKDOWN);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboardRowList = new ArrayList<KeyboardRow>();
        List<String> options = new ArrayList<String>();
        options.add(korea);
        options.add(china);
        replyKeyboardMarkup = createOptions(replyKeyboardMarkup,
                keyboardRowList, options);
        request.setReplyMarkup(replyKeyboardMarkup);

        try {
            execute(request); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void processCountry(String input, Long chatId) {
        countryChosen = input;
        SendMessage request = new SendMessage();
        request.setChatId(chatId.toString());
        request.setText("Great! Now, pick the year.");
        request.setParseMode(ParseMode.MARKDOWN);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboardRowList = new ArrayList<KeyboardRow>();
        List<String> options = new ArrayList<String>();
        options.add("2019");
        options.add("2020");
        replyKeyboardMarkup = createOptions(replyKeyboardMarkup,
                keyboardRowList, options);
        request.setReplyMarkup(replyKeyboardMarkup);

        try {
            execute(request); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void processYear(String input, Long chatId) {
        yearChosen = input;
        SendMessage request = new SendMessage();
        request.setChatId(chatId.toString());
        request.setText("Awesome! Lastly, pick the genre.");
        request.setParseMode(ParseMode.MARKDOWN);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboardRowList = new ArrayList<KeyboardRow>();
        List<String> options = new ArrayList<String>();
        options.add("Fantasy");
        options.add("Historical");
        options.add("Mystery");
        options.add("Romance");
        options.add("Youth");
        replyKeyboardMarkup = createOptions(replyKeyboardMarkup,
                keyboardRowList, options);
        //added the line after this -> purpose: remove the keyboard after pressing the button
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        request.setReplyMarkup(replyKeyboardMarkup);

        try {
            execute(request); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void processGenre(String input, Long chatId) {
        genreChosen = input;
        Database db = new Database();

        SendMessage request = new SendMessage();
        request.setChatId(chatId.toString());
        List<String> options = db.getDramaTitle(countryChosen, yearChosen, genreChosen);
        if (options.isEmpty()) {
            request.setText("No drama found. Please try again");
            request.setParseMode(ParseMode.MARKDOWN);
        } else {
            request.setText("These are the generated dramas.");
            request.setParseMode(ParseMode.MARKDOWN);

            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setResizeKeyboard(true);
            List<KeyboardRow> keyboardRowList = new ArrayList<KeyboardRow>();

            replyKeyboardMarkup = createOptions(replyKeyboardMarkup,
                    keyboardRowList, options);
            //added the line after this -> purpose: remove the keyboard after pressing the button
            replyKeyboardMarkup.setOneTimeKeyboard(true);
            request.setReplyMarkup(replyKeyboardMarkup);
        }
        try {
            execute(request); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void processCallBackQuery(String data, Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        Database db = new Database();
        Drama selectedDrama = db.getDrama(dramaSelectedTitle);
        if (data.equals("see_synopsis")) {
            message.setText(selectedDrama.getSynopsis());
        } else if (data.equals("see_cast")) {
            message.setText(selectedDrama.getCast());
        } else if (data.equals("see_numEps")) {
            message.setText(Integer.toString(selectedDrama.getEps()));
        } else if (data.equals("see_year")) {
            message.setText(selectedDrama.getYear());
        } else { //genre
            message.setText(selectedDrama.getGenre().toString());
        }

        message.setParseMode(ParseMode.MARKDOWN);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void processRequest(String update, Long chatId) {
        dramaSelectedTitle = update;
        SendMessage request = new SendMessage();
        request.setChatId(chatId.toString());
        request.setText(update);
        request.setParseMode(ParseMode.MARKDOWN);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<List<InlineKeyboardButton>>();
        List<InlineKeyboardButton> rowInline1 = new ArrayList<InlineKeyboardButton>();
        List<InlineKeyboardButton> rowInline2 = new ArrayList<InlineKeyboardButton>();

        //synopsis
        InlineKeyboardButton synopsis = new InlineKeyboardButton();
        synopsis.setText("Synopsis");
        synopsis.setCallbackData("see_synopsis");
        rowInline1.add(synopsis);

        //cast
        InlineKeyboardButton cast = new InlineKeyboardButton();
        cast.setText("Cast");
        cast.setCallbackData("see_cast");
        rowInline1.add(cast);

        //numOfEps
        InlineKeyboardButton numEps = new InlineKeyboardButton();
        numEps.setText("Number of Episodes");
        numEps.setCallbackData("see_numEps");
        rowInline2.add(numEps);

        //year
        InlineKeyboardButton year = new InlineKeyboardButton();
        year.setText("Year");
        year.setCallbackData("see_year");
        rowInline1.add(year);

        //genre
        InlineKeyboardButton genre = new InlineKeyboardButton();
        genre.setText("Genre");
        genre.setCallbackData("see_genre");
        rowInline2.add(genre);

        // Set the keyboard to the markup
        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);

        // Add it to the message
        markupInline.setKeyboard(rowsInline);
        request.setReplyMarkup(markupInline);
        try {
            execute(request); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}