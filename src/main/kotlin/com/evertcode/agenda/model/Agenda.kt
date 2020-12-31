package com.evertcode.agenda.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Agenda(
    @Id
    @GeneratedValue
    var id: Long,

    @Column(name = "first_name")
    var firstname: String,

    @Column(name = "last_name")
    var lastname: String,

    @Column(name = "phone_number")
    var phoneNumber: String,

    @Column(name = "cellphone_number")
    var cellphoneNumber: String
)