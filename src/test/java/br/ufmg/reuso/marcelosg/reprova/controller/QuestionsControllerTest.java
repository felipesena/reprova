package br.ufmg.reuso.marcelosg.reprova.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataMongo
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
class QuestionsControllerTest {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MockMvc mockMvc;
    //Questions tests
    @Test
    void testSingleQuestionCreation() throws Exception {
        var json = "{\"description\":\"Description\",\"statement\":\"Statement\",\"discipline\":\"Discipline\",\"themes\":[\"theme1\", \"theme2\"],\"tags\":[\"tag1\", \"tag2\"],\"pvt\":true,\"estimateTimeInMinutes\":3,\"difficulty\":\"EASY\",\"filePath\":\"File/Path\"}";
        var result = this.mockMvc.perform(post("/questions")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json)).andExpect(status().is2xxSuccessful()).andReturn();
        assertEquals(201, result.getResponse().getStatus());
    }
    
    @Test
    void testSingleQuestionCreationEmptyForm() throws Exception {
        var result = this.mockMvc.perform(post("/questions")
        .contentType(MediaType.APPLICATION_JSON)
        .content("")).andReturn();
        assertEquals(400, result.getResponse().getStatus());
    }
    
    @Test
    void testGetAllQuestions() throws Exception {
        var json = "{\"description\":\"Description\",\"statement\":\"Statement\",\"discipline\":\"Discipline\",\"themes\":[\"theme1\", \"theme2\"],\"tags\":[\"tag1\", \"tag2\"],\"pvt\":true,\"estimateTimeInMinutes\":3,\"difficulty\":\"EASY\",\"filePath\":\"File/Path\"}";
        this.mockMvc.perform(post("/questions")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json)).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(post("/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(post("/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(status().is2xxSuccessful());

        var result = this.mockMvc.perform(get("/questions")).andExpect(status().is2xxSuccessful()).andReturn();
        JSONArray jsonArray = new JSONArray(result.getResponse().getContentAsString());

        assertEquals(3, jsonArray.length());
    }
    
    @Test
    void testFindQuestionById() throws Exception {
        var json = "{\"description\":\"Description\",\"statement\":\"Statement\",\"discipline\":\"Discipline\",\"themes\":[\"theme1\", \"theme2\"],\"tags\":[\"tag1\", \"tag2\"],\"pvt\":true,\"estimateTimeInMinutes\":3,\"difficulty\":\"EASY\",\"filePath\":\"File/Path\"}";
        var response = this.mockMvc.perform(post("/questions")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json)).andExpect(status().is2xxSuccessful()).andReturn();
        
        
        JSONObject jsonObject = new JSONObject(response.getResponse().getContentAsString());
        var result = this.mockMvc.perform(get("/questions/" + jsonObject.getString("id"))).andExpect(status().is2xxSuccessful()).andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }
    
    @Test
    void testFindQuestionWrongId() throws Exception {
        var json = "{\"description\":\"Description\",\"statement\":\"Statement\",\"discipline\":\"Discipline\",\"themes\":[\"theme1\", \"theme2\"],\"tags\":[\"tag1\", \"tag2\"],\"pvt\":true,\"estimateTimeInMinutes\":3,\"difficulty\":\"EASY\",\"filePath\":\"File/Path\"}";
        this.mockMvc.perform(post("/questions")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json)).andExpect(status().is2xxSuccessful());
        
        
        var result = this.mockMvc.perform(get("/questions/-1")).andReturn();
        assertEquals(404, result.getResponse().getStatus());
    }
    
    @Test
    void testAddGradesToWrongQuestion() throws Exception {
        var json = "{\"description\":\"Description\",\"statement\":\"Statement\",\"discipline\":\"Discipline\",\"themes\":[\"theme1\", \"theme2\"],\"tags\":[\"tag1\", \"tag2\"],\"pvt\":true,\"estimateTimeInMinutes\":3,\"difficulty\":\"EASY\",\"filePath\":\"File/Path\"}";
        var grades = "{\"year\": 2020,\"semester\": 2,\"discipline\": \"Software Reuse\",\"grades\": [{\"student\": \"1\", \"grade\": 7},{\"student\": \"2\", \"grade\": 5.8},{\"student\": \"3\", \"grade\": 2},{\"student\": \"4\", \"grade\": 7},{\"student\": \"5\", \"grade\": 4.5},{\"student\": \"6\", \"grade\": 2},{\"student\": \"7\", \"grade\": 8},{\"student\": \"8\", \"grade\": 9.5}]}";
        this.mockMvc.perform(post("/questions")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json)).andExpect(status().is2xxSuccessful());
        
        var result = this.mockMvc.perform(put("/questions/-1/grades")
                .contentType(MediaType.APPLICATION_JSON)
                .content(grades)).andReturn();
        assertEquals(404, result.getResponse().getStatus());
    }
    
    //Exams tests
    @Test
    void testCreateRandomExam() throws Exception {
        var json = "{\"strategyType\": \"RANDOM\",\"totalQuestions\": 2,\"title\": \"Sample exam created with Random strategy\",\"year\": 2021,\"semester\": 2,\"saveExam\": true }";
        var result = this.mockMvc.perform(post("/exams/generator")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andReturn();
        assertEquals(201, result.getResponse().getStatus());
    }
    
    @Test
    void testCreateRandomExamEmptyForm() throws Exception {
        var result = this.mockMvc.perform(post("/exams/generator")
                .contentType(MediaType.APPLICATION_JSON)
                .content("")).andReturn();
        assertEquals(400, result.getResponse().getStatus());
    }
    
    @Test
    void testDeleteExamById() throws Exception {
        var json = "{\"strategyType\": \"RANDOM\",\"totalQuestions\": 2,\"title\": \"Sample exam created with Random strategy\",\"year\": 2021,\"semester\": 2,\"saveExam\": true }";
        var response = this.mockMvc.perform(post("/exams/generator")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(status().is2xxSuccessful()).andReturn();
        
        JSONObject jsonObject = new JSONObject(response.getResponse().getContentAsString());
        var result = this.mockMvc.perform(delete("/exams/" + jsonObject.getString("id"))).andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }
    
    @Test
    void testDeleteExamWrongId() throws Exception {
        var json = "{\"strategyType\": \"RANDOM\",\"totalQuestions\": 2,\"title\": \"Sample exam created with Random strategy\",\"year\": 2021,\"semester\": 2,\"saveExam\": true }";
        this.mockMvc.perform(post("/exams/generator")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(status().is2xxSuccessful());
        
        var result = this.mockMvc.perform(delete("/exams/-1")).andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }
    
    @Test
    void testFindExamById() throws Exception {
        var json = "{\"strategyType\": \"RANDOM\",\"totalQuestions\": 2,\"title\": \"Sample exam created with Random strategy\",\"year\": 2021,\"semester\": 2,\"saveExam\": true }";
        var response = this.mockMvc.perform(post("/exams/generator")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(status().is2xxSuccessful()).andReturn();
        
        JSONObject jsonObject = new JSONObject(response.getResponse().getContentAsString());
        var result = this.mockMvc.perform(get("/exams/" + jsonObject.getString("id"))).andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }
    
    @Test
    void testFindExamWrongId() throws Exception {
        var json = "{\"strategyType\": \"RANDOM\",\"totalQuestions\": 2,\"title\": \"Sample exam created with Random strategy\",\"year\": 2021,\"semester\": 2,\"saveExam\": true }";
        this.mockMvc.perform(post("/exams/generator")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(status().is2xxSuccessful());
        
        var result = this.mockMvc.perform(get("/exams/-1")).andReturn();
        assertEquals(404, result.getResponse().getStatus());
    }
    
    @Test
    void testGetAllExams() throws Exception {
        var json = "{\"strategyType\": \"RANDOM\",\"totalQuestions\": 2,\"title\": \"Sample exam created with Random strategy\",\"year\": 2021,\"semester\": 2,\"saveExam\": true }";
        this.mockMvc.perform(post("/exams/generator")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(status().is2xxSuccessful());
        var result = this.mockMvc.perform(get("/exams")).andExpect(status().is2xxSuccessful()).andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }
    
    @BeforeEach
    void setUp() {
        mongoTemplate.getDb().drop();
    }

}