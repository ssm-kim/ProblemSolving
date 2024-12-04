import java.io.*;
import java.util.*;

class Meeting implements Comparable<Meeting> {
    int[] time;

    public Meeting(int[] time) {
        this.time = time;
    }

    @Override
    public int compareTo(Meeting other) {
        if (this.time[1] == other.time[1]) {
            return this.time[0] - other.time[0];
        }
        return this.time[1] - other.time[1];
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Meeting[] meetings = new Meeting[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            meetings[i] = new Meeting(new int[] {
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            });
        }

        Arrays.sort(meetings);


        for (Meeting meeting : meetings) {
            System.out.println(Arrays.toString(meeting.time));
        }

        int count = 0;
        int endTime = 0;

        for (Meeting meeting : meetings) {
            if (meeting.time[0] >= endTime) {
                count++;
                endTime = meeting.time[1];
            }
        }

        System.out.println(count);
    }
}