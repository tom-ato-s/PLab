import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static int n; // максимальное значение кугового массива
    private static int m; // длинна обхода, массива интервала
    private static int first; // первое значение в интервале
    private static int lest; // последнее значение в интервале
    private static int namberI; // текущее значение в интервале
    private static int[] arrInterval;
    private static List<Integer> listWay = new ArrayList();
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in); // ввод даных с консоли
        if (in.hasNextInt()){
            n = in.nextInt();
        } else {
            System.out.print("Введены некорректные данные!");
            return;
        }
        if (in.hasNextInt()){
            m = in.nextInt();
        } else {
            System.out.print("Введены некорректные данные!");
            return;
        }
        arrInterval = new int[m]; // создаем массив длинной m для интервала
        namberI = 1;

        do{ // проход по круговому массиву
            for (int i = 0; i < m; i++) { // очищение массива интервала
                arrInterval[i] = 0;
            }

            for(int i = 0; i<m; i++) { // заполнение массива интервала
                arrInterval[i] = namberI;
                if (namberI==n) {
                    namberI=1;
                } else namberI++;
            }

            first = arrInterval[0];
            lest = arrInterval[m-1];
            namberI = lest;
            listWay.add(first); // добавление числа в ArrayList пути
        } while (lest!=1);

        for (int i = 0; i<listWay.size(); i++) { // вывод пути в консоль
            System.out.print(listWay.get(i));
        }
    }
}