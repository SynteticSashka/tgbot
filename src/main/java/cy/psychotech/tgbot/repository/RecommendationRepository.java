package cy.psychotech.tgbot.repository;

import static cy.psychotech.db.tables.AccentuationRecommendations.ACCENTUATION_RECOMMENDATIONS;

import cy.psychotech.db.tables.records.AccentuationRecommendationsRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class RecommendationRepository {
  private final DSLContext dslContext;

  public List<String> getRecommendationList() {
    return this.dslContext.selectFrom(ACCENTUATION_RECOMMENDATIONS)
        .fetch()
        .stream()
        .map(AccentuationRecommendationsRecord::component2)
        .collect(Collectors.toList());
  }
}
