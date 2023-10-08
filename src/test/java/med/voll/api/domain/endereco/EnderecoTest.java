package med.voll.api.domain.endereco;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class EnderecoTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deve Devolver Cógido http 400 quando informações estiverem Invalidas..")
    @WithMockUser
    void atualizarInformacoes() {
    }
}