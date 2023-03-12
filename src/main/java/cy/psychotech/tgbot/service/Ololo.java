package cy.psychotech.tgbot.service;

import cy.psychotech.tgbot.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class Ololo {
  private final RecommendationRepository recommendationRepository;

  @PostConstruct
  public void init() {
//    recommendationRepository.getRecommendationList().forEach(r -> {
//      System.out.println(r);
//      System.out.println("==============================");
//    });
  }
}
