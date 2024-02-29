package ar.edu.unsam.algo3.domain

   class Invitado(
    var nombre:String,
    var apellido:String,
    var comeEnsalada:Boolean,
    var gramosDeCarne:Double,
    var ingredientesDeEnsalada: MutableList<String>,
    var confirmado: Boolean

   ):Entidad{
       override var id: Int = 0
       override fun condicionDeBusqueda(value:String):Boolean =
           this.nombre.contains(value, true)

       fun calcularMontoAConsumir(): Double {
           var montoTotal = gramosDeCarne * 100
           if (comeEnsalada) {
               montoTotal += ingredientesDeEnsalada.size * 200
           }
           return montoTotal
       }
       fun limpiarIngredientesDeEnsalada() {
           if (!comeEnsalada) {
               ingredientesDeEnsalada.clear()
           }
           }


   }

