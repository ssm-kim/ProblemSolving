import java.io.*;
import java.util.*;

public class Main {

    static class Meeting implements Comparable<Meeting> {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting other) {
            if (this.end == other.end) {
                return Integer.compare(this.start, other.start);
            }  // 만약 끝나는 시간이 같으면 시작 시간 순 정렬
            return Integer.compare(this.end, other.end);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Meeting[] meetings = new Meeting[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            meetings[i] = new Meeting(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        Arrays.sort(meetings);
        int answer = 1;
        int end = meetings[0].end;

        for (int i = 1; i < N; i++) {
            int nextStart = meetings[i].start;
            if (nextStart >= end) {
                end = meetings[i].end;
                answer++;
            }  // 현재 회의 끝나는 시간과 다음 회의 시작 시간이 같다면 다음 회의 시작 !!
        }
        System.out.println(answer);
    }
}