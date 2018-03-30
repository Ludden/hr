package testjava;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class Node {
    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }
}

public class Solution {

    static void agents() {
        int[][] t0 = new int[][]{{1481222000, 1481222020}, {1481222000, 1481222040}, {1481222030, 1481222035}};
        int[][] t1 = new int[][]{{1481222000, 1481222020}, {1481222001, 1481222040}, {1481222002, 1481222035}};
        int[][] t2 = new int[][]{{1481222000, 1481222010}, {1481222020, 1481222030}, {1481222040, 1481222050}};
        int[][] t3 = new int[][]{{1481222000, 1481222050}, {1481222020, 1481222050}, {1481222040, 1481222050}};
        int[][] t4 = new int[][]{{1481222000, 1481222050}, {1481222020, 1481222050}, {1481222040, 1481222050}, {1481222050, 1481222060}};
        int[][] t5 = new int[][]{{1481222000, 1481222050}, {1481222020, 1481222050}, {1481222040, 1481222050}, {1481222045, 1481222060}};

        int v0 = numberOfAgentsToAdd(1, t0);
        int v1 = numberOfAgentsToAdd(1, t1);
        int v2 = numberOfAgentsToAdd(1, t2);
        int v3 = numberOfAgentsToAdd(1, t3);
        int v4 = numberOfAgentsToAdd(1, t4);
        int v5 = numberOfAgentsToAdd(1, t5);
        System.out.println(1 == v0);
        System.out.println(2 == v1);
        System.out.println(0 == v2);
        System.out.println(2 == v3);
        System.out.println("2=" + v4);
        System.out.println(3 == v5);
    }

    static int numberOfAgentsToAdd(int numberOfAgents, int[][] callsTimes) {
        List<Integer> queue = new ArrayList<Integer>();
        int maxCalls = 0;
        for (int i = 0; i < callsTimes.length; i++) {

            // remove ended calls
            for (ListIterator<Integer> it = queue.listIterator(); it.hasNext(); ) {

                if (callsTimes[i][0] > it.next()) {
                    it.remove();
                }
            }
            // add call to queue
            queue.add(callsTimes[i][1]);
            if (queue.size() > maxCalls) {
                maxCalls = queue.size();
            }
        }
        return maxCalls - numberOfAgents;
    }

    static void hotels() {
        String in1 = "breakfast beach citycenter location metro view staff price";
        String[] keywords = in1.split(" ");

        int[] idArr = {1, 2, 1, 1, 2};
        String[] reviewArr = {"This hotel has a nice view of the citycenter. The location is perfect.",
                "The breakfast is ok. Regarding location, it is quite far from citycenter but price is cheap so it is worth.",
                "Location is excellent, 5 minutes from citycenter. There is also a metro station very close to the hotel.",
                "They said I couldn't take my dog and there were other guests with dogs! That is not fair.",
                "Very friendly staff and good cost-benefit ratio. Its location is a bit far from citycenter."};

        rateHotels(keywords, idArr, reviewArr);

    }

    private static void rateHotels(String[] keywords, int[] idArr, String[] reviewArr) {
        Map<Integer, Integer> mentions = new HashMap<>();

        for (int i = 0; i < idArr.length; i++) {
            for (String w : keywords) {
                if (reviewArr[i].toLowerCase().contains(w)) {
                    if (mentions.containsKey(idArr[i])) {
                        mentions.put(idArr[i], mentions.get(idArr[i]) + 1);
                    } else {
                        mentions.put(idArr[i], 1);
                    }
                }
            }
        }

        Stream<Map.Entry<Integer, Integer>> sorted = mentions.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

        //1= 5
        //2= 7
        sorted.forEach(w -> System.out.println(w));

    }

    private static void scanner() {
        Scanner in = new Scanner(System.in);
//        int len = in.nextInt();
        int len = 5;
        String s = "1 -2 4 -5 1";

//        String[] sarr = in.next().split(" ");
        String[] sarr = s.split(" ");
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = Integer.parseInt(sarr[i]);
        }

        int neg = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int acc = 0;
                for (int k = i; k <= j; k++) {
                    acc += arr[k];
                }
                if (acc < 0) neg++;
            }
        }
        System.out.println(neg);
    }

    static void subarrays() {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();

        ArrayList<ArrayList<Integer>> n = new ArrayList();
        for (int i = 0; i < len; i++) {
            int lineLen = in.nextInt();
            n.add(new ArrayList());
            for (int j = 0; j < lineLen; j++) {
                n.get(i).add(in.nextInt());
            }
        }
        int qlen = in.nextInt();
        for (int i = 0; i < qlen; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            if (x < n.size()) {
                if (y <= n.get(x - 1).size()) {
                    int val = n.get(x - 1).get(y - 1);
                    System.out.println(val);
                } else {
                    System.out.println("ERROR!");
                }
            } else {
                System.out.println("ERROR!");
            }

        }
    }


    static boolean doMove(int current, int[] game, int leap) {
        if (current < 0 || game[current] == 1) {
            return false;
        } else if (current + leap >= game.length || current + 1 >= game.length) {
            return true;
        }
        game[current] = 1;
        return doMove(current + leap, game, leap) || doMove(current + 1, game, leap) || doMove(current - 1, game, leap);

    }

    static boolean canWin(int leap, int[] game) {
        return doMove(0, game, leap);
    }

    static void java1dArrayPart2() {
        File inFile = new File("src/testjava/in8.txt");
        File resFile = new File("src/testjava/out8.txt");
        try (Scanner scan = new Scanner(inFile); Scanner resScan = new Scanner(resFile)) {
            int q = scan.nextInt();
            while (q-- > 0) {
                System.out.println("Q:" + q);
                if (q == 4629) {
                    System.out.println("breaking");
                }
                int n = scan.nextInt();
                int leap = scan.nextInt();

                int[] game = new int[n];
                for (int i = 0; i < n; i++) {
                    game[i] = scan.nextInt();
                }

                String v = canWin(leap, game) ? "YES" : "NO";
                String re = resScan.next();
                if (!v.equals(re)) {
                    System.out.println("break here");
                }

//                System.out.print((canWin(leap, game)) ? "YES" : "NO");
//                System.out.println("=" + resScan.next());
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static boolean balanced(String str) {


        Stack<Character> s = new Stack();

        for (char c : str.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                s.push(c);
            } else if (s.empty()) {
                return false;
            } else {
                switch (c) {
                    case ')':
                        if (s.pop() != '(') {
                            return false;
                        }
                        break;
                    case '}':
                        if (s.pop() != '{') {
                            return false;
                        }
                        break;
                    case ']':
                        if (s.pop() != '[') {
                            return false;
                        }
                        break;
                    default:
                        return false;
                }
            }
        }
        return s.empty();

    }

    private static void javaList() {
        File inFile = new File("src/testjava/listIn0.txt");
        try (Scanner in = new Scanner(inFile)) {
            int n = in.nextInt();
            ArrayList<Integer> list = new ArrayList();
            for (int i = 0; i < n; i++) {
                list.add(in.nextInt());
            }
            int q = in.nextInt();
            for (int i = 0; i < q; i++) {
                String query = in.next();
                if (query.equals("Insert")) {
                    int pos = in.nextInt();
                    if (pos == 5) {
                        System.out.println("break");
                    }
                    int value = in.nextInt();
                    if (pos < list.size()) {
                        list.add(pos, value);
                    } else {
                        list.add(value);
                    }

                } else {
                    int pos = in.nextInt();
                    if (pos >= 0 && pos < list.size()) {
                        list.remove(pos);
                    }

                }
            }
            for (int w : list) {
                System.out.print(w + " ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static long getWays(long n, long[] c) {
        // [1, 2, 3]

//        while (sum < n) {
//
//        }
//
//        int[] arr = new int[(int) (n + 1)];
//        int count = 0;
//        for (int i = 0; i < c.length; i++) {
//            for (int j = i; j < c.length; j++) {
//                if ()
//            }
//        }
        return 0;
    }


    private static void coinChange() {
        //tc1
        System.out.println(getWays(4, new long[]{1, 2, 3}) == 4);
        //tc2
        System.out.println(getWays(10, new long[]{2, 5, 3, 6}) == 5);

    }

    static String getSmallestAndLargest(String s, int k) {
        String smallest = "";
        String largest = "";

        for (int i = 0; i <= s.length() - k; i++) {
            String a = s.substring(i, i + k);
            System.out.println(a.compareTo(smallest));
            if (smallest == "") smallest = s.substring(i, i + k);
            if (s.substring(i, i + k).compareTo(smallest) < 0) {
                smallest = s.substring(i, i + k);
            }
            if (largest == "") largest = s.substring(i, i + k);
            if (s.substring(i, i + k).compareTo(largest) > 0) {
                largest = s.substring(i, i + k);
            }
        }

        System.out.println(smallest);
        System.out.println(largest);
        return smallest + "\n" + largest;
    }

    private static void regex2(String a) {
        String regex1 = "\\b(\\w+)\\b(?:\\s+\\1\\b)+";
        String regex2 = "\\b([a-z]+)\\b(?:\\s+\\1\\b)+";
        Pattern p = Pattern.compile(regex1, Pattern.CASE_INSENSITIVE);


        String input = a;

        Matcher m = p.matcher(input);

        // Check for subsequences of input that match the compiled pattern
        while (m.find()) {
            String b = m.group();
            String c = m.group(0);
            String d = m.group(1);
            System.out.println("asda");
            input = input.replaceAll(m.group(0), m.group(1));
        }

        // Prints the modified sentence.
        System.out.println(input);

    }

    private static void exceptionHandling() {
        File inFile = new File("src/testjava/exceptions.txt");
        try (Scanner s = new Scanner(inFile)) {
            int a = s.nextInt();
            int b = s.nextInt();
            System.out.println(a / b);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(e.toString());
            System.out.println(e);
            System.out.println(e);
            System.out.println(e);
            System.out.println(e);
            System.out.println(e);
            System.out.println();
        }
    }

    private static void tagExtractor() {
        File inFile = new File("src/testjava/tags.txt");
        try (Scanner in = new Scanner(inFile)) {
            int testCases = Integer.parseInt(in.nextLine());
            while (testCases > 0) {
                String line = in.nextLine();
                String rgx = "\\<(([^>])+)\\>(([^<])+)\\<\\/\\1\\>";
                Pattern p = Pattern.compile(rgx);
                Matcher m = p.matcher(line);
                int found = 0;
                while (m.find()) {
                    found++;
                    System.out.println(m.group(3));
                }
                if (found == 0) {
                    System.out.println("None");
                }
                testCases--;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static Node insert(Node head, int data) {
        if (head != null) {
            head.next = insert(head.next, data);
        } else {
            head = new Node(data);
        }
        return head;
    }

    private static void linkedListProblem() {
        Node head = null;
        int e1 = 2;
        int e2 = 3;
        int e3 = 4;
        int e4 = 1;
        head = insert(head, e1);
        head = insert(head, e2);
        head = insert(head, e3);
        head = insert(head, e4);

    }


    private static void canYouAccess() {
        int num = 2;
        Object o = null;
        try {
            Class c1 = Class.forName("testjava.Solution$Inner");
            Class c2 = Class.forName("testjava.Solution$Inner$Private");
            c1.newInstance();
            Constructor cons = c2.getDeclaredConstructor(c1);
            cons.setAccessible(true);
            o = cons.newInstance(c1.newInstance());

            Method m = c2.getDeclaredMethod("powerof2", int.class);
            m.setAccessible(true);
            String res = (String) m.invoke(o, num);
            System.out.println(num + " is " + res);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | ClassNotFoundException | InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println(o.getClass().getCanonicalName());

        String x = ((Inner.Private) o).powerof2(2);
        System.out.println(x);

    }

    static class Inner {
        private class Private {
            private String powerof2(int num) {
                return ((num & num - 1) == 0) ? "power of 2" : "not a power of 2";
            }
        }
    }


    private static void checkPrime(int... n) {
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < n.length; i++) {
            boolean isPrime = true;
            if (n[i] == 1) {
                isPrime = false;
            } else if (n[i] > 2) {
                for (int j = 2; j <= Math.sqrt(n[i]); j++) {
                    if (n[i] % j == 0) isPrime = false;
                }
            }
            if (isPrime) al.add(n[i]);
        }
        if (al.size() == 0) {
            System.out.println();
        } else {
            for (int i = 0; i < al.size(); i++) {
                System.out.print(al.get(i) + " ");
            }
            System.out.println();
        }
    }

    private static void md5() {
        String s1 = "HelloWorld";
        String s2 = "Javarmi123";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] ba = md.digest(s2.getBytes("UTF-8"));
            String hash = DatatypeConverter.printHexBinary(ba).toLowerCase();
            System.out.println();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private void binaryTrees() {
        int T = 6;
        BinTreeNode root = null;
        root = insertBinTree(root, 3);
        root = insertBinTree(root, 5);
        root = insertBinTree(root, 4);
        root = insertBinTree(root, 7);
        root = insertBinTree(root, 2);
        root = insertBinTree(root, 1);
        levelOrder(root);
    }

    private void levelOrder(BinTreeNode root) {
        Queue<BinTreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            BinTreeNode head = q.poll();
            System.out.print(head.data + " ");
            if (head.left != null) q.add(head.left);
            if (head.right != null) q.add(head.right);
        } // 3 2 5 1 4 7

    }

    public BinTreeNode insertBinTree(BinTreeNode root, int data) {
        if (root == null) {
            return new BinTreeNode(data);
        } else {
            BinTreeNode cur;
            if (data <= root.data) {
                cur = insertBinTree(root.left, data);
                root.left = cur;
            } else {
                cur = insertBinTree(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    class BinTreeNode {
        BinTreeNode left, right;
        int data;

        BinTreeNode(int data) {
            this.data = data;
            left = right = null;
        }

    }

    private static void primeCh(int n) {
        boolean isPrime = true;
        if (n == 1) {
            isPrime = false;
        } else if (n == 2) {
            isPrime = true;
        } else {
            for (int j = 3; j <= Math.sqrt(n); j += 2) {
                if (n % j == 0) {
                    isPrime = false;
                    break;
                }
            }
        }
        if (isPrime) {
            System.out.println("Prime");
        } else {
            System.out.println("Not prime");
        }
    }

    private static void nestedLogic() {
        String retStr = "24 12 1898";
        String dueStr = "18 9 1898";
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
        try {
            Date returnedDate = sdf.parse(retStr);
            Date dueDate = sdf.parse(dueStr);
            long returned = returnedDate.getTime();
            long due = dueDate.getTime();
            long late = returned - due;
            Calendar returnedCal = Calendar.getInstance();
            returnedCal.setTime(returnedDate);
            Calendar dueCal = Calendar.getInstance();
            dueCal.setTime(dueDate);
            if (late < 0) {
                System.out.println("0");
            } else if (returnedCal.get(Calendar.MONTH) == dueCal.get(Calendar.MONTH) && returnedCal.get(Calendar.YEAR) == dueCal.get(Calendar.YEAR)) {// same month and year
                System.out.println(15 * (late / (1000 * 24 * 60 * 60)));
            } else if (returnedCal.get(Calendar.YEAR) == dueCal.get(Calendar.YEAR)) { // same year

                System.out.println(500 * (returnedCal.get(Calendar.MONTH) - dueCal.get(Calendar.MONTH)));
            } else {
                System.out.println("10000");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private static void regexPatterns() {
        File inFile = new File("src/testjava/testfiles/regexPatternsIn3.txt");
        try (Scanner s = new Scanner(inFile)) {
            int n = s.nextInt();
            String rgx = ".*\\@gmail.com";
            Pattern p = Pattern.compile(rgx);
            List<String> names = new ArrayList<>();
            s.nextLine();
            for (int i = 0; i < n; i++) {
                String[] entry = s.nextLine().split(" ");
                String name = entry[0];
                String mail = entry[1];
                Matcher m = p.matcher(mail);
                if (m.matches()) {
                    names.add(name);
                }
            }
            Collections.sort(names);
            for (String name : names) {
                System.out.println(name);
            }
        } catch (Exception e) {

        }

    }

    private static void bitwiseAnd() {
        File inFile = new File("src/testjava/testfiles/bitwiseAndIn3.txt");
        File outFile = new File("src/testjava/testfiles/bitwiseAndOut3.txt");
        try (Scanner s = new Scanner(inFile);
             Scanner out = new Scanner(outFile)) {
            int t = s.nextInt();
            for (int i = 0; i < t; i++) {
                int max = 0;
                int n = s.nextInt();
                int k = s.nextInt();
                int[] set = new int[n];
                for (int j = 1; j <= n; j++) {
                    set[j - 1] = j;
                }
                for (int j = 0; j < n - 1; j++) {
                    for (int l = 1; l < n; l++) {
                        int res = set[j] & set[l];
                        if (l != j && (res < k && res > max)){
                            max = res;
                        }
                    }
                }

                int correct = out.nextInt();
                if (max != correct) {
                    System.out.println("problem");
                }
                System.out.println(max);
            }
        } catch (Exception e) {

        }

    }

    public static void main(String[] args) {
//        agents();
//        hotels();
//        scanner();
//        subarrays();
//        java1dArrayPart2();
//        javaList();
//        String[] strArr = new String[] {"{}()", "({()})", "{}(", "[]", "[{()}]", "({()})", "({)}", "[[", "}{"};
//        for (String s: strArr) {
//            boolean res = balanced(s);
//            System.out.println(res);
//        }
//        coinChange();
//        getSmallestAndLargest("ZASKFDLklhfsdfsDLJFSJGIHEKHIPEINNNFIGHKkjgksfgjrotyotoyjtkjkLJOIOEHEKHKKDJGKFGJkfjhglfhjtrhkjfkhjnfglhkjflgjhtrljhfljhfgljhfgljhfgljhtrklyjhtrkjhfgkljhfgjhfljhtrljlfjhfgljhfglkjhflyjtljtrlyjhtryjtrtykhrktherktjhtrkyjhkujhtykhtryhrthHKLJHLHRLHTLRHLKHTRLKHLHRLHLKHLKHKLHLKHLHKLHKHJKHKJHKJHJKHKHJKHKHHLHLHLHKHKJHKJKKHKHKHKHKHHKHKHKHKHkhktryhtlhtklhtrkyhtrkyhtrkjyhtrkyhrekthtrkyhtrkhtrkyhtrkhtrkyhtrkhtrkyhtrkhtrkyhtrkhtrkyhtrkhtrkyhtrkhtrkyhtrkrtkyhtrklyhjrOEOHKDHFksdhfklHLHKHLHKKJHJHKGKLHLHJLJHLHLHLHLHHLHLHLHH", 1);
//        regex2("Goodbye bye bye bye world world world");
//        exceptionHandling();
//        tagExtractor();
//        linkedListProblem();
//        canYouAccess();
//        checkPrime(2, 1, 3, 4, 5);
//        VisitorPattern.main();
//        md5();
//        Solution s = new Solution();
//        s.binaryTrees();
//        LinkedListDeletion.main();
//        nestedLogic();
//        regexPatterns();
//        bitwiseAnd();


//        EvenTree.main();


        System.out.println();

    }


    class Student {
        private String name;
        private String id;
        private String email;

        public String getName() {
            return name;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void anothermethod() {
        }
    }

    class Player {
        String name;
        int score;

        public Player(String n, int s) {
            this.name = n;
            this.score = s;
            List<String> hej = new ArrayList<>();
            hej.sort((s1, t1) -> {
                if (s1 != "") {
                    return 1;
                } else {
                    return 0;
                }
            });
        }

        void add(int... a) {
            System.out.println(a[1]);
        }

        int divisor_sum(int n) {
            int sum = 0;
            for (int i = 1; i <= n / 2; i++) {
                if (n % i == 0) sum += i;
            }
            BitSet bs = new BitSet(5);
            Deque d = new ArrayDeque();
            Map<Integer, Integer> hm = new HashMap<>();
            PriorityQueue<Student1> pq = new PriorityQueue<>((s1, s2) -> {
                if (s1.cgpa != s2.cgpa) {
                    return Double.compare(s1.cgpa, s2.cgpa);
                } else if (!s1.name.equals(s2.name)) {
                    return s1.name.compareTo(s2.name);
                } else {
                    return Integer.compare(s1.id, s2.id);
                }
            });
            for (Student std : (Student[]) pq.toArray()) {

            }
            List<Student1> sl = new ArrayList<>();
            sl.add(pq.poll());
            d.stream().distinct().count();
            String s = "";
            switch (s) {
                case "": {

                }
            }
            return sum + n;
        }
    }

    class Student1 {
        int id;
        String name;
        double cgpa;

        public Student1(int id, String name, double cgpa) {
            this.id = id;
            this.name = name;
            this.cgpa = cgpa;
        }

        int getID() {
            return id;
        }

        String getName() {
            return name;
        }

        double getCgpa() {
            return cgpa;
        }
    }

    class Checker implements Comparator<Player> {

        public int compare(Player p1, Player p2) {
            String s1 = "a";
            String s2 = "b";
            return 0;
        }

    }
}

class Singleton {
    public String str = new String();

    private Singleton() {
    }

    static Singleton s = null;

    static Singleton getSingleInstance() {
        if (s == null) {
            s = new Singleton();
        }
        return s;
    }
}

interface PerformOperation {
    boolean check(int a);
}

class MyMath {
    public static boolean checker(PerformOperation p, int num) {
        return p.check(num);
    }

    PerformOperation isOdd() {
        return x -> x % 2 == 1;
    }

    PerformOperation isPrime() {
        return x -> {
            boolean prime = true;
            if (x == 1) {
                prime = false;
            } else if (x > 2) {
                for (int i = 2; i <= Math.sqrt(x); i++) {
                    if (x % i == 0) {
                        prime = false;
                        break;
                    }
                }
            }
            return prime;
        };
    }

    PerformOperation isPalindrome() {
        return x -> new StringBuilder(x).reverse().toString().equals(Integer.toString(x));
    }
}