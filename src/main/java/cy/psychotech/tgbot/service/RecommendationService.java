package cy.psychotech.tgbot.service;

import cy.psychotech.db.tables.pojos.AccentuationProgress;
import cy.psychotech.tgbot.repository.ProgressRepository;
import cy.psychotech.tgbot.repository.RecommendationRepository;
import cy.psychotech.tgbot.util.ServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecommendationService {
  private final RecommendationRepository recommendationRepository;
  private final ProgressRepository progressRepository;
  
  // Система рекомендаций
  public List<String> getRecommendations(AccentuationProgress progress) {
    final Map<Long, Integer> scalesWithValues = ServiceUtils.progressToMap(progress);
    final List<String> texts = recommendationRepository.getRecommendationList();
    List<String> recommendations = new ArrayList<>();

    // Большинство показателей меньше или равно 6
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getValue() <= 6)
        .count() >= 8) {
      recommendations.add(texts.get(0));
    }
    // Более 5 показателей 19+ баллов
    if (scalesWithValues.entrySet().stream().filter(r -> r.getValue() >= 19).count() >= 5) {
      recommendations.add(texts.get(1));
    }
    // Гипертимность, циклотимность и демонстративность <=7
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 1 || r.getKey() == 5 || r.getKey() == 10)
        .filter(r -> r.getValue() <= 7)
        .count() >= 3) {
      recommendations.add(texts.get(2));
    }
    // Гипертимность, циклотимность и демонстративность >=18
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 1 || r.getKey() == 5 || r.getKey() == 10)
        .filter(r -> r.getValue() >= 18)
        .count() >= 3) {
      recommendations.add(texts.get(3));
    }
    // Застревание, возбудимость, эмотивность, тревожность, экзальтированность (3 из них) <=7
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 2 || r.getKey() == 4 || r.getKey() == 7 || r.getKey() == 8 || r.getKey() == 9)
        .filter(r -> r.getValue() <= 7)
        .count() >= 3) {
      recommendations.add(texts.get(4));
    }
    // Застревание, возбудимость, эмотивность, тревожность, экзальтированность (3 из них) >=18
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 2 || r.getKey() == 4 || r.getKey() == 7 || r.getKey() == 8 || r.getKey() == 9)
        .filter(r -> r.getValue() >= 18)
        .count() >= 3) {
      recommendations.add(texts.get(5));
    }
    // Демонстративность и гипертимность >= 15
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 1 || r.getKey() == 5)
        .filter(r -> r.getValue() >= 15)
        .count() >= 2) {
      recommendations.add(texts.get(6));
    }
    // Педантичность и гипертимность >= 15
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 3 || r.getKey() == 5)
        .filter(r -> r.getValue() >= 15)
        .count() >= 2) {
      recommendations.add(texts.get(7));
    }
    // Педантичность и дистимность >= 15
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 3 || r.getKey() == 6)
        .filter(r -> r.getValue() >= 15)
        .count() >= 2) {
      recommendations.add(texts.get(8));
    }
    // Педантичность и тревожность >= 15
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 3 || r.getKey() == 7)
        .filter(r -> r.getValue() >= 15)
        .count() >= 2) {
      recommendations.add(texts.get(9));
    }
    // Гипертимность и дистимность 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 5 || r.getKey() == 6)
        .filter(r -> r.getValue() >= 16)
        .count() >= 2) {
      recommendations.add(texts.get(10));
    }
    // Гипертимность 16+ дистимность 8-
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 5 && r.getValue() >= 16) || (r.getKey() == 6 && r.getValue() <= 8))
        .count() >= 2) {
      recommendations.add(texts.get(11));
    }
    // Дистимность 12+ гипертимность 8-
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 5 && r.getValue() <= 8) || (r.getKey() == 6 && r.getValue() >= 12))
        .count() >= 2) {
      recommendations.add(texts.get(12));
    }
    // Дистимность и гипертимность 8-, циклотимность 8+, застревание 15+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 5 && r.getValue() <= 8)
            || (r.getKey() == 6 && r.getValue() <= 8)
            || (r.getKey() == 10 && r.getValue() >= 8)
            || (r.getKey() == 2 && r.getValue() >= 15)
        ).count() >= 4) {
      recommendations.add(texts.get(13));
    }
    // Дистимность и гипертимность 8-, циклотимность 8+, застревание 15+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 5 && r.getValue() <= 8)
            || (r.getKey() == 6 && r.getValue() <= 8)
            || (r.getKey() == 9 && r.getValue() >= 8)
            || (r.getKey() == 7 && r.getValue() >= 15)
        ).count() >= 4) {
      recommendations.add(texts.get(14));
    }
    // Гипертимность и циклотимность 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 5 || r.getKey() == 10)
        .filter(r -> r.getValue() >= 16)
        .count() >= 2) {
      recommendations.add(texts.get(15));
    }
    // Гипертимность 16+ циклотимность 8-
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 5 && r.getValue() >= 16) || (r.getKey() == 10 && r.getValue() <= 8))
        .count() >= 2) {
      recommendations.add(texts.get(16));
    }
    // Циклотимность 16+ гипертимность 8-
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 10 && r.getValue() <= 8) || (r.getKey() == 5 && r.getValue() >= 16))
        .count() >= 2) {
      recommendations.add(texts.get(17));
    }
    // Циклотимность и гипертимность 8-
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 5 || r.getKey() == 10)
        .filter(r -> r.getValue() <= 8)
        .count() >= 2) {
      recommendations.add(texts.get(18));
    }
    // Дистимность 12+ циклотимность 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 10 && r.getValue() >= 16) || (r.getKey() == 6 && r.getValue() >= 12))
        .count() >= 2) {
      recommendations.add(texts.get(19));
    }
    // Дистимность 12+ циклотимность 8-
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 10 && r.getValue() <= 8) || (r.getKey() == 6 && r.getValue() >= 12))
        .count() >= 2) {
      recommendations.add(texts.get(20));
    }
    // Дистимность 6- циклотимность 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 10 && r.getValue() >= 16) || (r.getKey() == 6 && r.getValue() <= 6))
        .count() >= 2) {
      recommendations.add(texts.get(21));
    }
    // Дистимность 6- циклотимность 8-
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 10 && r.getValue() <= 8) || (r.getKey() == 6 && r.getValue() <= 6))
        .count() >= 2) {
      recommendations.add(texts.get(22));
    }
    // Экзальтированность и циклотимность 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 8 || r.getKey() == 10)
        .filter(r -> r.getValue() >= 16)
        .count() >= 2) {
      recommendations.add(texts.get(23));
    }
    // Экзальтированность 16+ циклотимность 8-
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 8 && r.getValue() <= 8) || (r.getKey() == 10 && r.getValue() >= 16))
        .count() >= 2) {
      recommendations.add(texts.get(24));
    }
    // Экзальтированность и циклотимность 8-
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 8 || r.getKey() == 10)
        .filter(r -> r.getValue() <= 8)
        .count() >= 2) {
      recommendations.add(texts.get(25));
    }
    // Экзальтированность и эмотивность 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 8 || r.getKey() == 9)
        .filter(r -> r.getValue() >= 16)
        .count() >= 2) {
      recommendations.add(texts.get(26));
    }
    // Экзальтированность 8- эмотивность 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 8 && r.getValue() <= 8) || (r.getKey() == 9 && r.getValue() >= 16))
        .count() >= 2) {
      recommendations.add(texts.get(27));
    }
    // Экзальтированность 16+ эмотивность 8-
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 9 && r.getValue() <= 8) || (r.getKey() == 8 && r.getValue() >= 16))
        .count() >= 2) {
      recommendations.add(texts.get(28));
    }
    // Экзальтированность и эмотивность 8-
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 8 || r.getKey() == 9)
        .filter(r -> r.getValue() <= 8)
        .count() >= 2) {
      recommendations.add(texts.get(29));
    }
    // Гипертимность, эмотивность тревожность 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 5 || r.getKey() == 7 || r.getKey() == 9)
        .filter(r -> r.getValue() >= 16)
        .count() >= 3) {
      recommendations.add(texts.get(30));
    }
    // Гипертимность, эмотивность 16+, тревожность 8-
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 5 && r.getValue() >= 16)
            || (r.getKey() == 9 && r.getValue() >= 16)
            || (r.getKey() == 7 && r.getValue() <= 8)
        )
        .count() >= 3) {
      recommendations.add(texts.get(31));
    }
    // Гипертимность, эмотивность , дистимность, застревание 16+, циклотимность и воздубимость 8-
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 5 && r.getValue() >= 16)
            || (r.getKey() == 9 && r.getValue() >= 16)
            || (r.getKey() == 6 && r.getValue() >= 12)
            || (r.getKey() == 2 && r.getValue() >= 16)
            || (r.getKey() == 10 && r.getValue() <= 8)
            || (r.getKey() == 4 && r.getValue() <= 8)
        )
        .count() >= 6) {
      recommendations.add(texts.get(32));
    }
    // Гипертимность 16+ тревожность 8-
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 7 && r.getValue() <= 8) || (r.getKey() == 5 && r.getValue() >= 16))
        .count() >= 2) {
      recommendations.add(texts.get(33));
    }
    // Гипертимность 8- тревожность 16+, дистимность 12+ застревание 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 5 && r.getValue() <= 8)
            || (r.getKey() == 7 && r.getValue() >= 16)
            || (r.getKey() == 6 && r.getValue() >= 12)
            || (r.getKey() == 2 && r.getValue() >= 16)
            || (r.getKey() == 10 && r.getValue() >= 16)
        )
        .count() >= 5) {
      recommendations.add(texts.get(34));
    }

    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 5 && r.getValue() <= 8)
            || (r.getKey() == 7 && r.getValue() >= 16)
            || (r.getKey() == 6 && r.getValue() >= 12)
            || (r.getKey() == 4 && r.getValue() <= 10)
            || (r.getKey() == 8 && r.getValue() <= 10)
        )
        .count() >= 5) {
      recommendations.add(texts.get(35));
    }

    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 5 && r.getValue() <= 8)
            || (r.getKey() == 7 && r.getValue() >= 16)
            || (r.getKey() == 6 && r.getValue() >= 12)
            || (r.getKey() == 9 && r.getValue() >= 16)
        )
        .count() >= 4) {
      recommendations.add(texts.get(36));
    }

    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 5 && r.getValue() <= 8)
            || (r.getKey() == 7 && r.getValue() >= 16)
            || (r.getKey() == 1 && r.getValue() >= 15)
            || (r.getKey() == 8 && r.getValue() >= 15)
        )
        .count() >= 4) {
      recommendations.add(texts.get(37));
    }
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 5 || r.getKey() == 7)
        .filter(r -> r.getValue() <= 8)
        .count() >= 2) {
      recommendations.add(texts.get(38));
    }
    // Дистимность 12+ тревожность 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 6 && r.getValue() >= 12)
            || (r.getKey() == 7 && r.getValue() >= 16)
        )
        .count() >= 2) {
      recommendations.add(texts.get(39));
    }
    // Дистимность 12+ тревожность 8+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 6 && r.getValue() >= 12)
            || (r.getKey() == 7 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(texts.get(40));
    }
    // Дистимность 6- тревожность 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 6 && r.getValue() <= 6)
            || (r.getKey() == 7 && r.getValue() >= 16)
        )
        .count() >= 2) {
      recommendations.add(texts.get(41));
    }
    // Дистимность 6- тревожность 8-
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 6 && r.getValue() <= 6)
            || (r.getKey() == 7 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(texts.get(42));
    }
    // Циклотимность и тревожность 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 10 || r.getKey() == 7)
        .filter(r -> r.getValue() >= 16)
        .count() >= 2) {
      recommendations.add(texts.get(43));
    }
    // Циклотимность 16+ тревожность 8-
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 10 && r.getValue() >= 16)
            || (r.getKey() == 7 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(texts.get(44));
    }
    // Циклотимность 8- тревожность 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 7 && r.getValue() >= 16)
            || (r.getKey() == 10 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(texts.get(45));
    }
    // Циклотимность и тревожность 8+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 10 || r.getKey() == 7)
        .filter(r -> r.getValue() <= 8)
        .count() >= 2) {
      recommendations.add(texts.get(46));
    }
    // Эмотивность и тревожность 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 9 || r.getKey() == 7)
        .filter(r -> r.getValue() >= 16)
        .count() >= 2) {
      recommendations.add(texts.get(47));
    }
    // Эмотивность 16+ тревожность 8-
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 9 && r.getValue() >= 16)
            || (r.getKey() == 7 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(texts.get(48));
    }
    // Эмотивность 8- тревожность 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 7 && r.getValue() >= 16)
            || (r.getKey() == 9 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(texts.get(49));
    }
    // Эмотивность и тревожность 8+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 9 || r.getKey() == 7)
        .filter(r -> r.getValue() <= 8)
        .count() >= 2) {
      recommendations.add(texts.get(50));
    }
    // Экзальтированность и тревожность 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 8 || r.getKey() == 7)
        .filter(r -> r.getValue() >= 16)
        .count() >= 2) {
      recommendations.add(texts.get(51));
    }
    // Экзальтированность 16+ тревожность 8-
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 8 && r.getValue() >= 16)
            || (r.getKey() == 7 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(texts.get(52));
    }
    // Экзальтированность 8- тревожность 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 7 && r.getValue() >= 16)
            || (r.getKey() == 8 && r.getValue() <= 8)
            || (r.getKey() == 5 && r.getValue() >= 16)
            || (r.getKey() == 9 && r.getValue() >= 16)
        )
        .count() >= 4) {
      recommendations.add(texts.get(53));
    }
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 7 && r.getValue() >= 16)
            || (r.getKey() == 8 && r.getValue() <= 8)
            || (r.getKey() == 6 && r.getValue() >= 12)
            || (r.getKey() == 5 && r.getValue() <= 8)
            || (r.getKey() == 9 && r.getValue() <= 8)
            || (r.getKey() == 4 && r.getValue() <= 8)
        )
        .count() >= 6) {
      recommendations.add(texts.get(54));
    }
    // Экзальтированность и тревожность 8+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 8 || r.getKey() == 7)
        .filter(r -> r.getValue() <= 8)
        .count() >= 2) {
      recommendations.add(texts.get(55));
    }
    // Застревание и тревожность 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 2 && r.getValue() >= 16)
            || (r.getKey() == 7 && r.getValue() >= 16)
            || (r.getKey() == 9 && r.getValue() >= 16)
            || (r.getKey() == 10 && r.getValue() >= 16)
            || (r.getKey() == 6 && r.getValue() <=10)
        )
        .count() >= 5) {
      recommendations.add(texts.get(56));
    }
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 2 && r.getValue() >= 16)
            || (r.getKey() == 7 && r.getValue() >= 16)
            || (r.getKey() == 9 && r.getValue() <= 8)
            || (r.getKey() == 10 && r.getValue() <= 8)
            || (r.getKey() == 6 && r.getValue() >=10)
        )
        .count() >= 5) {
      recommendations.add(texts.get(57));
    }
    // Застревание 16+ тревожность 8-
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 2 && r.getValue() >= 16)
            || (r.getKey() == 7 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(texts.get(58));
    }
    // Застревание 8- тревожность 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 7 && r.getValue() >= 16)
            || (r.getKey() == 2 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(texts.get(59));
    }
    // Застревание и тревожность 8-
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 2 || r.getKey() == 7)
        .filter(r -> r.getValue() <= 8)
        .count() >= 2) {
      recommendations.add(texts.get(60));
    }
    // Педантичность и тревожность 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 3 && r.getValue() >= 16)
            || (r.getKey() == 7 && r.getValue() >= 16)
            || (r.getKey() == 5 && r.getValue() >= 16)
            || (r.getKey() == 9 && r.getValue() >= 16)
        )
        .count() >= 4)  {
      recommendations.add(texts.get(61));
    }
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 3 && r.getValue() >= 16)
            || (r.getKey() == 7 && r.getValue() >= 16)
            || (r.getKey() == 2 && r.getValue() >= 16)
            || (r.getKey() == 6 && r.getValue() >= 10)
        )
        .count() >= 4) {
      recommendations.add(texts.get(62));
    }
    // Педантичность 8- тревожность 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 7 && r.getValue() >= 16)
            || (r.getKey() == 3 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(texts.get(63));
    }
    // Педантичность и тревожность 8-
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 3 || r.getKey() == 7)
        .filter(r -> r.getValue() <= 8)
        .count() >= 2) {
      recommendations.add(texts.get(64));
    }
    // Демонстративность и тревожность 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 1 || r.getKey() == 7)
        .filter(r -> r.getValue() >= 16)
        .count() >= 2) {
      recommendations.add(texts.get(65));
    }
    // Тревожность 8- демонстративность 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 1 && r.getValue() >= 16)
            || (r.getKey() == 7 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(texts.get(66));
    }
    // Демонстративность 8-тревожность 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 7 && r.getValue() >= 16)
            || (r.getKey() == 1 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(texts.get(67));
    }
    // Демонстративность и тревожность 8-
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 1 || r.getKey() == 7)
        .filter(r -> r.getValue() <= 8)
        .count() >= 2) {
      recommendations.add(texts.get(68));
    }
    // Возбудимость и тревожность 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 4 && r.getValue() >= 16)
            || (r.getKey() == 7 && r.getValue() >= 16)
            || (r.getKey() == 2 && r.getValue() >= 16)
        )
        .count() >= 3) {
      recommendations.add(texts.get(69));
    }
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 4 && r.getValue() >= 16)
            || (r.getKey() == 7 && r.getValue() >= 16)
            || (r.getKey() == 2 && r.getValue() <= 8)
        )
        .count() >= 3) {
      recommendations.add(texts.get(70));
    }
    // Возбудимость 16+ тревожность 8-
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 4 && r.getValue() >= 16)
            || (r.getKey() == 7 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(texts.get(71));
    }
    // Возбудимость 8- тревожность 16+
    if (scalesWithValues.entrySet().stream()
        .filter(r -> (r.getKey() == 7 && r.getValue() >= 16)
            || (r.getKey() == 4 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(texts.get(72));
    }
    // Возбудимость и тревожность 8-
    if (scalesWithValues.entrySet().stream()
        .filter(r -> r.getKey() == 4 || r.getKey() == 7)
        .filter(r -> r.getValue() <= 8)
        .count() >= 2) {
      recommendations.add(texts.get(73));
    }
    return recommendations;
  }
}
