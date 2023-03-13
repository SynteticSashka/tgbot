package cy.psychotech.tgbot.service;

import cy.psychotech.tgbot.model.Response;

public interface StateHandler {
  Response handle(String id, String text, int state);
}
