package ar.edu.unsam.algo3.Services

import ar.edu.unsam.algo3.domain.Invitado
import ar.edu.unsam.algo3.domain.RepositorioInvitados
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

    @Service
    class InvitadoService(@Autowired val repoInvitados: RepositorioInvitados<Invitado>) {

        fun confirmarInvitado(id: Int) {
            val invitado = repoInvitados.getById(id)
            invitado.confirmado = true
            repoInvitados.update(invitado)
        }
    }






