package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class HolderNameTest {
    private PaymentController paymentController;
    @BeforeEach
    void setUp() {
        paymentController = new PaymentController();
    }
    @ParameterizedTest
    @CsvSource({
            "nam 10, false",
            "Lam, false",
            "Le thanh nam, true",
            "kscq2_group7_2021, false",
            "<script>alert(1)</script>, false",
            "OR 1=1, false",
            "null,false"
    })
    void test(String cardNumber, boolean expected) {
        //when
        boolean isValid = paymentController.validateHolderName(cardNumber);
        //then
        assertEquals(expected, isValid);
    }
}