import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {
        Random r = new Random();
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        BST<Integer> bsttest = new BST();

        for (int x = 0; x < 5000; x += 1) {

            int insert = RandomGenerator.getRandomInt(500000);
            bsttest.add(insert);
            double avdepth = bsttest.averagedepth();
            xValues.add(x);
            yValues.add(avdepth);
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).
                xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("average depth", xValues, yValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
        Random r = new Random();
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        BST<Integer> bsttest = new BST();
        for (int x = 0; x < 1000; x += 1) {
            int insert = RandomGenerator.getRandomInt(1000);
            bsttest.add(insert);
        }
        for (int x = 0; x < 5000; x += 1) {
            int delete = RandomGenerator.getRandomInt(1000);
            int insert = RandomGenerator.getRandomInt(1000);
            bsttest.deleteTakingSuccessor(delete);
            bsttest.add(insert);
            double avdepth = bsttest.averagedepth();
            xValues.add(bsttest.sumnodedepth);
            yValues.add(avdepth);
        }

        XYChart chart = new XYChartBuilder().width(800).height(600)
                .xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("average depth2", xValues, yValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
        Random r = new Random();
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        BST<Integer> bsttest = new BST();
        for (int x = 0; x < 1000; x += 1) {
            int insert = RandomGenerator.getRandomInt(1000);
            bsttest.add(insert);
        }
        for (int x = 0; x < 5000; x += 1) {
            int delete = RandomGenerator.getRandomInt(1000);
            int insert = RandomGenerator.getRandomInt(1000);
            bsttest.deleteTakingRandom(delete);
            bsttest.add(insert);
            double avdepth = bsttest.averagedepth();
            xValues.add(bsttest.sumnodedepth);
            yValues.add(avdepth);
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).
                xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("average depth3", xValues, yValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        experiment1();
        experiment2();
        experiment3();
    }
}
