package bearmaps.proj2ab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrieSet implements TrieSet61B {
    private Node root = new Node('s', false);


    private class Node {
        private HashMap<Character, Node> map;
        private boolean isKey;
        private char key;

        private Node(char c, boolean bool) {
            map = new HashMap<>(); //is it okay if we don't initialize the instance variables here?;
            isKey = bool;
            key = c;
        }
    }

    /**
     * Clears all items out of Trie
     */
    public void clear() {
        root.map.clear();
    }

    /**
     * Returns true if the Trie contains KEY, false otherwise
     */
    public boolean contains(String key) {
        if (key == null || key.length() < 1) {
            return false;
        }
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(key.charAt(i))) {
                return false;
            }
            curr = curr.map.get(c);
        }
        return curr.isKey;
    }

    /**
     * Inserts string KEY into Trie
     */
    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }


    /**
     * Returns a list of all words that start with PREFIX
     */
    public List<String> keysWithPrefix(String prefix) {
        List<String> list = new ArrayList<>();
        Node curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (curr.map.containsKey(c)) {
                curr = curr.map.get(c);
            } else {
                throw new IllegalArgumentException();
            }
        }
        if (curr.isKey) {
            list.add(prefix);
        }
        helper(curr, list, prefix);
        return list;
    }

    private void helper(Node p, List list, String word) {
        if (p.isKey) {
            list.add(word);
        }
        for (Node n : p.map.values()) {
            helper(p.map.get(n.key), list, (word + n.key));
        }
    }

    /**
     * Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key) {
        List<String> list = keysWithPrefix(key);
        if (!list.isEmpty()) {
            String currMax = list.get(0);
            for (String word : list) {
                if (word.length() > currMax.length()) {
                    currMax = word;
                }
            }
            return currMax;
        } else return null;
    }

}
