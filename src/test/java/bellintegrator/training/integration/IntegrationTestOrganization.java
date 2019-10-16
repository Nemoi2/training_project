package bellintegrator.training.integration;

import bellintegrator.training.controller.OrganizationController;
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
public class IntegrationTestOrganization {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrganizationController organizationController;

    @Test
    public void getOrganizationSuccessTest() throws Exception {

        this.mockMvc.perform(get("/api/organization/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"data\":{\"id\":1,\"name\":\"Gazprom\"," +
                        "\"fullName\":\"OOO Gazprom\",\"inn\":\"123456789\",\"kpp\":\"123454321\"," +
                        "\"address\":\"st. Lenina 1\",\"phone\":null,\"isActive\":true}}")));
    }

    @Test
    public void getOrganizationFailTest() throws Exception {

        this.mockMvc.perform(get("/api/organization/4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString
                        ("error\":\"Ошибка Сервера: Not found organization with id is 4")));
    }

    @Test
    public void addOrganizationSuccessTest() throws Exception {

        this.mockMvc.perform(post("/api/organization/save").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"name\":\"name\",\"fullName\":\"fullName\",\"inn\":\"inn\",\"kpp\":\"kpp\"," +
                        "\"address\":\"address\",\"phone\":\"1\",\"isActive\":\"false\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"data\":{\"result\":\"success\"}}")));
    }

    @Test
    public void addOrganizationFailTest() throws Exception {

        this.mockMvc.perform(post("/api/organization/save").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"fullName\":\"fullName\",\"inn\":\"inn\",\"kpp\":\"kpp\"," +
                        "\"address\":\"address\",\"phone\":\"1\",\"isActive\":\"false\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"error\":\"Ошибка Сервера:" +
                        " addOrganization.organizationView.name: name cannot be null\"}")));
    }


    @Test
    public void updateOrganizationSuccessTest() throws Exception {

        this.mockMvc.perform(post("/api/organization/update").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"id\":\"3\",\"name\":\"name\",\"fullName\":\"fullName\",\"inn\":\"inn\",\"kpp\":\"kpp\"," +
                        "\"address\":\"address\",\"phone\":\"1\",\"isActive\":\"false\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"data\":{\"result\":\"success\"}}")));
    }

    @Test
    public void updateOrganizationFailTest() throws Exception {

        this.mockMvc.perform(post("/api/organization/update").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"id\":\"5\",\"name\":\"name\",\"fullName\":\"fullName\",\"inn\":\"inn\",\"kpp\":\"kpp\"," +
                        "\"address\":\"address\",\"phone\":\"1\",\"isActive\":\"false\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"error\":\"Ошибка Сервера:" +
                        " Not found organization with id is 5\"}")));
    }

    @Test
    public void organizationsSuccessTest() throws Exception {

        this.mockMvc.perform(post("/api/organization/list").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"name\":\"Gazprom\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"data\":[{\"id\":1,\"name\":\"Gazprom\"," +
                        "\"isActive\":true}")));
    }

    @Test
    public void organizationsFailTest() throws Exception {

        this.mockMvc.perform(post("/api/organization/list").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"inn\":\"inn\",\"isActive\":\"false\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"error\":\"Ошибка Сервера:" +
                        " organizations.organizationsView.name: name cannot be null\"}")));
    }
}