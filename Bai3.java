import java.util.*;

// Định nghĩa lớp Point để biểu diễn một điểm có tọa độ (x,y)
class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

// Lớp chứa tất cả các phương thức để tìm bao lồi
class ConvexHull {
    // Hàm xác định hướng quay
    static int orientation(Point p, Point q, Point r) {
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (val == 0)
            return 0;
        return (val > 0) ? 1 : -1;
    }

    // Hàm tìm bao lồi
    static List<Point> convexHull(Point points[], int n) {
        if (n < 3)
            return new ArrayList<>();

        // Bước 1: Tìm điểm có y nhỏ nhất (nếu trùng, lấy x nhỏ nhất)
        int minYIndex = 0;
        for (int i = 1; i < n; i++) {
            if (points[i].y < points[minYIndex].y ||
                    (points[i].y == points[minYIndex].y && points[i].x < points[minYIndex].x)) {
                minYIndex = i;
            }
        }

        // Đưa điểm gốc xuống đầu danh sách
        Point temp = points[0];
        points[0] = points[minYIndex];
        points[minYIndex] = temp;

        Point pivot = points[0];

        // Bước 2: Sắp xếp các điểm theo góc cực nhỏ
        Arrays.sort(points, 1, n, (p1, p2) -> {
            int o = orientation(pivot, p1, p2);
            if (o == 0) {
                return (p1.x - pivot.x) * (p1.x - pivot.x) + (p1.y - pivot.y) * (p1.y - pivot.y)
                        - (p2.x - pivot.x) * (p2.x - pivot.x) - (p2.y - pivot.y) * (p2.y - pivot.y);
            }
            return (o == -1) ? -1 : 1;
        });

        // Bước 3: Dùng stack để tìm bao lồi
        Stack<Point> stack = new Stack<>();
        stack.push(points[0]);
        stack.push(points[1]);
        stack.push(points[2]);

        // Duyệt qua danh sách điểm để tìm bao lồi
        for (int i = 3; i < n; i++) {
            while (stack.size() >= 2) {
                Point second = stack.pop();
                Point first = stack.peek();
                if (orientation(first, second, points[i]) == -1) {
                    stack.push(second);
                    break;
                }
            }
            stack.push(points[i]);
        }

        // Trả về danh sách điểm thuộc bao lồi
        return new ArrayList<>(stack);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input:");
        int n = sc.nextInt();
        Point points[] = new Point[n];

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            points[i] = new Point(x, y);
        }

        List<Point> hull = convexHull(points, n);

        System.out.println("Output:");

        Collections.reverse(hull);
        for (Point p : hull) {
            System.out.println(p.x + " " + p.y);
        }
    }
}