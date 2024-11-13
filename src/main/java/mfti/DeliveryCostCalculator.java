package mfti;

public class DeliveryCostCalculator {

    // Метод для расчета стоимости доставки
    public static double calculateDeliveryCost(double distance, String size, boolean isFragile, String workload) {
        double baseCost = 0;

        // Расчет стоимости по расстоянию
        if (distance > 30) {
            if (isFragile) {
                return -1; // Доставка невозможна
            }
            baseCost += 300;
        } else if (distance > 10) {
            baseCost += 200;
        } else if (distance > 2) {
            baseCost += 100;
        } else if (distance > 0) {
            baseCost += 50;
        } else {
            return -1; // Неверное расстояние
        }

        // Расчет стоимости по габаритам
        if ("большие".equalsIgnoreCase(size)) {
            baseCost += 200;
        } else if ("маленькие".equalsIgnoreCase(size)) {
            baseCost += 100;
        } else {
            return -1; // Неверные габариты
        }

        // Учет хрупкости
        if (isFragile) {
            baseCost += 300;
        }

        // Коэффициент загруженности
        double workloadFactor = 1.0;
        switch (workload.toLowerCase()) {
            case "очень высокая":
                workloadFactor = 1.6;
                break;
            case "высокая":
                workloadFactor = 1.4;
                break;
            case "повышенная":
                workloadFactor = 1.2;
                break;
            default:
                workloadFactor = 1.0; // Во всех остальных случаях
                break;
        }

        // Умножение на коэффициент загруженности
        double finalCost = baseCost * workloadFactor;

        // Проверка минимальной суммы доставки
        finalCost = Math.max(finalCost, 400);

        return finalCost;
    }

    // Пример использования функции
    public static void main(String[] args) {
        double distance = 30; // пример расстояния
        String size = "большие"; // пример габаритов
        boolean isFragile = true; // пример хрупкости
        String workload = "повышенная"; // пример загруженности

        double cost = calculateDeliveryCost(distance, size, isFragile, workload);

        if (cost == -1) {
            System.out.println("Доставка невозможна или введены неверные данные.");
        } else {
            System.out.println("Стоимость доставки: " + cost + " рублей.");
        }
    }
}