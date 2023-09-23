package med.voll.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MedicoController.class )
@EnableWebMvc
@TestPropertySource(properties = {"server.port=8080"})
class MedicoControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;


    private MockMvc mockMvc;


    private ObjectMapper objectMapper;

    @MockBean
    private MedicoRepository medicoRepository;

}