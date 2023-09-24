import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GreedyAlgorithm {

    static List<Item> globalItems = new ArrayList<>();
    static int people = 0;
    static int globalMin = 1000000000;
    static List<Item> peopleItems = new ArrayList<>();
    static List<Item> result = new ArrayList<>();

    public static void main(String[] args) {
        greedyAlgorithm();
    }

    public static void greedyAlgorithm() {
        Scanner sc = new Scanner(System.in);
        people = sc.nextInt();
        getItem();
        initPeopleItems();
        DFS(0);

        System.out.println("result = " + result);
    }

    public static void getItem() {
        // 스프레드시트 데이터를 읽어와서 globalItems 리스트에 추가
            Scanner sc = new Scanner(System.in);
            String name = sc.next();
            int count = sc.nextInt();
            int price = sc.nextInt();


            for (int i = 1; i <= count; i++) {
                    Item item = new Item(name, price);
                    globalItems.add(item);
            }
    }

    public static void initPeopleItems() {
        for (int i = 0; i < people; i++) {
            Item item = new Item("", 0);
            peopleItems.add(item);
        }
    }
    public static void DFS(int n) {
        if (n == globalItems.size()) {
            int temp = getMax() - getMin();
            if (temp < globalMin) {
                globalMin = temp;
                result = peopleItems;
            }
        } else {
            for (int i = 0; i < people; i++) {
                peopleItems.set(i, new Item(
                        peopleItems.get(i).name + "|" + globalItems.get(n).name ,
                        peopleItems.get(i).price + globalItems.get(n).price));
                DFS(n + 1);
                peopleItems.set(i, new Item(
                        peopleItems.get(i).name.substring(0, peopleItems.get(i).name.length() - (globalItems.get(i).name.length() + 1)),
                        peopleItems.get(i).price - globalItems.get(n).price));
            }
        }
    }

    public static int getMax() {
        int max = 0;
        for (int i = 0; i < peopleItems.size(); i++) {
            if (max < peopleItems.get(i).price) {
                max = peopleItems.get(i).price;
            }
        }
        return max;
    }

    public static int getMin() {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < peopleItems.size(); i++) {
            if (min > peopleItems.get(i).price) {
                min = peopleItems.get(i).price;
            }
        }
        return min;
    }

    static class Item {
        String name;
        int price;

        Item(String name, int price) {
            this.name = name;
            this.price = price;
        }
    }
}
