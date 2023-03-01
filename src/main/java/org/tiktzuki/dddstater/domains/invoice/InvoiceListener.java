package org.tiktzuki.dddstater.domains.invoice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.tiktzuki.dddstater.domains.invoice.model.Invoice;
import org.tiktzuki.dddstater.domains.invoice.model.InvoiceRepository;
import org.tiktzuki.dddstater.domains.order.model.OrderShipped;

import javax.transaction.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class InvoiceListener {
    private final InvoiceRepository invoiceRepository;

    @EventListener
    @Transactional
    public void onOrderShipped(OrderShipped event) {
        log.info("received: {}", event);
        Invoice invoice = new Invoice();
        invoice.setDescription("Simple invoice");
        invoiceRepository.save(invoice);
        log.info("after save");
    }
}
