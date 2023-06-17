package com.itm.space.taskservice.api.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class TaskRequest {

    private final UUID topicId;

    private final String text;

    private final String title;
    private final List<UUID> attachments;
}


