package cy.psychotech.tgbot.util;

import lombok.experimental.UtilityClass;
import java.util.ArrayList;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

@UtilityClass
public class KeyboardUtils {
  public ReplyKeyboard getYesNoKeyboard() {
    ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
    keyboard.setResizeKeyboard(true); //подгоняем размер
    keyboard.setOneTimeKeyboard(false); //скрываем после использования

    //Создаем список с рядами кнопок
    ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
    //Создаем один ряд кнопок и добавляем его в список
    KeyboardRow keyboardRow = new KeyboardRow();
    keyboardRows.add(keyboardRow);
    //Добавляем две кнопки
    keyboardRow.add(new KeyboardButton("Скорее да"));
    keyboardRow.add(new KeyboardButton("Скорее нет"));
    //добавляем лист с одним рядом кнопок в главный объект
    keyboard.setKeyboard(keyboardRows);
    return keyboard;
  }
}
