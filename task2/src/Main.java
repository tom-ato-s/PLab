import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class  Main {
    private static String  pathFile1;
    private static String  pathFile2;
    private static int numberLine;
    private static String strCentr;

    private static float xCentr;
    private static float yCentr;
    private static float radius;
    private static List<String> arrPoint = new ArrayList<String>();
    private static float[] arrCentr = new float[2];
    private static List<Integer> arrAnsver = new ArrayList();

    private static float[] strToFloat(String line) {
        float[] arrFloat = new float[2];
        String[] parts;
        int arr_index = 0;

        try {
            parts = line.split(" ");
        } catch (PatternSyntaxException e) {
            //wrong string format
            return null;
        } catch (NullPointerException e) {
            //null pointer string
            return null;
        }
        if (parts.length < 2)
            return null;//wrong string format
        for (int i = 0; i < parts.length; i++) {
            try {
                arrFloat[arr_index] = Float.parseFloat(parts[i]);
            } catch (NullPointerException e) {
                //null pointer string
                continue;
            } catch (NumberFormatException e) {
                //wrong number format
                return null;
            }
            arr_index++;
            if (arr_index == 2)
                return arrFloat;
        }
        //wrong string format
        return null;
    }


    public static void main(String[] args) {

        // ввод пути к файлам
        Scanner in = new Scanner(System.in);
        if (in.hasNext()){
            pathFile1 = in.nextLine();
        } else {
            System.out.print("Введены некорректные данные!");
            return;
        }

        if (in.hasNext()){
            pathFile2 = in.nextLine();
        } else {
            System.out.print("Введены некорректные данные!");
            return;
        }

        //считываем первый файл
        try {
            File file1 = new File(pathFile1);
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file1);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            numberLine = 1;
            while (line != null) {
                if(numberLine == 1) {
                    strCentr = line;
                    arrCentr = strToFloat(strCentr); // приобразуем String с координатами в float
                }
                if (numberLine == 2) {
                    radius = Float.parseFloat(line); //приобразуем строку с радиусом в float
                }
                // считываем остальные строки в цикле
                line = reader.readLine();
                numberLine++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //считываем второй файл
        try {
            File file2 = new File(pathFile2);
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file2);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            numberLine = 1;
            while (line != null) {
                if(numberLine != 100) {
                    arrPoint.add(line);
                    // считываем остальные строки в цикле
                    line = reader.readLine();
                    numberLine++;
                } else break;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //сравнение точек
        // radius;
        xCentr = arrCentr[0];
        yCentr = arrCentr[1];

        for(int i = 0; i<arrPoint.size(); i++) {
            float[] pointFloat = strToFloat(arrPoint.get(i));
            float xPoint = pointFloat[0];
            float yPoint = pointFloat[1];
            //растояние от цента до точки
            float rPoint = (float) sqrt(pow((xCentr-xPoint), 2) + pow((yCentr-yPoint), 2));

            if((xPoint > xCentr-radius)&(xPoint< xCentr+radius)
                    &(yPoint > yCentr-radius)&(yPoint < yCentr+radius)
            ) {
                arrAnsver.add(0);
            }else if(rPoint == radius) {
                arrAnsver.add(1);
            } else  arrAnsver.add(2);
        }

        // вывод в консоль результатов
        for (int i = 0; i< arrAnsver.size(); i++) {
            System.out.println(arrAnsver.get(i));
        }
    }
}