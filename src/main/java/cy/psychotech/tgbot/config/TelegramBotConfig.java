package cy.psychotech.tgbot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class TelegramBotConfig {
  @Value("${telegrambot.webHookPath}")
  private String webHookPath;
  @Value("${telegrambot.userName}")
  private String userName;
  @Value("${telegrambot.botToken}")
  private String botToken;
}
