import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int cnt = 0;
        int last = N;
        for (int i = N; i > 0; i--) {
            if (arr[i] == last) {
                cnt++;
                last--;
            }
        }
        System.out.println(N - cnt);
    }
}