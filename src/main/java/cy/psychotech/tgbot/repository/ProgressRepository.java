package cy.psychotech.tgbot.repository;

import static cy.psychotech.db.tables.AccentuationProgress.ACCENTUATION_PROGRESS;

import cy.psychotech.db.tables.pojos.AccentuationProgress;
import java.util.Optional;

import cy.psychotech.tgbot.model.AccentuationScale;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProgressRepository {
  private final DSLContext dslContext;

  public Optional<AccentuationProgress> setIsDone(String id, boolean isDone) {
    return this.dslContext.update(ACCENTUATION_PROGRESS)
        .set(ACCENTUATION_PROGRESS.IS_DONE, isDone)
        .where(ACCENTUATION_PROGRESS.ID.eq(id))
        .returning()
        .fetchOptional()
        .map(r -> r.into(AccentuationProgress.class));
  }

  public Optional<AccentuationProgress> upScale(String id, AccentuationScale scale, int change) {
    var maybeProgress = getProgress(id, true);

    if (maybeProgress.isPresent()) {
      var progress = maybeProgress.get();

      final Integer value = switch (scale) {
        case DISTIMNOST -> progress.getDistimScore();
        case EMOTIVNOST -> progress.getEmotivScore();
        case TREVOZJNOST -> progress.getTrevozjScore();
        case VOZBUDIMOST -> progress.getVozbScore();
        case ZASTREVANIE -> progress.getZastrScore();
        case GIPERTIMNOST -> progress.getGipertimScore();
        case PEDANTICHNOST -> progress.getPedantScore();
        case TSIKLOTIMNOST -> progress.getTsiklotimScore();
        case EXALTIROVANNOST -> progress.getExaltScore();
        case DEMONSTRATIVNOST -> progress.getDemonstrScore();
      };

      return this.dslContext.update(ACCENTUATION_PROGRESS)
          .set(AccentuationScale.getField(scale), value + change)
          .where(ACCENTUATION_PROGRESS.ID.eq(id))
          .returning()
          .fetchOptional()
          .map(r -> r.into(AccentuationProgress.class));
    }
    return Optional.empty();
  }

  public Optional<AccentuationProgress> getProgress(String id, boolean forUpdate) {
    var select = this.dslContext.selectFrom(ACCENTUATION_PROGRESS)
        .where(ACCENTUATION_PROGRESS.ID.eq(id));

    var step = forUpdate ? select.forUpdate() : select;

    return step
        .fetchOptional()
        .map(r -> r.into(AccentuationProgress.class));
  }

  public Optional<AccentuationProgress> getProgress(String id) {
    return getProgress(id, false);
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
