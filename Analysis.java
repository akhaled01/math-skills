import java.io.File;
import java.util.*;


public class Analysis {
    public static void main(String[] args) {
        // * try and catch Exceptions */
        try {
            if (args.length != 1 || args[0] == "") {
                throw new Exception("Incorrect Args Passed. Insert Only 1 file.");
            }

            // * file reader */
            Scanner fileInput = new Scanner(new File(args[0]));

            ArrayList<Integer> MainDatabase = new ArrayList<Integer>();

            while (fileInput.hasNextLine()) {
                MainDatabase.add(Integer.parseInt(fileInput.nextLine()));
            }

            // * Output results */
            System.out.println("Average: " + AvgCalc(MainDatabase));
            System.out.println("Median: " + MedianCalc(MainDatabase));
            System.out.println("Variance: " + Variance(MainDatabase));
            System.out.println("Standard Deviation: " + Sdeviation(MainDatabase));

            // * close Reader */
            fileInput.close();

        } catch (Exception e) {
            System.out.println("MATH-SKILLS ERROR");
            System.out.println(e.getMessage());
        }
    }

    //* Mean */
    static int AvgCalc(ArrayList<Integer> Database) {
        int sum = 0;

        for (int a : Database) {
            sum += a;
        }

        return sum / Database.size();
    }

    //* Median */
    static int MedianCalc(ArrayList<Integer> Database) {
        int smalloc, temp, median;

        for (int i = 0; i < Database.size(); i++) {
            smalloc = i;
            for (int j = i + 1; j < Database.size(); j++) {
                if (Database.get(smalloc) > Database.get(j)) {
                    smalloc = j;
                }
            }
            temp = Database.get(smalloc);
            Database.set(smalloc, Database.get(i));
            Database.set(i, temp);
        }

        if (Database.size() % 2 != 0) {
            median = Database.get((Database.size() / 2));
        } else {
            median = Database.get((Database.size() / 2) - 1) + Database.get((Database.size() / 2));
            median /= 2;
        }
        return median;
    }

    //* Variance */
    static int Variance(ArrayList<Integer> Database) {
        int average = AvgCalc(Database);
        int squaredSubtracts = 0;

        for (int a : Database) {
            int toAppend = a - average;
            squaredSubtracts += Math.pow(toAppend, 2);
        }

        return Math.round(squaredSubtracts / Database.size());
    }

    //* standard deviation */
    static int Sdeviation(ArrayList<Integer> Database) {
        return (int) Math.round(Math.sqrt(Variance(Database)));
    }
}
