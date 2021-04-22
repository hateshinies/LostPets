import domain.Extra;
import domain.ExtraType;
import domain.Type;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ExtraTest {

    @Test
    public void shouldAddExtra() {
        LocalDate startDate = LocalDate.of(2021,4,21);
        int daysQuantity = 4;
        String coordinates = "41,61825;56,36931";
        Extra extra = new Extra();
        Type type = new Type();
        type.create(ExtraType.LOST,120,"The owner seeks for pet");

        extra.add(startDate,daysQuantity,coordinates,type);

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(extra).hasFieldOrPropertyWithValue("startDate",startDate);
        soft.assertAll();
    }

    @Test
    public void shouldCalculateReceipt() {
        LocalDate startDate = LocalDate.of(2021,4,21);
        int daysQuantity = 4;
        String coordinates = "41,61825;56,36931";
        Type type = new Type();
        type.create(ExtraType.FOUND,240,"The owner seeks for pet");
        Extra extra = new Extra();

        extra.add(startDate,daysQuantity,coordinates,type);

        Integer amount = extra.calcReceipt();

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(amount).isInstanceOf(Integer.class);
        soft.assertAll();
    }

    @Test
    public void shouldPayReceipt() {
        LocalDate startDate = LocalDate.of(2021,4,21);
        int daysQuantity = 4;
        String coordinates = "41,61825;56,36931";
        Extra extra = new Extra();
        Type type = new Type();
        type.create(ExtraType.LOST,120,"The owner seeks for pet");
        extra.add(startDate,daysQuantity,coordinates,type);

        extra.payReceipt(14531567);

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(extra).hasFieldOrPropertyWithValue("isPublishing",true);
        soft.assertAll();
    }
}
