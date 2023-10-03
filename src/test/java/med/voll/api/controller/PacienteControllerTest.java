package med.voll.api.controller;

import med.voll.api.domain.paciente.PacienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class PacienteControllerTest {



    @Autowired
    private MockMvc mockMvc;



    @MockBean
    private PacienteRepository repository;





    @Test
    @DisplayName("Deve devolver código http 400 quando informções estão invalidas")
    @WithMockUser
    void cadastrar_cenario1() throws Exception{

        var response = mockMvc
                .perform(post("/pacientes"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());


    }

    @Test
    @DisplayName("Deve Devolver Código http 400 quando informações estão invalidas")
    @WithMockUser
    void listar_cenario1() throws Exception {

        var response = mockMvc
                .perform(post("/pacientes"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve Devolver Código http 400 quando informações estão invalidas")
    @WithMockUser
    void atualizar_cenario1() throws Exception {

        var response = mockMvc
                .perform(post("/pacientes"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @DisplayName("Deve Devolver Código http 400 quando informações estiverem invalidas")
    @WithMockUser
    void excluir_cenario1() throws Exception {

        var response = mockMvc
                .perform(post("/pacientes"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());


    }
}