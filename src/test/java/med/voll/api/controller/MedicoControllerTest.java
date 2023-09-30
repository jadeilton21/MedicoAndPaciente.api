package med.voll.api.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.*;
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
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<DadosCadastroMedico> dadosCadastroMedicoJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoMedico> dadosDetalhamentoMedicoJson;


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
    //@Test
    //@DisplayName("Deve devolver código 400 http quando informações estão invalidas")
    //@WithMockUser
    // void cadastro_cenario1() throws Exception{
    //   var response = mockMvc
    //         .perform(post("/Medicos"))
    //         .andReturn().getResponse();
    //      assertThat(response.getStatus())
    //          .isEqualTo(HttpStatus.BAD_REQUEST.value());

    //@Test
    //DisplayName("Deve devolver Código 400 http quando informações estão invalidas")
    //@WithMockUser
    // void cadastro_cenario1() throws Exception{
    //      var response = mockMvc
    //      .perform(post("/Medicos"))
    //      .andReturn().getResponse();
    //    assertThat(response.getStatus())
    //          .isEqualTo(HttpStatus.BAD_REQUEST.value());

    @Test
    @DisplayName("Deve devolver código http 200 quando informações estão validas")
    @WithMockUser
    void cadastrar_cenario2() throws Exception{
        var dadosCadastro = new DadosCadastroMedico(
                "ProficionalDaMaconha",
                "MaconheiroMaster@voll.med",
                "82977334066",
                "123333",
                Especialidade.ORTOPEDIA,
                dadosEndereco());

        when(medicoRepository.save(any())).thenReturn(new Medico(dadosCadastro));

        var response = mockMvc
                .perform(post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroMedicoJson.write(dadosCadastro).getJson()))
                .andReturn().getResponse();

        var dadosDetalhamento = new DadosDetalhamentoMedico(
                null,
                dadosCadastro.nome(),
                dadosCadastro.email(),
                dadosCadastro.crm(),
                dadosCadastro.telefone(),
                dadosCadastro.especialidade(),
                new Endereco(dadosCadastro.endereco())
        );
        var jsonEsperado = dadosDetalhamentoMedicoJson.write(dadosDetalhamento).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua 2",
                "bairro",
                "12345678",
                "Piranhas",
                "AL",
                "casa",
                "3"
        );
    }


    @Test
    @DisplayName("Deve Devolver Código http 400 quando informações estão invalidas")
    @WithMockUser
    void listar_cenario1() throws Exception {

        var response = mockMvc
                .perform(post("/medicos"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @DisplayName("Deve devolver código http 400 quando informações estão invalidas")
    @WithMockUser
    void atualizar_cenario1() throws Exception {

        var response = mockMvc
                .perform(post("/medicos"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());

    }
}