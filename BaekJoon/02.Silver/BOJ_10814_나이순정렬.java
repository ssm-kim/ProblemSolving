import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class Person {
    int age;
    String name;
    int seq;

    public Person(int age, String name, int seq) {
        this.age = age;
        this.name = name;
        this.seq = seq;
    }

    @Override
    public String toString() {
        return age + " " + name;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<Person> members = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            members.add(new Person(age, name, i + 1));
        }

        Collections.sort(members, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                if (p1.age == p2.age) {
                    return Integer.compare(p1.seq, p2.seq);
                } else {
                    return Integer.compare(p1.age, p2.age);
                }
            }
        });

        for (Person member : members) {
            System.out.println(member);
        }
    }
}