package bearmaps.proj2c;

import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.proj2ab.KDTree;
import bearmaps.proj2ab.Point;
import bearmaps.proj2ab.TrieSet;
import bearmaps.proj2ab.WeirdPointSet;
import edu.princeton.cs.algs4.StdRandom;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {
    private HashMap<Point, Node> pointNodeHashMap = new HashMap<>();
    private List<Point> weirdPointInput = new ArrayList<>();
    private WeirdPointSet pointSet;
    private List<Node> nodes;
    private TrieSet trie = new TrieSet();
    private HashMap<String, ArrayList<String>> cleanAndNot = new HashMap<>();
    private HashMap<String, ArrayList<Node>> cleanAndNode = new HashMap<>();



    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        // You might find it helpful to uncomment the line below:
        nodes = this.getNodes();
        for (Node node : nodes) {
            if (node.name() != null) {
                trie.add(cleanString(node.name()));
                if (cleanAndNode.containsKey(cleanString(node.name()))) {
                    cleanAndNode.get(cleanString(node.name())).add(node);
                } else {
                    ArrayList<Node> list = new ArrayList<>();
                    list.add(node);
                    cleanAndNode.put(cleanString(node.name()), list);
                }
                if (cleanAndNot.containsKey(cleanString(node.name()))) {
                    cleanAndNot.get(cleanString(node.name())).add(node.name());
                } else {
                    ArrayList<String> list = new ArrayList<>();
                    list.add(node.name());
                    cleanAndNot.put(cleanString(node.name()), list);
                }
            }
            if (!neighbors(node.id()).isEmpty()) {
                Point point = new Point(node.lon(), node.lat());
                pointNodeHashMap.put(point, node);
                weirdPointInput.add(point);
            }
        }
        pointSet = new WeirdPointSet(weirdPointInput);
    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        return pointNodeHashMap.get(pointSet.nearest(lon, lat)).id();
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        ArrayList<String> sol = new ArrayList<>();
        for (String s : trie.keysWithPrefix(prefix)) {
            for (String string : cleanAndNot.get(s)) {
                sol.add(string);
            }
        }
        return sol;
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (Node node : cleanAndNode.get(locationName)) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", node.name());
            map.put("lon", node.lon());
            map.put("id", node.id());
            map.put("lat", node.lat());
            list.add(map);
        }
        return list;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

    public static void main(String[] args) {

    }
}
