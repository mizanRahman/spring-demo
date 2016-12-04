package com.konasl.epg.application;

/**
 * Created by mac on 12/3/16.
 */
public interface TransactionWindowOpeningService {
    TemporaryTransactionCredential openTransactionWindow(TransactionWindowOpenParams params);
}
