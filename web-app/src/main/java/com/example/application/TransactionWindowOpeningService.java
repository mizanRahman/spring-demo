package com.example.application;

import com.example.domain.core.Token;
import org.springframework.stereotype.Service;

/**
 * Created by mac on 12/3/16.
 */
public interface TransactionWindowOpeningService {
    TemporaryTransactionCredential openTransactionWindow(TransactionWindowOpenParams params);
}
