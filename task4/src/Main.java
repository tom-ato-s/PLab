import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static String pathFile;
    private static List<Integer> list = new ArrayList(); // мвссив введенных даных
    private static int[] nums; //массив значений
    private static int sumArr; // суммма элементов массива

    private static int midleАrr; // среднее значение, до которого будем менять массив
    private static int lenght; // количество элементов в массиве
    private static int steps; // количество шагов
    private static int max;
    private static int min;

    public static void main(String[] args) {

        // ввод пути к файлу
        Scanner in = new Scanner(System.in);
        if (in.hasNext()){
            pathFile = in.nextLine();
        } else {
            System.out.print("Введены некорректные данные!");
            return;
        }

        //считываем значение из файла в ArrayList
        try {
            File file1 = new File(pathFile);
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file1);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            while (line != null) {
                list.add(Integer.parseInt(line));
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //приобразовуем данные в массив
        nums = new int[list.size()];
        sumArr = 0;
        lenght = list.size();
        steps = 0;
        for(int i = 0; i<lenght; i++){
            nums[i] = list.get(i);
            // находим max и min массива
            if(i == 0) {
                max = nums[i];
                min = nums[i];
            } else {
                if (nums[i] < min)
                    min = nums[i];
                if (nums[i] > max)
                    max = nums[i];
            }
            System.out.println( nums[i]);
            sumArr = sumArr + nums[i];
        }

        midleАrr = Math.round(sumArr/nums.length);

        int deltaPolovina = 0;
        int upSteps = 0; // количество изменений на уменьшение
        int dounSteps = 0; // количество изменений на увеличение
        int step = 0;
        int stepTemp = 0;
        for(int i = 0; i<lenght; i++) {

            // находим max и min массива до i-го элемента
            if(i == 0) {
                max = nums[i];
                min = nums[i];
            } else {
                if (nums[i] < min)
                    min = nums[i];
                if (nums[i] > max)
                    max = nums[i];

                deltaPolovina = Math.round(max - min)/2;


                int n = i;
                int prohod = 0;
                while (true){
                    // считаем количество шагов до deltaPolovina
                    for (int j = 0; j <= n; j++) {
                        prohod++;
                        if (max != min) {
                            step = nums[j] - deltaPolovina;
                            if (step > 0) {
                                upSteps = upSteps + step;
                            } else if (step < 0) {
                                dounSteps = dounSteps - step;
                            }
                        } else continue;
                    }
                    // сравниваем количество шагов на снижение и на увеличение
                    steps = upSteps + dounSteps;
                    if (prohod == 2) {
                        if (upSteps - dounSteps > 0) {
                            deltaPolovina++;
                        } else if (upSteps - dounSteps < 0) {
                            deltaPolovina--;
                        }
                        stepTemp = steps;
                    } else if(prohod > 2){
                        if (stepTemp - steps > 0) {
                            stepTemp = steps;
                        } else {
                            steps = stepTemp;
                            break;
                        }
                    }

                }
            }
        }
        for(int k = 0; k<lenght; k++) {
            System.out.println( " " + nums[k]);
        }
        System.out.println(deltaPolovina);

        System.out.println(steps);

    }
}