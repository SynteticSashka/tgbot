package cy.psychotech.tgbot.util;

import cy.psychotech.db.tables.pojos.AccentuationProgress;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ServiceUtils {
  public Map<Long, Integer> progressToMap(AccentuationProgress progress) {
    return Map.of(
        1L, progress.getDemonstrScore(),
        2L, progress.getZastrScore(),
        3L, progress.getPedantScore(),
        4L, progress.getVozbScore(),
        5L, progress.getGipertimScore(),
        6L, progress.getDistimScore(),
        7L, progress.getTrevozjScore(),
        8L, progress.getExaltScore(),
        9L, progress.getEmotivScore(),
        10L, progress.getTsiklotimScore()
    );
  }
}
