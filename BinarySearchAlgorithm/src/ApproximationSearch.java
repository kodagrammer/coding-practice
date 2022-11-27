import java.util.ArrayList;
import java.util.List;

public class ApproximationSearch {
    public void runTest(double inputValue) {
        List<String> collection = makeStrCollection(50000);

        long startTime = System.currentTimeMillis();

        int approxIdx = searchApproxIdx(collection, inputValue);

        if(approxIdx > 0){
            System.out.println(inputValue + "의 근사값에 대한 인덱스: " + approxIdx + ", 값: " + collection.get(approxIdx));
        } else {
            System.out.println("근사값이 없습니다.");
        }

        long endTime = System.currentTimeMillis();

        System.out.println("============= 소요시간 : " + (endTime - startTime)/1000d + "s =============");
    }

    public List<String> makeStrCollection(int size) {
        List<String> collection = new ArrayList<>();

        for(int idx = 0; idx < size; idx++){
            collection.add(Double.toString(idx * 0.035));
        }

        return collection;
    }

    public static int searchApproxIdx(List<String> collection, double inputValue){
        int returnIdx = collection.indexOf(Double.toString(inputValue));

        if(returnIdx < 0) {
            int low = 0;
            int high = collection.size() - 1;

            while(low <= high) {
                int midIdx = (high + low)/2;
                double midValue = Double.parseDouble(collection.get(midIdx));

                if(midValue == inputValue) {
                    return midIdx;
                } else if(midValue < inputValue) {
                    low = midIdx + 1;
                } else {
                    high = midIdx - 1;
                }
            }

            double lowValue = Math.abs(Double.parseDouble(collection.get(low)) - inputValue);
            double highValue = Math.abs(Double.parseDouble(collection.get(high)) - inputValue);

            returnIdx = lowValue > highValue ? high : low;
        }

        return returnIdx;
    }
}
