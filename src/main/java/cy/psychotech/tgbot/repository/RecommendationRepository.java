package cy.psychotech.tgbot.repository;

import static cy.psychotech.db.tables.AccentuationRecommendations.ACCENTUATION_RECOMMENDATIONS;

import cy.psychotech.db.tables.records.AccentuationRecommendationsRecord;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecommendationRepository {
  private final DSLContext dslContext;

  public List<String> getRecommendationList() {
    try (final Stream<AccentuationRecommendationsRecord> stream =
             this.dslContext.selectFrom(ACCENTUATION_RECOMMENDATIONS)
                 .fetch()
                 .stream()) {
      return stream
          .map(AccentuationRecommendationsRecord::component2)
          .collect(Collectors.toList());
    }
  }
}
