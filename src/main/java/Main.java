import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = "new_data.json";
        String json = readString(fileName);
        List<Employee> employeeList = jsonToList(json);
        employeeList.forEach(System.out::println);
    }

    private static String readString(String filename) {
        StringBuilder output = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String str;
            while ((str = br.readLine()) != null) {
                output.append(str);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return output.toString();
    }

    private static List<Employee> jsonToList(String json) {
        List<Employee> employeeList = new ArrayList<>();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            Object obj = new JSONParser().parse(json);
            JSONArray jsonArray = (JSONArray) obj;
            for (Object o : jsonArray) {
                employeeList.add(gson.fromJson(o.toString(), Employee.class));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
}
