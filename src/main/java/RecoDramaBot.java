import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class RecoDramaBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "RecoDrama_Bot";
    }

    @Override
    public String getBotToken() {
        return "1469593034:AAHBnNJdUvF0AVw1BiSMa7UqIO0Ou3svD3I";
    }

    @Override
    public void onUpdateReceived(Update update) {

    }
}
