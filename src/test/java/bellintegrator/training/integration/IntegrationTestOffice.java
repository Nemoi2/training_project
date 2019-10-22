package bellintegrator.training.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTestOffice {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getOfficeSuccessTest() throws Exception {

        this.mockMvc.perform(get("/api/office/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"data\":{\"id\":1,\"name\":\"Gaz_Penza\"," +
                        "\"address\":\"st. Moscow 1\",\"phone\":null,\"isActive\":true}}")));
    }

    @Test
    public void getOfficeFailTest() throws Exception {

        this.mockMvc.perform(get("/api/office/4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString
                        ("error\":\"Ошибка Сервера: Not found office with id is 4")));
    }

    @Test
    public void addOfficeSuccessTest() throws Exception {

        this.mockMvc.perform(post("/api/office/save").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"orgId\":\"1\",\"name\":\"name\",\"address\":\"address\",\"phone\":\"1\"," +
                        "\"isActive\":\"false\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"data\":{\"result\":\"success\"}}")));
    }

    @Test
    public void addOfficeFailTest() throws Exception {

        this.mockMvc.perform(post("/api/office/save").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"name\":\"name\",\"address\":\"address\",\"phone\":\"1\",\"isActive\":\"false\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"error\":\"Ошибка Сервера:" +
                        " addOffice.officesView.orgId: must not be null\"}")));
    }


    @Test
    public void updateOfficeSuccessTest() throws Exception {

        this.mockMvc.perform(post("/api/office/update").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"id\":\"3\",\"name\":\"name\",\"address\":\"address\",\"phone\":\"1\"," +
                        "\"isActive\":\"false\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"data\":{\"result\":\"success\"}}")));
    }

    @Test
    public void updateOfficeFailTest() throws Exception {

        this.mockMvc.perform(post("/api/office/update").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"id\":\"5\",\"name\":\"name\",\"address\":\"address\",\"phone\":\"1\"," +
                        "\"isActive\":\"false\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"error\":\"Ошибка Сервера:" +
                        " Not found office with id is 5\"}")));
    }

    @Test
    public void officesSuccessTest() throws Exception {

        this.mockMvc.perform(post("/api/office/list").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"orgId\":\"1\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"data\":[{\"id\":1,\"name\":\"Gaz_Penza\"," +
                        "\"isActive\":true},{\"id\":2,\"name\":\"Gaz_Saratov\",\"isActive\":true}]")));
    }

    @Test
    public void officesFailTest() throws Exception {

        this.mockMvc.perform(post("/api/office/list").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"name\":\"name\",\"isActive\":\"false\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"error\":\"Ошибка Сервера:" +
                        " offices.officesView.orgId: must not be null\"}")));
    }
}