package cy.psychotech.tgbot.config;

import cy.psychotech.tgbot.model.TelegramBot;
import cy.psychotech.tgbot.model.TelegramFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
  private final TelegramBotConfig botConfig;

  @Bean
  public SetWebhook setWebhookInstance() {
    return SetWebhook.builder().url(botConfig.getWebHookPath()).build();
  }

  @Bean
  public TelegramBot springWebhookBot(SetWebhook setWebhook, TelegramFacade telegramFacade) {
    TelegramBot bot = new TelegramBot(telegramFacade, setWebhook);
    bot.setBotToken(botConfig.getBotToken());
    bot.setBotUsername(botConfig.getUserName());
    bot.setBotPath(botConfig.getWebHookPath());
    return bot;
  }
}
