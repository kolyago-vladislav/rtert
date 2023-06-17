package com.itm.space.taskservice.controller;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.itm.space.taskservice.BaseIntegrationTest;
import com.itm.space.taskservice.api.request.TaskRequest;
import com.itm.space.taskservice.util.TaskControllerConst;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DBRider
@DBUnit(caseSensitiveTableNames = true, schema = "public")
public class TaskControllerImplTest extends BaseIntegrationTest {

    private final UUID testCreateTopicId = UUID.randomUUID();
    private final List<UUID> testCreateAttachments = List.of(UUID.randomUUID(), UUID.randomUUID());

    private final UUID testUpdateTopicId = UUID.randomUUID();
    private final List<UUID> testUpdateAttachments = List.of(UUID.randomUUID(), UUID.randomUUID());


    @Test
    @DataSet(value = {
            "datasets/tasks.yml",
            "datasets/tasksToTopics.yml"
    },
            cleanAfter = true, cleanBefore = true)
    @WithMockUser(authorities = {"ROLE_MODERATOR"})
    public void shouldSuccessfullyCreateTaskTest() throws Exception {
        // Создание тестового запроса
        TaskRequest request = TaskRequest.builder()
                .topicId(testCreateTopicId)
                .text("test")
                .title("testtitle")
                .attachments(testCreateAttachments)
                .build();

        RequestBuilder postRequest = requestWithContent(post(TaskControllerConst.TASK_PATH), request)
                .with(csrf().asHeader());

        mvc.perform(postRequest)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.topicId").value(testCreateTopicId.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value("test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.attachments", hasSize(testCreateAttachments.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.attachments[*]").value(containsInAnyOrder(testCreateAttachments.stream().map(UUID::toString).toArray())));


    }


    @Test
    @DataSet(value = {"datasets/tasks.yml",
            "datasets/tasksToTopics.yml"},
            cleanAfter = true, cleanBefore = true)
    @WithMockUser(authorities = {"ROLE_MODERATOR"})
    public void shouldUpdateTaskTest() throws Exception {
        TaskRequest request = TaskRequest.builder()
                .topicId(testUpdateTopicId)
                .text("test")
                .title("testtitle")
                .attachments(testUpdateAttachments)
                .build();

        RequestBuilder putRequest = requestWithContent(post(TaskControllerConst.TASK_PATH), request)
                .with(csrf().asHeader());

        mvc.perform(putRequest)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.topicId").value(testUpdateTopicId.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value("test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.attachments[*]").value(containsInAnyOrder(testUpdateAttachments.stream().map(UUID::toString).toArray())));
    }
}




