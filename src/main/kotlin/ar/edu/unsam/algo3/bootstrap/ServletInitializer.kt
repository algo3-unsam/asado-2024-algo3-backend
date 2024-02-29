package ar.edu.unsam.algo3.bootstrap

import ar.edu.unsam.algo3.AsadoApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

class ServletInitializer: SpringBootServletInitializer() {
    override fun configure(aplication : SpringApplicationBuilder): SpringApplicationBuilder{
        return aplication.sources(AsadoApplication:: class.java)
    }
}