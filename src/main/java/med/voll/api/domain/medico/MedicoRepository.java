package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
    @Query("""
            select m from Medico m
            where
            m.ativo
            and
            m.especialidade = :especialidade
            and
            m.id not in(
                select c.medico.id from Consulta c
                where
                c.dataTime = :dataTime
            and
                c.motivoCancelamento is null
            )
            order by rand()
            limit 1
""")
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);
    @Query("""
            select m.ativo
            from Medico m
            where
            m.id = :id
            """)
    Boolean findAllById(Long id);
}
