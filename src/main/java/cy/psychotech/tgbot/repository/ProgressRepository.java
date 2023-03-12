package cy.psychotech.tgbot.repository;

import static cy.psychotech.db.tables.AccentuationProgress.ACCENTUATION_PROGRESS;

import cy.psychotech.db.tables.pojos.AccentuationProgress;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProgressRepository {
  private final DSLContext dslContext;

  public Optional<AccentuationProgress> getProgress(Long chatId) {
    return this.dslContext.selectFrom(ACCENTUATION_PROGRESS)
        .where(ACCENTUATION_PROGRESS.CHAT_ID.eq(chatId))
        .fetchOptional()
        .map(r -> r.into(AccentuationProgress.class));
  }

  public Optional<AccentuationProgress> getResult(Long chatId) {
    return this.getProgress(chatId).filter(r -> r.getIsDone().equals(true));
  }
}
