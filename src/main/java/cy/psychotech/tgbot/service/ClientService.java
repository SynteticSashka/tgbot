package cy.psychotech.tgbot.service;

import cy.psychotech.db.tables.pojos.Client;
import cy.psychotech.tgbot.repository.ClientRepository;
import cy.psychotech.tgbot.repository.ProgressRepository;
import cy.psychotech.tgbot.repository.StateMessagesRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {
  private final ClientRepository clientRepository;
  private final StateMessagesRepository stateMessagesRepository;
  private final ProgressRepository progressRepository;

  public Client getOrCreateClient(Long chatId) {
    final Optional<Client> maybeClient = clientRepository.getClient(chatId);

    if (maybeClient.isEmpty()) {
      final Client client = clientRepository.createClient(chatId);
      progressRepository.createProgress(client.getId());
      return client;
    }

    return maybeClient.get();
  }

  public Client setName(String id, String name) {
    return clientRepository.setName(id, name);
  }

}
