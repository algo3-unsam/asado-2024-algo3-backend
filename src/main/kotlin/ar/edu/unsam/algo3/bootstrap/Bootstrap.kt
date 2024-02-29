package ar.edu.unsam.algo3.bootstrap

import ar.edu.unsam.algo3.domain.Invitado
import ar.edu.unsam.algo3.domain.RepositorioInvitados
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Service

@Service
class Bootstrap(
  val repoInvitado : RepositorioInvitados<Invitado>
): InitializingBean {



private fun crearInvitados() = repoInvitado.apply {
    create(Invitado("Juan", "Perez", true, 300.0, mutableListOf("tomate", "cebolla"), true))
    create(Invitado("María", "Gomez", false, 200.0, mutableListOf(), false))
    create(Invitado("Carlos", "Rodriguez", true, 250.0, mutableListOf("cebolla"), true))
    create(Invitado("Jose", "Ramos", true, 100.0, mutableListOf("tomate", "lechuga"), false))
    create(Invitado("Marta", "Rondon", false, 150.0, mutableListOf(), false))
    create(Invitado("Pepe", "Alvide", true, 400.0, mutableListOf("lechuga"), false))

}
    override fun afterPropertiesSet() {
        crearInvitados()
    }
}