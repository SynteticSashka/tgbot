package cy.psychotech.tgbot.repository;

import static cy.psychotech.db.Tables.ACCENTUATION_QUESTIONS;

import cy.psychotech.db.tables.records.AccentuationQuestionsRecord;
import cy.psychotech.tgbot.exception.QuestionNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccentuationQuestionsRepository {
  private final DSLContext dslContext;

  public String getQuestion(int number) {
    return this.dslContext.selectFrom(ACCENTUATION_QUESTIONS)
        .where(ACCENTUATION_QUESTIONS.ID.eq((long) number))
        .fetchOptional()
        .map(AccentuationQuestionsRecord::component2)
        .orElseThrow(() -> new QuestionNotFoundException(String.format("Question %d not found", number)));
  }
}
