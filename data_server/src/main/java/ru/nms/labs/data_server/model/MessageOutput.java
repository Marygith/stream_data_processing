package ru.nms.labs.data_server.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageOutput {
    private String from;
    private String text;
    private String time;


}
