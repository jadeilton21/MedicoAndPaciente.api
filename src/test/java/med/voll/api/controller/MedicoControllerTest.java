package med.voll.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import med.voll.api.domain.medico.MedicoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(classes = MedicoController.class )
@EnableWebMvc
@AutoConfigureMockMvc
class MedicoControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;


    private ObjectMapper objectMapper;

    @MockBean
    private MedicoRepository medicoRepository;

    @Test
    @DisplayName("Deve devolver código 400 http quando informações estão invalidas")
    @WithMockUser
    void cadastrar_cenario1() throws Exception{

        var response = mockMvc
                .perform(post("/medicos"))
                .andReturn().getResponse();


        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());


    }




































}