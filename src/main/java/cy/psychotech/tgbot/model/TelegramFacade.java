package cy.psychotech.tgbot.model;

import cy.psychotech.tgbot.service.PrimaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class TelegramFacade {
  private final PrimaryService primaryService;

  public BotApiMethod<?> handleUpdate(Update update) {
    if (update.hasCallbackQuery()) {
      CallbackQuery callbackQuery = update.getCallbackQuery();
      return null;
    } else if (update.hasMessage()) {
      return primaryService.handleMessage(update);
    }
    return null;
  }
}
