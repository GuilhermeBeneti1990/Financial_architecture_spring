package com.beneti.transactionsvc.config;

import com.beneti.transactionsvc.validator.impl.EmpyTransactionValidationBean;
import com.beneti.transactionsvc.validator.impl.TransactionValidation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.system.JavaVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionBeanConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @Conditional(TransactionEnableNewerThanJavaSeventeen.class)
    public TransactionValidation emptyTransactionNewerThanJavaSeventeenValidation() {
        return new EmpyTransactionValidationBean();
    }

    @ConditionalOnJava(range = ConditionalOnJava.Range.OLDER_THAN, value = JavaVersion.SEVENTEEN)
    @Bean
    @ConditionalOnMissingBean
    @Conditional(TransactionEnableNewerThanJavaSeventeen.class)
    public TransactionValidation emptyTransactionJavaOlderThanSeventeenValidation() {
        return new EmpyTransactionValidationBean();
    }

}
