package cy.psychotech.tgbot.repository;

import static cy.psychotech.db.tables.Client.CLIENT;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ClientRepository {
  private final DSLContext dslContext;

}
