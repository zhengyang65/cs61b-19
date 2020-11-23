import org.w3c.dom.Node;

import java.util.*;

public class MyTrieSet implements TrieSet61B {
    private Node root;
    private static class Node {
        char c;
        Node left, mid, right;
        Boolean val = false;
    };

    /** Clears all items out of Trie */
    @Override
    public void clear() {
        root = null;
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    @Override
    public boolean contains(String key) {
        Node x = get(root, key, 0);
        return x.val;
    }
    public Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        char c = key.charAt(d);
        if (c < x.c) {
            return get(x.left, key, d);
        } else if (c > x.c) {
            return get(x.right, key, d);
        } else if (d < key.length() - 1) {
            return get(x.mid, key, d + 1);
        } else {
            return x;
        }
    }

    /** Inserts string KEY into Trie */
    @Override
    public void add(String key) {
        root = add(root, key, true, 0);
    }
    public Node add(Node x, String key, Boolean value, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }
        if (c < x.c) {
            x.left = add(x.left, key, value, d);
        } else if (c > x.c) {
            x.right = add(x.right, key, value, d);
        } else if (d < key.length() - 1) {
            x.mid = add(x.mid, key, value, d + 1);
        } else {
            x.val = value;
        }
        return x;
    }
    /** Returns a list of all words that start with PREFIX */
    @Override
    public List<String> keysWithPrefix(String prefix) {
        ArrayList<String> queue = new ArrayList<>();
        collect(get(root, prefix, 0), queue, prefix);
        return queue;
    }
    public void collect(Node x, List<String> q, String pre) {
        if (x == null) {
            return;
        }
        //先看两边再看中间
        if (x.left != null) {
            collect(x.left, q, pre);
        }
        if (x.right != null) {
            collect(x.right, q, pre);
        }
        if (x.val) {
            pre += x.c;
            q.add(pre);
        }
        if (x.mid != null) {
            collect(x.mid, q, pre);
        }

    }
    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
    public static void main(String[] args) {
        MyTrieSet t = new MyTrieSet();
        t.add("same");
        t.add("sad");
        t.add("sam");
        t.add("sap");
        ArrayList<String> q1 = new ArrayList<>();
        q1 = (ArrayList<String>) t.keysWithPrefix("sa");
        for (String s:q1) {
            System.out.println(s);
        }
    }
}
