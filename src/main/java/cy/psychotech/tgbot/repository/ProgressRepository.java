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

  public Optional<AccentuationProgress> getProgress(String id) {
    return this.dslContext.selectFrom(ACCENTUATION_PROGRESS)
        .where(ACCENTUATION_PROGRESS.ID.eq(id))
        .fetchOptional()
        .map(r -> r.into(AccentuationProgress.class));
  }

  public AccentuationProgress createProgress(String id) {
    return this.dslContext.insertInto(ACCENTUATION_PROGRESS)
        .columns(ACCENTUATION_PROGRESS.ID)
        .values(id)
        .returning()
        .fetchOne()
        .map(r -> r.into(AccentuationProgress.class));
  }

  public Optional<AccentuationProgress> getResult(String id) {
    return this.getProgress(id).filter(r -> r.getIsDone().equals(true));
  }
}
