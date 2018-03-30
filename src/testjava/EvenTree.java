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
class EvenTreeNode {

    private final int nodeId;
    EvenTreeNode parent;
    Set<EvenTreeNode> children;
    boolean isLeaf;

    EvenTreeNode(int nodeId) {
        this.nodeId = nodeId;
    }

    void addChild(int childId) {
        children.add(new EvenTreeNode(childId));
    }
}

public class EvenTree {

    private static ArrayList<EvenTreeNode> buildNodes(int n, int[][] vus) {
        ArrayList<EvenTreeNode> nodes = new ArrayList<>();
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
//            nodes.add(new EvenTreeNode(i + 1, edges[i]));
        }
        return nodes;
    }

    public static void main() {
        File inFile = new File("src/testjava/testfiles/evenTreeIn9.txt");
        try (Scanner s = new Scanner(inFile)) {
            String[] treeNodesEdges = s.nextLine().split(" ");
            int treeNodes = Integer.parseInt(treeNodesEdges[0].trim());
            int treeEdges = Integer.parseInt(treeNodesEdges[1].trim());

            int[][] vus = new int[treeEdges][2];
            int[] treeFrom = new int[treeEdges];
            int[] treeTo = new int[treeEdges];

//            for (int treeItr = 0; treeItr < treeEdges; treeItr++) {
//                String[] treeFromTo = s.nextLine().split(" ");
//                vus[treeItr][0] = Integer.parseInt(treeFromTo[0].trim());
//                vus[treeItr][1] = Integer.parseInt(treeFromTo[1].trim());
//            }
            for (int treeItr = 0; treeItr < treeEdges; treeItr++) {
                String[] treeFromTo = s.nextLine().split(" ");
                treeFrom[treeItr] = Integer.parseInt(treeFromTo[0].trim());
                treeTo[treeItr] = Integer.parseInt(treeFromTo[1].trim());
            }

            int[] edgeCount = new int[treeNodes];
            for (int i = 0; i < treeEdges; i++) {
                edgeCount[treeFrom[i]-1]++;
                edgeCount[treeTo[i]-1]++;
            }
            int count = 0;
            for (int i = 0; i < edgeCount.length; i++) {
                if (edgeCount[i] % 2 == 0) {
                    count++;
                }
            }
            EvenTreeNode root = new EvenTreeNode(1);
            for (int i = 0; i < treeFrom.length; i++) {

            }


            ArrayList<EvenTreeNode> nodes = buildNodes(treeNodes, vus);

            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
