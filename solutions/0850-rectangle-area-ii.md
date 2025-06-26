# 850. Rectangle Area II

## Java

```java
class Diff2d {
    List<List<Integer>> res = new ArrayList<>();
    List<List<Integer>> diff = new ArrayList<>();
    int m, n;

    Diff2d(int m, int n) {
        this.m = m;
        this.n = n;
        for (int i = 0; i < m + 1; i++) {
            List<Integer> resRow = new ArrayList<>();
            List<Integer> diffRow = new ArrayList<>();
            for (int j = 0; j < n + 1; j++) {
                resRow.add(0);
                diffRow.add(0);
            }
            res.add(resRow);
            diff.add(diffRow);
        }
    }

    void set(int x0, int y0, int x1, int y1, int value) {
        diff.get(x0).set(y0, diff.get(x0).get(y0) + value);
        diff.get(x0).set(y1 + 1, diff.get(x0).get(y1 + 1) - value);
        diff.get(x1 + 1).set(y0, diff.get(x1 + 1).get(y0) - value);
        diff.get(x1 + 1).set(y1 + 1, diff.get(x1 + 1).get(y1 + 1) + value);
    }

    void compute() {
        res.get(0).set(0, diff.get(0).get(0));
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int a = i == 0 ? 0 : res.get(i - 1).get(j);
                int b = j == 0 ? 0 : res.get(i).get(j - 1);
                int c = (i == 0 || j == 0) ? 0 : res.get(i - 1).get(j - 1);
                res.get(i).set(j, a + b - c + diff.get(i).get(j));
            }
        }
//        print();
    }

    void print(){
        System.out.println("res:");
        for (List<Integer> row : res) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }

        System.out.println("diff:");
        for (List<Integer> row : diff) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}

class Solution {
    final int M = (int) (1e9 + 7);

    public int rectangleArea(int[][] rectangles) {
        Set<Integer> pointX = new TreeSet<>();
        Set<Integer> pointY = new TreeSet<>();
        for (int[] rectangle : rectangles) {
            pointX.add(rectangle[0]);
            pointY.add(rectangle[1]);
            pointX.add(rectangle[2]);
            pointY.add(rectangle[3]);
        }

        List<Integer> row = new ArrayList<>(pointX);
        List<Integer> col = new ArrayList<>(pointY);

        Map<Integer, Integer> x2index = new HashMap<>();
        Map<Integer, Integer> y2index = new HashMap<>();

        for (int i = 0; i < row.size(); i++) {
            x2index.put(row.get(i), i);
        }
        for (int i = 0; i < col.size(); i++) {
            y2index.put(col.get(i), i);
        }

        int m = row.size(), n = col.size();
        Diff2d grid = new Diff2d(m, n);
        for (int[] rectangle : rectangles) {
            int i1 = x2index.get(rectangle[0]);
            int j1 = y2index.get(rectangle[1]);
            int i2 = x2index.get(rectangle[2]) - 1;
            int j2 = y2index.get(rectangle[3]) - 1;
            grid.set(i1, j1, i2, j2, 1);
        }
        grid.compute();

        long area = 0L;
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (grid.res.get(i).get(j) > 0) {
                    long dx = row.get(i + 1) - row.get(i);
                    long dy = col.get(j + 1) - col.get(j);
                    area += dx * dy % M;
                    area %= M;
                }
            }
        }

        return (int) area;
    }
}
```

## C++
```cpp
using ll = long long;

class Diff2d
{
public:
    vector<vector<int>> f;
    vector<vector<int>> diff;
    int m, n;
    Diff2d(int m, int n)
    {
        this->m = m;
        this->n = n;
        diff.resize(m + 1);
        f.resize(m + 1);
        for (int i = 0; i < m + 1; i++)
        {
            diff[i].resize(n + 1);
            f[i].resize(n + 1);
        }
    }
    void set(int x0, int y0, int x1, int y1, int val)
    {
        diff[x0][y0] += val;
        diff[x0][y1 + 1] -= val;
        diff[x1 + 1][y0] -= val;
        diff[x1 + 1][y1 + 1] += val;
    }
    void compute()
    {
        f[0][0] = diff[0][0];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
            {
                int a = i == 0 ? 0 : f[i - 1][j];
                int b = j == 0 ? 0 : f[i][j - 1];
                int c = (i == 0 || j == 0) ? 0 : f[i - 1][j - 1];
                f[i][j] = a + b - c + diff[i][j];
            }
    }
    void print()
    {
        cout << "f:" << endl;
        for (int i = 0; i <= m; i++)
        {
            for (int j = 0; j <= n; j++)
            {
                cout << f[i][j] << " ";
            }
            cout << endl;
        }

        cout << "diff:" << endl;
        for (int i = 0; i <= m; i++)
        {
            for (int j = 0; j <= n; j++)
            {
                cout << diff[i][j] << " ";
            }
            cout << endl;
        }
    }
};

class Solution
{
    ll M = 1e9 + 7;

public:
    int rectangleArea(vector<vector<int>> &rectangles)
    {
        set<ll> pointX;
        set<ll> pointY;
        for (auto rect : rectangles)
        {
            pointX.insert(rect[0]);
            pointY.insert(rect[1]);
            pointX.insert(rect[2]);
            pointY.insert(rect[3]);
        }

        vector<ll> row(pointX.begin(), pointX.end());
        vector<ll> col(pointY.begin(), pointY.end());

        unordered_map<ll, ll> X2idx;
        unordered_map<ll, ll> Y2idx;
        for (int i = 0; i < row.size(); i++)
            X2idx[row[i]] = i;
        for (int i = 0; i < col.size(); i++)
            Y2idx[col[i]] = i;

        int m = row.size(), n = col.size();
        Diff2d grid(m, n);

        for (auto rect : rectangles)
        {
            int i = X2idx[rect[0]];
            int j = Y2idx[rect[1]];
            int x = X2idx[rect[2]] - 1;
            int y = Y2idx[rect[3]] - 1;
            grid.set(i, j, x, y, 1);
        }
        grid.compute();

        ll ret = 0;
        for (int i = 0; i < m - 1; i++)
            for (int j = 0; j < n - 1; j++)
            {
                if (grid.f[i][j] > 0)
                {
                    ll dx = row[i + 1] - row[i];
                    ll dy = col[j + 1] - col[j];
                    ret += dx * dy % M;
                    ret %= M;
                }
            }
        return ret;
    }
};
```