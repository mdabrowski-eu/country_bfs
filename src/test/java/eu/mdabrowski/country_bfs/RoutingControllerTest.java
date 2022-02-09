package eu.mdabrowski.country_bfs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class RoutingControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldFindRoute() throws Exception {
        // when
        ResultActions resultActions = mockMvc.perform(get("/routing/CZE/ITA")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.route[0]", is("CZE")))
                .andExpect(jsonPath("$.route[2]", is("ITA")));
    }

    @Test
    void shouldReturnBadRequestIfDestinationIsUnreachable() throws Exception {
        // when
        ResultActions resultActions = mockMvc.perform(get("/routing/CZE/CAN")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isBadRequest());
    }
}
