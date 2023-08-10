package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsultas {




    @Autowired
    private ConsultaRepository repository;


    public void validar(DadosAgendamentoConsulta dados){
        var medicoPossuiUmaOutraConsultaNesseHorario = repository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(),dados.data());
        if(!medicoPossuiUmaOutraConsultaNesseHorario){
            throw new ValidacaoException("Médico já possui uma Consulta agendada nesse Momento.");
        }

    }
}
