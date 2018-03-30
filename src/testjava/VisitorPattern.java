package testjava;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by plundahl.
 */

enum Color {
    RED, GREEN
}

abstract class Tree {
    private int value;
    private Color color;
    private int depth;

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {
    private ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    @Override
    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }
}

class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis {
    public abstract int getResult();

    public abstract void visitNode(TreeNode node);

    public abstract void visitLeaf(TreeLeaf leaf);
}

class SumInLeavesVisitor extends TreeVis {
    int sum = 0;

    public int getResult() {
        //implement this
        return sum;
    }

    public void visitNode(TreeNode node) {
        //implement this
    }

    public void visitLeaf(TreeLeaf leaf) {
        sum += leaf.getValue();
        //implement this
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    long product = 1;

    public int getResult() {
        //implement this
        return (int) (product % ((int) Math.pow(10, 9) + 7));
    }

    public void visitNode(TreeNode node) {
        //implement this
        if (node.getColor() == Color.RED) {
            if (node.getValue() != 0) {
                product = product * node.getValue() % (long) (Math.pow(10, 9) + 7);
            }
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        //implement this
        if (leaf.getColor() == Color.RED) {
            if (leaf.getValue() != 0) {
                product = product * leaf.getValue() % (long) (Math.pow(10, 9) + 7);
            }
        }
    }
}

class FancyVisitor extends TreeVis {
    int nonLeafSum = 0;
    int greenLeafSum = 0;

    public int getResult() {
        //implement this
        return Math.abs(nonLeafSum - greenLeafSum);
    }

    public void visitNode(TreeNode node) {
        //implement this
        if (node.getDepth() == 0 || node.getDepth() % 2 == 0) {
            nonLeafSum += node.getValue();
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        //implement this
        if (leaf.getColor() == Color.GREEN) {
            greenLeafSum += leaf.getValue();
        }
    }
}


class RedGreenNode {
    int nodeId;
    Color color;
    int value;
    Set<Integer> edges;

    public RedGreenNode(int nodeId, Color color, int value, Set<Integer> edges) {
        this.nodeId = nodeId;
        this.color = color;
        this.value = value;
        this.edges = edges;
    }

    public Set<Integer> getChildren2(int nodeId) {
        edges.remove(nodeId);
        return edges;
    }
}

public class VisitorPattern {

    private static Color getCol(int color) {
        if (color == 0) return Color.RED;
        else return Color.GREEN;
    }

    static Tree buildTree2(TreeNode currentNode, int nodeId, ArrayList<RedGreenNode> nodes, int depth, int parentNodeId) {
        for (int childNodeId : nodes.get(nodeId - 1).getChildren2(parentNodeId)) {
            RedGreenNode childNode = nodes.get(childNodeId - 1);
            if (childNode.edges.size() == 1) {
                currentNode.addChild(new TreeLeaf(childNode.value, childNode.color, depth + 1));
            } else {
                TreeNode newNode = new TreeNode(childNode.value, childNode.color, depth + 1);
                currentNode.addChild(newNode);
                buildTree2(newNode, childNodeId, nodes, depth + 1, nodeId);
            }
        }
        return currentNode;
    }

    private static ArrayList<RedGreenNode> buildNodes2(int n, int[] x, int[] c, int[][] vus) {
        ArrayList<RedGreenNode> nodes = new ArrayList<>();
        Set[] edges = new HashSet[n];
        for (int i = 0; i < vus.length; i++) {
            if (edges[vus[i][0] - 1] == null)
                edges[vus[i][0] - 1] = new HashSet();
            edges[vus[i][0] - 1].add(vus[i][1]);
            if (edges[vus[i][1] - 1] == null)
                edges[vus[i][1] - 1] = new HashSet();
            edges[vus[i][1] - 1].add(vus[i][0]);
        }
        for (int i = 0; i < n; i++) {
            nodes.add(new RedGreenNode(i + 1, getCol(c[i]), x[i], edges[i]));
        }
        return nodes;
    }

    public static Tree solve() {

        File inFile = new File("src/testjava/testfiles/visitorIn13.txt");
        try (Scanner s = new Scanner(inFile)) {
            long t0 = System.currentTimeMillis();
            int n = s.nextInt();
            int[] x = new int[n];
            for (int i = 0; i < n; i++) {
                x[i] = s.nextInt();
            }
            int[] c = new int[n];
            for (int i = 0; i < n; i++) {
                c[i] = s.nextInt();
            }
            int[][] vus = new int[n - 1][2];
            for (int i = 0; i < n - 1; i++) {
                vus[i][0] = s.nextInt();
                vus[i][1] = s.nextInt();
            }
            System.out.println("readFileTime: " + (System.currentTimeMillis() - t0));

            TreeNode root = new TreeNode(x[0], getCol(c[0]), 0);
            t0 = System.currentTimeMillis();
            ArrayList<RedGreenNode> nodes = buildNodes2(n, x, c, vus);
            System.out.println("buildNodes time: " + (System.currentTimeMillis() - t0));
            t0 = System.currentTimeMillis();
            buildTree2(root, 1, nodes, 0, 0);
            System.out.println("buildTree time: " + (System.currentTimeMillis() - t0));
            return root;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void main() {
        long t0 = System.currentTimeMillis();
        Tree root = solve();
        System.out.println("solve time: " + (System.currentTimeMillis() - t0));
        SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
        ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
        FancyVisitor vis3 = new FancyVisitor();

        root.accept(vis1);
        root.accept(vis2);
        root.accept(vis3);

        int res1 = vis1.getResult();
        int res2 = vis2.getResult();
        int res3 = vis3.getResult();

        // 0    // 1        // 13
        System.out.println(res1);   // 24   // 45136    // 39660110
        System.out.println(res2);   // 40   // 56618427 // 259313238
        System.out.println(res3);   // 15   // 14333    // 14563025
        System.out.println("total time: " + (System.currentTimeMillis() - t0));

    }


}
