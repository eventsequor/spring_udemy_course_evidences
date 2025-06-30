package com.eventsequor.relations_jpa.repositories;

import com.eventsequor.relations_jpa.entities.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface IInvoiceRepository extends CrudRepository<Invoice, Long> {
}
