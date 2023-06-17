package com.itm.space.taskservice.api.response;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class TaskResponse {
    private UUID id;
    private UUID topicId;
    private String text;
    private String title;
    private List<UUID> attachments;
}
