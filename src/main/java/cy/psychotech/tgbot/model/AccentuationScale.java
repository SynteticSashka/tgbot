package cy.psychotech.tgbot.model;

import static cy.psychotech.db.tables.AccentuationProgress.ACCENTUATION_PROGRESS;

import lombok.RequiredArgsConstructor;
import org.jooq.Field;

@RequiredArgsConstructor
public enum AccentuationScale {
  DEMONSTRATIVNOST(1, ACCENTUATION_PROGRESS.DEMONSTR_SCORE),
  ZASTREVANIE(2, ACCENTUATION_PROGRESS.ZASTR_SCORE),
  PEDANTICHNOST(3, ACCENTUATION_PROGRESS.PEDANT_SCORE),
  VOZBUDIMOST(4, ACCENTUATION_PROGRESS.VOZB_SCORE),
  GIPERTIMNOST(5, ACCENTUATION_PROGRESS.GIPERTIM_SCORE),
  DISTIMNOST(6, ACCENTUATION_PROGRESS.DISTIM_SCORE),
  TREVOZJNOST(7, ACCENTUATION_PROGRESS.TREVOZJ_SCORE),
  EXALTIROVANNOST(8, ACCENTUATION_PROGRESS.EXALT_SCORE),
  EMOTIVNOST(9, ACCENTUATION_PROGRESS.EMOTIV_SCORE),
  TSIKLOTIMNOST(10, ACCENTUATION_PROGRESS.TSIKLOTIM_SCORE);

  private final Integer id;
  private final Field<Integer> field;

  public static Field<Integer> getField(AccentuationScale scale) {
    return scale.field;
  }

  public static Integer getId(AccentuationScale scale) {
    return scale.id;
  }
}
