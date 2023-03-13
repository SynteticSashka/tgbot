package cy.psychotech.tgbot.service;

import cy.psychotech.tgbot.repository.StateMessagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccentuationService {
  private final StateMessagesRepository repository;

  public String getFullResult(String clientId) {
    //TODO Здесь нужно собрать весь ответ с результатами
  }

}
