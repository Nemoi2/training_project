package bellintegrator.training.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTestHandbook {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void docsSuccessTest() throws Exception {

        this.mockMvc.perform(get("/api/docs"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"data\":[{\"name\":\"military ID\"," +
                        "\"code\":7},{\"name\":\"Passport of a foreign citizen\",\"code\":10}," +
                        "{\"name\":\"Passport of a citizen RF\",\"code\":21}]}")));
    }

    @Test
    public void countriesFailTest() throws Exception {

        this.mockMvc.perform(get("/api/countries"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString
                        ("{\"data\":[{\"name\":\"Belarus\",\"code\":375},{\"name\":\"Ukraine\"," +
                                "\"code\":380},{\"name\":\"Russian Federation\",\"code\":643}]}")));
    }
}