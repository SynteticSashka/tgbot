package cy.psychotech.tgbot.repository;

import static cy.psychotech.db.tables.Client.CLIENT;

import cy.psychotech.db.tables.pojos.Client;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ClientRepository {
  private final DSLContext dslContext;

  public Optional<Client> getClient(Long chatId) {
    return this.dslContext.selectFrom(CLIENT)
        .where(CLIENT.CHAT_ID.eq(chatId))
        .fetchOptional()
        .map(r -> r.into(Client.class));
  }

  public Optional<Client> setName(String id, String name) {
    return this.dslContext.update(CLIENT)
        .set(CLIENT.NAME, name)
        .set(CLIENT.UPDATED_AT, LocalDateTime.now())
        .where(CLIENT.ID.eq(id))
        .returning()
        .fetchOptional()
        .map(r -> r.into(Client.class));
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
