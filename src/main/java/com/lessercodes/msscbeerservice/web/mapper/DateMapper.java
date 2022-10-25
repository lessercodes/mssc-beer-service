package com.lessercodes.msscbeerservice.web.mapper;

import lombok.val;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class DateMapper {

    OffsetDateTime timestampToOffsetDateTime(Timestamp timestamp) {
        if (timestamp != null) {
            val ldt =  timestamp.toLocalDateTime();
            return OffsetDateTime.of(
                    ldt.getYear(),
                    ldt.getMonthValue(),
                    ldt.getDayOfMonth(),
                    ldt.getHour(),
                    ldt.getMinute(),
                    ldt.getSecond(),
                    ldt.getNano(),
                    ZoneOffset.UTC
            );
        }
        return null;
    }

    Timestamp offsetDateTimeToTimestamp(OffsetDateTime offsetDateTime) {
        if (offsetDateTime != null) {
            return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
        }
        return null;
    }

}
