package com.hyperlogy_ban_hang_2.util;

import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class ConvertDate {
    public String convertTimestampToDate(Timestamp timestamp) {

        // Định nghĩa định dạng ngày đầu vào và đầu ra
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Phân tích chuỗi đầu vào thành LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse((CharSequence) timestamp, inputFormatter);

        // Định dạng LocalDateTime thành định dạng đầu ra mong muốn
        String outputDate = dateTime.format(outputFormatter);
        System.out.println("out put ne: " + outputDate);
        return outputDate;
    }
}
