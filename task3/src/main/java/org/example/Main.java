package org.example;

public class Main {
    private static String pathValues;
    private static String pathTests;

    private static Map <Long, String> idValuesMap = new HashMap<Long, String>();
    public static void main(String[] args) {

        // ввод пути к файлам
        Scanner in = new Scanner(System.in);
        if (in.hasNext()){
            pathValues = in.nextLine();
        } else {
            System.out.print("Введены некорректные данные!");
            return;
        }

        if (in.hasNext()){
            pathTests = in.nextLine();
        } else {
            System.out.print("Введены некорректные данные!");
            return;
        }

        //считываем первый файл
        try {
            // считывание файла JSON
            FileReader reader = new FileReader(pathValues);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObjectValues = (JSONObject) jsonParser.parse(reader);
            // получение массива
            JSONArray lang= (JSONArray) jsonObjectValues.get("values");
            // берем каждое значение из массива json отдельно
            Iterator i = lang.iterator();
            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();
                //добавляем элементы в HashMap
                long id = (Long) innerObj.get("id");
                String value = (String) innerObj.get("value");
                idValuesMap.put(id, value);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

        //считываем второй файл

        try {
            // считывание файла JSON
            FileReader reader = new FileReader(pathTests);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObjectTests = (JSONObject) jsonParser.parse(reader);
            JSONArray jsonArrayReport = (JSONArray)jsonObjectTests.get("tests");
            jsonArrayReport.toJSONString();


            for(Map.Entry<Long, String> item : idValuesMap.entrySet()){

                System.out.printf("Key: %d  Value: %s \n", item.getKey(), item.getValue());
                String key = (String)item.getKey().toString();
                String value = item.getValue();
                String s = idValuesMap.get("id");
                Object dataObject = JsonPath.parse(jsonArrayReport.toJSONString()).read("$[?(@.id == " + key + ")]");
                JSONObject j = (JSONObject) dataObject;
                j.put("value", value);
            }

            //   создание, запись файла
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter("report.json");
                //inherited method from java.io.OutputStreamWriter
                fileWriter.write(jsonArrayReport.toJSONString());
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    if (fileWriter != null) {
                        fileWriter.flush();
                        fileWriter.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }
}
