package D2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SWEA_2005 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("SWEA/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            ArrayList<Integer> nums = new ArrayList<>();
            String [] input  = br.readLine().split(" ");
            for (String num : input) {
                nums.add(Integer.parseInt(num));
            }

            System.out.println(nums);

            break;
        }
    }
}
