package vn.unicloud.fnb.domain.order.invoice

import jakarta.persistence.*

@Entity
data class InvoiceEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long
)