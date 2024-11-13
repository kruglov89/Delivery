import mfti.DeliveryCostCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    @DisplayName("Дистанция равна 0")
    @Tag("smoke")
    public void testDistanceIs0() {
        double cost = DeliveryCostCalculator.calculateDeliveryCost(0, "маленькие", false, "обычная");
        assertEquals(-1, cost, "Доставка невозможна для расстояния 0 км");
    }

    @Test
    @DisplayName("Хрупкий груз на расстояние более 30 км")
    @Tag("smoke")
    public void testDistanceMore30AndIsFragile() {
        double cost = DeliveryCostCalculator.calculateDeliveryCost(30.01, "маленькие", true, "обычная");
        assertEquals(-1, cost, "Доставка невозможна для расстояния более 30 км при наличии хрупкой упаковки");
    }

    @Test
    @DisplayName("Минимальная стоимость доставки")
    @Tag("smoke")
    public void testMinimumCost() {
        double cost = DeliveryCostCalculator.calculateDeliveryCost(5, "маленькие", false, "обычная");
        assertEquals(400, cost, "Стоимость доставки не может быть меньше 400");
    }

    @Test
    @DisplayName("Проверка габаритов")
    @Tag("smoke")
    public void testErrorSize() {
        double cost = DeliveryCostCalculator.calculateDeliveryCost(5, "средние", false, "обычная");
        assertEquals(-1, cost, "Габариты должны быть или \"большие\" или \"маленькие\"");
    }
}