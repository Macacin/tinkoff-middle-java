package third_task.first_exercise.service;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class DateService {

    private final ObjectProvider<SimpleDateFormat> todayFmt;
    private final ObjectProvider<SimpleDateFormat> isoFmt;
    private final MessageSource msgs;
    private final Locale locale;
    private final String promptKey;
    private final String unknownKey;

    public DateService(
            @Qualifier("todayFormatter") ObjectProvider<SimpleDateFormat> todayFmt,
            @Qualifier("isoFormatter") ObjectProvider<SimpleDateFormat> isoFmt,
            MessageSource msgs,
            Locale locale,
            @Value("${message.prompt.key}") String promptKey,
            @Value("${message.unknown.key}") String unknownKey
    ) {
        this.todayFmt = todayFmt;
        this.isoFmt = isoFmt;
        this.msgs = msgs;
        this.locale = locale;
        this.promptKey = promptKey;
        this.unknownKey = unknownKey;
    }

    public String prompt() {
        return msgs.getMessage(promptKey, null, locale);
    }

    public String unknown() {
        return msgs.getMessage(unknownKey, null, locale);
    }

    public String formatToday() {
        return todayFmt.getObject().format(new Date());
    }

    public String formatIso() {
        return isoFmt.getObject().format(new Date());
    }
}