package cy.psychotech.tgbot.service;

import cy.psychotech.db.tables.pojos.AccentuationProgress;
import cy.psychotech.db.tables.pojos.Client;
import cy.psychotech.tgbot.model.AccentuationScale;
import cy.psychotech.tgbot.model.Response;
import cy.psychotech.tgbot.repository.AccentuationQuestionsRepository;
import cy.psychotech.tgbot.repository.ClientRepository;
import cy.psychotech.tgbot.repository.ProgressRepository;
import cy.psychotech.tgbot.repository.StateMessagesRepository;
import cy.psychotech.tgbot.util.KeyboardUtils;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResponseService implements StateHandler {
  private static final String MESSAGE_NOT_PROCESSED = "Не могу распознать ваш ответ. Пожалуйста, воспользуйтесь кнопками!";
  private final ClientRepository clientRepository;
  private final StateMessagesRepository stateMessagesRepository;
  private final AccentuationService accentuationService;
  private final ProgressRepository progressRepository;
  private final AccentuationQuestionsRepository questionsRepository;

  /***
   * This is main handler
   *
   * @param id - client id
   * @param text - message text
   * @param state - current state
   * @return - response to client
   */

  @Override
  @Transactional
  public Response handle(String id, String text, int state) {
    Response.ResponseBuilder builder = Response.builder();

    switch (state) {
      // Запрос имени
      case 0 -> {
        builder.text(stateMessagesRepository.getMessage(0));
        clientRepository.toNextState(id);
      }
      // Возврат ИДшника
      case 1 -> {
        final Client client = clientRepository.setName(id, text);
        final String textWithName =
            String.format(stateMessagesRepository.getMessage(1), client.getName(), client.getId());
        builder.text(textWithName);
        builder.keyboard(KeyboardUtils.getKeyboard(List.of(
            "Да",
            "У меня эта диагностика позади, я хочу восстановить результаты"))
        );
        clientRepository.toNextState(id);
      }
      // Начало диагностики/Восстановление результатов
      case 2 -> {
        if (text.equals("Да")) {
          // Начинаем диагностику, первый вопрос
          builder.text(stateMessagesRepository.getMessage(4));
          builder.keyboard(KeyboardUtils.getYesNoKeyboard());
          clientRepository.toNextState(id, 3);
        } else {
          // Идём на восстановление результатов
          builder.text(stateMessagesRepository.getMessage(2));
          clientRepository.toNextState(id);
        }
      }
      case 3 -> {
        //Пытаемся восстановить результаты
        final Optional<AccentuationProgress> progress = progressRepository.getProgress(id);

        if (progress.isPresent() && progress.get().getIsDone().equals(true)) {
          builder.text(accentuationService.getFullResult(id));
          clientRepository.toNextState(id, 89); //Перекидываем на этап завершённой диагностики
        } else {
          // Начинаем диагностику, первый вопрос
          String textToSend =
              stateMessagesRepository.getMessage(3) + "\\n"
              + questionsRepository.getQuestion(1); // Вопросы начинаются с 1
          builder.text(textToSend);
          builder.keyboard(KeyboardUtils.getYesNoKeyboard());
          clientRepository.toNextState(id, 2);
        }
      }
      // 4 стейта нет!
      // Принимаем первый ответ, задаём второй вопрос
      case 5 -> {
        builder.text(processDiagnostic(text, id, 2, AccentuationScale.GIPERTIMNOST, 3, true));
        builder.keyboard(KeyboardUtils.getYesNoKeyboard());
      }
      // Принимаем 2 ответ, задаём вопрос 3
      case 6 -> {
        builder.text(processDiagnostic(text, id, 3, AccentuationScale.ZASTREVANIE, 2, true));
        builder.keyboard(KeyboardUtils.getYesNoKeyboard());
      }
      // Принимаем 3 ответ, задаём вопрос 4
      case 7 -> {
        builder.text(processDiagnostic(text, id, 4, AccentuationScale.EMOTIVNOST, 3, true));
        builder.keyboard(KeyboardUtils.getYesNoKeyboard());
      }
      // Принимаем 4 ответ, задаём вопрос 5
      case 8 -> {
        builder.text(processDiagnostic(text, id, 5, AccentuationScale.TREVOZJNOST, 3, false));
        builder.keyboard(KeyboardUtils.getYesNoKeyboard());
      }
      // Принимаем 5 ответ, задаём вопрос 6
      case 9 -> {
        builder.text(processDiagnostic(text, id, 6, AccentuationScale.TSIKLOTIMNOST, 3, true));
        builder.keyboard(KeyboardUtils.getYesNoKeyboard());
      }
      // Принимаем 6 ответ, задаём вопрос 7
      case 10 -> {
        builder.text(processDiagnostic(text, id, 7, AccentuationScale.DEMONSTRATIVNOST, 2, true));
        builder.keyboard(KeyboardUtils.getYesNoKeyboard());
      }
    }

    return builder.build();
  }

  public String processDiagnostic(String text,
                                  String clientId,
                                  int question,
                                  AccentuationScale scale,
                                  int change,
                                  boolean ifYes) {
    String textToSend = questionsRepository.getQuestion(question);
    if (ifYes) {
      if (text.contains("да")) {
        progressRepository.upScale(clientId, scale, change);
        clientRepository.toNextState(clientId);
        return textToSend;
      } else if (text.contains("нет")) {
        clientRepository.toNextState(clientId);
        return textToSend;
      } else {
        return MESSAGE_NOT_PROCESSED;
      }
    } else {
      if (text.contains("да")) {
        clientRepository.toNextState(clientId);
        return textToSend;
      } else if (text.contains("нет")) {
        progressRepository.upScale(clientId, scale, change);
        clientRepository.toNextState(clientId);
        return textToSend;
      } else {
        return MESSAGE_NOT_PROCESSED;
      }
    }
  }
}
