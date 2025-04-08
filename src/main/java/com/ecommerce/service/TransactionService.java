package com.ecommerce.service;

import com.ecommerce.modal.Order;
import com.ecommerce.modal.Seller;
import com.ecommerce.modal.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction createTransaction(Order order);
    List<Transaction> getTransactionsBySeller(Seller seller);

    List<Transaction> getAllTransactions();

}
