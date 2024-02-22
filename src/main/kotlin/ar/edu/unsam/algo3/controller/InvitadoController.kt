package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.DTO.InvitadosDTO
import ar.edu.unsam.algo3.domain.Invitado
import ar.edu.unsam.algo3.domain.RepositorioInvitados
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("*")
class InvitadoController(@Autowired val repoInvitados : RepositorioInvitados<Invitado>) {


    @GetMapping("/ordenado")
    @Operation(summary = "devuelve un invitado  ordenado por nombre")
    fun ordenarInvitados(
        @RequestParam("nombreABuscar") nombreABuscar : String
        ): List<InvitadosDTO> {
        return repoInvitados.mostrarInvitadosPorNombre(nombreABuscar)
    }

    @GetMapping("/confirmado-ordenado")
    @Operation(summary = "devuelve un invitado confirmado  ordenado por nombre")
    fun ordenarInvitadosConfirmados(
        @RequestParam("nombreABuscar") nombreABuscar : String
    ): List<InvitadosDTO> {
        return repoInvitados.mostrarInvitadosConfirmadosPorNombre(nombreABuscar)
    }

    @GetMapping("/invitados")
    fun obtenerInvitados(): List<InvitadosDTO> {
        return repoInvitados.convertiraDTO()
    }

    @GetMapping("/monto")
    fun obtenerMontoTotalConfirmados(): Double {
        val invitadosConfirmados = repoInvitados.obtenerInvitadosConfirmados()
        return repoInvitados.calcularMontoTotal(invitadosConfirmados)
    }


    @DeleteMapping("/{idInvitado}")
    @Operation(summary = "Elimina un Invitado por su id")
    fun deleteInvitado(
        @PathVariable idInvitado: Int) {
        val invitado = repoInvitados.getById(idInvitado)
        repoInvitados.delete(invitado)
    }

}