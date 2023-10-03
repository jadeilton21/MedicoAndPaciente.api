package med.voll.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import med.voll.api.domain.consulta.AgendaDeConsultas;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.medico.Especialidade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest(classes = ConsultaController.class)
@EnableWebMvc
@TestPropertySource(properties = {"server.port=8080"})
class ConsultaControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;



    private MockMvc mockMvc;

    private ObjectMapper objectMapper;



    @MockBean
    private AgendaDeConsultas agendaDeConsultas;

    @BeforeEach
    public void beforeAll() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper().registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());


    }



    @Test
    @DisplayName("Deve Devolver Código http 400 quando informacoes estao invalidas")
    @WithMockUser
    void agendar_cenario1() throws Exception {
        var response = mockMvc.perform(post("/consultas")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());


    }
    // Treinando testes:




    //@Test
    //DisplayName("Deve Devolver Código http 400 quando Informações estão invalidas")
    //@WithMockUser
    // void agendar_cenario1() throws Exception{
    //   var response = mockMvc.perform(post("/consultas")).andReturn().getResponse();

    // assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());


    @Test
    @DisplayName("Deve Devolver Código http 200 quando as informações estiverem validas.")
    @WithMockUser
    void agendar_cenario2() throws Exception {


        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;


        var dadosDetalhamento = new DadosDetalhamentoConsulta(4l,2l, 5l, data);


        when(agendaDeConsultas.agendar(any())).thenReturn(dadosDetalhamento);

        var response = mockMvc
                .perform(
                        post("/consultas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        new DadosAgendamentoConsulta(2l, 5l, data, especialidade)
                                ))

                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = objectMapper.writeValueAsString(
                dadosDetalhamento
        );

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);



    }

    @Test
    @DisplayName("Deve devolver código http 400 quando informações estão invalidas")
    @WithMockUser
    void cancelar_cenario1() throws Exception{

        var response = mockMvc
                .perform(post("/consultas"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());

    }
}