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
        var json = "{\n" + "    \"description\":\"Description\",\n" + "    \"statement\":\"Statement\",\n" + "    \"discipline\":\"Discipline\",\n" + "    \"stats\":{\n" + "        \"average\":1.4,\n" + "        \"median\":1.5,\n" + "        \"mean\":1.4,\n" + "        \"standardDeviation\":0.3,\n" + "        \"min\":1,\n" + "        \"max\":2\n" + "    },\n" + "    \"themes\":[\"theme1\",\"theme2\"],\n" + "    \"tags\":[\"tag1\",\"tag2\"],\n" + "    \"pvt\":true,\n" + "    \"estimateTimeInMinutes\":3,\n" + "    \"difficulty\":\"EASY\",\n" + "    \"filePath\":\"File/Path\",\n" + "    \"semesterGrades\":[\n" + "        {\"year\":2000,\n" + "        \"semester\":1,\n" + "        \"discipline\":\"discipline\",\n" + "        \"stats\":{\n" + "            \"average\":1.4,\n" + "            \"median\":1.5,\n" + "            \"mean\":1.4,\n" + "            \"standardDeviation\":0.3,\n" + "            \"min\":1,\n" + "            \"max\":2\n" + "        },\n" + "        \"grades\":[{\"student\":\"Student1\",\"grade\":1.4}]\n" + "        }]\n" + "}";
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