package ea.ea.controllers;

import ea.ea.models.Transaction;
import ea.ea.services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller for creating conversions.
 */

@RestController
@RequestMapping("/api")
public class ConversionController {
    private final TransactionsService transactionsService;

    @Autowired
    public ConversionController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionsService.createTransaction(transaction);
    }
}
