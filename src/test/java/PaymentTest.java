import domain.Payment;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class PaymentTest {

    @Test
    public void shouldCreateNewPayment() {
        int amount = 1250;
        Payment payment = new Payment();

        payment.create(amount);

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(payment).hasFieldOrPropertyWithValue("amount", amount);
        soft.assertAll();
    }

    @Test
    public void shouldPay() {
        long transactionId = 456987696441L;
        Payment payment = new Payment();

        payment.pay(transactionId);

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(payment).hasFieldOrProperty("executedAt").isNotNull();
        soft.assertAll();
    }
}
