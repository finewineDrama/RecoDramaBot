import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.*;
import java.util.concurrent.CompletableFuture;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
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

import static java.net.URI.*;


public class RecoDramaBot extends TelegramLongPollingBot {
    private static final Logger LOG = Logger.getLogger(RecoDramaBot.class.getName());
    public static String countryChosen;
    public static String yearChosen;
    public static String genreChosen;
    public static String dramaChosenTitle;
    public List<String> options;
    private static final String korea = "South Korea";
    private static final String china = "China";
    //private static final String korea = EmojiParser.parseToUnicode("South Korea :kr:");
    //private static final String china = EmojiParser.parseToUnicode("China :cn:");


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

            } else if (input.equals("2019") || input.equals("2020") || input.equals("2017") || input.equals("2018")) {
                processYear(input, chatId);

                //returns list of dramas

            } else if (input.equals("Fantasy") || input.equals("Historical")
                    || input.equals("Mystery") || input.equals("Romance") || input.equals("Youth")) {
                try {
                    processGenre(input, chatId);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                /*
                SendPhoto dramaPoster = new SendPhoto();
                dramaPoster.setChatId(chatId.toString());
                //dramaPoster.setPhoto("https://images.unsplash.com/photo-1590272456521-1bbe160a18ce?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MTF8fHxlbnwwfHx8&w=1000&q=80");
                //dramaPoster.setPhoto(getPhoto("https://images.unsplash.com/photo-1590272456521-1bbe160a18ce?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MTF8fHxlbnwwfHx8&w=1000&q=80"));
                try {
                    execute(dramaPoster);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

                URL url = null;
                try {
                    url = new URL("https://www.kindpng.com/picc/m/276-2764257_instagram-icon-instagram-logo-small-size-hd-png.png");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                URI uri = null;
                try {
                    uri = url.toURI();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                File file = new File(uri.toString());
                SendPhoto dramaPoster = new SendPhoto();
                InputFile photo = new InputFile(file);
                dramaPoster.setPhoto(photo);
                dramaPoster.setChatId(chatId.toString());
                try {
                    execute(dramaPoster);
                    System.out.println("printed");
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                    System.out.println("end");
                }

                 */

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
        options.add("2017");
        options.add("2018");
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

    public void processGenre(String input, Long chatId) throws SQLException {
        genreChosen = input;

        SendMessage request = new SendMessage();
        request.setChatId(chatId.toString());

        String url = "jdbc:mysql://127.0.0.1:3306/dramadb";
        String username = "root";
        String password = "IL2pBB&D";
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM drama WHERE country = " + "\"" + countryChosen + "\"" + " and genre = " + "\"" + genreChosen + "\"" + " and year = "+ "\"" +
                yearChosen + "\"";
        ResultSet rs = statement.executeQuery(sql);
        options = new ArrayList<>();
        while (rs.next()) {
            options.add(rs.getString("title"));
        }

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
            //replyKeyboardMarkup.setOneTimeKeyboard(true);
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
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/dramadb";
            String username = "root";
            String password = "IL2pBB&D";
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String sql = "select * from drama where title = " + "\"" + dramaChosenTitle + "\"";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            if (data.equals("see_synopsis")) {
                message.setText(rs.getString("synopsis"));
            } else if (data.equals("see_cast")) {
                message.setText(rs.getString("cast"));
            } else if (data.equals("see_numEps")) {
                message.setText(Integer.toString(rs.getInt("episodes")));
            } else if (data.equals("see_year")) {
                message.setText(rs.getString("year"));
            } else if (data.equals("see_genre")) {
                message.setText(rs.getString("genre"));
            } else { //back
                message.setText("These are the generated dramas.");
                message.setParseMode(ParseMode.MARKDOWN);

                ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                replyKeyboardMarkup.setResizeKeyboard(true);
                List<KeyboardRow> keyboardRowList = new ArrayList<KeyboardRow>();

                replyKeyboardMarkup = createOptions(replyKeyboardMarkup,
                        keyboardRowList, options);
                //added the line after this -> purpose: remove the keyboard after pressing the button
                replyKeyboardMarkup.setOneTimeKeyboard(true);
                message.setReplyMarkup(replyKeyboardMarkup);

            }
            message.setParseMode(ParseMode.MARKDOWN);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    public void processRequest(String update, Long chatId) {
        dramaChosenTitle = update;
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

        //numOfEps
        InlineKeyboardButton numEps = new InlineKeyboardButton();
        numEps.setText("Num of Eps");
        numEps.setCallbackData("see_numEps");
        rowInline1.add(numEps);

        //cast
        InlineKeyboardButton cast = new InlineKeyboardButton();
        cast.setText("Cast");
        cast.setCallbackData("see_cast");
        rowInline2.add(cast);

        //year
        InlineKeyboardButton year = new InlineKeyboardButton();
        year.setText("Year");
        year.setCallbackData("see_year");
        rowInline2.add(year);

        //genre
        InlineKeyboardButton genre = new InlineKeyboardButton();
        genre.setText("Genre");
        genre.setCallbackData("see_genre");
        rowInline2.add(genre);

        //back
        InlineKeyboardButton back = new InlineKeyboardButton();
        back.setText("Back");
        back.setCallbackData("see_previous");
        rowInline2.add(back);

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

    /*
    public InputFile getPhoto(String url) {
        // Create a URI from url
        URI uri = create(url);
        File file = new File(uri);
        InputFile photo = new InputFile(file);
        return photo;
    }

     */
}