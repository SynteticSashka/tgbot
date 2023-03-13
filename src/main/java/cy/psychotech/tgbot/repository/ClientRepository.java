package cy.psychotech.tgbot.repository;

import static cy.psychotech.db.tables.Client.CLIENT;

import cy.psychotech.db.tables.pojos.Client;
import cy.psychotech.tgbot.exception.ClientNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;



@Repository
@RequiredArgsConstructor
public class ClientRepository {
  private final DSLContext dslContext;

  public Optional<Client> getClient(Long chatId) {
    return this.get(chatId, null, false);
  }

  public Optional<Client> get(Long chatId, String id, boolean forUpdate) {
    final var select = this.dslContext.selectFrom(CLIENT);

    final var whereStep = id != null
        ? select.where(CLIENT.ID.eq(id))
        : select.where(CLIENT.CHAT_ID.eq(chatId));

    final var step = forUpdate ? whereStep.forUpdate() : whereStep;

    return step
        .fetchOptional()
        .map(r -> r.into(Client.class));
  }

  public Optional<Client> toNextState(String clientId) {
    return toNextState(clientId, 1);
  }

  public Optional<Client> toNextState(String clientId, int steps) {
    final Optional<Client> maybeClient = get(null, clientId, true);

    return maybeClient.map(client -> this.dslContext.update(CLIENT)
        .set(CLIENT.CURRENT_STATE, client.getCurrentState() + steps)
        .set(CLIENT.UPDATED_AT, LocalDateTime.now())
        .returning()
        .fetchOptional()
        .map(r -> r.into(Client.class))
        .orElseThrow(() -> new ClientNotFoundException(String.format("Client with id %s not found", clientId))));
  }

  public Client setName(String id, String name) {
    return this.dslContext.update(CLIENT)
        .set(CLIENT.NAME, name)
        .set(CLIENT.UPDATED_AT, LocalDateTime.now())
        .where(CLIENT.ID.eq(id))
        .returning()
        .fetchOptional()
        .map(r -> r.into(Client.class))
        .orElseThrow(() -> new ClientNotFoundException(String.format("Client with id %s not found", id)));
  }

  public Client createClient(Long chatId) {
    final String id = UUID.randomUUID().toString();
    return this.dslContext.insertInto(CLIENT)
        .columns(CLIENT.CHAT_ID, CLIENT.ID)
        .values(chatId, id)
        .returning()
        .fetchOne()
        .map(r -> r.into(Client.class));
  }

}
