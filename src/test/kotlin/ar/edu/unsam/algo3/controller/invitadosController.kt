package ar.edu.unsam.algo3.controller


import ar.edu.unsam.algo3.domain.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName("Dado un controller de invitados")
class invitadosController (@Autowired val mockMvc: MockMvc) {


    @Test
    fun `Se pueden filtrar los invitados por nombre a Pepe Alvide con id = 6`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/ordenado?nombreABuscar=Pepe"))
        .andExpect(MockMvcResultMatchers.status().isOk)
       .andExpect(MockMvcResultMatchers.jsonPath("$.[0].apellido").value("Alvide"))
    }
    @Test
    fun `Si no se reciben filtros, se obtendran todas las figuritas disponibles`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/ordenado?nombreABuscar="))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(6))
    }
    @Test
    fun `Se pueden eliminar un invitado por su id`() {
        mockMvc.perform(MockMvcRequestBuilders.delete("/borrar/1"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
    @Test
    fun `Se puede confirmar un invitado por su id`() {
        mockMvc.perform(MockMvcRequestBuilders.put("/confirmar/2"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
    @Test
    fun `Se puede cancelar un invitado por su id`() {
        mockMvc.perform(MockMvcRequestBuilders.put("/cancelar/1"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }



}