package cy.psychotech.tgbot.service;

import cy.psychotech.tgbot.model.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {
  private static final String ERROR_MESSAGE = "Упс! Что-то сломалось. Пожалуйста, сообщите об ошибке моему создателю!";
  private static final String CANT_READ_MESSAGE = "Не могу распознать ответ. Пожалуйста, проверь и напиши ещё раз!";

  @Transactional
  public Response handleMessage(Message message) {
    return null; //TODO
  }
}
