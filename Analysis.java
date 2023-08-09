import java.io.File;
import java.util.*;

public class Analysis {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Incorrect Arg length. Insert Only 1 file.");
            System.exit(1);
        }

        if (args[0].isEmpty()) {
            System.out.println("Insert proper filename");
            System.exit(1);
        }

        // * try and catch Exceptions */
        try {
            // * file reader */
            File f = new File(args[0]);
            boolean a = f.exists() && !f.isDirectory();
            if (!a) {
                throw new Exception("This file Don't exist");
            } else if (f.length() == 0) {
                throw new Exception("File is Empty");
            }

            Scanner fileInput = new Scanner(f);

            ArrayList<Integer> MainDatabase = new ArrayList<Integer>();

            while (fileInput.hasNextLine()) {
                MainDatabase.add(Integer.parseInt(fileInput.nextLine()));
            }

            // * Output results */
            System.out.println("Average: " + Round(AvgCalc(MainDatabase)));
            System.out.println("Median: " + Round(MedianCalc(MainDatabase)));
            System.out.println("Variance: " + Math.round(Variance(MainDatabase)));
            System.out.println("Standard Deviation: " + Round(Sdeviation(MainDatabase)));

            // * close Reader */
            fileInput.close();

        } catch (Exception e) {
            System.out.println("MATH-SKILLS ERROR");
            System.out.println(e.getMessage());
        }
    }

    // * Mean */
    static Double AvgCalc(ArrayList<Integer> Database) {
        Double sum = 0.0;
        ArrayList<Double> mDatabase = new ArrayList<Double>();
        for (int a : Database) {
            mDatabase.add((double) a);
        }

        for (Double a : mDatabase) {
            sum += a;
        }

        // System.out.println("Avg Before Rounding: " + (sum / mDatabase.size()));
        // System.out.println(sum / mDatabase.size());
        return sum / mDatabase.size();
    }

    // * Median */
    static Double MedianCalc(ArrayList<Integer> Database) {
        // int smalloc, temp, median;
        Double median;

        Collections.sort(Database);

        // for (int i = 0; i < Database.size(); i++) {
        //     smalloc = i;
        //     for (int j = i + 1; j < Database.size(); j++) {
        //         if (Database.get(smalloc) > Database.get(j)) {
        //             smalloc = j;
        //         }
        //     }
        //     temp = Database.get(smalloc);
        //     Database.set(smalloc, Database.get(i));
        //     Database.set(i, temp);
        // }

        if (Database.size() % 2 != 0) {
            median = (double) Database.get(((Database.size()) / 2));
        } else {
            median = (double) Database.get((Database.size() / 2)) + Database.get(((Database.size() / 2) -1));
            // System.out.println(Database.get((Database.size() / 2)) + " " + Database.get(((Database.size() / 2) - 1)));
            median /= 2;
        }
        return median;
    }

    // * Variance */
    static Double Variance(ArrayList<Integer> Database) {
        Double average = AvgCalc(Database);
        Double squaredSubtracts = 0.0;
        ArrayList<Double> mDatabase = new ArrayList<Double>();
        for (int a : Database) {
            mDatabase.add((double) a);
        }

        for (Double a : mDatabase) {
            double toAppend = a - average;
            squaredSubtracts += Math.pow(toAppend, 2);
        }

        Double OriginalVariance = squaredSubtracts / Database.size();

        return  OriginalVariance;
    }

    // * standard deviation */
    static Double Sdeviation(ArrayList<Integer> Database) {
        return Math.sqrt(Variance(Database));
    }

    // * Custom Round Method */
    static int Round(double n) {
        int Afterpoint = (int) (n * 10) % 10;
        int BeforePoint = (int) n;

        if (Afterpoint >= 5) {
            return BeforePoint + 1;
        } else {
            return BeforePoint;
        }

    }

    static long RoundL(Double n) {
        long Afterpoint = (n.longValue() * 10) % 10;
        long BeforePoint = n.longValue();

        if (Afterpoint >= 5) {
            return BeforePoint + 1;
        } else {
            return BeforePoint;
        }

    }
}
