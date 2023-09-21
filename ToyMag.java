import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ToyMag {
    private ArrayList<Toy> toys;
    
    /**
     * 
     */
    public ToyMag() {
        toys = new ArrayList<>();
    }
// Метод добавления игрушек в магазин

public void add(Toy toy) {
    boolean foundToy = false;
    for (Toy t : toys) {
        if (t.getId() == toy.getId()) {
            t.setQuantity(t.getQuantity() + toy.getQuantity());
            foundToy = true;
            break;
        }
    }

    if (!foundToy) {
        toys.add(toy);
    }
}

//Метод изменения веса игрушек

public void setWeight(int toyId, double weight) {
    for (Toy t : toys) {
        if (t.getId() == toyId) {
            t.setWeight(weight);
            break;
        }
    }
}

//Метод получения списка игрушек с их атрибутами (id, название, кол-во, вес)

public ArrayList<String> getToyList() {
    ArrayList<String> toyList = new ArrayList<>();
    for (Toy t : toys) {
        toyList.add("ID: " + t.getId() + "Название: " + t.getName() + "Количество: " + t.getQuantity() + "Вес: " + t.getWeight());    
    }
    return toyList;
}

//Метод розыгрыша игрушек

public ArrayList<Toy> playGame(int count) {
    ArrayList<Toy> winners = new ArrayList<>();
    double weightSum = 0;
    for (Toy t : toys) {
        weightSum += t.getWeight();
    }

    Random random = new Random();
    for (int i = 0; i < count; i++) {
        double randomNumber = random.nextDouble() * weightSum;
        double currentSum = 0;
        for (Toy t : toys) {
            currentSum += t.getWeight();
            if (currentSum >= randomNumber) {
                if (t.getQuantity() > 0) {
                    winners.add(t);
                    t.setQuantity(t.getQuantity() - 1);
                    weightSum -= t.getWeight();
                }
                break;
            }
        }
    }
    return winners;
}

//Метод сохранения данных об игрушках в файл

/**
 * @param filename
 * @throws IOException
 */
public void saveToFile(String filename) throws IOException {
    try (FileWriter writer = new FileWriter(new File(filename))) {
        for (Toy t : toys) {
            writer.write(t.getId() + "," + t.getName() + "," + t.getQuantity() + "," + t.getWeight() + "n");
        }
    }
}

//Метод загрузки данных об игрушках из файла
/**
 * @param fileName
 * @throws IOException
 */
public void loadFromFile(String fileName) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] toyData = line.split(",");
            Toy toy = new Toy(Integer.parseInt(toyData[0]), toyData[1], Integer.parseInt(toyData[2]), Double.parseDouble(toyData[3]));
            toys.add(toy);
        }
    }
}
}