package cy.psychotech.tgbot.service;

import cy.psychotech.db.tables.pojos.Client;
import cy.psychotech.tgbot.model.Response;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class PrimaryService {
  private final ClientService clientService;
  private final ResponseService responseService;

  /***
   * This is main method than process any message incoming from bot
   */
  public SendMessage handleMessage(Update update) {
    final Message message = update.getMessage();
    final SendMessage.SendMessageBuilder builder = SendMessage.builder()
        .chatId(String.valueOf(message.getChatId()));

    if (message.hasText() || message.hasReplyMarkup()) {
      final Response response = handleMessage(message);
      if (response.hasText()) {
        builder.text(response.getText());
      }
      if (response.hasKeyboard()) {
        builder.replyMarkup(response.getKeyboard());
      }
    }

    return builder.build();
  }

  @Transactional
  public Response handleMessage(Message message) {
    final Long chatId = message.getChatId();

    // Если у нас уже есть клиент из этого чата - возвращаем его, если нет - создаём нового
    final Client client = clientService.getOrCreateClient(chatId);
    // Основная метка, на каком этапе сейчас находится клиент
    final Integer currentState = client.getCurrentState();

    return responseService.handle(
        client.getId(),
        Optional.ofNullable(message.getText()).orElse(""),
        currentState
    );
  }
}
