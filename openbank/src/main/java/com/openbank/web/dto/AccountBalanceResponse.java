package com.openbank.web.dto;

public record AccountBalanceResponse(
        String apiTranId,
        String apiTranDtm,
        String rspCode,
        String rspMessage,
        String bankTranId,
        String bankTranDate,
        String bankCodeTran,
        String bankRspCode,
        String bankRspMessage,
        String bankName,
        String savingsBankName,
        String fintechUseNum,
        long balanceAmt,
        long availableAmt,
        String accountType,
        String productName,
        String accountIssueDate,
        String maturityDate,
        String lastTranDate
) {
}
