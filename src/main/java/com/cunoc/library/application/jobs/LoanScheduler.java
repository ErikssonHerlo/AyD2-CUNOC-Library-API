package com.cunoc.library.application.jobs;

import com.cunoc.library.application.usecases.LoanUseCase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LoanScheduler {

    private final LoanUseCase loanUseCase;

    public LoanScheduler(LoanUseCase loanUseCase) {
        this.loanUseCase = loanUseCase;
    }

    @Scheduled(cron = "0 */10 * * * ?") // Ejecuta cada 10 minutos
    public void checkLoansInDefault() {
        loanUseCase.checkLoansInDefault();
        System.out.println("Checked loans in default.");
    }
}
