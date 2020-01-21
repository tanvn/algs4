package coursera.assignment.week1;

import java.util.Arrays;

public class UnionFind {
  private int N;
  private int[] arr;
  private int[] rootSize;

  public UnionFind(int N) {
    this.N = N;
    this.arr = new int[N];
    this.rootSize = new int[N];
    for (int i = 0; i < N; i++) {
      this.arr[i] = i;
      this.rootSize[i] = 1;
    }
  }

  public int root(int i) {
    while (this.arr[i] != i) {
      i = this.arr[i];
    }
    return this.arr[i];
  }

  public void union(int p, int q) {
    int pRoot = root(p);
    int qRoot = root(q);

    int largerComp = this.rootSize[pRoot] >= this.rootSize[qRoot] ? pRoot : qRoot;
    int smallerComp = this.rootSize[pRoot] < this.rootSize[qRoot] ? pRoot : qRoot;
    this.arr[smallerComp] = this.arr[largerComp];
    this.rootSize[largerComp] += this.rootSize[smallerComp];
    this.rootSize[smallerComp] = 0;
  }

  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }

  public int count() {
    int componentCount = 0;
    for (int i = 0; i < this.arr.length; i++) {
      if (this.arr[i] == i) {
        componentCount++;
      }
    }
    return componentCount;
  }

  @Override
  public String toString() {
    return "UnionFind{"
        + "N="
        + N
        + ", arr="
        + Arrays.toString(arr)
        + ", rootSize="
        + Arrays.toString(rootSize)
        + '}';
  }

  public static void main(String[] args) {
    UnionFind uf1 = new UnionFind(30);
    uf1.union(1, 2);
    uf1.union(3, 2);
    uf1.union(4, 5);
    uf1.union(8, 9);
    System.out.println(uf1.connected(1, 3));
    System.out.println("total components =" + uf1.count());
    uf1.union(3, 9);
    System.out.println(uf1.connected(2, 8));
    System.out.println(uf1);
    System.out.println("total components =" + uf1.count());

    uf1.union(6,10);
    uf1.union(14,16);
    uf1.union(19,6);
    uf1.union(20,10);

    System.out.println("total components =" + uf1.count());
    uf1.union(10,14);
    uf1.union(10,8);
    System.out.println(uf1.connected(19,3));
    System.out.println(uf1);

  }
}
