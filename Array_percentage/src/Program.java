
/*Существует массив из 1000 элементов, содержащий случайные целые числа от 1 до 1000
Выводится график семи секторов, каждый из которых это проценты суммы одного сектора от общей суммы секторов
* */

import java.util.Random; // Для рандома

import javafx.application.Application; // Для отображения графиков
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Program extends Application {

    public static void main(String[] args) {
        launch(args); // Запуск JavaFX
    }

    @Override
    public void start(Stage primaryStage) {
        int[] array = new int[1000]; // Создание ПЕРВОГО массива с 1000 элементами и его заполнение рандомными числами
        for (int i = 0; i < array.length; i++) {
            array[i] = generateRandomNumber(1, 1000);
        }

        int totalSum = 0; // Общая сумма всех элементов первого массива
        for (int i = 0; i < array.length; i++) {
            totalSum += array[i];
        }

        double[] array_percent = new double[7]; // Создание ВТОРОГО массива, содержащий ПРОЦЕНТЫ суммы каждых 1000/7 элементов первого массива
        for (int i = 0; i < array_percent.length; i++) {
            int sum = 0;
            for (int j = i * (1000 / 7); j < (i + 1) * (1000 / 7); j++) {
                sum += array[j];
            }
            double percent = (double) sum / totalSum * 100.0;
            array_percent[i] = percent;
        }

        CategoryAxis xAxis = new CategoryAxis(); // Создание объектов для осей графика
        NumberAxis yAxis = new NumberAxis();

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis); // Создание объектов графика
        barChart.setTitle("Проценты по секторам");

        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>(); // Создание объектов для данных графика
        dataSeries.setName("Проценты");

        String[] sectorNames = {"Сектор 1", "Сектор 2", "Сектор 3", "Сектор 4", "Сектор 5", "Сектор 6", "Сектор 7"}; // Заполнение данные графика
        for (int i = 0; i < array_percent.length; i++) {
            dataSeries.getData().add(new XYChart.Data<>(sectorNames[i], array_percent[i]));
        }

        barChart.getData().add(dataSeries); // Добавление данных на график

        Scene scene = new Scene(barChart, 800, 600); // Создание сцены и добавление на нее график
        primaryStage.setScene(scene);

        primaryStage.show(); // Отображение окна
    }

    public static int generateRandomNumber(int min, int max) { // Функция генерации рандомного числа
        if (min >= max) {
            throw new IllegalArgumentException("Минимальное значение должно быть меньше максимального");
        }
        Random random = new Random();
        int randomNumber = random.nextInt((max - min) + 1) + min;
        return randomNumber;
    }
}
