package cy.psychotech.tgbot.service;

import cy.psychotech.db.tables.pojos.AccentuationProgress;
import cy.psychotech.tgbot.repository.ProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgressService {
  private final ProgressRepository progressRepository;

  public AccentuationProgress createProgress(String id) {
    return progressRepository.createProgress(id);
  }
}
