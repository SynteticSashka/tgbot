package cy.psychotech.tgbot.repository;

import static cy.psychotech.db.tables.StateMessages.STATE_MESSAGES;

import cy.psychotech.db.tables.pojos.StateMessages;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StateMessagesRepository {
  private final DSLContext dslContext;

  public Optional<String> getMessage(int state) {
    return this.dslContext.selectFrom(STATE_MESSAGES)
        .where(STATE_MESSAGES.STATE.eq(state))
        .fetchOptional()
        .map(r -> r.into(StateMessages.class))
        .map(StateMessages::getMessage);
  }
}
