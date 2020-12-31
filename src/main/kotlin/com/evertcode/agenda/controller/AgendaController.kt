package com.evertcode.agenda.controller

import com.evertcode.agenda.model.Agenda
import com.evertcode.agenda.repository.AgendaRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/agendas")
class AgendaController(val agendaRepository: AgendaRepository) {


    @PostMapping
    fun save(@RequestBody(required = true) agenda: Agenda) =
        ResponseEntity.ok().body(this.agendaRepository.save(agenda))

    @PatchMapping("/{id}")
    fun update(
        @PathVariable(name = "id") id: Long,
        @RequestBody(required = true) agenda: Agenda
    ) = this.validateUpdate(id, agenda)


    @GetMapping
    fun getAll() = ResponseEntity.ok().body(this.agendaRepository.findAll().toList())

    @GetMapping("/{id}")
    fun getById(@PathVariable(name = "id") id: Long) =
        ResponseEntity.ok().body(this.agendaRepository.findById(id).get())

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable(name = "id") id: Long) = this.validateDelete(id)

    private fun validateDelete(id: Long) =
        if (this.getOptionalAgendaById(id).isPresent) {
            this.agendaRepository.deleteById(id)
            ResponseEntity.noContent()
        } else ResponseEntity.badRequest()

    private fun validateUpdate(id: Long, agenda: Agenda): ResponseEntity<Agenda> {
        val tmpAgenda = this.getOptionalAgendaById(id)

        if (tmpAgenda.isPresent) {
            val tAgenda = tmpAgenda.get()

            tAgenda.firstname = agenda.firstname
            tAgenda.lastname = agenda.lastname
            tAgenda.phoneNumber = agenda.phoneNumber
            tAgenda.cellphoneNumber = agenda.cellphoneNumber

            return ResponseEntity.ok().body(this.agendaRepository.save(tAgenda))
        }

        return ResponseEntity.badRequest().body(agenda)
    }

    private fun getOptionalAgendaById(id: Long) = this.agendaRepository.findById(id)
}