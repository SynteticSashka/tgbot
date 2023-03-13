package cy.psychotech.tgbot.service;

import cy.psychotech.db.tables.pojos.Client;
import cy.psychotech.tgbot.exception.ClientNotFoundException;
import cy.psychotech.tgbot.model.Response;
import cy.psychotech.tgbot.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService extends AbstractService {
  private static final String ERROR_MESSAGE = "Упс! Что-то сломалось. Пожалуйста, сообщите об ошибке моему создателю!";
  private static final String CANT_READ_MESSAGE = "Не могу распознать ответ. Пожалуйста, проверь и напиши ещё раз!";
  private final ClientRepository clientRepository;

  @Override
  public Response handle(int state) {

  }

  public Client getOrCreateClient(Long chatId) {
    return clientRepository.getClient(chatId)
        .orElse(clientRepository.createClient(chatId));
  }

  public Client setName(String id, String name) {
    return clientRepository.setName(id, name)
        .orElseThrow(() -> new ClientNotFoundException(String.format("Client with id %s not found", id)));
  }


}
