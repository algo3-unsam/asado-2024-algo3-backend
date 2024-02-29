package ar.edu.unsam.algo3.Services

import ar.edu.unsam.algo3.DTO.InvitadosDTO
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

        fun cancelarConfirmacion(id: Int) {
            val invitado = repoInvitados.getById(id)
            invitado.confirmado = false
            repoInvitados.update(invitado)
        }


        fun actualizarInvitado(id: Int, invitadoActualizado: InvitadosDTO): Invitado {
            val invitadoExistente = repoInvitados.getById(id)
            invitadoExistente.apply {
                nombre = invitadoActualizado.nombre
                apellido = invitadoActualizado.apellido
                comeEnsalada = invitadoActualizado.comeEnsalada
                gramosDeCarne = invitadoActualizado.gramosDeCarne
                ingredientesDeEnsalada= invitadoActualizado.ingredientesDeEnsalada
                confirmado = invitadoActualizado.confirmado
            }
            repoInvitados.update(invitadoExistente)

            return invitadoExistente
        }
    }






