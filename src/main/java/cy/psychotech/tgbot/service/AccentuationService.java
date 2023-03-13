package cy.psychotech.tgbot.service;

import cy.psychotech.tgbot.model.Response;
import cy.psychotech.tgbot.repository.StateMessagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccentuationService extends AbstractService {
  private final StateMessagesRepository repository;

  public Response handle(int state) {

  }
}
