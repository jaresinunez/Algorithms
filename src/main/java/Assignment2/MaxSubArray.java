package Assignment2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// 100,113,110,85,105,102,86,63,81,101,94,106,101,79,94,90,97
public class MaxSubArray {
    public static void main(String[] args) {
        Random rand = new Random();
        ArrayList<Integer> stockPrices = new ArrayList<>();
        // for (int i = 0; i < 100; i++){ stockPrices.add(rand.nextInt(50,121)); }
        Collections.addAll(stockPrices, 100,113,110,85,105,102,86,63,81,101,94,106,101,79,94,90,97);
        ArrayList<Integer> stockPriceChanges = getStockPriceChanges(stockPrices);

        Triplet<Integer,Integer,Integer> maxSubArr = findMaxSubArray(stockPriceChanges, 0, stockPriceChanges.size() - 1);

        printTable(stockPrices, stockPriceChanges, 15);
        System.out.println("maxSubArray: " + stockPriceChanges.subList(maxSubArr.getLow(), maxSubArr.getHigh() + 1));
        System.out.println("Low Index: " + (maxSubArr.getLow() + 1));   // as it corresponds to the stockPrices array
        System.out.println("High Index: " + (maxSubArr.getHigh() + 1));   // as it corresponds to the stockPrices array
        System.out.println("Profit: " + maxSubArr.getSum()); // sum of the total changes in price from the suggested buy date to the suggested sell date
    }

    public static void printTable(ArrayList<Integer> A, ArrayList<Integer> B, int columnsPerRow){
        int loop = 0;
        for (int i = 0; i <= A.size(); i++){
            if ((i % columnsPerRow == 0 || i == A.size()) && i > 0){
                String formatMe;
                String divider;
                int jStartsAt;

                if (i % columnsPerRow == 0) {   // a complete columned row
                    jStartsAt = i - columnsPerRow;
                    formatMe = getFormatString(columnsPerRow);
                    divider = getDivider(columnsPerRow);
                }
                else {  // the last elements that do not
                    jStartsAt = A.size() - (A.size() % columnsPerRow);
                    formatMe = getFormatString(A.size() % columnsPerRow);
                    divider = getDivider(A.size() % columnsPerRow);
                }

                ArrayList<String> title = new ArrayList<>(); title.add("Day");
                ArrayList<String> stockPriceStr = new ArrayList<>(); stockPriceStr.add("Price");
                ArrayList<String> stockPriceChangesStr = new ArrayList<>(); stockPriceChangesStr.add("Change");

                for (int j = jStartsAt; j < i; j++){ title.add(Integer.toString(j)); }  // Table header
                for (int j = jStartsAt; j < i; j++) { stockPriceStr.add(Integer.toString(A.get(j))); }  // Price row
                if(loop < 1)    // the first column in the first row should be empty
                    stockPriceChangesStr.add("");
                else
                    stockPriceChangesStr.add(Integer.toString(B.get(jStartsAt - 1)));
                for (int j = jStartsAt; j < i - 1; j++) { stockPriceChangesStr.add(Integer.toString(B.get(j))); }   // Price Change row

                // print the table
                System.out.print(divider);
                System.out.format(formatMe, title.toArray());
                System.out.print("\n" + divider);
                System.out.format(formatMe, stockPriceStr.toArray());
                System.out.format(formatMe, stockPriceChangesStr.toArray());
                System.out.println("\n" + divider);

                loop++;
            }
        }
    }

    public static String getDivider (int numberOfColumns){
        String divider = "|----------";
        for (int j = 0; j < numberOfColumns; j++) { divider = divider.concat("--------"); }
        divider = divider.concat("|");
        return divider;
    }

    public static String getFormatString (int numberOfColumns){
        String formatMe = "\n|%8s  |";
        for (int j = 0; j < numberOfColumns; j++) {
            formatMe = formatMe.concat("%5s  |");
        }
        return formatMe;
    }

    public static ArrayList<Integer> getStockPriceChanges(ArrayList<Integer> A){
        if (A.size() > 1) {
            ArrayList<Integer> changes = new ArrayList<>();
            for (int i = 1; i < A.size(); i++) {
                changes.add(A.get(i) - A.get(i - 1));
            }
            return changes;
        } else
            return new ArrayList<>();
    }

    public static Triplet<Integer, Integer, Integer> findMaxSubArray(ArrayList<Integer> A, int low, int high){
        if (high == low){
            return new Triplet<>(low, high, A.get(low));
        } else {
            int mid = (low + high) / 2; // integer division good bc we are looking for an index value
            Triplet<Integer, Integer, Integer> leftSub = findMaxSubArray(A, low, mid);
            Triplet<Integer, Integer, Integer> rightSub = findMaxSubArray(A, mid + 1, high);
            Triplet<Integer, Integer, Integer> crossSub = findMaxCrossArray(A, low, mid, high);

            System.out.println("leftSub: " + A.subList(leftSub.getLow(), leftSub.getHigh() + 1));
            System.out.println("rightSub: " + A.subList(rightSub.getLow(), rightSub.getHigh() + 1));
            System.out.println("crossSub: " + A.subList(crossSub.getLow(), crossSub.getHigh() + 1));
            System.out.println("---------------------------------");

            if (leftSub.getSum() >= rightSub.getSum() && leftSub.getSum() >= crossSub.getSum()) {
                return new Triplet<>(leftSub.getLow(), leftSub.getHigh(), leftSub.getSum());
            }
            else if (rightSub.getSum() >= leftSub.getSum() && rightSub.getSum() >= crossSub.getSum()) {
                return new Triplet<>(rightSub.getLow(), rightSub.getHigh(), rightSub.getSum());
            }
            else {
                return new Triplet<>(crossSub.getLow(), crossSub.getHigh(), crossSub.getSum());
            }
        }
    }

    public static Triplet<Integer, Integer, Integer> findMaxCrossArray(ArrayList<Integer> A, int low, int mid, int high){
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = 0, maxRight = 0;
        for (int i = mid; i >= low; i--){
            sum += A.get(i);
            if (sum > leftSum){
                leftSum = sum;
                maxLeft = i;
            }
        }
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int j = mid + 1; j <= high; j++){
            sum += A.get(j);
            if (sum > rightSum){
                rightSum = sum;
                maxRight = j;
            }
        }
        return new Triplet<>(maxLeft, maxRight, leftSum + rightSum);
    }
}
