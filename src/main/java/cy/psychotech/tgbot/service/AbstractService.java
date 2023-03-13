package cy.psychotech.tgbot.service;

import cy.psychotech.tgbot.model.Response;

public abstract class AbstractService {
  public abstract Response handle(int state);
}
