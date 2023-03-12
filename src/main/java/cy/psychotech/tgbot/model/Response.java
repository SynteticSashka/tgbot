package cy.psychotech.tgbot.model;

import java.util.Optional;
import lombok.Builder;
import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

@Data
@Builder
public class Response {
  private String text;
  private ReplyKeyboard keyboard;

  public boolean hasText() {
    return text != null;
  }

  public boolean hasKeyboard() {
    return keyboard != null;
  }

  public Optional<String> getOptionalText() {
    return Optional.ofNullable(text);
  }

  public Optional<ReplyKeyboard> getOptionalReplyKeyboard() {
    return Optional.ofNullable(keyboard);
  }
}
