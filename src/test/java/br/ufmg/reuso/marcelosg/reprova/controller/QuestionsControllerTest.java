package br.ufmg.reuso.marcelosg.reprova.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuestionsControllerTest {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MockMvc mockMvc;
    @Test
    void testSingleQuestionCreation() throws Exception {
        var json = "{\"description\":\"Description\",\"statement\":\"Statement\",\"discipline\":\"Discipline\",\"themes\":[\"theme1\", \"theme2\"],\"tags\":[\"tag1\", \"tag2\"],\"pvt\":true,\"estimateTimeInMinutes\":3,\"difficulty\":\"EASY\",\"filePath\":\"File/Path\"}";
        this.mockMvc.perform(post("/questions")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json)).andExpect(status().is2xxSuccessful());

        this.mockMvc.perform(get("/questions")).andExpect(MockMvcResultMatchers.content().json("["+json+"]"));
    }
    @BeforeEach
    void setUp() {
        mongoTemplate.getDb().drop();
    }

}