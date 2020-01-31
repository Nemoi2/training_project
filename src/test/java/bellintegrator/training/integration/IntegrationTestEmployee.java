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
public class IntegrationTestEmployee {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getEmployeeSuccessTest() throws Exception {

        this.mockMvc.perform(get("/api/user/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"data\":{\"id\":1,\"firstName\":\"Kozlov\"," +
                        "\"secondName\":null,\"middleName\":null,\"position\":\"worker\",\"phone\":null," +
                        "\"docName\":\"Passport\",\"docNumber\":\"1122334455\",\"docDate\":\"2019-09-11\"," +
                        "\"citizenshipName\":\"Russian Federation\",\"citizenshipCode\":643,\"isIdentified\":true}}")));
    }

    @Test
    public void getEmployeeFailTest() throws Exception {

        this.mockMvc.perform(get("/api/user/5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString
                        ("{\"error\":\"Ошибка Сервера: Not found user with id is 5\"}")));
    }

    @Test
    public void addEmployeeSuccessTest() throws Exception {

        this.mockMvc.perform(post("/api/user/save").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"officeId\":\"2\",\"firstName\":\"firstName\",\"secondName\":\"secondName\"," +
                        "\"middleName\":\"middleName\",\"position\":\"admin\",\"phone\":\"1\",\"docCode\":\"21\"," +
                        "\"docName\":\"passport\",\"docNumber\":\"123\",\"docDate\":\"2019-09-11\"," +
                        "\"citizenshipCode\":\"643\",\"isIdentified\":\"false\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"data\":{\"result\":\"success\"}}")));
    }

    @Test
    public void addEmployeeFailTest() throws Exception {

        this.mockMvc.perform(post("/api/user/save").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"firstName\":\"firstName\",\"secondName\":\"secondName\"," +
                        "\"middleName\":\"middleName\",\"position\":\"admin\",\"phone\":\"1\",\"docCode\":\"21\"," +
                        "\"docName\":\"passport\",\"docNumber\":\"123\",\"docDate\":\"2019-09-11\"," +
                        "\"citizenshipCode\":\"643\",\"isIdentified\":\"false\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"error\":\"Ошибка Сервера:" +
                        " addEmployee.employeesView.officeId: must not be null\"}")));
    }


    @Test
    public void updateEmployeeSuccessTest() throws Exception {

        this.mockMvc.perform(post("/api/user/update").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"id\":\"3\",\"officeId\":\"2\",\"firstName\":\"firstName\"," +
                        "\"secondName\":\"secondName\",\"middleName\":\"middleName\",\"position\":\"admin\"," +
                        "\"phone\":\"1\",\"docCode\":\"21\",\"docName\":\"passport\",\"docNumber\":\"123\"," +
                        "\"docDate\":\"2019-09-11\",\"citizenshipCode\":\"643\",\"isIdentified\":\"false\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"data\":{\"result\":\"success\"}}")));
    }

    @Test
    public void updateEmployeeFailTest() throws Exception {

        this.mockMvc.perform(post("/api/user/update").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"id\":\"5\",\"officeId\":\"1\",\"firstName\":\"firstName\"," +
                        "\"secondName\":\"secondName\",\"middleName\":\"middleName\",\"position\":\"admin\"," +
                        "\"phone\":\"1\",\"docCode\":\"21\",\"docName\":\"passport\",\"docNumber\":\"123\"," +
                        "\"docDate\":\"2019-09-11\",\"citizenshipCode\":\"643\",\"isIdentified\":\"false\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"error\":\"Ошибка Сервера:" +
                        " Not found user with id is 5\"}")));
    }

    @Test
    public void employeesSuccessTest() throws Exception {

        this.mockMvc.perform(post("/api/user/list").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"officeId\":\"1\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"data\":[{\"id\":1,\"firstName\":\"Kozlov\"," +
                        "\"secondName\":null,\"middleName\":null,\"position\":\"worker\"},{\"id\":3," +
                        "\"firstName\":\"Tarkov\",\"secondName\":\"Andrey\",\"middleName\":\"Sergeevich\"," +
                        "\"position\":\"administrator\"}]}")));
    }

    @Test
    public void employeesFailTest() throws Exception {

        this.mockMvc.perform(post("/api/user/list").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"firstName\":\"name\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"error\":\"Ошибка Сервера:" +
                        " employees.employeesView.officeId: must not be null\"}")));
    }
}