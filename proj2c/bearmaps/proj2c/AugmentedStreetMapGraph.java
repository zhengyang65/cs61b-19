package bearmaps.proj2c;

import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.proj2ab.KDTree;
import bearmaps.proj2ab.Point;
import edu.princeton.cs.algs4.TrieST;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {
    Map<Point, Node> map;
    TrieST<Long> stringtriest;
    Map<String, String> triest;
    Map<String, Node> getlocation;
    Map<Long, Node> getdirection;

    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        // You might find it helpful to uncomment the line below:
        map = new HashMap<>();
        stringtriest = new TrieST<>();
        triest = new HashMap<>();
        getlocation = new HashMap<>();
        getdirection = new HashMap<>();
        List<Node> nodes = this.getNodes();
        for (Node n : nodes) {
            getdirection.put(n.id(), n);
            if (n.name() != null) {
                stringtriest.put(cleanString(n.name()), n.id());
                triest.put(cleanString(n.name()), n.name());
                getlocation.put(cleanString(n.name()), n);
            }
            if (neighbors(n.id()).size() == 0) {
                continue;
            }
            Point p = new Point(n.lon(), n.lat());
            map.put(p, n);
            //getdirection.put(n.id(), n);
        }
    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        KDTree kdTree = new KDTree(map);
        Point p = kdTree.nearest(lon, lat);
        Node n = map.get(p);
        long id = n.id();
        return id;
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
        String cleanString = cleanString(prefix);
        List<String> result = new ArrayList<>();
        for (String s:stringtriest.keysWithPrefix(cleanString)) {
            result.add(triest.get(s));
        }
        return result;
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
        List<Map<String, Object>> result = new LinkedList<>();
        for (String s:stringtriest.keysThatMatch(cleanString(locationName))) {
            Map<String, Object> hashmap = new HashMap<>();
            Node node = getlocation.get(s);
            hashmap.put("lat", node.lat());
            hashmap.put("lon", node.lon());
            hashmap.put("name", node.name());
            hashmap.put("id", node.id());
            result.add(hashmap);
        }
        return result;
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

}
