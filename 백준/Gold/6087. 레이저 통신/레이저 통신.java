import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int W, H;
    static final int INF = 100 * 100; // 최대 cost
    static int[][] map, cost;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static Point start, end;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        cost = new int[H][W];

        boolean flag = false;

        for(int i=0; i<H; i++) {
            String s = br.readLine();
            for(int j=0; j<W; j++) {
                cost[i][j] = INF;

                char c = s.charAt(j);
                if(c == '*') map[i][j] = -1;
                else if(c == 'C') {
                    map[i][j] = 1;

                    if(flag) {
                        end = new Point(i, j, -1, 0);
                    } else {
                        start = new Point(i, j, -1, 0);
                        cost[i][j] = 0;
                        flag = true;
                    }
                }
            }
        }
        dijkstra();
        System.out.println(cost[end.r][end.c]);
    }
    static void dijkstra() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(start);

        while(!pq.isEmpty()) {
            Point cur = pq.poll();

            if(cur.r == end.r && cur.c == end.c) break;

            for(int i=0; i<4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                int tempCnt = cur.cnt;

                if(nr>=0 && nr<H && nc>=0 && nc<W && map[nr][nc] != -1) {
                    if(cur.dir != -1 && cur.dir != i) tempCnt++; // 레이저 방향과 이동방향 맞추기
                    if(cost[nr][nc] >= tempCnt) { // 거울 수가 더 작을 경우 큐에 추가
                        cost[nr][nc] = tempCnt;
                        pq.add(new Point(nr, nc, i, tempCnt));
                    }
                }
            }
        }
    }

}

class Point implements Comparable<Point> {
    int r;
    int c;
    int dir; // 현재 방향
    int cnt; // 거울 수

    public Point(int r, int c, int dir, int cnt) {
        this.r = r;
        this.c = c;
        this.dir = dir;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Point p) {
        return this.cnt - p.cnt;
    }
}