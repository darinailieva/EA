package ea.ea.controllers;

import ea.ea.models.Transaction;
import ea.ea.services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Rest controller for retrieving list of conversions and filtering.
 */

@RestController
@RequestMapping("/api")
public class ConversionListController {
    private final TransactionsService transactionsService;

    @Autowired
    public ConversionListController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @GetMapping
    public List<Transaction> getTransactions() {
        return transactionsService.getAll();
    }

    @GetMapping("/page")
    public Page<Transaction> getTransactions(@RequestParam int page,
                                         @RequestParam int size){
        return transactionsService.getAll(PageRequest.of(page,size));
    }

    @GetMapping("/{id}")
    public Transaction getByID(@PathVariable int id) {
        return transactionsService.getById(id);
    }

    @GetMapping("/filter")
    public List<Transaction> getTransactions(@RequestParam String currency) {
        return transactionsService.filterByCurrency(currency);
    }

    @GetMapping("/sort")
    public List<Transaction> sortByDate() {
        return transactionsService.sortByDateAndSourceCurrency();
    }

    @GetMapping("/date")
    public List<Transaction> findAllByDate(@RequestParam("date")
                                               @DateTimeFormat(pattern = "yyyy-MM-dd")Date date,
                                         @RequestParam int page,
                                         @RequestParam int size){
        return transactionsService.filterByDate(date, PageRequest.of(page,size));
    }

}
