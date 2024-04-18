package com.vietqr.org.scheduled;

import java.util.List;

public interface IAutomationService {
    void syncInformationCustomer();
    void syncTrDate(long from, long to);
    void syncTrMonth(long from, long to, List<String> bankIds);
}
