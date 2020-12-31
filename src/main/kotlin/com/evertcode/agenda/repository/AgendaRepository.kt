package com.evertcode.agenda.repository

import com.evertcode.agenda.model.Agenda
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AgendaRepository: CrudRepository<Agenda, Long> {
}