package cy.psychotech.tgbot.exception;

public class QuestionNotFoundException extends RuntimeException {
  public QuestionNotFoundException (String message) {
    super(message);
  }
}
