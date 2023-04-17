package vn.unicloud.fnb.domain.order.invoice;

import org.axonframework.common.AxonConfigurationException;
import org.axonframework.modelling.command.GenericJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
