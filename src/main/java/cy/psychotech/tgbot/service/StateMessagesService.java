package cy.psychotech.tgbot.service;

import cy.psychotech.tgbot.exception.StateNotFoundException;
import cy.psychotech.tgbot.repository.StateMessagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateMessagesService {
  private final StateMessagesRepository repository;

  public String getMessageForState(int state) {
    return repository.getMessage(state)
        .orElseThrow(() -> new StateNotFoundException(String.format("State %d not found", state)));
  }
}
