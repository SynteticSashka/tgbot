package cy.psychotech.tgbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class PrimaryService {
  private final ClientService clientService;

  /***
   * This is main method than process any message incoming from bot
   */
  public SendMessage handleMessage(Update update) {
    final Message message = update.getMessage();
    final SendMessage.SendMessageBuilder builder = SendMessage.builder()
        .chatId(String.valueOf(message.getChatId()));

    if (message.hasText()) {
      builder.text(clientService.handleMessage(message));
    }

    return builder.build();
  }

  public SendMessage CallbackQuery(Update update) {
    return new SendMessage(); //TODO
  }

}
