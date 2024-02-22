package ar.edu.unsam.algo3.domain

import ar.edu.unsam.algo3.DTO.InvitadosDTO
import ar.edu.unsam.algo3.DTO.toDTO
import org.springframework.stereotype.Component

@Component
class Repositorio<T : Entidad> {
    val elementos: MutableList<T> = mutableListOf<T>()
    var nextId = 1

    fun create(elemento: T) {
        validarElementoNuevo(elemento)
        asignarId(elemento)
        elementos.add(elemento)
    }

    private fun validarElementoNuevo(elemento: T) {
        if (!elemento.esNueva()) {
            throw ElementoYaExisteException(
                "No se puede crear el elemento con ID ${elemento.id}" +
                        ". Este ID ya existe en el repositorio"
            )
        }
    }

    private fun asignarId(elemento: T) {
        elemento.id = nextId++
    }

    fun getCantidad(): Int {
        return elementos.size
    }

    fun delete(elemento: T) {
        val elementoExistente = getById(elemento.id)
        elementos.remove(elementoExistente)
    }

    fun update(elemento: T) {
        val elementoViejo = getById(elemento.id)
        reemplazarElemento(elementoViejo, elemento)
    }

    private fun reemplazarElemento(elementoViejo: T, nuevo: T) {
        val index = elementos.indexOf(elementoViejo)
        if (index != -1) {
            elementos[index] = nuevo
        }
    }

    fun getById(id: Int): T {
        return elementos.find { elemento -> elemento.id == id }
            ?: throw ElementoInexistenteException("No se encontró el elemento con ID $id")
    }

}
@Component
    class RepositorioInvitados<T: Invitado> : Repositorio<T>() {
    lateinit var InvitadosFiltrados : List<InvitadosDTO>

        fun search(value: String): List<InvitadosDTO> {
        return InvitadosFiltrados.filter { it.nombre.contains(value, true) }
    }
    fun convertiraDTO(): List<InvitadosDTO> = elementos.map { it.toDTO() }

    fun mostrarInvitadosPorNombre(nombreABuscar : String) : List<InvitadosDTO>{
        InvitadosFiltrados = convertiraDTO()
        if (nombreABuscar.isNotEmpty()) {
            InvitadosFiltrados = search(nombreABuscar)
        }
        return InvitadosFiltrados
    }


    fun obtenerInvitadosConfirmados(): List<InvitadosDTO> {
        InvitadosFiltrados = convertiraDTO()
        if (!this::InvitadosFiltrados.isInitialized || InvitadosFiltrados.isEmpty()) {
            return emptyList()
        }
        return InvitadosFiltrados.filter { it.confirmado }
    }

    fun mostrarInvitadosConfirmadosPorNombre(nombreABuscar: String): List<InvitadosDTO> {
        val invitadosConfirmados = obtenerInvitadosConfirmados()
        val invitadosFiltradosPorNombre = if (nombreABuscar.isNotEmpty()) {
            invitadosConfirmados.filter { it.nombre.contains(nombreABuscar, ignoreCase = true) }
        } else {
            invitadosConfirmados
        }
        return invitadosFiltradosPorNombre
    }
    fun calcularMontoTotal(invitados: List<InvitadosDTO>): Double {
        return invitados.sumOf { it.calcularMonto() }
    }

}







