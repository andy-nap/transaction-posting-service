package com.posting.service.domain.port.out;

import com.posting.service.domain.model.entity.Transaction;

public interface TransactionPort {
    void save(Transaction transaction);
}
