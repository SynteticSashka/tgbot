package cy.psychotech.tgbot.controller;

import cy.psychotech.tgbot.model.TelegramBot;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@RequiredArgsConstructor
public class WebHookController {
  private final TelegramBot telegramBot;

  @PostMapping("/")
  public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
    return telegramBot.onWebhookUpdateReceived(update);
  }

  @GetMapping
  public ResponseEntity get() {
    return ResponseEntity.ok().build();
  }
}
