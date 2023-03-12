package cy.psychotech.tgbot.repository;

import static cy.psychotech.db.tables.Dictionary.DICTIONARY;

import cy.psychotech.db.tables.pojos.Dictionary;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DictionaryRepository {
  private final DSLContext dslContext;

  public Optional<String> getText(Long id) {
    return this.dslContext.selectFrom(DICTIONARY)
        .where(DICTIONARY.ID.eq(id))
        .fetchOptional()
        .map(r -> r.into(Dictionary.class))
        .map(Dictionary::getText);
  }
}
