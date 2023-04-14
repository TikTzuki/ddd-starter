package vn.unicloud.fnb.invoice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import vn.unicloud.fnb.invoice.model.Invoice;
import vn.unicloud.fnb.invoice.model.InvoiceRepository;
import vn.unicloud.fnb.order.OrderShipped;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
@ProcessingGroup("amqpEvents")
public class InvoiceEventHandler {
    private final InvoiceRepository invoiceRepository;

    @EventHandler
    public void on(OrderShipped event) throws InterruptedException {
        log.info("--> received event1: {}", event);
        Invoice invoice = new Invoice();
        invoice.setDescription("--> invoice created");
        invoiceRepository.save(invoice);
        log.info("--> invoice saved {}", invoice.getId());
    }

}