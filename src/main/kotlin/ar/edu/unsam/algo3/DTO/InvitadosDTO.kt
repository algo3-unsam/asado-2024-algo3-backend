package ar.edu.unsam.algo3.DTO

import ar.edu.unsam.algo3.domain.Invitado

class InvitadosDTO (
    val id : Int,
    val nombre : String,
    val apellido : String,
    val comeEnsalada : Boolean,
    var gramosDeCarne : Double,
    var ingredientesDeEnsalada: MutableList<String>,
    var confirmado: Boolean,
    var calcularMonto: Double

) {


}
fun Invitado.toDTO() = InvitadosDTO(
    id = this.id,
    nombre = this.nombre,
    apellido = this.apellido,
    comeEnsalada = this.comeEnsalada,
    gramosDeCarne = this.gramosDeCarne,
    ingredientesDeEnsalada = this.ingredientesDeEnsalada,
    confirmado = this.confirmado,
    calcularMonto = this.calcularMontoAConsumir()
)


